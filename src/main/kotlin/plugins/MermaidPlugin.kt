package plugins

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import WebPlugin
import com.vladsch.flexmark.ext.gitlab.GitLabExtension
import com.vladsch.flexmark.util.misc.Extension
import kotlinx.html.*

class MermaidPlugin(override val enabled: Boolean = true) : WebPlugin {

    companion object {
        const val activePluginName = "htmx"
    }

    // alters generation of mermaid code blocks to be a div with class mermaid as the js expects
    override fun flexmarkExtensions(): Collection<Extension> = listOf(GitLabExtension.create())

    context(EnvContext)
    override fun pages(): List<Page> {
        return emptyList()// TODO add mermaid js from webjar
    }

    // loads mermaid js
    context(EnvContext, OutputContext, LanguageContext, PageContext, FOOTER)
    override fun footerTags() {
        if(activePlugin.contains(activePluginName)) {
            this@FOOTER.script { +(
                    "window.onload=function(){" +
                            "mermaid.initialize({" +
                            "'theme':'dark','background':'#111111'" +
                            "});" +
                            "};")
            }
            this@FOOTER.script { defer = true; src ="https://cdn.jsdelivr.net/npm/mermaid/dist/mermaid.min.js" }
        }
    }
}
