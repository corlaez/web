package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.HEAD
import kotlinx.html.link

class WebMentionPlugin(override val enabled: Boolean = true) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext, HEAD)
    override fun headTags() {
        this@HEAD.link { rel="webmention"; href="https://webmention.io/corlaez.com/webmention" }
        this@HEAD.link { rel="pingback"; href="https://webmention.io/corlaez.com/xmlrpc" }
    }
}
