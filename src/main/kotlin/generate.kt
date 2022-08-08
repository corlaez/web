import C.replaceConstants

context(EnvContext)
fun generate() {
    val resources = Resources(
        sytlesCss = loadAndMergeCss().replaceConstants(),
        indexEn = loadResourceAsString("/en/index.md"),
        indexEs = loadResourceAsString("/es/index.md"),
        faviconTags = loadResourceAsString("/tags.txt").replace(">\n", ">"),
        wsReload = loadResourceAsString("/wsReload.js").replace("PORT", port),
    )
    val output = with(OutputContext(resources)) {
        Output(
            enPages = with(LanguageContext(Language.en)) { listOf(
                with(PageContext("index.html", pageOgType = "website")) { asHtmlPage(resources.indexEn) }
            )},
            esPages = with(LanguageContext(Language.es)) { listOf(
                with(PageContext("index.html", pageOgType = "website")) { asHtmlPage(resources.indexEs) }
            )},
            otherPages = listOf(
                Page("styles.css", "/assets/", resources.sytlesCss)
            ),
            staticDir = "static"
        )
    }
    deleteDirectory("output")
    copyDirectory(output.staticDir, "output")
    output.otherPages.forEach { saveFile(it.content, "output${it.namespace}", it.name) }
    output.enPages.forEach { saveFile(it.content, "output${it.namespace}", it.name) }
    output.esPages.forEach { saveFile(it.content, "output${it.namespace}", it.name) }
}

fun loadAndMergeCss(): String {
    return listOf(
        loadResourceAsString("/css/splendor.css"),
        loadResourceAsString("/css/overrides.css").minifyCss(),
        loadResourceAsString("/css/fire.css").minifyCss(),
    ).joinToString("")
}

private fun String.minifyCss() = this
    .replace(Regex(",\\s+"), ",")
    .replace(Regex(";\\s+"), ";")
    .replace(Regex("\\{\\s+"), "{")
    .replace(Regex("}\\s+"), "}")
    .replace(Regex("/\\*\\s+"), "/*")
    .replace(Regex("\\*/\\s+"), "*/")
