package mn.dev.epa.model

data class NewsFeed(
    val highlightedNews: List<News>,
    val news: List<News>
) {
    val allNews: List<News> = highlightedNews + news
}