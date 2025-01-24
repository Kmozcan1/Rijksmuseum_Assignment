import androidx.paging.PagingSource
import com.albertheijn.rijksmuseumassignment.data.ArtPagingSource
import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectListedDto
import com.albertheijn.rijksmuseumassignment.data.model.dto.ImageDto
import com.albertheijn.rijksmuseumassignment.data.model.response.CollectionResponse
import com.albertheijn.rijksmuseumassignment.data.network.RijksmuseumApi
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtImage
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ArtPagingSourceTest {
    private val mockApi: RijksmuseumApi = mock()

    @Test
    fun `load returns page when successful`() = runBlocking {
        val mockResponse = CollectionResponse(
            artObjects = listOf(
                ArtObjectListedDto(
                    hasImage = true,
                    headerImage = ImageDto(
                        url = "https://example.com/image.jpg",
                        height = 100,
                        width = 100
                    ),
                    id = "1",
                    objectNumber = "123",
                    title = "Test Art",
                    principalOrFirstMaker = "Test Author",
                    links = null,
                    longTitle = "asd",
                    permitDownload = null,
                    productionPlaces = null,
                    showImage = true,
                    webImage = ImageDto(
                        url = "https://example.com/image.jpg",
                        height = 100,
                        width = 100
                    )
                )
            )
        )

        whenever(methodCall = mockApi.getCollection(page = 0)).thenReturn(mockResponse)

        val pagingSource = ArtPagingSource(rijksmuseumApi = mockApi)

        val result = pagingSource.load(
            params = PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        val expectedArt = Art(
            author = "Test Author",
            image = ArtImage(
                height = 100,
                width = 100,
                url = "https://example.com/image.jpg"
            ),
            objectNumber = "123",
            title = "Test Art"
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = listOf(expectedArt),
                prevKey = null,
                nextKey = null
            ), result
        )
    }

    @Test
    fun `load returns error when exception is thrown`() = runBlocking {
        val exception = RuntimeException("Network error")

        whenever(methodCall = mockApi.getCollection(page = 0)).thenThrow(exception)

        val pagingSource = ArtPagingSource(rijksmuseumApi = mockApi)

        val result = pagingSource.load(
            params = PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        assert(value = result is PagingSource.LoadResult.Error)
        assertEquals(exception, (result as PagingSource.LoadResult.Error).throwable)
    }
}
