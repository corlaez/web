data class EnvContext(val arg: Args, val port: String) {
    val domain: String = when(arg) {
        Args.prd -> "https://corlaez.com"
        else -> "http://localhost:$port"
    }
}

data class OutputContext(val resources: Resources)

data class LanguageContext(val language: Language) {
    val langNamespace = when(language) {
        Language.en -> "/"
        Language.es -> "/es/"
    }
    val t: LocalizedText = LocalizedText(language)
}

context(EnvContext, LanguageContext)
class PageContext(val fileName: String, val pageOgType: String = "article") {
    val pageUrl get() = domain + langNamespace + fileName
    // No id is needed if filenames remain in english despite locale
    val LocalizedText.headTitle get() = getTitleMap(language)[fileName]!!
    val LocalizedText.headMetaDescription get() = getDescriptionMap(language)[fileName]!!
    val LocalizedText.heroTitle get() = getHeroTitleMap(language)[fileName]!!
    val LocalizedText.heroDescription get() = getHeroDescriptionMap(language)[fileName]!!
}
