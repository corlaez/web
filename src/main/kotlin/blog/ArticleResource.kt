package blog

import LanguageContext
import PageContext
import TitlesAndDescriptions
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

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
    fun content() = "<em><time datetime=\"${toDateTime(createdDate)}\" class=\"dt-published\">${toHumanDate(createdDate)}</time>${lastUpdate()}</em>\n\n" + rawContent

    context(LanguageContext, PageContext)
    fun contentWithPermalink() = "<a class=\"u-url\" href=\"$pageUrl\">" +
            "<em><time datetime=\"${toDateTime(createdDate)}\" class=\"dt-published\">${toHumanDate(createdDate)}</time>${lastUpdate()}</em>" +
            "</a>\n\n" + rawContent

    private fun toDateTime(s: String) = LocalDateTime.of(LocalDate.parse(s), LocalTime.MIDNIGHT)
        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    context(LanguageContext)
    private fun toHumanDate(s: String) = LocalDate.parse(s)
        .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(language.locale()))

    context(LanguageContext)
    private fun lastUpdate(): String {
        return if (modifiedDate.isNotBlank()) { " (" +
                "${t.lastUpdate}: " +
                "<time datetime=\"${toDateTime(modifiedDate)}\" class=\"dt-updated\">" +
                toHumanDate(modifiedDate) +
                "</time>" +
                ")"
        } else ""
    }
}
