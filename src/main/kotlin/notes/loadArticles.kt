package notes

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import asHtmlPage
import kotlinx.html.article
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.hr
import kotlinx.html.unsafe
import listFilenamesInDirectory
import loadResourceAsString

fun loadArticles(folder: String): List<NoteResource> {
    return listFilenamesInDirectory(folder)
        .map { NoteResource(it, loadResourceAsString("$folder/$it")) }
        .sortedByDescending { it.id }
}

context(EnvContext, LanguageContext, OutputContext)
fun MutableList<Page>.addBlogPages(articles: List<NoteResource>) {
    // todo p-category

    val contents = articles.map { articleResource ->
        val blogId = articleResource.id
        val titlesAndDescriptions = articleResource.titlesAndDescriptions
        with(PageContext("note/$blogId", pageOgType = "article", titlesAndDescriptions, "note")) {
            val c = contentWithPermalink(articleResource)
            add(asHtmlPage(c))
            c
        }
    }

    val mergedArticles = contents.joinToString("") { content ->
        buildString {
            h().article(classes = "h-entry") {
                div(classes = "e-content") {
                    unsafe {
                        +content
                    }
                }
                hr()
                br()
                br()
            }
        }
    }

    add(with(PageContext("note/index.html", pageOgType = "website", t.notesIndexTitlesAndDescriptions)) {
        val hiddenPermalink = "<a class=\"u-url\" href='$pageUrl'></a>"
        asHtmlPage(hiddenPermalink + mergedArticles)
    })
}
