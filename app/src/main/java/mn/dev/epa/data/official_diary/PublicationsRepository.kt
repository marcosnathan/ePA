package mn.dev.epa.data.official_diary

import mn.dev.epa.data.Result
import mn.dev.epa.model.Publication
import mn.dev.epa.model.PublicationsFeed
import java.util.Date

interface PublicationsRepository {

    suspend fun searchByQuery(query: String): Result<List<Publication>>

    suspend fun searchByDate(date: Date): Result<List<Publication>>

    suspend fun getPublicationsFeed() : Result<PublicationsFeed>
}