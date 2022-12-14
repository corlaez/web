import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet

class MarkdownSupport(private val webPlugins: List<WebPlugin>) {
    private val options = with(MutableDataSet()) {
//        uncomment to set optional extensions
//        set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));
//        uncomment to convert soft-breaks to hard breaks
//        set(HtmlRenderer.SOFT_BREAK, "<br />\n")
        set(Parser.EXTENSIONS, webPlugins.flatMap { it.flexmarkExtensions() });
        this
    }
    private val parser: Parser = Parser.builder(options).build()
    private val htmlRenderer = HtmlRenderer.builder(options).build()

    fun mdToHtml(inputMarkdown: String) = htmlRenderer.render(parser.parse(inputMarkdown)).replace(">\n", ">")
}
