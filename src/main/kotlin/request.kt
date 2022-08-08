import java.net.HttpURLConnection
import java.net.URL

fun httpGet(urlStr: String): Pair<Int, String> {
    val url = URL(urlStr)
    val con = url.openConnection() as HttpURLConnection
    con.requestMethod = "GET"
    return runCatching {
        con.responseCode to con.inputStream.bufferedReader().readText()
    }.getOrDefault(500 to "Couldn't connect")
}
