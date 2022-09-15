package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.HEAD
import kotlinx.html.link

class IndieAuthPlugin(override val enabled: Boolean = true) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun headTags(head: HEAD) {
        with(head) {
            // avoid using indieauth.com as it is shutting down. Spin up own server or find alternative
            link { rel = "authorization_endpoint"; href = "https://indieauth.com/auth" }
            link { rel = "token_endpoint"; href = "https://tokens.indieauth.com/token" }
        }
    }
}
