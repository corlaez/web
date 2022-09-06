more-kotlin-less-js.html
Más Kotlin, menos JS

Más Kotlin, menos JS
Artículo acerca de la renovación de esta web que ahora es generada con Kotlin. Publicado en 2022 por Armando Cordova.
2022-08-26


En los últimos años, JavaScript se ha expandido de manera increíble. Aplicaciones complejas, frameworks y lenguajes que compilan a JavaScript han sido desarrollados para crear interfaces y experiencias ricas y complejas.
Yo empecé a usar React en el trabajo en 2017 en una aplicación que realmente requería el uso de dicha librería. A mí me gustó trabajar con React y lo utilizaba siempre que podía.
Es así pues que terminé escribiendo mi web personal en React, en retrospectiva una pésima idea pues mi web no mostraba nada sin JavaScript, afectando el SEO y reduciendo la velocidad de carga.

Hoy, finalmente, reescribí este website agregando unos grandes cambios que he estado planeando por un tiempo:

* La web es generada de manera estática usando [Kotlin](https://kotlinlang.org/)
* La página web **ya no requiere JavaScript** para ser renderizada.
* Los artículos son escritos en [Markdown](https://www.markdownguide.org/)
* **Soporte para múltiples idiomas** para plantillas y artículos
* Las plantillas (templates) son definidas en Kotlin usando [Kotlinx HTML DSL](https://github.com/Kotlin/kotlinx.html). (Similar a JSX pero fuertemente tipado)
* Hay un **dev server** que recarga las páginas cuando el código cambia (inspirado en [react-hot-loader](https://github.com/gaearon/react-hot-loader))

Estos proyectos e ideas (en inglés) han influenciado el diseño de mi website:

* [La pequeña web](https://ar.al/2020/08/07/what-is-the-small-web/): Aral explica los problemas de la web centralizada que domina hoy. Él trabaja con Laura Kalbag para reducir la dificultad de crear sitios web independientes.
* [Protolo Gemini](https://gemini.circumlunar.space/): Gemini es un protocolo más ligero y seguro que HTTP. No existe el concepto de scripts en los clientes (Correcto, no hay JS). Aun así puedes hacer login usando llaves asimétricas y existen aplicaciones similares a reddit o twitter, etc.
* [htmx](https://htmx.org/) AJAX, CSS Transitions y WebSockets en atributos HTML. Permite usar HTML como el motor del estado de una aplicación web, reduciendo significativamente la necesidad de usar JSON o escribir JavaScript
* [website < 14kb](https://endtimes.dev/why-your-website-should-be-under-14kb-in-size/) endtime.dev's article on the significant performance improvement that smaller web pages gain.

Ahí lo tienes, creo que estos cambios me permitirán experimentar y crear con mayor facilidad. Ya veremos...
