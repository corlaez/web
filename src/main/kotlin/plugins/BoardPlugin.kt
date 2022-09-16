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

class BoardPlugin(override val enabled: Boolean = true) : WebPlugin {
    context(EnvContext, OutputContext)
    override fun pages(): List<Page> {
        val boards = mapOf(
            Language.en to loadArticles("/${Language.en}/board").first(),
            Language.es to loadArticles("/${Language.es}/board").first(),
        )
        return buildList {
            boards.forEach { (lang, board) ->
                with(LanguageContext(lang)) {
                    with(PageContext("board.html", pageOgType = "website", t.notesIndexTitlesAndDescriptions)) {
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
                if (pageUrl.contains("board.html")) {
                    classes = classes + "selected u-url"
                }
                href = "${language.langPath()}board.html"
                +t.board
            }
            +" "
        }
    }
}
