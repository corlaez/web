package notes

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.hr
import kotlinx.html.unsafe
import listFilenamesInDirectory
import loadResourceAsString
import templates.asHtmlPage

fun loadArticles(folder: String): List<NoteResource> {
    return listFilenamesInDirectory(folder)
        .map { NoteResource(it, loadResourceAsString("$folder/$it")) }
        .sortedByDescending { it.id }
}

context(EnvContext, LanguageContext, OutputContext)
fun MutableList<Page>.addBlogPages(articles: List<NoteResource>) {
    // todo p-category

    val contents = articles.map { articleResource ->
        val outputFileName = articleResource.outputFileName
        val titlesAndDescriptions = articleResource.titlesAndDescriptions
        with(PageContext("note/$outputFileName", pageOgType = "article", titlesAndDescriptions)) {
            val c = contentWithPermalink(articleResource)
            add(asHtmlPage("h-entry", c))
            c
        }
    }

    val mergedArticles = contents.joinToString("") { content ->
        createH().div(classes = "h-entry") {
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

    add(with(PageContext("note/index.html", pageOgType = "website", t.notesIndexTitlesAndDescriptions)) {
        asHtmlPage("h-feed", mergedArticles)
    })
}
