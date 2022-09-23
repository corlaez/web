package plugins

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import WebPlugin
import com.vladsch.flexmark.ext.gitlab.GitLabExtension
import com.vladsch.flexmark.util.misc.Extension
import kotlinx.html.FOOTER
import kotlinx.html.script

class MermaidPlugin(override val enabled: Boolean = true) : WebPlugin {
    // alters generation of mermaid code blocks to be a div with class mermaid as the js expects
    override fun flexmarkExtensions(): Collection<Extension> = listOf(GitLabExtension.create())

    context(EnvContext)
    override fun pages(): List<Page> {
        return emptyList()// TODO add mermaid js from webjar
    }

    // loads mermaid js
    context(EnvContext, OutputContext, LanguageContext, PageContext, FOOTER)
    override fun footerTags() {
        // todo how to lazy load this only when needed
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
