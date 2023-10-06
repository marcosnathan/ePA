package mn.dev.epa.model

data class PublicationsFeed(
    val recentPublications: List<Publication>,
    val publications: List<Publication>,
)