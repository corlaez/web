object OutputPaths {
    val WINE_IMAGE_PATH = "/assets/dark-red.webp"
    val SIGNATURE_IMAGE_PATH = "/assets/signature-400.webp"
    val SIGNATURE2_IMAGE_PATH = "/assets/signature-white-210.webp"
    val STYLES_CSS_PATH = "/styles.css"

    fun String.replaceOutputPaths() = this
        .replace("WINE_IMAGE_PATH", WINE_IMAGE_PATH)
        .replace("SIGNATURE_IMAGE_PATH", SIGNATURE_IMAGE_PATH)
        .replace("SIGNATURE2_IMAGE_PATH", SIGNATURE2_IMAGE_PATH)
        .replace("STYLES_CSS_PATH", STYLES_CSS_PATH)
}
