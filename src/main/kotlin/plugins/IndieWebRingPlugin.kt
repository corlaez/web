package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.BODY
import kotlinx.html.a
import kotlinx.html.p

class IndieWebRingPlugin(override val enabled: Boolean = true) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun bodyTags(body: BODY) {
        body.p(classes = "center") {
            a(classes = "u-url") {
                href = "https://xn--sr8hvo.ws/%F0%9F%94%AE%F0%9F%90%9C%F0%9F%9A%87/previous"; rel = "nofollow"; +"←"
            }
            +" An "
            a(classes = "u-url") {
                href = "https://xn--sr8hvo.ws"; rel = "nofollow"; +"IndieWeb Webring"
            }
            +" \uD83D\uDD78\uD83D\uDC8D "
            a(classes = "u-url")  {
                href = "https://xn--sr8hvo.ws/%F0%9F%94%AE%F0%9F%90%9C%F0%9F%9A%87/next"; rel = "nofollow"; +"→"
            }
        }
    }
}
