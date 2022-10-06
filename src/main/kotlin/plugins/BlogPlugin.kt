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
import kotlinx.html.*

/**
 * Gets Markdown files from resources and transforms each into an article page and merges them into an index page
 * TODO: Parameter blog folder
 * TODO: Parameter output
 * TODO: Parameter template
 * TODO: Index only with titles and separate page for full text search
 * TODO: Use generic mf2 Resource instead of ArticleResource
 * TODO: Supported Langs and default lang
 */
class BlogPlugin(override val enabled: Boolean = true) : WebPlugin {
    context(EnvContext, OutputContext)
    override fun pages(): List<Page> {
        createDirectory("deploy/output/blog")
        createDirectory("deploy/output/${Language.es}/blog")
        val articles = mapOf(
            Language.en to loadArticles("/${Language.en}/blog"),
            Language.es to loadArticles("/${Language.es}/blog"),
        )
        return buildList {
            articles.forEach { (lang, articles) ->
                with(LanguageContext(lang)) {
                    addBlogPages(articles)
                }
            }
        }
    }

    context(EnvContext, LanguageContext, PageContext, NAV)
    override fun navTags() {
        with(this@NAV) {
            a {
                if (path == "" || path.contains("blog")) {
                    classes = classes + "selected"
                    if (isIndex) classes = classes + "u-url"
                }
                href = language.langPath()
            +"Blog"
            }
            +" "
        }
    }
}
