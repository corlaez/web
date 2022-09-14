import plugins.MermaidPlugin
import org.eclipse.jetty.http.HttpStatus
import plugins.BlogPlugin
import plugins.BoardPlugin
import plugins.DevCss
import plugins.DevPlugin
import plugins.IndieWebRingPlugin
import plugins.NotesPlugin
import plugins.WebMentionPlugin
import java.time.LocalDateTime

fun main(args: Array<String>) {
    val arg = Args.valueOf(args[0])
    val port = System.getenv("PORT") ?: "8080"
    val webPlugins = listOf(
        BlogPlugin(),
        NotesPlugin(),
        BoardPlugin(),
        DevPlugin(DevCss.BORDER),
        MermaidPlugin(false),
        WebMentionPlugin(false),
        IndieWebRingPlugin()
    ).filter { it.enabled }
    if (arg.isRegenerate()) {
        val serverArg = getRequestArg(port)
        with(EnvContext(serverArg, port, webPlugins)) {
            generate()
            devGetRequestReload()
        }
    } else {
        with(EnvContext(arg, port, webPlugins)) {
            generate()
            serve()
        }
    }
}

private fun getRequestArg(port: String): Args {
    val (argStatus, argResponse) = httpGet("http://localhost:$port/dev/arg")
    return if (argStatus != HttpStatus.OK_200) {
        logger.warn("server failed to provide arg (generating as prd). Code: $argStatus")
        Args.prd
    } else {
        Args.valueOf(argResponse)
    }
}

context(EnvContext)
private fun devGetRequestReload() {
    if (arg.isDev()) {
        val (reloadStatus, reloadResponse) = httpGet("http://localhost:$port/dev/reload")
        if (reloadStatus != HttpStatus.OK_200) error("dev server failed to reload clients. Code: $reloadStatus")
        logger.info("$reloadResponse ${LocalDateTime.now()}")
    }
}
