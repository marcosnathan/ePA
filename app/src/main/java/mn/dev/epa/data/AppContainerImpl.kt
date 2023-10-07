package mn.dev.epa.data

import android.content.Context
import mn.dev.epa.data.news.NewsRepository
import mn.dev.epa.data.news.impl.FakeNewsRepository
import mn.dev.epa.data.official_diary.PublicationsRepository
import mn.dev.epa.data.official_diary.impl.FakePublicationsRepository

interface AppContainer {
    val newsRepository: NewsRepository
    val publicationsRepository: PublicationsRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val publicationsRepository: PublicationsRepository by lazy {
        FakePublicationsRepository()
    }

    override val newsRepository: NewsRepository by lazy {
        FakeNewsRepository()
    }
}