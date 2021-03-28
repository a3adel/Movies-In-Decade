package com.example.movies.data.repos.images

import com.example.movies.IMAGE_SEARCH_ENTITY
import com.example.movies.PHOTOS_ENTITY_PAGE
import com.example.movies.PHOTOS_PAGE
import com.example.movies.data.Resource
import com.example.movies.data.remote.PhotosApiInterface
import com.example.movies.data.repos.images.PHOTOS_ERRORS_CAUSES.NETWORK_ERROR
import com.example.movies.data.utils.NetworkConnectivity
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class PhotosRemoteClientTest {
    private lateinit var SUT: PhotosRemoteClient
    private lateinit var networkConnectivity: NetworkConnectivity
    private lateinit var mapper: PhotosPageEntityToPhotosPageMapper
    private lateinit var errorManager: PhotosErrorManager
    private lateinit var apiInterface: PhotosApiInterface

    @Before
    fun setup() {
        errorManager = mockk()
        networkConnectivity = mockk()
        mapper = mockk(relaxed = true)
        apiInterface = mockk()
        SUT = PhotosRemoteClient(apiInterface, networkConnectivity, mapper, errorManager)
    }

    @Test
    fun `call to search should return success resource object when data returned from apiInterface`() =
        runBlockingTest {
            coEvery {
                apiInterface.searchPhotos(
                    text = any(),
                    page = any()
                )
            } returns Response.success(IMAGE_SEARCH_ENTITY)
            coEvery { mapper.mapFrom(any()) } returns PHOTOS_PAGE
            every { networkConnectivity.isConnected() } returns true
            val resource = SUT.searchPhotos(query = "hello", page = 1,50)
            verify(exactly = 1) { mapper.mapFrom(any()) }

            Assert.assertEquals(PHOTOS_PAGE, (resource as Resource.Success).data)
        }


    @Test
    fun `call to search should return network error resource object when no data returned from apiInterface`() =
        runBlockingTest {

            every { networkConnectivity.isConnected() } returns false
            every { errorManager.getErrorMessage(NETWORK_ERROR) } returns ""
            verify(exactly = 0) {
                mapper.mapFrom(PHOTOS_ENTITY_PAGE)
            }
            coVerify(exactly = 0) { apiInterface.searchPhotos(text = any(), page = any()) }
            val resource = SUT.searchPhotos(query = "helllo", page = 1,50)
            Assert.assertEquals(
                NETWORK_ERROR,
                (resource as Resource.Error).cause
            )
        }

}