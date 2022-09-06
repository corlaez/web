package plugins

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import WebPlugin
import kotlinx.html.BODY
import kotlinx.html.HEAD
import kotlinx.html.link

class WebMentionPlugin(override val enabled: Boolean = true) : WebPlugin {
    context(EnvContext)
    override fun pages(): List<Page> {
        return emptyList()
    }

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun headTags(head: HEAD) {
        head.link { rel="webmention"; href="https://webmention.io/corlaez.com/webmention" }
        head.link { rel="pingback"; href="https://webmention.io/corlaez.com/xmlrpc" }
    }

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun bodyTags(body: BODY) { }
}
