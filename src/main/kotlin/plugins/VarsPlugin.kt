package plugins

import common.*
import common.WebVariables
import kotlinx.html.HEAD
import kotlinx.html.link

class VarsPlugin(
    override val enabled: Boolean = true
) : WebPlugin {

    init {
        WebVariables.register("SIGNATURE_IMAGE_PATH" to "/assets/signature-400.webp")
        WebVariables.register("WINE_IMAGE_PATH" to "/assets/dark-red.webp")
        WebVariables.register("AUDIO_RGB" to "#bd1e1e")
        WebVariables.register("OWNER_NAME" to "Armando Cordova")
    }

    context(EnvContext, OutputContext, LanguageContext, PageContext, HEAD)
    override fun headTags() {
        this@HEAD.link {
            rel = "preload"
            href = WebVariables["WINE_IMAGE_PATH"]
            attributes += "as" to "image"
        }
    }
}
