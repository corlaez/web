more-kotlin-less-js.html
More Kotlin, Less JS!
2022 August 3 (Last update: August 2022)
More Kotlin, Less JS!
Corlaez article about the 2022 website renovation for this site that is now generated with Kotlin by Armando Cordova.

Today, I finally rewrote this website adding some big changes I have been planning for a while:

* The web is generated statically using [Kotlin](https://kotlinlang.org/)
* There is a **dev server** that reloads as code changes (similar to [react-hot-loader](https://github.com/gaearon/react-hot-loader))
* The articles are written in [Markdown](https://www.markdownguide.org/)
* Templates are defined using the [Kotlinx HTML](https://github.com/Kotlin/kotlinx.html). Better than pure HTML or JSX IMO.
* **Internalization support** for the Kotlin templates and Markdown articles
* The website **no longer requires JavaScript** to render

In the last years, JavaScript has grown beyond the web and libraries, frameworks and compilers have been developed to create rich and complex web UIs and experiences.
That has led most students and projects to use complex tools when it doesn't always make sense to do so. However, **avoiding JS** for the UI creation (whether is client or server side) **is the best way to build accessible, fast, cheap and SEO friendly websites** with less code and fewer bugs.

These are the projects that have influenced my thoughts about simple websites the most:

=> [htmx](https://htmx.org/) AJAX, CSS Transitions, WebSockets and Server Sent Events directly in HTML, using attributes and reclaim HTML as the Hypermedia to be exchanged between browser and HTTP Server

=> [Gemini Protocol](https://gemini.circumlunar.space/): Gemini is a lighter and simpler protocol than HTTP with mandatory Transport Layer Security and privacy focus (JavaScript does not exist)

=> [The Small Web](https://ar.al/2020/08/07/what-is-the-small-web/): Aral Balkan and Laura Kalbag are building tools to promote independent web avoiding the centralized web and the problems it creates for democracy and society

### So... How did I build this site?

I am sure there are static site generators I could have used to write markdown articles and generate a website. However, I am an enthusiastic Kotlin programmer and a control freak.
So, *naturally*, I built a small(ish) Kotlin utility program to build a static HTML site.

#### Kotlin Libraries:

* [flexmark-all](https://github.com/vsch/flexmark-java): for markdown to html generation
* [kotlinx-html-jvm](https://github.com/Kotlin/kotlinx.html): to write type safe html templates in kotlin
* [javalin](https://github.com/javalin/javalin): Lightweight http server to preview my static site
* [slf4j-simple](https://mvnrepository.com/artifact/org.slf4j/slf4j-simple): Added to support javalin's logs

I initially used markdown-jvm but after running into issues with it, I replaced it with flexmark.

Most Java/Kotlin examples seem to focus on backends and server side rendering. On the other hand, the source for this web feels more like gulp. Pairing a dev server with the [File Watchers](https://www.jetbrains.com/help/idea/using-file-watchers.html) IntelliJ plugin I was able to achieve hot reloading for a static site in kotlin.

I could share a lot more about this endeavour, but I will leave it for another day. In the meantime and if you are curious here is the [source code](https://github.com/corlaez/web)
