package notes

import EnvContext
import LanguageContext
import PageContext
import kotlinx.html.*
import toDateTime
import toHumanDate

context(EnvContext, LanguageContext)
private fun NoteResource.unsafeLastUpdate() = if (modifiedDate.isBlank()) "" else buildString {
    append(" (${t.lastUpdate}: ")
    h().time(classes = "dt-updated") {
        dateTime = toDateTime(modifiedDate)
        +toHumanDate(modifiedDate)
    }
    append(")")
}

context(EnvContext, LanguageContext)
private fun FlowOrPhrasingContent.datesHtml(noteResource: NoteResource) {
    em {
        time(classes = "dt-published") {
            dateTime = toDateTime(noteResource.createdDate)
            +toHumanDate(noteResource.createdDate)
        }
        unsafe {
            +noteResource.unsafeLastUpdate()
        }
    }
}

context(EnvContext, LanguageContext)
fun content(noteResource: NoteResource) =  buildString {
    h().p {
        datesHtml(noteResource)
    }
    append(mdToHtml(noteResource.rawContent))
}


context(EnvContext,LanguageContext, PageContext)
fun contentWithPermalink(noteResource: NoteResource) = buildString {
    h().p {
        a(classes = "u-url") {
            href = pageUrl
            datesHtml(noteResource)
        }
    }
    append(mdToHtml(noteResource.rawContent))
}
