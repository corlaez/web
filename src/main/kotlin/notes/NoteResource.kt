package notes

import TitlesAndDescriptions

class NoteResource(name: String, unparsedContent: String) {
    val id: Int = name.substring(0, name.length - 3).toInt()
    val titlesAndDescriptions: TitlesAndDescriptions
    val createdDate: String
    val modifiedDate: String
    val rawContent: String

    init {
        val unparsedContentLines = unparsedContent.lines()
        createdDate = unparsedContentLines[0]
        modifiedDate = unparsedContentLines[1]
        rawContent = unparsedContentLines.subList(3, unparsedContentLines.size).joinToString("\n")
        titlesAndDescriptions = TitlesAndDescriptions(
            null,
            "",
            C.OWNER_NAME + ": " + rawContent.substring(0,40),
            rawContent,
        )
    }
}
