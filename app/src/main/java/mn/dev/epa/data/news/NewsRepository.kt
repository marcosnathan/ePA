package mn.dev.epa.data.news

import mn.dev.epa.data.Result
import mn.dev.epa.model.News
import mn.dev.epa.model.NewsFeed
import mn.dev.epa.model.PublicationsFeed

interface NewsRepository {
    suspend fun getNewsFeed() : Result<NewsFeed>

    suspend fun search(query: String): Result<List<News>>
}