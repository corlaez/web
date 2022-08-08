import kotlinx.html.*
import kotlinx.html.stream.appendHTML

context(EnvContext, OutputContext, LanguageContext, PageContext)
fun asHtmlPage(contentMd: String): Page {
    val contentHtml = mdToHtml(contentMd)
    val htmlPageString = buildString {
        append("<!DOCTYPE html>")
        appendHTML(prettyPrint = false).html {
            lang = language.toString()
            head { headTags() }
            body {
                div(classes = "fire") {
                    h1 { +t.hiIamArmando }
                    p { +t.hiIamArmandoSubtitle }
                }
                noScript { +"Hey you, browsing with JavaScript off. You are welcomed! this page does not require JS to work properly :)" }
                p {
                    audio {
                        controls = true
                        loop = true
                        source {
                            src = OutputPaths.BEC_AUDIO
                            type = "audio/mpeg"
                        }
                    }
                }
                unsafe {
                    +contentHtml
                }
                signatureAndThanks()
            }
        }
    }
    return Page(fileName, htmlPageString)
}

context(EnvContext, OutputContext, LanguageContext)
private fun HEAD.headTags() {
    meta { charset = "utf-8" }
    meta { name = "theme-color"; content = "#000000" }
    meta { name = "viewport"; content = "user-scalable=yes, width=device-width,initial-scale=1,shrink-to-fit=no" }
    meta { name = "description"; content = t.metaDescription }
    meta { name = "robots"; content = "index, follow" }

    meta { attributes += "property" to "og:url"; content=domain }
    meta { attributes += "property" to "og:type"; content="website" }
    meta { attributes += "property" to "og:image"; content="/assets/logo.png" }
    meta { attributes += "property" to "og:title"; content="Software Blog" }
    meta { attributes += "property" to "og:locale"; content=language.toString() }
    meta { attributes += "property" to "og:site_name"; content="Corlaez" }
    meta { attributes += "property" to "og:description"; content="Personal blog by Armando Cordova" }

    meta { name="twitter:card"; content="summary_large_image" }
    meta { name="twitter:image"; content="/assets/logo.png" }
    meta { name="twitter:image:alt"; content="Logo that reads A R"}
    meta { name="twitter:creator"; content="@corlaez" }
    meta { name="twitter:site"; content="@corlaez" }
    meta { name="twitter:site_name"; content="@corlaez" }
    meta { name="twitter:title"; content="" }
    meta { name="twitter:description"; content="" }

    link { rel = "preload"; href = OutputPaths.WINE_IMAGE_PATH; attributes += "as" to "image"; }

    style { +resources.sytlesCss; }
    title { +"Armando" }
    unsafe {
        +resources.faviconTags
    }
    devWsReloadScript()
}

context(EnvContext, OutputContext, LanguageContext)
private fun HEAD.devWsReloadScript() {
    if (arg.isDev())
        script {
            async = true
            defer = true
            unsafe {
                +resources.wsReload
            }
        }
}

context(OutputContext, LanguageContext)
private fun BODY.signatureAndThanks() {
    p(classes = "center signature") {  }
    p(classes = "center") { +t.thanksForYourVisit  }
    p { a { href = "https://github.com/corlaez"; +"=> https://github.com/corlaez" } }
    p { a { href = "https://linkedin.com/in/corlaez"; +"=> https://linkedin.com/in/corlaez" } }
    p { a { href = "https://twitter.com/corlaez"; +"=> https://twitter.com/corlaez" } }
}
