package utils

import EnvContext

context(EnvContext)
fun String.minifyCss() = if(prettyPrint) this else this
    .replace(Regex("\\{\\s+"), "{")
    .replace(Regex(":\\s+"), ":")
    .replace(Regex(",\\s+"), ",")
    .replace(Regex(";\\s+"), ";")
    .replace(Regex("}\\s+"), "}")
    .replace(Regex("/\\*\\s+"), "/*")
    .replace(Regex("\\*/\\s+"), "*/")
