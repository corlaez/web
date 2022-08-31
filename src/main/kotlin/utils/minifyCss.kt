package utils

fun String.minifyCss() = this
    .replace(Regex("\\{\\s+"), "{")
    .replace(Regex(":\\s+"), ":")
    .replace(Regex(",\\s+"), ",")
    .replace(Regex(";\\s+"), ";")
    .replace(Regex("}\\s+"), "}")
    .replace(Regex("/\\*\\s+"), "/*")
    .replace(Regex("\\*/\\s+"), "*/")
