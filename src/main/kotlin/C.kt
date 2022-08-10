object C {
    private const val SIGNATURE_IMAGE_PATH = "/assets/signature-400.webp"
    private const val SIGNATURE2_IMAGE_PATH = "/assets/signature-white-210.webp"
    // referenced directly in the html template
    const val BEC_AUDIO = "/assets/bec.mp3"// audio
    const val BEC_AUDIO_TYPE = "audio/mpeg"// audio
    const val WINE_IMAGE_PATH = "/assets/dark-red.webp"// preload
    const val THEME_RGB = "#db3636"// og meta tags
    const val THEME_DARK_RGB = "#9d1e1e"
    const val BACKGROUND_RGB = ""// TODO
    const val TWITTER_HANDLE = "@corlaez"// og meta tags
    const val WEBSITE_NAME = "Corlaez Blog"// og meta tags
    const val LOGO_SQR_IMAGE_PATH = "/assets/logo.PNG"// og meta tags
    const val SERVICE_WORKER_JS_PATH = "/serviceWorker.js"// script

    fun String.replaceTemplateConstants() = this
        .replace("SIGNATURE_IMAGE_PATH", SIGNATURE_IMAGE_PATH)
        .replace("SIGNATURE2_IMAGE_PATH", SIGNATURE2_IMAGE_PATH)
        .replace("WINE_IMAGE_PATH", WINE_IMAGE_PATH)
        .replace("THEME_RGB", THEME_RGB)
        .replace("THEME_DARK_RGB", THEME_DARK_RGB)
}
