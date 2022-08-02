### Links

* [https://github.com/corlaez](https://github.com/corlaez)
* [https://linkedin.com/in/corlaez](https://linkedin.com/in/corlaez)
* [https://twitter.com/corlaez](https://twitter.com/corlaez)

## Blog

### Renovacion de mi sitio web: Mas Kotlin, menos JS

***August 2, 2022***

Quiero compartir porque esta web no tiene JavaScript.

En los ultimos años, JavaScript ha credido por encima de la web y librerias, frameworks y compiladores han sido desarrollados para crear interfaces y experiencis ricas y complejas.
Esto ha llevado a que muchos estudiantes y projectos usen herramientas complejas cuando no siempre tiene sentido hacer eso.

Sin embargo, evitar JS para la creacion de la UI (ya sea en el cliente o en el servidor) es la mejor manera de construir sitios web accesibles, rapidos, baratos y con buen SEO, todo esto con menos codigo y menos bugs.

These are some projects that have influenced my thoughts about simple websites:

* [(https://htmx.org/](https://htmx.org/) AJAX, CSS Transitions, WebSockets and Server Sent Events directly in HTML, using attributes and reclaim HTML as the Hypermedia to be exchanged between browser and HTTP Server
* [https://gemini.circumlunar.space/](https://gemini.circumlunar.space/): Gemini is a lighter and simpler protocol than HTTP with mandatory Transport Layer Security and privacy focus (No JavaScript here)
* [The Small Web](https://ar.al/2020/08/07/what-is-the-small-web/): This one advocates for more independent sites avoiding the centralized web that dominates. They are also building tools to easy independent web creation

At the moment this web does not use htmx, but I am not closed to the idea of using in the future if the need arises. That said, I would strive to make the site usable even with JS off.

***So... How did I build this site?***

There is no shortage of static site generators that may meet the requirements that I need. However, I am an enthusiastic Kotlin programmer and a control freak.
So, naturally, I built a small Kotlin utility program to build a static HTML site.

#### Kotlin Libraries:

* implementation("org.jetbrains:markdown-jvm:0.3.1")
* implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.5")

If I needed htmx I can import the corresponding webjar instead of a npm library.

### Azure Chatbot

***August 2, 2022*** (Project demo shared on October 2019)

*Converting into blog post after the fact. Project demo was shared in the old website.*

"Daniel in my floor" was a Skype bot that I work for my coworkers when I worked at Bellatrix. Daniel came every day selling delicious home-made sandwiches.
He stayed a few minutes outside each floor with Bellatrix offices and other companies. However, we would have to step out at the right time to catch him, or we would miss him.

The bot was meant to Announce when Daniel arrives to your floor. You subscribe to a given floor, and when the bot is notified that Daniel is in a given floor, you receive a message and a notification.
The simple program relied in trusts and accurate reports of its participants to work but it was a fun experiment to run. The bot is deployed but deactivated

[Source code](https://github.com/corlaez/df-bot)
<a target="_blank" rel="noopener" href="https://join.skype.com/bot/6397acda-dd3f-46e2-a90b-af14bd2c6565">Open Skype's Bot Info Page</a>

### Overmind tutorial

***Jan 5, 2019***

*Converting into blog post after the fact. The video was shared in the old website.*

A basic tutorial on the JavaScript UI library state manager [Overmind](https://overmindjs.org/)

[Youtube Link to the Ovemind tutorial](https://www.youtube.com/watch?v=pe1F0-A-e8U)
