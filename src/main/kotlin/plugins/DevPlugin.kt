package plugins

import EnvContext
import LanguageContext
import OutputContext
import PageContext
import WebPlugin
import kotlinx.html.HEAD
import kotlinx.html.script
import kotlinx.html.style
import kotlinx.html.unsafe
import loadResourceAsString
import utils.minifyCss

enum class DevCss {
    NONE, BORDER, BORDER_AND_BACKGROUND
}

class DevPlugin(private val devCss: DevCss = DevCss.NONE, override val enabled: Boolean = true) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext, HEAD)
    override fun headTags() {
        if (arg.isDev()){// Check here because the EnvContext may be borrowed from the server
            this@HEAD.script {
                async = true
                defer = true
                unsafe {
                    +loadResourceAsString("/wsReload.js").replace("PORT", port)
                }
            }
            if (devCss == DevCss.BORDER || devCss == DevCss.BORDER_AND_BACKGROUND) {
                var css = loadResourceAsString("/css/dev.css")
                if (devCss == DevCss.BORDER) {
                    css = css.lines().filter { !it.contains("background") }.joinToString("").minifyCss()
                }
                this@HEAD.style {
                    unsafe {
                        +css
                    }
                }
            }
        }
    }
}
