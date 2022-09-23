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
import kotlinx.html.NAV
import kotlinx.html.a
import kotlinx.html.classes

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

    context(EnvContext, LanguageContext, PageContext, NAV)
    override fun navTags() {
        this@NAV.a {
            if (path.contains("note")) {
                classes = classes + "selected"
                if (isIndex) classes = classes + "u-url"
            }
            href = "${language.langPath()}note"
            +t.notes
        }
        +" "
    }
}
