import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.flavours.space.SFMFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

val mdFlavour = GFMFlavourDescriptor()
val mdParser = MarkdownParser(mdFlavour)

fun mdToHtmlBody(inputMarkdown: String) = HtmlGenerator(
    inputMarkdown,
    mdParser.buildMarkdownTreeFromString(inputMarkdown),
    mdFlavour,
).generateHtml()

fun mdToHtml(inputMarkdown: String) = mdToHtmlBody(inputMarkdown).removeSurrounding("<body>", "</body>")
