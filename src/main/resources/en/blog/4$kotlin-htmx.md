kotlin-htmx.html
Kotlin-htmx

Kotlin-htmx
Corlaez article about an example implementation of htmx using Kotlin HTML DSL by Armando Cordova.
2022-09-19


Today I will talk about why I think [Kotlin](https://kotlinlang.org/) and [htmx](https://htmx.org/) mix so well. You can follow along (or skip the article) with the code [here](https://github.com/corlaez/kotlin-htmx).

Kotlin as you may know is a modern JVM and (more recently) multiplatform language. It has a great type system and interesting advanced features while still being a pragmatic tool meant for engineers.

htmx is a JavaScript library that allows you to write HTML attribute instead of JS code to load content from the server and manipulate the DOM. Your HTML will be more powerful than ever and you will deal less with JS.

Let's start! The main function creates a [Javalin](https://javalin.io/) HTTP Server (static files config including [Webjars](https://www.webjars.org/) which is how we will import the htmx js code avoiding node) and adds the "Friends" module (which is an extension function). Finally, it starts the server. A key config I want to highlight is the webjars setup that will help us serve htmx:

```kotlin
// https://github.com/corlaez/kotlin-htmx/blob/master/src/main/kotlin/javalin.kt
config.addStaticFiles { staticFiles ->
    staticFiles.directory = "META-INF/resources/webjars/"
    staticFiles.location = Location.CLASSPATH
}
```

addFriendsModule creates an index route and a route for each friend. ctx.html is the Javalin function to respond with an HTML string.

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

Let's focus on htmlFriendProfile and htmlFriendsIndex now:

```kotlin
// https://github.com/corlaez/kotlin-htmx/blob/master/src/main/kotlin/friends/html.kt
fun htmlFriendsIndex(friends: List<String>): String {
    return htmlBaseTemplate { /* htmlBaseTemplate is just another function of this app. Its parameter is the HTML that is inside the braces */
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

htmlFriendsIndex calls htmlBaseTemplate, this auxiliary function is where the html, head and body tags are defined (including css and htmx script). Its argument is the HTML markup that goes inside the BODY tag. The index builds its contents using regular Kotlin loops.

The hx functions are simple HTMLTag extension functions that add attributes (`attributes += "hx-get" to "/$friend"`). The autocomplete game of HTML DSL gives me the best experience writing HTML ever and the htmx integration is seamless. It is hard to articulate how good is it until you try it.

And as a closing argument while this example uses an HTTP Server, you could use the DSL and htmx to make a static website. Actually this blog post is an example of a Kotlin HTML DSL static website.

Cons!

* The DSL is written with advanced Kotlin features and will force you to use advanced features if you try to break templates into pieces as I did: i.e. there is a lambda with receiver in `fun htmlBaseTemplate(bodyFn: BODY.() -> Unit): String`
* Kotlin and its advanced features are better experienced with IntelliJ IDEA. There is a great free community edition but no other great alternatives that I know of.
* While the HTML DSL autocomplete is very powerful at preventing mistakes, it does rely on a lot of imports. I recommend isolating the template code from the rest of your code (which is probably a good idea anyway)
* Regarding HTMX, perhaps the biggest drawback and advantage is that it goes against the norm: it works best if you serve HTML partials from your server, the old school way. This works great for websites meant to be used by humans.

I hope this was interesting, have fun!
