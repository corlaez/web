object C {
    private const val SIGNATURE_IMAGE_PATH = "/assets/signature-400.webp"
    private const val SIGNATURE2_IMAGE_PATH = "/assets/signature-white-210.webp"
    private const val STYLES_CSS_PATH = "/assets/styles.css"
    // referenced directly in the html template
    const val BEC_AUDIO = "/assets/bec.mp3"
    const val WINE_IMAGE_PATH = "/assets/dark-red.webp"
    const val LOGO_SQR_IMAGE_PATH = "/assets/logo.PNG"
    const val SERVICE_WORKER_JS_PATH = "/serviceWorker.js"
    const val THEME_RGB = "#A10000"
    const val TWITTER_HANDLE = "@corlaez"
    const val WEBSITE_NAME = "Corlaez Blog"

    fun String.replaceConstants() = this
        .replace("WINE_IMAGE_PATH", WINE_IMAGE_PATH)
        .replace("SIGNATURE_IMAGE_PATH", SIGNATURE_IMAGE_PATH)
        .replace("SIGNATURE2_IMAGE_PATH", SIGNATURE2_IMAGE_PATH)
        .replace("STYLES_CSS_PATH", STYLES_CSS_PATH)
        .replace("LOGO_SQR_IMAGE_PATH", LOGO_SQR_IMAGE_PATH)
        .replace("THEME_RGB", THEME_RGB)
        .replace("TWITTER_HANDLE", TWITTER_HANDLE)
        .replace("WEBSITE_NAME", WEBSITE_NAME)
}
