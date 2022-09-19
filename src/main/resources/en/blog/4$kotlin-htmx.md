kotlin-htmx.html
Kotlin-htmx

Kotlin-htmx
Corlaez article about an example implementation of htmx using Kotlin HTML DSL by Armando Cordova.
2022-09-19


Today I will talk about why I think [Kotlin](https://kotlinlang.org/) and [htmx](https://htmx.org/) mix so well. You can follow along (or skip the article) with the code [here](https://github.com/corlaez/kotlin-htmx) (there is also a static generation example in the static branch there). Try the demo [here](https://corlaez.com/assets/htmx/index.html).

Kotlin as you may know is a modern JVM and (more recently) multiplatform language. It has a great type system and interesting advanced features while still being a pragmatic tool meant for engineers.

htmx is a JavaScript library that allows you to write HTML attributes instead of JS code to load content from the server and manipulate the DOM. Your HTML will be more powerful than ever and you will deal less with JS.

Let's start! The main function creates a [Javalin](https://javalin.io/) HTTP Server (static files config including [Webjars](https://www.webjars.org/) which is how we will import the htmx js code avoiding node) and adds the "Friends" module (which is an extension function). Finally, it starts the server. A key config I want to highlight is the webjars setup that will help us serve htmx:

```kotlin
// https://github.com/corlaez/kotlin-htmx/blob/master/src/main/kotlin/javalin.kt
config.addStaticFiles { staticFiles ->
    staticFiles.directory = "META-INF/resources/webjars/"
    staticFiles.location = Location.CLASSPATH
}
```

It is important to mention that Javalin can be replaced with any HTTP Server you like. In fact your can drop the server altogether, write your HTML Strings to files and make your own static generator (that's how corlaez.com is written)

Then addFriendsModule creates an index route and a route for each friend. ctx.html is the Javalin function to respond with an HTML string. friends.forEach will be [inlined](https://kotlinlang.org/docs/inline-functions.html) into a regular for loop

```kotlin
// https://github.com/corlaez/kotlin-htmx/blob/master/src/main/kotlin/friends/addFriendsModule.kt
fun Javalin.addFriendsModule() {
    val friends = listOf("Joseph", "Nicholas", "Caesar")
    get("/") { ctx ->
        ctx.html(htmlFriendsIndex(friends))
    }
    friends.forEach { friend ->
        get("/$friend") { ctx ->
            val characteristic = listOf("swimmer", "runner", "debater").random()
            ctx.html(htmlFriendProfile(friend, characteristic))
        }
    }
}
```

Next we have the base html template which is a Kotlin function. The parameter is a [lambda with receiver](https://kotlinlang.org/docs/lambdas.html#function-literals-with-receiver) that will populate our HTML body tag and the return is an String. `createHTML` takes out fancy DSL and outputs a String as well.
If you try to define a body tag inside a head or viceversa the editor and compiler will let you know.

```kotlin
// https://github.com/corlaez/kotlin-htmx/blob/master/src/main/kotlin/htmlBaseTemplate.kt
fun htmlBaseTemplate(bodyFn: BODY.() -> Unit): String {
    return "<!DOCTYPE html>" + createHTML().html {
        lang = "en"
        head {
            script { src = "/htmx.org/$htmxVersion/dist/htmx.js" }
            link { href = "/modest-variation.css"; rel="stylesheet" }
        }
        body {
            bodyFn()
        }
    }
}
```

Let's now focus on htmlFriendProfile and htmlFriendsIndex now:

```kotlin
// https://github.com/corlaez/kotlin-htmx/blob/master/src/main/kotlin/friends/html.kt
fun htmlFriendsIndex(friends: List<String>): String {
    return htmlBaseTemplate {
        h1 { a { href = "/"; +"My Friends" } }// nested `a` inside a `h1`. To insert a text inside any tag we use the + operator
        friends.forEach { friend ->// Regular Kotlin used to loop!
            h2 {
                a {
                    href = "/$friend"// Sets cursor pointer and works without js
                    hxGet("/$friend")
                    hxSwap("outerHTML")
                    hxTarget("closest h2")
                    +friend
                }
            }
        }
    }
}

fun htmlFriendProfile(friend: String, characteristic: String): String {
    return createHTML().article { // createHTML will take all the DSL and produce a String
        h2(classes = "your-css-class") {
            +friend
        }
        p {
            +"My friend $friend is smart and a good $characteristic"
        }
    }
}
```

htmlFriendsIndex calls htmlBaseTemplate as this is meant to be a full page. This index builds its contents using regular Kotlin loops. htmlFriendProfile, on the other hand, creates the HTML directly as it is just a partial HTML document.

The hx functions are simple HTMLTag [extension functions](https://kotlinlang.org/docs/extensions.html) that add HTML attributes.

```kotlin
// https://github.com/corlaez/kotlin-htmx/blob/master/src/main/kotlin/hx.kt
fun HTMLTag.hxGet(value: String) {
    attributes += "hx-get" to value
}
fun HTMLTag.hxSwap(value: String) {
    attributes += "hx-swap" to value
}
fun HTMLTag.hxTarget(value: String) {
    attributes += "hx-target" to value
}
```

The autocomplete game of HTML DSL gives me the best experience writing HTML I have ever experienced and the htmx integration is seamless. It is hard to articulate how good is it until you try it (Use [IntelliJ IDEA](https://www.jetbrains.com/idea/) if in doubt, there is a free community edition, and they do offer education licences if you have an institutional email)

**Cons!**

* The DSL is written with advanced Kotlin features and will force you to use/learn (maybe not that bad) advanced features if you try to break templates into different functions as I did.
* Kotlin's advanced features are better experienced with IntelliJ IDEA. There is a great free community edition but no other great alternatives that I know of.
* While the HTML DSL autocomplete is very powerful at preventing mistakes, it does rely on a lot of imports. I recommend isolating the template code from the rest of your code (which is probably a good idea anyway)
* Regarding HTMX, perhaps the biggest drawback and advantage is that it goes against the norm: it works best if you serve HTML partials from your server, the old school way. This works great for websites meant to be used by humans.

I hope this was interesting, have fun!
