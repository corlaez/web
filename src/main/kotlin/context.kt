import kotlinx.html.stream.appendHTML
import kotlinx.html.stream.createHTML

data class EnvContext(val arg: Args, val port: String, val webPlugins: List<WebPlugin>) {
    val domain: String = when(arg.isPrd()) {
        true -> C.DOMAIN
        false -> "http://localhost:$port"
    }
    // prettyPrint helps people to read and understand the source in their browsers and doesn't do much to reduce size
    val prettyPrint = true
    private val markdownSupport = MarkdownSupport(webPlugins)

    fun mdToHtml(inputMarkdown: String) = markdownSupport.mdToHtml(inputMarkdown)
    fun Appendable.h() = appendHTML(prettyPrint = prettyPrint)
    fun createH() = createHTML(prettyPrint = prettyPrint)
}

data class OutputContext(val resources: Resources)

data class LanguageContext(val language: Language) {
    val t: LocalizedText = LocalizedText(language)
}

context(EnvContext, LanguageContext)
class PageContext(
    val fileName: String,
    val pageOgType: String,
    private val titlesAndDescriptions: TitlesAndDescriptions,
    val activePlugin: List<String> = emptyList()
) {
    val isIndex = fileName.split("/").last() == "index.html"
    val path = if (isIndex) fileName.split("/").dropLast(1).joinToString("/")  else fileName
    val pageUrl get() = domain + language.langPath() + path

    val LocalizedText.headTitle get() = titlesAndDescriptions.metaTitle
    val LocalizedText.headMetaDescription get() = titlesAndDescriptions.metaDescription
    val LocalizedText.heroTitle get() = titlesAndDescriptions.visibleTitle
    val LocalizedText.heroDescription get() = titlesAndDescriptions.visibleDescription
}
