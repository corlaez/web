package blog

import EnvContext
import LanguageContext
import PageContext
import kotlinx.html.FlowOrPhrasingContent
import kotlinx.html.a
import kotlinx.html.em
import kotlinx.html.p
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
private fun FlowOrPhrasingContent.datesHtml(articleResource: ArticleResource) {
    em {
        time(classes = "dt-published") {
            dateTime = toDateTime(articleResource.createdDate)
            +toHumanDate(articleResource.createdDate)
        }
        unsafe {
            +articleResource.unsafeLastUpdate()
        }
    }
}

context(EnvContext, LanguageContext)
fun content(articleResource: ArticleResource) =  buildString {
    h().p {
        datesHtml(articleResource)
    }
    append(mdToHtml(articleResource.rawContent))
}


context(EnvContext,LanguageContext, PageContext)
fun contentWithPermalink(articleResource: ArticleResource) = buildString {
    h().p {
        a(classes = "u-url") {
            href = pageUrl
            datesHtml(articleResource)
        }
    }
    append(mdToHtml(articleResource.rawContent))
}
