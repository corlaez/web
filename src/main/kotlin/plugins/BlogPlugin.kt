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
import kotlinx.html.NAV
import kotlinx.html.a
import kotlinx.html.classes

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

    context(EnvContext, LanguageContext, PageContext)
    override fun navTags(nav: NAV) {
        with(nav) {
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
