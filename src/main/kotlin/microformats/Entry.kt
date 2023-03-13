package microformats

import common.TitlesAndDescriptions

data class Entry(
    val createdDate: String,
    val modifiedDate: String?,
    val id: Int,
    val readableId: String,// is this needed?
    val titlesAndDescriptions: TitlesAndDescriptions,
    val heroTitle: String?,
    val unparsedContent: String,
)
