package plugins

import common.*
import kotlinx.html.*

class AudioHeaderPlugin(
    override val enabled: Boolean = true
) : WebPlugin {

    context(EnvContext, LanguageContext, PageContext, HEADER)
    override fun headerTags() {
        this@HEADER.div(classes = "center") {
            audio {
                attributes += "preload" to "none"
                controls = true
                loop = true
                source {
                    src = "/assets/bec.mp3"
                    type = "audio/mpeg"
                }
            }
            noScript {
                p { +(when(language) {
                    englishUnitedStatesLanguage -> "Hey you, browsing with JavaScript off. You are welcomed! " +
                            "This page does not require JS to work properly :)"
                    spanishPeruLanguage -> "Hey tu, navegando con JavaScript deshabilitado. Bienvenido! " +
                            "Esta página no requiere JS para funcionar de manera apropiada :)"
                    else -> throwUnsupportedLanguage()
                }) }
            }
        }
    }
}
