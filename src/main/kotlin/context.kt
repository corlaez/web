import kotlinx.html.stream.appendHTML

data class EnvContext(val arg: Args, val port: String, val webPlugins: List<WebPlugin>) {
    val domain: String = when(arg.isPrd()) {
        true -> C.DOMAIN
        false -> "http://localhost:$port"
    }

    private val markdownSupport = MarkdownSupport(webPlugins)
    fun mdToHtml(inputMarkdown: String) = markdownSupport.mdToHtml(inputMarkdown)
    fun Appendable.h() = appendHTML(prettyPrint = !arg.isPrd())
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
) {
    val isIndex = fileName.split("/").last() == "index.html"
    val path = if (isIndex) fileName.split("/").dropLast(1).joinToString("/")  else fileName
    val pageUrl get() = domain + language.langPath() + path

    val LocalizedText.headTitle get() = titlesAndDescriptions.metaTitle
    val LocalizedText.headMetaDescription get() = titlesAndDescriptions.metaDescription
    val LocalizedText.heroTitle get() = titlesAndDescriptions.visibleTitle
    val LocalizedText.heroDescription get() = titlesAndDescriptions.visibleDescription
}
