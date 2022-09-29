package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.*

class IndieWebRingPlugin(override val enabled: Boolean = true) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext, FOOTER)
    override fun footerTags() {
        this@FOOTER.p(classes = "center") {
            a(classes = "u-url") {
                href = "https://xn--sr8hvo.ws/%F0%9F%94%AE%F0%9F%90%9C%F0%9F%9A%87/previous";rel = C.EXTERNAL_RELS;+"←"
            }
            +" An "
            a(classes = "u-url") {
                href = "https://xn--sr8hvo.ws"; rel = C.EXTERNAL_RELS; +"IndieWeb Webring"
            }
            +" \uD83D\uDD78\uD83D\uDC8D "
            a(classes = "u-url")  {
                href = "https://xn--sr8hvo.ws/%F0%9F%94%AE%F0%9F%90%9C%F0%9F%9A%87/next"; rel = C.EXTERNAL_RELS; +"→"
            }
        }
    }
}
