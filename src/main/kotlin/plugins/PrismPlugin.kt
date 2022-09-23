package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.FOOTER
import kotlinx.html.HEAD
import kotlinx.html.link
import kotlinx.html.script

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

    context(EnvContext, OutputContext, LanguageContext, PageContext, HEAD)
    override fun headTags() {
        this@HEAD.link {
            rel = "stylesheet"
            href = "/assets/prism.css"
        }
    }
    context(EnvContext, OutputContext, LanguageContext, PageContext, FOOTER)
    override fun footerTags() {
        this@FOOTER.script {
//            async = true
//            defer = true
            src = "/assets/prism.js"
        }
    }
}
