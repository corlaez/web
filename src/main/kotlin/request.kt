import java.net.HttpURLConnection
import java.net.URL

fun httpGet(urlStr: String): Pair<Int, String> {
    val url = URL(urlStr)
    val con = url.openConnection() as HttpURLConnection
    con.requestMethod = "GET"
    return con.responseCode to con.inputStream.bufferedReader().readText()
}
