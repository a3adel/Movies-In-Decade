package com.example.movies.domain.useCases

import com.example.movies.domain.dataSources.PhotosRepo
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class SearchPhotosUseCaseTest{
    lateinit var SUT:SearchPhotosUseCase
    lateinit var repo: PhotosRepo

    @Before
    fun setup(){
        repo = mockk(relaxed = true)
        SUT = SearchPhotosUseCase(repo)
    }

    @Test
    fun `search photos in repo should be called once when searchPhoto in usecase is called`(){
        SUT.searchPhotos("")
        verify (exactly = 1){ repo.searchPhotos("") }
    }
}