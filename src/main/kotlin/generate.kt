import OutputPaths.replaceOutputPaths

context(EnvContext)
fun generate() {
    val resources = Resources(
        sytlesCss = loadAndMergeCss().replaceOutputPaths(),
        indexEn = loadResourceAsString("/en/index.md"),
        indexEs = loadResourceAsString("/es/index.md"),
        faviconTags = loadResourceAsString("/tags.txt"),
        wsReload = loadResourceAsString("/wsReload.js").replace("PORT", port.toString()),
    )
    val output = with(OutputContext(resources)) {
        Output(
            enPages = with(LanguageContext(Language.en)) { listOf(
                Page("index.html", asHtmlPage(resources.indexEn))
            )},
            esPages = with(LanguageContext(Language.es)) { listOf(
                Page("index.html", asHtmlPage(resources.indexEs))
            )},
            otherPages = listOf(
                Page("styles.css", resources.sytlesCss)
            ),
            staticDir = "static"
        )
    }
    deleteDirectory("output")
    copyDirectory(output.staticDir, "output/assets")
    output.otherPages.forEach { saveFile(it.content, "output/", it.name) }
    output.enPages.forEach { saveFile(it.content, "output/", it.name) }
    output.esPages.forEach { saveFile(it.content, "output/es", it.name) }
}

fun loadAndMergeCss(): String {
    return listOf(
        loadResourceAsString("/css/splendor.css"),
        loadResourceAsString("/css/overrides.css"),
        loadResourceAsString("/css/fire.css"),
    ).joinToString("")
}
