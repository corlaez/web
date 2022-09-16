package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.HEAD
import kotlinx.html.link

class WebMentionPlugin(override val enabled: Boolean = true) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun headTags(head: HEAD) {
        head.link { rel="webmention"; href="https://webmention.io/corlaez.com/webmention" }
        head.link { rel="pingback"; href="https://webmention.io/corlaez.com/xmlrpc" }
    }
}
