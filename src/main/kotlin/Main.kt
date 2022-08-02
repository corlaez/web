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
    if (argStatus != 200) throw IllegalStateException("server failed to provide arg. Code: $argStatus")
    return Args.valueOf(argResponse)
}

context(EnvContext)
private fun devGetRequestReload() {
    if (arg.isDev()) {
        val (reloadStatus, reloadResponse) = httpGet("http://localhost:$port/dev/reload")
        if (reloadStatus != 200) throw IllegalStateException("dev server failed to reload clients. Code: $reloadStatus")
        logger.info("$reloadResponse ${LocalDateTime.now()}")
    }
}
