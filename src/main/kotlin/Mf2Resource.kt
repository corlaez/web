// WIP
class Mf2Resource(name: String, unparsedContent: String) {
    val id: Int = name.split("$")[0].toInt()
    val blogId: String = name.split("$")[1].substring(0, name.length - 3)
    val outputFileName: String = "$blogId.html"
    val titlesAndDescriptions: TitlesAndDescriptions
    val createdDate: String
    val modifiedDate: String?
    val mdContent: String
    private val variables: MutableMap<String, String> = mutableMapOf()

    init {
        val unparsedContentLines = unparsedContent.lines()
        mdContent = unparsedContentLines.dropWhile {
            val isVariable = it.startsWith("$")
            if (isVariable) {
                val keyAndValue = it.split("=")
                val key = keyAndValue[0].trim().removePrefix("$")
                val value = keyAndValue[1].trim()
                variables[key] = value
            }
            isVariable
        }.joinToString("\n")
        createdDate = variables["createdDate"]!!
        modifiedDate = variables["modifiedDate"]
        titlesAndDescriptions = TitlesAndDescriptions(
            variables["visibleTitle"],
            variables["visibleDescription"] ?: "",
            variables["metaTitle"] ?: (C.OWNER_NAME + ": " + mdContent.substring(0, 40.coerceAtMost(mdContent.length))),
            variables["metaDescription"] ?: mdContent,
        )
    }
}
