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
//
//Content is not sized correctly for the viewportThe viewport size of 412px does not match the window size of 360px. (li problem... does in happen on p with the right string?)
//
//PWA
//No matching service worker detected. You may need to reload the page, or check that the scope of the service worker for the current page encloses the scope and start URL from the manifest.
//Downloaded icon was empty or corrupted
//
//
//Does not register a service worker that controls page and start_url

// additional rich content
// https://developers.google.com/search/docs/advanced/structured-data/search-gallery