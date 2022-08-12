object C {
    private val list = mutableListOf<Pair<String, String>>()
    val SIGNATURE_IMAGE_PATH = register("SIGNATURE_IMAGE_PATH" to "/assets/signature-400.webp")
    val SIGNATURE2_IMAGE_PATH = register("SIGNATURE2_IMAGE_PATH" to "/assets/signature-white-210.webp")
    // referenced directly in the html template
    val BEC_AUDIO = register("BEC_AUDIO" to "/assets/bec.mp3")
    val BEC_AUDIO_TYPE = register("BEC_AUDIO_TYPE" to "audio/mpeg")
    val WINE_IMAGE_PATH = register("WINE_IMAGE_PATH" to "/assets/dark-red.webp")// preload
    val THEME_LIGHT_RGB = register("THEME_LIGHT_RGB" to "#fd7777")
    val THEME_STD_RGB = register("THEME_STD_RGB" to "#ff4242")
    val THEME_DARK_RGB = register("THEME_DARK_RGB" to "#bd1e1e")
    val BACKGROUND_RGB = register("BACKGROUND_RGB" to "#131516")
    val LINK_RGB = register("LINK_RGB" to THEME_LIGHT_RGB)
    val LINK_VISITED_RGB = register("LINK_VISITED_RGB" to THEME_STD_RGB)
    val AUDIO_RGB = register("AUDIO_RGB" to THEME_DARK_RGB)
    val TWITTER_HANDLE = register("TWITTER_HANDLE" to "@corlaez")
    val WEBSITE_NAME = register("WEBSITE_NAME" to "Corlaez Blog")
    val LOGO_SQR_IMAGE_PATH = register("LOGO_SQR_IMAGE_PATH" to "/assets/logo.PNG")
    val LOGO_SQR_THEME_RGB = register("LOGO_SQR_THEME_RGB" to THEME_DARK_RGB)
    val SERVICE_WORKER_JS_PATH = register("SERVICE_WORKER_JS_PATH" to "/serviceWorker.js")

    fun String.replaceTemplateConstants() = list.fold(this) { acc, pair ->
        acc.replace(pair.first, pair.second)
    }

    private fun register(pair: Pair<String, String>) = pair.let {
        list.add(it)
        it.second
    }
}
