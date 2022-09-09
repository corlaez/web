package blog

import EnvContext
import LanguageContext
import PageContext
import kotlinx.html.a
import kotlinx.html.em
import kotlinx.html.time
import kotlinx.html.unsafe
import toDateTime
import toHumanDate

context(EnvContext, LanguageContext)
private fun ArticleResource.unsafeLastUpdate() = if (modifiedDate.isBlank()) "" else buildString {
    append(" (${t.lastUpdate}: ")
    h().time(classes = "dt-updated") {
        dateTime = toDateTime(modifiedDate)
        +toHumanDate(modifiedDate)
    }
    append(")")
}

context(EnvContext, LanguageContext)
fun ArticleResource.content() =  buildString {
    h().em {
        time(classes = "dt-published") {
            dateTime = toDateTime(createdDate)
            +toHumanDate(createdDate)
        }
        unsafe {
            +unsafeLastUpdate()
        }
    }
    append(mdToHtml(rawContent))
}

context(EnvContext,LanguageContext, PageContext)
fun ArticleResource.contentWithPermalink() = buildString {
    h().a(classes = "u-url") {
        href = pageUrl
        em {
            time(classes = "dt-published") {
                dateTime = toDateTime(createdDate)
                +toHumanDate(createdDate)
            }
            unsafe {
                +unsafeLastUpdate()
            }
        }
    }
    append(mdToHtml(rawContent))
}
