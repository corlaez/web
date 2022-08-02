data class EnvContext(val arg: Args, val port: String)

data class OutputContext(val resources: Resources)

data class LanguageContext(val language: Language) {
    val t: LocalizedText = LocalizedText(language)
}
