package blog

import LanguageContext
import PageContext
import TitlesAndDescriptions

class ArticleResource(name: String, unparsedContent: String) {
    val id: Int = name.split("$")[0].toInt()
    val blogId: String
    val titlesAndDescriptions: TitlesAndDescriptions
    private val createdDate: String
    private val modifiedDate: String
    private val rawContent: String

    init {
        val unparsedContentLines = unparsedContent.lines()
        blogId = unparsedContentLines[0]
        titlesAndDescriptions = TitlesAndDescriptions(
            unparsedContentLines[1],
            unparsedContentLines[2],
            unparsedContentLines[3],
            unparsedContentLines[4],
        )
        createdDate = unparsedContentLines[5]
        modifiedDate = unparsedContentLines[6]
        rawContent = unparsedContentLines.subList(7, unparsedContentLines.size).joinToString("\n")
    }

    context(LanguageContext)
    fun content() = "<em><span class=\"dt-published\">${createdDate}</span>${lastUpdate()}</em>\n\n" + rawContent

    context(LanguageContext, PageContext)
    fun contentWithPermalink() = "<a class=\"u-url\" href=\"$pageUrl\"><em><span class=\"dt-published\">${createdDate}</span>${lastUpdate()}</em></a>\n\n" + rawContent

    context(LanguageContext)x
    private fun lastUpdate() = if (modifiedDate.isNotBlank()) " (${t.lastUpdate}: <span class=\"dt-updated\">$modifiedDate</span>)" else ""
}
