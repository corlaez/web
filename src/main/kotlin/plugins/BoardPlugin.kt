package plugins

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import WebPlugin
import asHtmlPage
import notes.loadArticles
import kotlinx.html.BODY
import kotlinx.html.HEAD

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

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun headTags(head: HEAD) { }

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun bodyTags(body: BODY) { }
}
