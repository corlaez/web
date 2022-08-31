more-kotlin-less-js.html
Más Kotlin, menos JS
2022 agosto 26
Más Kotlin, menos JS
Artículo acerca de la renovación de esta web que ahora es generada con Kotlin. Publicado en 2022 por Armando Cordova.

En los últimos años, JavaScript ha crecido por encima de la web y librerías, frameworks y compiladores han sido desarrollados para crear interfaces y experiencias ricas y complejas.
Esto ha llevado a que muchos estudiantes y proyectos usen herramientas complejas incluso cuando tiene sentido hacer eso.
Yo mismo cometí ese error al escribir mi sencilla página con React, obligando a los visitantes a activar JavaScript y haciendo que mi página sea más lenta y pesada.

 2017 I worked writing a React application (and some Kotlin backend code), I enjoyed the team and the tech I made at that time.
The best of React, that I wanted to keep for the future was the fact that the web would reload as I saved my code providing a fast feedback loop.
Furthermore, HTMX was a fantastic way to create and compose HTML components.

Hoy, finalmente, reescribí este website agregando unos grandes cambios que he estado planeando por un tiempo:

* La web es generada de manera estática usando [Kotlin](https://kotlinlang.org/)
* Hay un **dev server** que recarga las páginas cuando el código cambia (similar a [react-hot-loader](https://github.com/gaearon/react-hot-loader))
* Los artículos son escritos en [Markdown](https://www.markdownguide.org/)
* Las plantillas (templates) son definidas en Kotlin usando [Kotlinx HTML DSL](https://github.com/Kotlin/kotlinx.html). Mejor que HTML puro o JSX en mi opinión.
* **Soporte para múltiples idiomas** para plantillas y artículos
* La página web **ya no requiere JavaScript** para ser renderizada.


Tanto el dev server como el DSL para escribir HTML emulan los beneficios de React pero dejando de lado JS y Node.

These are some projects that have influenced my thoughts about simple websites:

* [The Small Web](https://ar.al/2020/08/07/what-is-the-small-web/): This one advocates for more independent sites avoiding the centralized web that dominates. They are also building tools to easy independent web creation
* [https://gemini.circumlunar.space/](https://gemini.circumlunar.space/): Gemini is a lighter and simpler protocol than HTTP with mandatory Transport Layer Security and privacy focus (No JavaScript here)
* [(https://htmx.org/](https://htmx.org/) AJAX, CSS Transitions, WebSockets and Server Sent Events directly in HTML, using attributes and reclaim HTML as the Hypermedia to be exchanged between browser and HTTP Server

At the moment this web does not use htmx, but I am not closed to the idea of using in the future if the need arises. That said, I would strive to make the site usable even with JS off.
