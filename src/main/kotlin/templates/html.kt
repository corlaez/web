package templates

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import kotlinx.html.*

context(EnvContext, OutputContext, LanguageContext, PageContext)
fun asHtmlPage(mainClasses: String, contentHtml: String): Page {
    val htmlPageString = buildString {
        append("<!DOCTYPE html>")
        h().html {
            lang = language.toString()
            head { headTags() }
            body(classes = mainClasses) {
                nav { navTags() }
                if (t.heroTitle != null) header { headerTags() }
                main(classes = "content${if(!isIndex) " e-content" else ""}") {
                    unsafe {
                        +contentHtml
                    }
                }
                footer {
                    hCard()
                    webPlugins.forEach{ it.footerTags() }
                }
            }
        }
    }
    return Page(fileName, language.langPath(), htmlPageString)
}

context(EnvContext, LanguageContext, PageContext)
private fun NAV.navTags() {
    webPlugins.forEach { it.navTags() }
    if (language != Language.es) {
        +" "
        a { href = "${Language.es.langPath()}${path}"; +"Versión en Español" }
    }
    if (language != Language.en) {
        +" "
        a { href = "${Language.en.langPath()}${path}"; +"English Version" }
    }
}

context(EnvContext, OutputContext, LanguageContext, PageContext)
private fun HEADER.headerTags() {
    div(classes = "fire") {
        a(classes = "u-url") {
            href = pageUrl
            h1(classes = "p-name") {
                +t.heroTitle!!
            }
        }
        if (t.heroDescription.isNotBlank()) p { +t.heroDescription }
    }
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
        noScript { p { +t.noScriptMessage } }
    }
}

context(EnvContext, OutputContext, LanguageContext, PageContext)
private fun HEAD.headTags() {
    meta { charset = "utf-8" }
    link { rel = "preload"; href = C.WINE_IMAGE_PATH; attributes += "as" to "image"; }
    link { rel = "canonical"; href = pageUrl }
    if (language != Language.en) link {
        rel = "alternate"; hrefLang = Language.en.toString()
        href = domain + "${Language.en.langPath()}${path}"
    }
    if (language != Language.es) link {
        rel = "alternate"; hrefLang = Language.es.toString()
        href = domain + "${Language.es.langPath()}${path}"
    }
    link { rel = "stylesheet"; href = "/styles.css" }
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
    webPlugins.forEach{ it.headTags() }
}

context(EnvContext, OutputContext, LanguageContext, PageContext)
private fun FOOTER.hCard() {
    // Based on http://microformats.org/wiki/representative-h-card-authoring
    div(classes = "h-card p-author")  {
        p(classes = "center signature") {
            img(classes = "u-photo") {
                alt = t.logoAlly
                attributes += "loading" to "lazy"
                src = C.SIGNATURE2_IMAGE_PATH
                width = C.SIGNATURE2_IMAGE_W
                height = C.SIGNATURE2_IMAGE_H
            }
        }
        p {
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
            a(classes = "u-url") {href = "https://github.com/corlaez";rel = "me authn ${C.EXTERNAL_RELS}"; +"Github";  }
            +" "
            a(classes = "u-url")  {href = "https://linkedin.com/in/corlaez";rel = "me ${C.EXTERNAL_RELS}"; +"LinkedIn" }
            +" "
            a(classes = "u-url")  {href = "https://twitter.com/corlaez";rel = "me ${C.EXTERNAL_RELS}"; +"Twitter" }
        }
    }
}
