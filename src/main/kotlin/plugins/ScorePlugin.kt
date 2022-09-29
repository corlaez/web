package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.*

class ScorePlugin(override val enabled: Boolean = true) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext, FOOTER)
    override fun footerTags() {
        this@FOOTER.p("center") {
            a {
                rel = "me ${C.EXTERNAL_RELS}"
                href = "//indieweb.rocks/corlaez.com"
                img {
                    // todo specify width and height
                    alt="IndieMark score"
                    src="//indieweb.rocks/sites/corlaez.com/scoreboard.svg"
                    attributes += "style" to "height: 4rem"
                }
            }
        }
    }
}
