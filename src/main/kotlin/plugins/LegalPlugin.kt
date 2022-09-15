package plugins

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import WebPlugin
import notes.loadArticles
import kotlinx.html.NAV
import kotlinx.html.a
import kotlinx.html.classes
import templates.asHtmlPage

class LegalPlugin(override val enabled: Boolean = true) : WebPlugin {
    context(EnvContext, OutputContext)
    override fun pages(): List<Page> {
        val boards = mapOf(
            Language.en to loadArticles("/${Language.en}/legal").first(),
            Language.es to loadArticles("/${Language.es}/legal").first(),
        )
        return buildList {
            boards.forEach { (lang, board) ->
                with(LanguageContext(lang)) {
                    with(PageContext("legal.html", pageOgType = "website", t.notesIndexTitlesAndDescriptions)) {
                        add(asHtmlPage(mdToHtml(board.unparsedContent)))
                    }
                }
            }
        }
    }

    context(EnvContext, LanguageContext, PageContext)
    override fun navTags(nav: NAV) {
        with(nav) {
            a {
                if (pageUrl.contains("legal.html")) {
                    classes = classes + "selected u-url"
                }
                href = "${language.langPath()}legal.html"
                rel = "legal privacy"
                +t.legal
            }
            +" "
        }
    }
}
