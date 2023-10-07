package mn.dev.epa.data.news.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import mn.dev.epa.data.Result
import mn.dev.epa.data.news.NewsRepository
import mn.dev.epa.data.official_diary.impl.publications
import mn.dev.epa.model.News
import mn.dev.epa.model.NewsFeed
import java.lang.IllegalArgumentException

class FakeNewsRepository : NewsRepository {

    private val newsFeed = MutableStateFlow<NewsFeed?>(null)

    override suspend fun getNewsFeed(): Result<NewsFeed> {
        return withContext(Dispatchers.IO) {
            delay(800)
            if (shouldRandomlyFail()) {
                Result.Error(IllegalStateException())
            } else {
                newsFeed.update { news }
                Result.Success(news)
            }
        }
    }

    override suspend fun search(query: String): Result<List<News>> {
        return withContext(Dispatchers.IO) {
            val results = news.allNews.filter {
                it.title.contains(query) || it.date.contains(query)
            }
            if (results.isEmpty()) {
                Result.Error(IllegalArgumentException("Notícia não encontrada"))
            } else {
                Result.Success(results)
            }
        }
    }


    private var requestCount = 0

    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}