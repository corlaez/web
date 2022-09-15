package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.BODY
import kotlinx.html.a
import kotlinx.html.img
import kotlinx.html.p

class ScorePlugin(override val enabled: Boolean = true) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun bodyTags(body: BODY) {
        with(body) {
            p("center") {
                a {
                    rel = "me";
                    href = "//indieweb.rocks/corlaez.com"
                    img() {
                        alt="IndieMark score"
                        src="//indieweb.rocks/sites/corlaez.com/scoreboard.svg"
                        attributes += "style" to "height: 4rem"
                    }
                }
            }
        }
    }
}
