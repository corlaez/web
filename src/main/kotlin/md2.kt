import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet

val options = with(MutableDataSet()) {
//    uncomment to set optional extensions
//    options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));
//    uncomment to convert soft-breaks to hard breaks
//    set(HtmlRenderer.SOFT_BREAK, "<br />\n")
    this
}

private val parser: Parser = Parser.builder(options).build()
private val htmlRenderer = HtmlRenderer.builder(options).build()

fun mdToHtml(inputMarkdown: String) = htmlRenderer.render(parser.parse(inputMarkdown))
