enum class Args {
    dev, prd, regenerate, prdWithoutServer;
    fun isDev() = this == dev
    fun isPrd() = this == prd || this == prdWithoutServer
    fun isRegenerate() = this == regenerate
    fun isPrdWithoutServer() = this == prdWithoutServer
}

data class Resources(
    val sytlesCss: String,
    val faviconTags: String,
    val manifestJson: String,// Google PWA
    val browserconfigXml: String,// Windows 8+
)

data class Page(val name: String, val namespace: String, val content: String)

data class Output(
    val pages: List<Page>,
    val staticDir: String,
)
