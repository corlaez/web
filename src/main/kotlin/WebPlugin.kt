import com.vladsch.flexmark.util.misc.Extension
import kotlinx.html.FOOTER
import kotlinx.html.HEAD
import kotlinx.html.NAV

interface WebPlugin {
    val enabled: Boolean

    fun flexmarkExtensions(): Collection<Extension> = emptyList()

    context(EnvContext, OutputContext)
    fun pages(): List<Page> = emptyList()

    context(EnvContext, OutputContext, LanguageContext, PageContext, HEAD)
    fun headTags() {}

    context(EnvContext, OutputContext, LanguageContext, PageContext, FOOTER)
    fun footerTags() {}

    context(EnvContext, LanguageContext, PageContext, NAV)
    fun navTags() {}
}
