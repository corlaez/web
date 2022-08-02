enum class Args {
    dev, prd, regenerate;
    fun isDev() = this == dev
    fun isRegenerate() = this == regenerate
    fun isPrd() = this == prd
}

data class Resources(
    val sytlesCss: String,
    val indexEn: String,
    val indexEs: String,
    val faviconTags: String,
    val wsReload: String
)
data class Page(val name: String, val content: String)
data class Output(val enPages: List<Page>, val esPages: List<Page>, val otherPages: List<Page>, val staticDir: String)
