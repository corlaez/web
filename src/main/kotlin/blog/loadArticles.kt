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
    // todo p-category
    val articleSeparator = "\n\n<hr/><br/><br/>\n\n"
    val mergedArticles = articles.joinToString("") {
        "<h2><a href='${language.langPath()}blog/${it.blogId}'>${it.titlesAndDescriptions.visibleTitle}</a></h2>" + it.content() + articleSeparator
    }

    add(with(PageContext("index.html", pageOgType = "website", t.blogIndexTitlesAndDescriptions)) {
        val hiddenPermalink = "<a class=\"u-url\" href='$pageUrl'></a>"
        asHtmlPage(hiddenPermalink + mergedArticles)
    })

    articles.forEach {
        with(it) {
            add(with(PageContext("blog/$blogId", pageOgType = "article", titlesAndDescriptions)) {
                asHtmlPage(contentWithPermalink())
            })
        }
    }
}
