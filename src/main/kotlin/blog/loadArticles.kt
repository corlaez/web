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
    val articleSeparator = "\n\n<hr/><br/><br/>\n\n"
    val mergedArticles = articles.joinToString("") {
        "<h2><a href='${language.langPath()}blog/${it.blogId}'>${it.titlesAndDescriptions.visibleTitle}</h2></a>\n\n" +
                it.content  + articleSeparator
    }

    add(with(PageContext("index.html", pageOgType = "website", t.blogIndexTitlesAndDescriptions)) {
        asHtmlPage(mergedArticles)
    })

    articles.forEach {
        with(it) {
            add(with(PageContext("blog/$blogId", pageOgType = "article", titlesAndDescriptions)) {
                asHtmlPage(content)
            })
        }
    }
}
