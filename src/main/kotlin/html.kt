import kotlinx.html.*
import kotlinx.html.stream.appendHTML

context(EnvContext, OutputContext, LanguageContext)
fun asHtmlPage(contentMd: String): String {
    val contentHtml = mdToHtml(contentMd)
    return buildString {
        append("<!DOCTYPE html>")
        appendHTML().html {
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
                            src = "/assets/bec.mp3"
                            type = "audio/mpeg"
                        }
                        +"Your browser does not support the audio tag."
                    }
                }
                unsafe {
                    +contentHtml
                }
                signatureAndThanks()
            }
        }
    }
}


context(EnvContext, OutputContext, LanguageContext)
private fun HEAD.headTags() {
    meta { charset = "utf-8" }
    meta { name = "theme-color"; content = "#000000" }
    meta { name = "viewport"; content = "width=device-width,initial-scale=1" }
    meta { name = "description"; content = t.metaDescription }
    link { rel = "stylesheet"; href = OutputPaths.STYLES_CSS_PATH; }
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
