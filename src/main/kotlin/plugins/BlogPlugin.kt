package plugins

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import WebPlugin
import blog.addBlogPages
import blog.loadArticles
import createDirectory
import kotlinx.html.BODY
import kotlinx.html.HEAD

class BlogPlugin(override val enabled: Boolean = true) : WebPlugin {
    context(EnvContext, OutputContext)
    override fun pages(): List<Page> {
        createDirectory("deploy/output/blog")
        createDirectory("deploy/output/${Language.es}/blog")
        val articles = mapOf(
            Language.en to loadArticles("/${Language.en}"),
            Language.es to loadArticles("/${Language.es}"),
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
