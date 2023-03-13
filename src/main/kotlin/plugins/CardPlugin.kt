package plugins

import common.*
import kotlinx.html.*

class CardPlugin(
    override val enabled: Boolean = true
) : WebPlugin {

    context(EnvContext, OutputContext, LanguageContext, PageContext, FOOTER)
    override fun footerTags() {
        // Based on http://microformats.org/wiki/representative-h-card-authoring
        this@FOOTER.div(classes = "h-card p-author")  {
            p(classes = "center signature") {
                img(classes = "u-photo") {
                    alt = t.logoAlly
                    attributes += "loading" to "lazy"
                    src = "/assets/signature-white-210.png"
                    width = "210"
                    height = "210"
                }
            }
            p {
                a(classes = "u-url u-uid") {
                    href = "https://corlaez.com"
                    +"corlaez.com"
                }
                +t.messageBetweenWebsiteAndName
                span(classes = "p-name") {
                    +WebVariables["OWNER_NAME"]
                }
                +". "
                +t.thanksForYourVisit
            }
            p(classes = "center") {
                a(classes = "u-url") {href = "https://github.com/corlaez";rel = "me authn ${WebVariables["EXTERNAL_RELS"]}"; +"Github"  }
                +" "
                a(classes = "u-url")  {href = "https://linkedin.com/in/corlaez";rel = "me ${WebVariables["EXTERNAL_RELS"]}"; +"LinkedIn" }
                +" "
                a(classes = "u-url")  {href = "https://twitter.com/corlaez";rel = "me ${WebVariables["EXTERNAL_RELS"]}"; +"Twitter" }
            }
        }
    }
}
