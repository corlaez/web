import kotlinx.html.stream.appendHTML

data class EnvContext(val arg: Args, val port: String, val webPlugins: List<WebPlugin>) {
    val domain: String = when(arg) {
        Args.prd -> "https://corlaez.com"
        else -> "http://localhost:$port"
    }

    private val markdownSupport = MarkdownSupport(webPlugins)
    fun mdToHtml(inputMarkdown: String) = markdownSupport.mdToHtml(inputMarkdown)
    fun Appendable.h() = appendHTML(prettyPrint = false)
}

data class OutputContext(val resources: Resources)

data class LanguageContext(val language: Language) {
    val t: LocalizedText = LocalizedText(language)
}

context(EnvContext, LanguageContext)
class PageContext(val path: String, val pageOgType: String, private val titlesAndDescriptions: TitlesAndDescriptions) {
    val pageUrl get() = domain + language.langPath() + (path.takeIf { it != "index.html" } ?: "")
    val isIndex = path == "index.html"

    val LocalizedText.headTitle get() = titlesAndDescriptions.metaTitle
    val LocalizedText.headMetaDescription get() = titlesAndDescriptions.metaDescription
    val LocalizedText.heroTitle get() = titlesAndDescriptions.visibleTitle
    val LocalizedText.heroDescription get() = titlesAndDescriptions.visibleDescription
}
