hexagonal-feedback.html
Hexagonal Feedback

Hexagonal Feedback
Corlaez article about feedback for a Hexagonal Architecture draft by Armando Cordova.
2022-09-20


Recently, Alistair Cockburn has been asking for feedback and making efforts to consolidate talks, documents and the concept of Hexagonal Architecture.

```text
// https://alistaircockburn.com/Articles/Component-Strategy-generalizes-Ports-Adapters#wbb1
I am drafting a new pattern, Component + Strategy, which is the more general form of Ports & Adapters (Hexagonal Architecture).
Just now, I'm looking for comments, corrections, improvements and general feedback.
```

In the [draft](https://alistaircockburn.com/Component%20plus%20strategy.pdf) on page 16 and 17 there is a code example of a configuration written in Java with Spring.

While Spring is very popular for Java programmers I think it is important to show a configuration written with no libraries (or reflection) and why not in a different language.

I proposed translating the code to Kotlin as it was, but couldn't wrap my head around the Driver interface. I will explain why I think it is problematic and submit it as feedback, along with my alternative configuration.

This is a very close translation of what I found on the draft, adding the interfaces and classes explicitly:

```kotlin
// Not my proposal but a close translation of what I found on the draft
interface Driver // (?)
class TestCases(val forDiscounting: ForDiscounting): Driver // Primary Adapter
class Console(val forDiscounting: ForDiscounting): Driver // Primary Adapter
interface ForDiscounting // Primary Port
class DiscounterApp(val rateRepository: ForObtainingRates): ForDiscounting // App
interface ForObtainingRates // Secondary Port
class StubRateRepository: ForObtainingRates // Secondary Adapter
class FileRateRepository: ForObtainingRates // Secondary Adapter

class DiscounterAppConfig(val prodDriver: Boolean, val prodRateRepository: Boolean) {
    val rateRepository: ForObtainingRates = kotlin.run {
        if(prodRateRepository) FileRateRepository() else StubRateRepository()
    }
    val discounterApp: ForDiscounting = DiscounterApp(rateRepository)
    val driver: Driver = kotlin.run {
        if(prodDriver) Console(discounterApp) else TestCases(discounterApp)
    }
}
```

As you can see, no library, just code. Sure this implementation will have to be instantiated manually somewhere else, but it will be fine. We can now remove the Spring dependency and its overhead. 
By the way, there is nothing preventing Java to use the same approach for the configuration (except it won't be as efficient as Kotlin in terms of LoC and readability).

_"But I see no functions and Spring wasn't creating fields"_, I hear you say, and you are right. By default, Spring provides beans as singletons this just means that it will call that method only once. I decided to use the fields for that reason and for simple (or well-designed) apps this pattern can scale well with a few tweaks.

Ok, onto Driver now (I am assuming this interface is not empty and defines functions to be implemented for Primary Adapters):

1. This interface has no name on Hexagonal Architecture, in clear contrast with all other classes and interfaces. It is effectively used as a Primary Adapter Interface
2. Primary Adapters are supposed to be technology dependent their API may need to be adjusted to satisfy the libraries, frameworks and protocols they work with
3. Primary Adapters depend on the application, but shouldn't "wear a uniform" to align with all others. Let the Primary Adapter implement what it wants and name it as it wishes
   * Just think about TestCases, realistically tests have other goals and naming requirements than a Console Primary Adapter
4. Finally, not all Primary Adapters fit well into the configuration. TestCases in my experience works best depending on the configuration and being instantiated by a framework

With that I would leave the new example configuration as:

```kotlin
// Proposal for alternative configuration in Kotlin
class TestCases(val forDiscounting: ForDiscounting) // Primary Adapter (instantiated by testing framework)
class Console(val forDiscounting: ForDiscounting) // Primary Adapter
interface ForDiscounting // Primary Port
class DiscounterApp(val rateRepository: ForObtainingRates): ForDiscounting // App
interface ForObtainingRates // Secondary Port
class StubRateRepository: ForObtainingRates // Secondary Adapter
class FileRateRepository: ForObtainingRates // Secondary Adapter

class DiscounterAppConfig(val prodRateRepository: Boolean) {
    private val rateRepository: ForObtainingRates = kotlin.run {
        if(prodRateRepository) FileRateRepository() else StubRateRepository()
    }
    val discounterApp: ForDiscounting = DiscounterApp(rateRepository)
    val discounterConsole: Console = Console(discounterApp)
}
```

One extra thing I have added is the `private` for the repository (or Secondary Adapters in General). The idea being that 
other classes should only use the discounterApp or discounterConsole and not depend on or use the Secondary Adapters, not even tests.

I am happy that Alistair is still working on promoting Hexagonal Architecture and asking feedback from the community. 
If you are reading this, Alistair, I want to say thank you from the heart because your writings have influenced my growth in this industry.

... now an extra section that I won't include in the proposal, but I find very useful:

I love using mocks on my tests. With a small change our configuration can be used with mocking libraries!

```kotlin
// Not included in proposal. A recommendation that works with mocking libraries
// Adding an App with a Secondary Port meant to be mocked
class DiscounterApp2(val rateRepository: ForObtainingRates, val chaosService: ForChaos): ForDiscounting // App
interface ForChaos // Secondary Port
class RealChaos: ForChaos // Secondary Adapter

class DiscounterAppConfig(val prodRateRepository: Boolean) {
   private val chaosService: ForChaos = RealChaos()
   private val rateRepository: ForObtainingRates = kotlin.run {
      if(prodRateRepository) FileRateRepository() else StubRateRepository()
   }
   val discounterApp: ForDiscounting = newDiscounterApp(chaosService)// Same function used here
   val discounterConsole: Console = newDiscounterConsole(discounterApp)// Same function used here

   // Functions used by tests, the parameters allow mocks to be injected.
   fun newDiscounterApp(chaosService: ForChaos) = DiscounterApp2(rateRepository, chaosService)
   fun newDiscounterConsole(discounterApp: ForDiscounting) = Console(discounterApp)
}
```

First we create functions to will instantiate our test subjects (app and Primary Adapters). This will also be used to instantiate their real counterparts.
It is important to use the same function on both cases to avoid wiring the objects with a different logic.

Now the new method may have fewer arguments than the underlying class. Only keep the arguments that are meant to be mocked in tests.
The other arguments can be injected directly using the already created instances (such is the case of rateRepository)

And there you go, the main code will work the same and the tests will be able to mock the designated arguments using these functions.

And that's all for now!

PS: The `kotlin.run` could very well be deleted from all my examples, but I am trying to show that you can actually write multiple expressions 
taking several lines to configure each field if you need to.
