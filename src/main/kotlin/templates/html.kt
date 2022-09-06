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
                    p {
                        +t.heroDescription
                        if(t.heroDescription.isNotBlank()) br()
                        if(path != "index.html") { a { href=language.langPath(); +t.backLink }; +" " }
                        if(language != Language.es) {
                            a { href="${Language.es.langPath()}$path"; +"Versión en Español" }; +" "
                        }
                        if(language != Language.en) {
                            a { href="${Language.en.langPath()}$path"; +"English Version" }; +" "
                        }
                    }
                }
                div(classes = "content") {
                    noScript { +t.noScriptMessage }
                    div(classes = "center") {
                        audio {
                            attributes += "preload" to "none"
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
                }
                hCardFooter()
                webPlugins.forEach{ it.bodyTags(this@body) }
            }
        }
    }
    return Page(path, language.langPath(), htmlPageString)
}

context(EnvContext, OutputContext, LanguageContext, PageContext)
private fun HEAD.headTags() {
    meta { charset = "utf-8" }
    link { rel = "preload"; href = C.WINE_IMAGE_PATH; attributes += "as" to "image"; }
    link { rel = "canonical"; href = pageUrl }

    style { unsafe { +resources.sytlesCss; } }
    meta { name = "viewport"; content = "user-scalable=yes, width=device-width,initial-scale=1,shrink-to-fit=no" }
    meta { name = "robots"; content = "index, follow" }

    title { +t.headTitle }
    meta { name = "description"; content = t.headMetaDescription }
    meta { name = "theme-color"; content = C.LOGO_SQR_THEME_RGB }// ios Safari (modern)
    meta { name = "author"; content = t.author }

    meta { attributes += "property" to "og:title"; content=t.headTitle }
    meta { attributes += "property" to "og:description"; content=t.headMetaDescription }
    meta { attributes += "property" to "og:url"; content=domain }
    meta { attributes += "property" to "og:type"; content=pageOgType }
    if (pageOgType == "article") {
//        meta { attributes += "property" to "og:article:published_time"; content="" }
//        meta { attributes += "property" to "og:article:modified_time"; content="" }
//        meta { attributes += "property" to "og:article:expiration_time"; content="" }
        meta { attributes += "property" to "og:article:author"; content=t.author }
//        meta { attributes += "property" to "og:article:section"; content="" }
//        meta { attributes += "property" to "og:article:tag"; content="" }
    }
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
        unsafe { +"""if('serviceWorker' in navigator){
            |navigator.serviceWorker.register("${C.SERVICE_WORKER_JS_PATH}")}""".trimMargin() }
    }
    webPlugins.forEach{ it.headTags(this@headTags) }
}

context(OutputContext, LanguageContext, PageContext)
private fun BODY.hCardFooter() {
    // Based on http://microformats.org/wiki/representative-h-card-authoring
    div(classes = "h-card")  {
        id = "h-card"
        p(classes = "center signature") {
            img(classes = "u-photo") {
                alt = t.logoAlly
                attributes += "loading" to "lazy"
                src = C.SIGNATURE2_IMAGE_PATH
                width = C.SIGNATURE2_IMAGE_W
                height = C.SIGNATURE2_IMAGE_H
            }
        }
        div {
            a(classes = "u-url u-uid") {
                href = "https://corlaez.com"
                +"corlaez.com"
            }
            +t.messageBetweenWebsiteAndName
            span(classes = "p-name") {
                +C.OWNER_NAME
            }
            +". "
            +t.thanksForYourVisit
        }
        p(classes = "center") {
            a(classes = "u-url") {href = "https://github.com/corlaez"; rel = "me authn"; +"Github";  }
            +" "
            a(classes = "u-url")  { href = "https://linkedin.com/in/corlaez"; rel = "me"; +"LinkedIn" }
            +" "
            a(classes = "u-url")  { href = "https://twitter.com/corlaez"; rel = "me"; +"Twitter" }
        }
    }
}
