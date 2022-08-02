enum class Language {
    en, es;
}

class LocalizedText(language: Language){
    val metaDescription = when(language) {
        Language.en -> "Armando Cordova's Personal Blog"
        Language.es -> "Blog personal de Armando Cordova"
    }
    val thanksForYourVisit = when(language) {
        Language.en -> "Thanks for your visit."
        Language.es -> "Gracias por la visita"
    }
    val hiIamArmando = when(language) {
        Language.en -> "Hi! I am Armando"
        Language.es -> "Hola! Soy Armando"
    }
    val hiIamArmandoSubtitle = when(language) {
        Language.en -> "Welcome to my blog  #Software #Kotlin"
        Language.es -> "Bienvenido a mi blog #Software #Kotlin"
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
