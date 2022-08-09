import java.lang.IllegalStateException
import java.time.LocalDateTime

fun main(args: Array<String>) {
    val arg = Args.valueOf(args[0])
    val port = System.getenv("PORT") ?: "8080"
    if (arg.isRegenerate()) {
        val serverArg = getRequestArg(port)
        with(EnvContext(serverArg, port)) {
            generate()
            devGetRequestReload()
        }
    } else {
        with(EnvContext(arg, port)) {
            generate()
            serve()
        }
    }
}

private fun getRequestArg(port: String): Args {
    val (argStatus, argResponse) = httpGet("http://localhost:$port/dev/arg")
    return if (argStatus != 200) {
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
        if (reloadStatus != 200) throw IllegalStateException("dev server failed to reload clients. Code: $reloadStatus")
        logger.info("$reloadResponse ${LocalDateTime.now()}")
    }
}
// First break articles into their own pages with links
// Then add rich content jsons (article and qa or education)
// https://developers.google.com/search/docs/advanced/structured-data/search-gallery
// Then work on categories
