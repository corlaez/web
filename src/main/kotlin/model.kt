enum class Args {
    dev, prd, regenerate, prdWithoutServer;
    fun isDev() = this == dev
    fun isPrd() = this == prd || this == prdWithoutServer
    fun isRegenerate() = this == regenerate
    fun noServer() = this == prdWithoutServer
}

data class Resources(
    val sytlesCss: String,
    val indexEn: String,
    val indexEs: String,
    val faviconTags: String,
    val wsReload: String,
    val manifestJson: String,
    val browserconfigXml: String,
)

data class Page(val name: String, val namespace: String, val content: String)

data class Output(
    val enPages: List<Page>,
    val esPages: List<Page>,
    val otherPages: List<Page> = emptyList(),
    val staticDir: String,
)
