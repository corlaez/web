package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.HEAD
import kotlinx.html.link

class MicrosubPlugin(override val enabled: Boolean = true) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext, HEAD)
    override fun headTags() {
        this@HEAD.link { rel = "microsub"; href = "https://aperture.p3k.io/microsub/781" }
    }
}
