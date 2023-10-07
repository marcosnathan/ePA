package mn.dev.epa.model

data class News(
    val id: String,
    val title: String,
    val author: String,
    val urlImage: String,
    val url: String,
    val date: String
)