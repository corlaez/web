enum class Language {
    en, es;

    fun lan_TE(): String= when(this) {
        en -> "en_US"
        es -> "es_PE"
    }
}

class LocalizedText(language: Language){
    val logoAlly = when(language) {
        Language.en -> "Logo that reads A R"
        Language.es -> "Logo con el texto A R"
    }
    val thanksForYourVisit = when(language) {
        Language.en -> "Thanks for your visit."
        Language.es -> "Gracias por la visita"
    }
    val esLinkText = when(language) {
        Language.en -> "English version"
        Language.es -> null
    }
    val enLinkText = when(language) {
        Language.en -> null
        Language.es -> "Version en español"
    }
}

fun getTitleMap(language: Language) =mapOf(
    "index.html" to when(language) {
        Language.en -> "Corlaez Blog"
        Language.es -> "Corlaez Blog"
    }
)
fun getDescriptionMap(language: Language) = mapOf(
    "index.html" to when(language) {
        Language.en -> "Armando Cordova's Personal Blog"
        Language.es -> "Blog personal de Armando Cordova"
    }
)
fun getHeroTitleMap(language: Language) = mapOf(
    "index.html" to when(language) {
        Language.en -> "Hi! I am Armando"
        Language.es -> "Hola! Soy Armando"
    }
)
fun getHeroDescriptionMap(language: Language) = mapOf(
    "index.html" to when(language) {
        Language.en -> "Welcome to my blog  #Software #Kotlin"
        Language.es -> "Bienvenido a mi blog #Software #Kotlin"
    }
)
