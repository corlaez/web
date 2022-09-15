import com.vladsch.flexmark.util.misc.Extension
import kotlinx.html.BODY
import kotlinx.html.HEAD
import kotlinx.html.NAV

interface WebPlugin {
    val enabled: Boolean

    fun flexmarkExtensions(): Collection<Extension> = emptyList()

    context(EnvContext, OutputContext)
    fun pages(): List<Page> = emptyList()

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    fun headTags(head: HEAD) {}

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    fun bodyTags(body: BODY) {}

    context(EnvContext, LanguageContext, PageContext)
    fun navTags(nav: NAV) {}
}
