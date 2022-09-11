package plugins

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import WebPlugin
import notes.addBlogPages
import notes.loadArticles
import createDirectory
import kotlinx.html.BODY
import kotlinx.html.HEAD

class NotesPlugin(override val enabled: Boolean = true) : WebPlugin {
    context(EnvContext, OutputContext)
    override fun pages(): List<Page> {
        createDirectory("deploy/output/note")
        createDirectory("deploy/output/${Language.es}/note")
        val articles = mapOf(
            Language.en to loadArticles("/${Language.en}/notes"),
            Language.es to loadArticles("/${Language.es}/notes"),
        )
        return buildList {
            articles.forEach { (lang, articles) ->
                with(LanguageContext(lang)) {
                    addBlogPages(articles)
                }
            }
        }
    }

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun headTags(head: HEAD) { }

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    override fun bodyTags(body: BODY) { }
}
