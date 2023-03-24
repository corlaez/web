import common.*
import plugins.*
import server.Arg

val envText = EnvText(
    author =  { "Armando Cordova" },
    logoAlly = { when(it) {
        englishUnitedStatesLanguage -> "Logo that reads A R"
        spanishPeruLanguage -> "Logo con el texto A R"
        else -> error("Invalid language")
    }},
    LOGO_SQR_THEME_RGB = "#A10000",
    LOGO_SQR_IMAGE_PATH = "/assets/banner2.png",
    WEBSITE_NAME = "Corlaez Blog",
    TWITTER_HANDLE = "@corlaez",
    SERVICE_WORKER_JS_PATH = "/serviceWorker.js",
    EXTERNAL_RELS = "nofollow noreferrer noopener",
)

fun main(args: Array<String>) {
    val envContext = EnvContext(
        arg = Arg.valueOf(args[0]),
        port = System.getenv("PORT") ?: "8080",
        productionDomain = "https://corlaez.com",
        envText = envText,
        languages = listOf(spanishPeruLanguage, englishUnitedStatesLanguage),
        webPlugins = webPlugins2(),
    )
    with(envContext) {
        run()
    }
}

private fun webPlugins2(): List<WebPlugin> {
    return listOf(
        BlogPlugin({
            when(it) {
                englishUnitedStatesLanguage -> TitlesAndDescriptions(
                    "Hi! I am Armando",
                    "Welcome to my website where I will share about software in general," +
                            " Kotlin and the Indie Web",
                    "Corlaez Blog",
                    "Welcome to the website of Armando Cordova. You will find blogs about topics " +
                            "such as Violin, Kotlin and IndieWeb",
                )
                spanishPeruLanguage -> TitlesAndDescriptions(
                    "Hola! Soy Armando",
                    "Bienvenido a mi asdasd web donde compartiré sobre software en general, Violin," +
                            " Kotlin y la Indie Web",
                    "Corlaez Blog",
                    "Bienvenido al sitio web de Armando Cordova. " +
                            "Encontrarás información sobre Violín, Kotlin y La Indie Web",
                )
                else -> error("Invalid language")
            }
        }),
        NotesPlugin({
            when(it) {
                englishUnitedStatesLanguage -> common.TitlesAndDescriptions(
                    null,
                    "",
                    "Notes made by Armando Cordova",
                    "Notes made by Armando Cordova. You will find notes about topics " +
                            "such as Violin, Kotlin and IndieWeb",
                )
                spanishPeruLanguage -> common.TitlesAndDescriptions(
                    null,
                    "",
                    "Apuntes hechos por Armando Cordova",
                    "Bienvenido al sitio web de Armando Cordova. " +
                            "Aquí encontrarás información sobre Violín, Kotlin y La Indie Web",
                )
                else -> error("Invalid language")
            }
        }),
        HtmxPlugin("htmx", "",),
        MdPlugin("board", "", { when(it) {
            englishUnitedStatesLanguage -> "Board"
            spanishPeruLanguage -> "Pizarra"
            else -> error("Invalid language")
        } }, {
            when(it) {
                englishUnitedStatesLanguage -> common.TitlesAndDescriptions(
                    null,
                    "",
                    "Board made by Armando Cordova",
                    "Board made by Armando Cordova. Ever changing, free scratch notes",
                )
                spanishPeruLanguage -> common.TitlesAndDescriptions(
                    null,
                    "",
                    "Apuntes hechos por Armando Cordova",
                    "Bienvenido al sitio web de Armando Cordova. " +
                            "Aquí encontrarás información sobre Violín, Kotlin y La Indie Web",
                )
                else -> error("Invalid language")
            }
        }),
        MdPlugin("legal", "", { when(it) {
            englishUnitedStatesLanguage -> "Legal"
            spanishPeruLanguage -> "Legal"
            else -> error("Invalid language")
        } }, {
            when(it) {
                englishUnitedStatesLanguage -> common.TitlesAndDescriptions(
                    null,
                    "",
                    "Board made by Armando Cordova",
                    "Board made by Armando Cordova. Ever changing, free scratch notes",
                )
                spanishPeruLanguage -> common.TitlesAndDescriptions(
                    null,
                    "",
                    "Apuntes hechos por Armando Cordova",
                    "Bienvenido al sitio web de Armando Cordova. " +
                            "Aquí encontrarás información sobre Violín, Kotlin y La Indie Web",
                )
                else -> error("Invalid language")
            }
        }, "legal privacy"),
        MdPlugin("hexagonal-proposal", "", { "Hexagonal" }, { when(it) {
            englishUnitedStatesLanguage -> TitlesAndDescriptions(
                null,
                "",
                "Hexagonal Proposal made by Armando Cordova",
                "Board made by Armando Cordova. Ever changing, free scratch notes",
            )
            spanishPeruLanguage -> TitlesAndDescriptions(
                null,
                "",
                "Propuesta Hexagonal hecha por Armando Cordova",
                "Bienvenido al sitio web de Armando Cordova. " +
                        "Aquí encontrarás información sobre Violín, Kotlin y La Indie Web",
            )
            else -> error("Invalid language")
        } }, null),
        DevPlugin(DevCss.BORDER),
        MermaidPlugin(false),
        WebMentionPlugin(),
        CardPlugin(),
        IndieWebRingPlugin(),
        IndieAuthPlugin(),
        MicrosubPlugin(),
        ScorePlugin(false),
        PrismPlugin(),
        LanguageNavPlugin({
            when(it) {
                englishUnitedStatesLanguage -> "English Version"
                spanishPeruLanguage -> "Versión en Español"
                else -> error("Invalid language")
            }
        }),
        VarsPlugin(),
        AudioHeaderPlugin(),
    ).filter { it.enabled }
}
