import C.replaceTemplateConstants
import utils.minifyCss

context(EnvContext)
fun generate() {
    val resources = Resources(
        sytlesCss = loadAndMergeCss().replaceTemplateConstants(),
        faviconTags = loadResourceAsString("/tags.txt").replace(">\n", ">").replaceTemplateConstants(),
        manifestJson = loadResourceAsString("/manifest.json").replaceTemplateConstants(),
        browserconfigXml = loadResourceAsString("/browserconfig.xml").replaceTemplateConstants(),
    )
    deleteDirectory("deploy/output")
    val output = with(OutputContext(resources)) {
        Output(
            pages = buildList {
                add(Page("manifest.json", "/", resources.manifestJson))
                add(Page("browserconfig.xml", "/", resources.browserconfigXml))
                webPlugins.forEach { addAll(it.pages()) }
            },
            staticDir = "static"
        )
    }
    output.pages.forEach { saveFile(it.content, "deploy/output${it.namespace}", it.name) }
    copyDirectory(output.staticDir, "deploy/output")
}

fun loadAndMergeCss(): String {
    return listOf(
        loadResourceAsString("/css/print.min.css"),
        loadResourceAsString("/css/fluidity.min.css"),
        loadResourceAsString("/css/modest-variation.css").minifyCss(),
        loadResourceAsString("/css/fire.css").minifyCss(),
    ).joinToString("")
}
