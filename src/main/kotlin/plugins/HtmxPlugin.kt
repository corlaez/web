package plugins

import EnvContext
import LanguageContext
import OutputContext
import Page
import PageContext
import WebPlugin
import createDirectory
import kotlinx.html.*
import templates.asHtmlPage

class HtmxPlugin(
    private val name: String,
    private val pathNamespace: String,
    override val enabled: Boolean = true
) : WebPlugin {
    private val friends = listOf("Nicholas", "Joseph", "Caesar")
    private val characteristic = listOf("swimmer", "runner", "debater")

    context(EnvContext, OutputContext, LanguageContext, PageContext, HEAD) override fun headTags() {
        this@HEAD.script { src = "/assets/htmx/htmx.js" }
    }

    context(EnvContext, OutputContext)
    override fun pages(): List<Page> {
        return buildList {
            for (language in Language.values()) {
                if (pathNamespace.isNotBlank()) createDirectory("deploy/output${language.langPath()}$pathNamespace")
                createDirectory("deploy/output${language.langPath()}$pathNamespace$name/")
                with(LanguageContext(language)) {
                    with(PageContext(
                        "$pathNamespace$name.html",
                        pageOgType = "website",
                        t.notesIndexTitlesAndDescriptions)) {
                        add(asHtmlPage("", friendsIndex(friends)))
                    }
                    friends.zip(characteristic).forEach { (f, c) ->
                        add(Page("$f.html", "/$pathNamespace$name/", friendPage(f, c)))
                    }
                }
            }
        }
    }

    context(EnvContext)
    private fun friendsIndex(friends: List<String>): String {
        return buildString { /* inside the block `this` is a BODY HTML instance */
            h().p { +"htmx ajax example:"}
            h().ul {
                friends.forEach { friend ->
                    li {
                        a {
                            href = "/htmx/$friend.html"// Sets cursor pointer and works without js
                            hxGet("/htmx/$friend.html")
                            hxSwap("outerHTML")
                            hxTarget("closest a")
                            +friend
                        }
                    }
                }
            }
            h().p {
                a {
                    href = "https://corlaez.com/blog/kotlin-htmx.html";
                    +"Go to related blog post in corlaez.com"
                }
            }
        }
    }

    context(EnvContext)
    private fun friendPage(friend: String, characteristic: String): String {
        return createH().article {
            p { strong { +friend } }
            p { +"My friend $friend is smart and a good $characteristic" }
        }
    }

    private fun HTMLTag.hxGet(value: String) {
        attributes += "hx-get" to value
    }
    private fun HTMLTag.hxSwap(value: String) {
        attributes += "hx-swap" to value
    }
    private fun HTMLTag.hxTarget(value: String) {
        attributes += "hx-target" to value
    }
}
