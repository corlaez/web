package blog

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import asHtmlPage
import kotlinx.html.a
import kotlinx.html.br
import kotlinx.html.details
import kotlinx.html.div
import kotlinx.html.h2
import kotlinx.html.hr
import kotlinx.html.summary
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
            h().details(classes = "h-entry") {
                summary(classes = "h2") {
                    h2 {
                        a(classes = "u-url p-name") {
                            href = "${language.langPath()}blog/${articleResource.blogId}"
                            +articleResource.titlesAndDescriptions.visibleTitle!!
                        }
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
        asHtmlPage(mergedArticles)
    })

    articles.forEach { articleResource ->
        val blogId = articleResource.blogId
        val titlesAndDescriptions = articleResource.titlesAndDescriptions
        val articlePage = with(PageContext("blog/$blogId", pageOgType = "article", titlesAndDescriptions)) {
            asHtmlPage(contentWithPermalink(articleResource))
        }
        add(articlePage)
    }
}
