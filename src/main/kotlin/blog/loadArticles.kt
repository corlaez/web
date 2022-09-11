package blog

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import asHtmlPage
import kotlinx.html.a
import kotlinx.html.article
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.h2
import kotlinx.html.hr
import kotlinx.html.unsafe
import listFilenamesInDirectory
import loadResourceAsString

fun loadArticles(folder: String): List<ArticleResource> {
    return listFilenamesInDirectory(folder)
        .filter { it.contains("$") }
        .map { ArticleResource(it, loadResourceAsString("$folder/$it")) }
        .sortedByDescending { it.id }
}

context(EnvContext, LanguageContext, OutputContext)
fun MutableList<Page>.addBlogPages(articles: List<ArticleResource>) {
    // todo p-category
    val mergedArticles = articles.joinToString("") { articleResource ->
        buildString {
            h().article(classes = "h-entry") {
                h2(classes = "p-name") {
                    a(classes = "u-url") {
                        href = "${language.langPath()}blog/${articleResource.blogId}"
                        +articleResource.titlesAndDescriptions.visibleTitle!!
                    }
                }
                div(classes = "e-content") {
                    unsafe {
                        +content(articleResource)
                    }
                }
                hr()
                br()
                br()
            }
        }

    }

    add(with(PageContext("index.html", pageOgType = "website", t.blogIndexTitlesAndDescriptions)) {
        val hiddenPermalink = "<a class=\"u-url\" href='$pageUrl'></a>"
        asHtmlPage(hiddenPermalink + mergedArticles)
    })

    articles.forEach { articleResource ->
        val blogId = articleResource.blogId
        val titlesAndDescriptions = articleResource.titlesAndDescriptions
        val articlePage = with(PageContext("blog/$blogId", pageOgType = "article", titlesAndDescriptions, "")) {
            asHtmlPage(contentWithPermalink(articleResource))
        }
        add(articlePage)
    }
}
