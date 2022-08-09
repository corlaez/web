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
                    h1 { +t.heroTitle }
                    p { +t.heroDescription }
                }
                noScript { +t.noScriptMessage }
                p {
                    audio {
                        controls = true
                        loop = true
                        source {
                            src = C.BEC_AUDIO
                            type = C.BEC_AUDIO_TYPE
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
    return Page(fileName, langNamespace, htmlPageString)
}

context(EnvContext, OutputContext, LanguageContext, PageContext)
private fun HEAD.headTags() {
    meta { charset = "utf-8" }
    link { rel = "preload"; href = C.WINE_IMAGE_PATH; attributes += "as" to "image"; }
    style { +resources.sytlesCss; }
    meta { name = "viewport"; content = "user-scalable=yes, width=device-width,initial-scale=1,shrink-to-fit=no" }
    meta { name = "robots"; content = "index, follow" }

    title { +t.headTitle }
    meta { name = "description"; content = t.headMetaDescription }
    meta { name = "theme-color"; content = C.THEME_RGB }// ios Safari (modern)

    meta { attributes += "property" to "og:title"; content=t.headTitle }
    meta { attributes += "property" to "og:description"; content=t.headMetaDescription }
    meta { attributes += "property" to "og:url"; content=domain }
    meta { attributes += "property" to "og:type"; content=pageOgType }
    meta { attributes += "property" to "og:image"; content=C.LOGO_SQR_IMAGE_PATH }
    meta { attributes += "property" to "og:locale"; content=language.languageWithTerritory() }
    meta { attributes += "property" to "og:site_name"; content=C.WEBSITE_NAME }

    meta { name="twitter:card"; content="summary_large_image" }
    meta { name="twitter:image"; content=C.LOGO_SQR_IMAGE_PATH }
    meta { name="twitter:image:alt"; content=t.logoAlly}
    meta { name="twitter:creator"; content=C.TWITTER_HANDLE }
    meta { name="twitter:site"; content=C.TWITTER_HANDLE }
    meta { name="twitter:site_name"; content=C.TWITTER_HANDLE }

    meta { name="twitter:title"; content=t.headTitle }
    meta { name="twitter:description"; content=t.headMetaDescription }

    unsafe {
        +resources.faviconTags
    }
    script {
        unsafe { +"""if('serviceWorker' in navigator){navigator.serviceWorker.register("${C.SERVICE_WORKER_JS_PATH}")}""" }
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
