import com.vladsch.flexmark.util.misc.Extension
import kotlinx.html.BODY
import kotlinx.html.HEAD

interface WebPlugin {
    val enabled: Boolean

    fun flexmarkExtensions(): Collection<Extension> = emptyList()

    context(EnvContext)
    fun pages(): List<Page>

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    fun headTags(head: HEAD)

    context(EnvContext, OutputContext, LanguageContext, PageContext)
    fun bodyTags(body: BODY)
}
