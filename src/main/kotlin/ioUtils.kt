import java.io.File
import java.nio.charset.Charset

fun loadResourceAsBytes(name: String) = "".javaClass.getResourceAsStream(name)!!.readBytes()
fun loadResourceAsString(name: String, charset: Charset = Charset.defaultCharset()) =
    String(loadResourceAsBytes(name), charset)

fun saveFile(content: String, folder: String?, name: String) {
    if (folder != null) File(folder).mkdirs()
    File("$folder$name").writeText(content)
}
fun copyDirectory(name: String, target: String) = File(name).copyRecursively(File(target), true)

fun deleteDirectory(name: String) = File(name).deleteRecursively()

fun saveFile(content: ByteArray, folder: String?, name: String) {
    if (folder != null) File(folder).mkdirs()
    File("$folder/$name").writeBytes(content)
}
fun copyFileFromResource(name: String, target: String) = File("".javaClass.getResource(name)!!.toURI())
    .copyRecursively(File(target), true)
