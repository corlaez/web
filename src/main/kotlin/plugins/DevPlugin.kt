package plugins

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import WebPlugin
import kotlinx.html.BODY
import kotlinx.html.HEAD
import kotlinx.html.script
import kotlinx.html.unsafe
import loadResourceAsString

class DevPlugin(override val enabled: Boolean = true) : WebPlugin {
    context(EnvContext)
    override fun pages(): List<Page> {
        return emptyList()
    }

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun headTags(head: HEAD) {
        if (arg.isDev()){
            head.script {
                async = true
                defer = true
                unsafe {
                    +loadResourceAsString("/wsReload.js").replace("PORT", port)
                }
            }
        }
    }

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun bodyTags(body: BODY) { }
}
