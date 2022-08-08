data class EnvContext(val arg: Args, val port: String) {
    val domain: String = when(arg) {
        Args.prd -> "https://corlaez.com/"
        else -> "http://localhost:$port/"
    }
}

data class OutputContext(val resources: Resources)

data class LanguageContext(val language: Language) {
    val t: LocalizedText = LocalizedText(language)
}

context(LanguageContext)
class PageContext(val fileName: String) {
    // No id is needed if filenames remain in english despite locale
    val LocalizedText.title get() = getTitleMap(language)[fileName]
    val LocalizedText.description get() = getDescriptionMap(language)[fileName]
}
