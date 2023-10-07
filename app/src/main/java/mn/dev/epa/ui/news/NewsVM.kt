package mn.dev.epa.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mn.dev.epa.R
import mn.dev.epa.data.Result
import mn.dev.epa.data.news.NewsRepository
import mn.dev.epa.model.News
import mn.dev.epa.model.NewsFeed
import mn.dev.epa.utils.ErrorMessage
import java.util.UUID

sealed interface NewsUiState {
    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
    val searchInput: String

    data class NoNews(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : NewsUiState

    data class hasNews(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String,
        val newsFeed: NewsFeed,
        val isNewsOpen: Boolean,
        val selectedNews: News?
    ) : NewsUiState
}

private data class NewsVMState(
    val newsFeed: NewsFeed? = null,
    val selectedNewsId: String? = null,
    val isNewsOpen: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
) {
    fun toUiState(): NewsUiState =
        if (newsFeed == null) {
            NewsUiState.NoNews(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
        } else {
            NewsUiState.hasNews(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
                newsFeed = newsFeed,
                selectedNews = newsFeed.allNews.find {
                    it.id == selectedNewsId
                },
                isNewsOpen = isNewsOpen,
            )
        }
}

class NewsVM(
    private val newsRepository: NewsRepository,
    preSelectedNewsId: String? = null
) : ViewModel() {
    private val viewModelState = MutableStateFlow(
        NewsVMState(
            isLoading = true,
            selectedNewsId = preSelectedNewsId,
            isNewsOpen = preSelectedNewsId != null
        )
    )

    val uiState = viewModelState
        .map(NewsVMState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshNews()
    }

    fun refreshNews() {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = newsRepository.getNewsFeed()
            viewModelState.update {
                when (result) {
                    is Result.Success -> it.copy(newsFeed = result.data, isLoading = false)
                    is Result.Error -> {
                        val errorMessages = it.errorMessages + ErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits,
                            messageId = R.string.app_name
                        )
                        it.copy(errorMessages = errorMessages, isLoading = false)
                    }
                }
            }
        }
    }

    fun selectNews(newsId: String) {
        interactedWithNewsDetails(newsId)
    }

    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }

    fun interactedWithFeed() {
        viewModelState.update {
            it.copy(isNewsOpen = false)
        }
    }

    fun interactedWithNewsDetails(newsId: String) {
        viewModelState.update {
            it.copy(
                selectedNewsId = newsId,
                isNewsOpen = true
            )
        }
    }

    fun onSearchInputChanged(searchInput: String){
        viewModelState.update {
            it.copy(searchInput = searchInput)
        }
    }

    companion object {
        fun provideFactory(
            newsRepository: NewsRepository,
            preSelectedNewsId: String? = null
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NewsVM(newsRepository, preSelectedNewsId) as T
            }
        }
    }
}