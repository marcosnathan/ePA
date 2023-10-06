package mn.dev.epa.ui.home

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
import mn.dev.epa.data.official_diary.PublicationsRepository
import mn.dev.epa.model.Publication
import mn.dev.epa.model.PublicationsFeed
import mn.dev.epa.utils.ErrorMessage
import java.util.UUID


sealed interface HomeUiState {
    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
    val searchInput: String

    data class NoPublications(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String

    ) : HomeUiState

    data class HasPublications(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String,
        val publicationsFeed: PublicationsFeed,
        val isPublicationOpen: Boolean,
        val selectedPublication: Publication,
    ) : HomeUiState
}

private data class HomeVMState(
    val publicationsFeed: PublicationsFeed? =null,
    val selectedPublicationId: String? = null,
    val isPublicationOpen: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
) {
    fun toUiState(): HomeUiState =
        if (publicationsFeed == null){
            HomeUiState.NoPublications(
                isLoading,
                errorMessages,
                searchInput
            )
        } else {
            HomeUiState.HasPublications(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
                publicationsFeed = publicationsFeed,
                selectedPublication = publicationsFeed.publications.find {
                    it.id == selectedPublicationId
                } ?: publicationsFeed.publications[0],
                isPublicationOpen = isPublicationOpen
            )
        }
}

class HomeVM(
    private val publicationsRepository: PublicationsRepository,
    preSelectedPublicationId: String? = null
) : ViewModel() {
    private val viewModelState = MutableStateFlow(
        HomeVMState(
            isLoading = true,
            selectedPublicationId = preSelectedPublicationId,
            isPublicationOpen = preSelectedPublicationId != null
        )
    )

    val uiState = viewModelState
        .map(HomeVMState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshPublications()


    }

    fun refreshPublications() {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = publicationsRepository.getPublicationsFeed()
            viewModelState.update {
                when(result) {
                    is Result.Success -> it.copy(publicationsFeed = result.data, isLoading = false)
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
    fun selectPublication(publicationId: String) {
        interactedWithArticleDetails(publicationId)
    }


    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }


    fun interactedWithFeed(){
        viewModelState.update {
            it.copy(isPublicationOpen = false)
        }
    }

    fun interactedWithArticleDetails(publicationId: String) {
        viewModelState.update {
            it.copy(
                selectedPublicationId = publicationId,
                isPublicationOpen = true
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
            publicationsRepository: PublicationsRepository,
            preSelectedPublicationId: String? = null
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeVM(publicationsRepository, preSelectedPublicationId) as T
            }
        }

    }
}