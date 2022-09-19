import java.io.File
import java.net.URI
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.*

fun getUri(name: String): URI {
    val url = "".javaClass.getResource(name)
    return url!!.toURI()
}
fun listFilenamesInDirectory(name: String): List<String> {
    val dirPath = Paths.get(getUri(name))
    val paths = Files.list(dirPath)
    return paths
        .map { p -> p.fileName.toString() }
        .collect(Collectors.toList())
}
fun loadResourceAsBytes(name: String) = "".javaClass.getResourceAsStream(name)!!.readBytes()
fun loadResourceAsString(name: String, charset: Charset = Charset.forName("UTF-8")) =
    String(loadResourceAsBytes(name), charset)

fun saveFile(content: String, folder: String?, name: String) {
    if (folder != null) createDirectory(folder)
    File("$folder$name").writeText(content)
}
fun copyDirectory(name: String, target: String) = File(name).copyRecursively(File(target), true)

fun deleteDirectory(name: String) = File(name).deleteRecursively()
fun createDirectory(name: String) = File(name).mkdirs()

fun saveFile(content: ByteArray, folder: String?, name: String) {
    if (folder != null) File(folder).mkdirs()
    File("$folder/$name").writeBytes(content)
}
fun saveFileFromResource(resourceName: String, folder: String?, name: String) {
    saveFile(loadResourceAsString(resourceName), folder, name)
}
