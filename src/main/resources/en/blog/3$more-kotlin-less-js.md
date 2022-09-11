more-kotlin-less-js.html
More Kotlin, Less JS!

More Kotlin, Less JS!
Corlaez article about the 2022 website renovation for this site that is now generated with Kotlin by Armando Cordova.
2022-08-03
2022-08-31

In the last years, JavaScript had an incredible growth, complex apps, frameworks and languages that compile to JS have been developed to create rich and complex web UIs and experiences.
I started to use React at work in 2017 in a webapp that truly required the use of that library. I enjoyed working with React and used it whenever I had a chance.
That's how I ended up writing mi personal website in React, in retrospective a terrible idea since my web wouldn't load at all without JavaScript, affecting SEO and reducing load speeds.

Today, I finally rewrote this website adding some big changes I have been planning for a while:

* The web is generated statically using [Kotlin](https://kotlinlang.org/)
* The website **no longer requires JavaScript** to render
* The articles are written in [Markdown](https://www.markdownguide.org/)
* **Internalization support** for the Kotlin templates and Markdown articles
* Templates are defined using the [Kotlinx HTML DSL](https://github.com/Kotlin/kotlinx.html). (Similar to JSX but strongly typed)
* There is a **dev server** that reloads webpages as code changes (inspired on [react-hot-loader](https://github.com/gaearon/react-hot-loader))

These projects and ideas have influenced the design of my website:

* [htmx](https://htmx.org/) AJAX, CSS Transitions, WebSockets and Server Sent Events directly in HTML, using attributes and reclaim HTML as the Hypermedia to be exchanged between browser and HTTP Server
* [Gemini Protocol](https://gemini.circumlunar.space/): Gemini is a lighter and simpler protocol than HTTP with mandatory Transport Layer Security and privacy focus (JavaScript does not exist)
* [The Small Web](https://ar.al/2020/08/07/what-is-the-small-web/): Aral Balkan and Laura Kalbag are building tools to promote independent web avoiding the centralized web and the problems it creates for democracy and society
* [website < 14kb](https://endtimes.dev/why-your-website-should-be-under-14kb-in-size/) endtime.dev's article on the significant performance improvement that smaller web pages gain.

So there you have it, I think this changes will allow me to experiment and create more easily. We shall see...