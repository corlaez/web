package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.BODY
import kotlinx.html.HEAD
import kotlinx.html.link
import kotlinx.html.script
import kotlinx.html.style
import kotlinx.html.unsafe
import loadResourceAsString
import utils.minifyCss

/**
 * Prism current config:
 * Okaidia style
 * minified
 * Markup + HTML + XML + SVG + MathML + SSML + Atom + RSS
 * CSS
 * C-like
 * JavaScript
 * Kotlin + Kotlin Script
 * Autolinker
 */
class PrismPlugin(override val enabled: Boolean = true) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun headTags(head: HEAD) {
        head.link {
            rel = "stylesheet"
            href = "/assets/prism.css"
        }
    }
    context(EnvContext, OutputContext, LanguageContext, PageContext)
            override fun bodyTags(body: BODY) {
        body.script {
//            async = true
//            defer = true
            src = "/assets/prism.js"
        }
    }
}
