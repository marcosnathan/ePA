package mn.dev.epa.data.official_diary.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import mn.dev.epa.data.Result
import mn.dev.epa.data.official_diary.PublicationsRepository
import mn.dev.epa.model.Publication
import mn.dev.epa.model.PublicationsFeed
import java.lang.IllegalArgumentException
import java.util.Date

class FakePublicationsRepository: PublicationsRepository {


    private val publicationsFeed = MutableStateFlow<PublicationsFeed?>(null)

    override suspend fun searchByQuery(query: String): Result<List<Publication>> {
        return withContext(Dispatchers.IO){
            val publications = publications.publications.filter {
                it.title.contains(query) || it.resume.contains(query) || it.agency.contains(query) || it.city.contains(query)
            }
            if (publications.isEmpty()){
                Result.Error(IllegalArgumentException("Publicação não encontrada"))
            }
            else {
                Result.Success(publications)
            }
        }
    }

    override suspend fun searchByDate(date: Date): Result<List<Publication>> {
        return withContext(Dispatchers.IO){
            val publications = publications.publications.filter {
                it.date == date
            }
            if (publications.isEmpty()){
                Result.Error(IllegalArgumentException("Publicação não encontrada"))
            }
            else {
                Result.Success(publications)
            }
        }
    }

    override suspend fun getPublicationsFeed(): Result<PublicationsFeed> {
        return withContext(Dispatchers.IO){
            delay(800)
            if (shouldRandomlyFail()){
                Result.Error(IllegalStateException())
            }
            else{
                publicationsFeed.update { publications }
                Result.Success(publications)
            }
        }
    }

    private var requestCount = 0

    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0


}