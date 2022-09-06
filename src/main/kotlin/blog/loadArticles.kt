package blog

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import asHtmlPage
import listFilenamesInDirectory
import loadResourceAsString

fun loadArticles(folder: String): List<ArticleResource> {
    return listFilenamesInDirectory(folder)
        .map { ArticleResource(it, loadResourceAsString("$folder/$it")) }
        .sortedByDescending { it.id }
}

context(EnvContext, LanguageContext, OutputContext)
fun MutableList<Page>.addBlogPages(articles: List<ArticleResource>) {
    // todo remove presentational html
    val articleSeparator = "\n\n<hr/><br/><br/>\n\n"
    // todo surround articles in article tag with h-entry, author reference (probably the link itself), e-content, etc
    // todo make sure the whole index is not marked as an article, an article deep link will mark the whole page as an article  (maybe not the footer, but the hero header will)
    val mergedArticles = articles.joinToString("") {
        "<h2><a href='${language.langPath()}blog/${it.blogId}'>${it.titlesAndDescriptions.visibleTitle}</a></h2>" + it.content() + articleSeparator
    }

    add(with(PageContext("index.html", pageOgType = "website", t.blogIndexTitlesAndDescriptions)) {
        asHtmlPage(mergedArticles)
    })

    articles.forEach {
        with(it) {
            add(with(PageContext("blog/$blogId", pageOgType = "article", titlesAndDescriptions)) {
                asHtmlPage(content())
            })
        }
    }
}
