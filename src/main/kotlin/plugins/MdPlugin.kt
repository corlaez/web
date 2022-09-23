package plugins

import EnvContext
import LanguageContext
import LocalizedText
import OutputContext
import Page
import PageContext
import WebPlugin
import createDirectory
import notes.loadArticles
import kotlinx.html.NAV
import kotlinx.html.a
import kotlinx.html.classes
import templates.asHtmlPage

class MdPlugin(
    private val name: String,
    private val pathNamespace: String,
    private val localizedName: (LocalizedText) -> String,
    private val navRel: String? = "",
    override val enabled: Boolean = true
) : WebPlugin {
    context(EnvContext, OutputContext)
    override fun pages(): List<Page> {
        return buildList {
            for (language in Language.values()) {
                if (pathNamespace.isNotBlank()) createDirectory("deploy/output${language.langPath()}$pathNamespace")
                with(LanguageContext(language)) {
                    with(PageContext(
                        "$pathNamespace$name.html",
                        pageOgType = "website",
                        t.notesIndexTitlesAndDescriptions)) {
                        val resources = loadArticles("/${language}/$name")// No pathNamespace here
                        val resource = resources.first()
                        add(asHtmlPage("", mdToHtml(resource.unparsedContent)))
                    }
                }
            }
        }
    }

    context(EnvContext, LanguageContext, PageContext, NAV)
    override fun navTags() {
        if(navRel != null) {
            this@NAV.a {
                if (pageUrl.contains("$pathNamespace$name.html")) {
                    classes = classes + "selected u-url"
                }
                href = "${language.langPath()}$pathNamespace$name.html"
                rel = navRel
                +localizedName(t)
            }
            +" "
        }
    }
}
