package blog

import TitlesAndDescriptions

class ArticleResource(name: String, unparsedContent: String) {
    val id: Int = name.split("$")[0].toInt()
    val blogId: String
    val titlesAndDescriptions: TitlesAndDescriptions
    val content: String

    init {
        val unparsedContentLines = unparsedContent.lines()
        blogId = unparsedContentLines[0]
        titlesAndDescriptions = TitlesAndDescriptions(
            unparsedContentLines[1],
            unparsedContentLines[2],
            unparsedContentLines[3],
            unparsedContentLines[4],
        )
        content = unparsedContentLines.subList(5, unparsedContentLines.size).joinToString("\n")
    }
}
