import java.lang.IllegalStateException

enum class Language {
    en, es;

    fun languageWithTerritory(): String= when(this) {
        en -> "en_US"
        es -> "es_PE"
    }
}

class LocalizedText(language: Language){
    val noScriptMessage = when(language) {
        Language.en -> "Hey you, browsing with JavaScript off. You are welcomed! this page does not require JS to work properly :)"
        Language.es -> "Hey tu, navegando con JavaScript deshabilitado. Bienvenido! esta pagina no requiere JS para funcionar de manera apropiada :)"
    }
    val logoAlly = when(language) {
        Language.en -> "Logo that reads A R"
        Language.es -> "Logo con el texto A R"
    }
    val thanksForYourVisit = when(language) {
        Language.en -> "Thanks for your visit."
        Language.es -> "Gracias por la visita"
    }
    val englishVersionLink = when(language) {
        Language.en -> null
        Language.es -> "English version"
    }
    val spanishVersionLink = when(language) {
        Language.en -> "Version en español"
        Language.es -> null
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
