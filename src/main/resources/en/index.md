<h2 id="website-update-more-kotlin-less-js">Website Update: More Kotlin, Less JS!</h2>

***August 3, 2022***

Today I finally updated this website. I am generating a static site with Kotlin and I will be able to create posts with markdown.
Besides that, as of today there is no JavaScript whatsoever which is a huge change since the old website would biu

In the last years, JavaScript has grown beyond the web and libraries, frameworks and compilers have been developed to create rich and complex web UIs and experiences.
That has led most students and projects to use complex tools when it doesn't always make sense to do so. 

However, avoiding JS for the UI creation (whether is client or server side) is the best way to build accessible, fast, cheap and SEO friendly websites with less code and fewer bugs.

These are some projects that have influenced my thoughts about simple websites:

=> [https://htmx.org/](https://htmx.org/) AJAX, CSS Transitions, WebSockets and Server Sent Events directly in HTML, using attributes and reclaim HTML as the Hypermedia to be exchanged between browser and HTTP Server

=> [[https://gemini.circumlunar.space/](https://gemini.circumlunar.space/): Gemini is a lighter and simpler protocol than HTTP with mandatory  Transport Layer Security and privacy focus (No JavaScript here)

=> [https://ar.al/2020/08/07/what-is-the-small-web/](https://ar.al/2020/08/07/what-is-the-small-web/): This one advocates for more independent sites avoiding the centralized web that dominates. They are also building tools to easy independent web creation

At the moment this web does not use htmx, but I am not closed to the idea of using in the future if the need arises. That said, I would strive to make the site usable even with JS off.

***So... How did I build this site?***

There is no shortage of static site generators that may meet the requirements that I need. However, I am an enthusiastic Kotlin programmer and a control freak.
So, *naturally*, I built a small Kotlin utility program to build a static HTML site.

### Kotlin Libraries:

* implementation("org.jetbrains:markdown-jvm:0.3.1")
* implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.5")
* implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.5")

If I needed htmx I can import the corresponding webjar instead of a npm library.

## Azure Chatbot

***August 3, 2022*** (Project demo shared on October 2019)

"Daniel in my floor" was a Skype bot that I work for my coworkers when I worked at Bellatrix. Daniel came every day selling delicious home-made sandwiches.
He stayed a few minutes outside each floor with Bellatrix offices and other companies. However, we would have to step out at the right time to catch him, or we would miss him.

The bot was meant to Announce when Daniel arrives to your floor. You subscribe to a given floor, and when the bot is notified that Daniel is in a given floor, you receive a message and a notification.
The simple program relied on trust and accurate reports of its participants to work but it was a fun experiment to run. The bot is deployed but deactivated

[https://github.com/corlaez/df-bot](https://github.com/corlaez/df-bot)

<a target="_blank" rel="noopener" href="https://join.skype.com/bot/6397acda-dd3f-46e2-a90b-af14bd2c6565">Open Skype's Bot Info Page</a>

## Overmind tutorial

***Jan 5, 2019***

A basic tutorial on the JavaScript UI library state manager [https://overmindjs.org/](https://overmindjs.org/)

[https://www.youtube.com/watch?v=pe1F0-A-e8U](https://www.youtube.com/watch?v=pe1F0-A-e8U)
