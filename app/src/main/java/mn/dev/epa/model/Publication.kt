package mn.dev.epa.model

import java.util.Date

data class Publication(
    val title: String,
    val date: Date,
    val agency: String,
    val city: String,
    val resume: String,
    val type: String,
    val url: String
)