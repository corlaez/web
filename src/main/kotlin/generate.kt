import C.replaceTemplateConstants
import blog.addBlogPages
import blog.loadArticles
import utils.minifyCss

context(EnvContext)
fun generate() {
    val resources = Resources(
        articles = mapOf(
            Language.en to loadArticles("/${Language.en}"),
            Language.es to loadArticles("/${Language.es}"),
        ),
        sytlesCss = loadAndMergeCss().replaceTemplateConstants(),
        faviconTags = loadResourceAsString("/tags.txt").replace(">\n", ">").replaceTemplateConstants(),
        manifestJson = loadResourceAsString("/manifest.json").replaceTemplateConstants(),
        browserconfigXml = loadResourceAsString("/browserconfig.xml").replaceTemplateConstants(),
    )
    val output = with(OutputContext(resources)) {
        Output(
            pages = buildList {
                resources.articles.forEach { (lang, articles) ->
                    with(LanguageContext(lang)) {
                        addBlogPages(articles)
                    }
                }
                add(Page("manifest.json", "/", resources.manifestJson))
                add(Page("browserconfig.xml", "/", resources.browserconfigXml))
                webPlugins.forEach { addAll(it.pages()) }
            },
            staticDir = "static"
        )
    }
    deleteDirectory("deploy/output")
    createDirectory("deploy/output/blog")
    createDirectory("deploy/output/${Language.es}/blog")
    output.pages.forEach { saveFile(it.content, "deploy/output${it.namespace}", it.name) }
    if (!arg.isPrdWithoutServer()) copyDirectory(output.staticDir, "deploy/output")
}

fun loadAndMergeCss(): String {
    return listOf(
        loadResourceAsString("/css/print.min.css"),
        loadResourceAsString("/css/fluidity.min.css"),
        loadResourceAsString("/css/modest-variation.css").minifyCss(),
        loadResourceAsString("/css/fire.css").minifyCss(),
    ).joinToString("")
}
