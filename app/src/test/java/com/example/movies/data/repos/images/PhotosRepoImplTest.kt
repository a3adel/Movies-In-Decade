package com.example.movies.data.repos.images

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

internal class PhotosRepoImplTest {
    lateinit var SUT: PhotosRepoImpl
    lateinit var remoteClient: PhotosPagingSource

    @Before
    fun setup() {
        remoteClient = mockk(relaxed = true)
        SUT = PhotosRepoImpl(remoteClient)
    }

    @Test
    fun `search for photos should call remote client search function`() = runBlockingTest {

        SUT.searchPhotos("")
        coVerify(exactly = 1) { remoteClient.setQuery("") }

    }

}