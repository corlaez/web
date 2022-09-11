import java.util.*

enum class Language {
    en, es;

    fun langPath() = when(this) {
        en -> "/"
        es -> "/es/"
    }

    fun languageWithTerritory(): String= when(this) {
        en -> "en_US"
        es -> "es_PE"
    }

    fun locale(): Locale = when(this) {
        en -> Locale.ENGLISH
        es -> Locale("es")
    }
}

class LocalizedText(language: Language) {
    val lastUpdate = when(language) {
        Language.en -> "Last update"
        Language.es -> "Última actualización"
    }
    val messageBetweenWebsiteAndName = when(language) {
        Language.en -> " has been brought to you by "
        Language.es -> " llega a usted gracias a "
    }
    val backLink = when(language) {
        Language.en -> "Back"
        Language.es -> "Volver"
    }
    val noScriptMessage = when(language) {
        Language.en -> "Hey you, browsing with JavaScript off. You are welcomed! " +
                "This page does not require JS to work properly :)"
        Language.es -> "Hey tu, navegando con JavaScript deshabilitado. Bienvenido! " +
                "Esta pagina no requiere JS para funcionar de manera apropiada :)"
    }
    val logoAlly = when(language) {
        Language.en -> "Logo that reads A R"
        Language.es -> "Logo con el texto A R"
    }
    val thanksForYourVisit = when(language) {
        Language.en -> "Thanks for your visit."
        Language.es -> "Gracias por la visita"
    }
    val author = "Armando Cordova"
    val blogIndexTitlesAndDescriptions = when(language) {
        Language.en -> TitlesAndDescriptions(
            "Hi! I am Armando",
            "Welcome to my blog #Software #Kotlin",
            "Corlaez Blog",
            "Armando Cordova's Personal Blog",// Todo 100 chars
        )
        Language.es -> TitlesAndDescriptions(
            "Hola! Soy Armando",
            "Bienvenido a mi blog #Software #Kotlin",
            "Corlaez Blog",
            "Bienvenido a mi blog #Software #Kotlin",
        )
    }
}

class TitlesAndDescriptions(
    val visibleTitle: String?,
    val visibleDescription: String,
    val metaTitle: String,
    val metaDescription: String,
)
