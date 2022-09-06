import plugins.MermaidPlugin
import org.eclipse.jetty.http.HttpStatus
import plugins.DevPlugin
import java.time.LocalDateTime

fun main(args: Array<String>) {
    val arg = Args.valueOf(args[0])
    val port = System.getenv("PORT") ?: "8080"
    val webPlugins = listOf(DevPlugin(), MermaidPlugin(false)).filter { it.enabled }
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
// bg animation> https://heckscaper.com/stuff/index.html

// use https://prismjs.com/ for syntax highlight NEW plugin
// Articles should only show translation links if the translation exists NEW
// Categories NEW

// use theme colors in css
// replace the banner with a solid color at least for og (linked in has agressive compression) Linkedin compat
// pure css dark/light mode https://www.jobsity.com/blog/how-to-make-dark-mode-for-websites-using-only-css NEW (may need js to make mermaid follow)

// keep checking https://app.asqatasun.org/ for html validity or learn how to incorporate to predeploy https://app.asqatasun.org/home/contract/page-result.html?wr=28874
// https://validator.w3.org/
// https://jigsaw.w3.org/css-validator/
// https://htmlhelp.org/tools/csscheck/