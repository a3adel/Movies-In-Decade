package com.example.movies.data.local.moviesFileManager

import com.example.movies.data.entities.MoviesEntity
import com.example.movies.data.local.fileReader.FileReader
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test
import java.io.FileNotFoundException

class MoviesFileInterfaceImplTest {
    val jsonValue = "   {\"movies\":[{\n" +
            "            \"title\": \"Destroyer\",\n" +
            "            \"year\": 2018,\n" +
            "            \"cast\": [\n" +
            "                \"Nicole Kidman\",\n" +
            "                \"Tatiana Maslany\",\n" +
            "                \"Sebastian Stan\",\n" +
            "                \"Toby Kebbell\",\n" +
            "                \"Scoot McNairy\"\n" +
            "            ],\n" +
            "            \"genres\": [\n" +
            "                \"Crime\",\n" +
            "                \"Thriller\"\n" +
            "            ],\n" +
            "            \"rating\": 2\n" +
            "        }\n" +
            "    ]\n" +
            "}"

    @Test
    fun test_get_movies_from_file_return_success()= runBlocking {
        val moshi = Moshi.Builder().build()
        val fileReader = mockk<FileReader>()
        every { fileReader.getTextFromAssetFile("movies.json") }.returns(jsonValue)
        val SUT = MoviesFileInterfaceImpl(moshi, fileReader)
        MatcherAssert.assertThat("", SUT.getMovies() is FileResponse.FileText)
        Assert.assertEquals((SUT.getMovies() as FileResponse.FileText).data.size, 1)
    }

    @Test
    fun test_get_movies_return_no_file()= runBlocking {
        val moshi = Moshi.Builder().build()
        val fileReader = mockk<FileReader>()
        try {
            every { fileReader.getTextFromAssetFile("") }.throws(FileNotFoundException(""))

            val SUT = MoviesFileInterfaceImpl(moshi, fileReader)
            MatcherAssert.assertThat("", SUT.getMovies() is FileResponse.Error)
            Assert.assertEquals((SUT.getMovies() as FileResponse.Error).cause, "file not found")
        } catch (exc: Exception) {
        }
    }

    @Test
    fun test_get_movies_return_null_array()= runBlocking {
        val moshi = mockk<Moshi>()
        val fileReader = mockk<FileReader>()

        every { moshi.adapter(MoviesEntity::class.java).fromJson(jsonValue) }.returns(null)
        every { fileReader.getTextFromAssetFile("movies.json") }.returns(jsonValue)
        val SUT = MoviesFileInterfaceImpl(moshi, fileReader)
        MatcherAssert.assertThat("", SUT.getMovies() is FileResponse.Error)
        Assert.assertEquals((SUT.getMovies() as FileResponse.Error).cause, "NullPointerException")

    }

    @Test
    fun test_get_movies_return_wrong_parse() = runBlocking{
        val moshi = mockk<Moshi>()
        val fileReader = mockk<FileReader>()

        every { moshi.adapter(MoviesEntity::class.java).fromJson(jsonValue) }.throws(
            JsonDataException()
        )
        try {


            every { fileReader.getTextFromAssetFile("movies.json") }.returns(jsonValue)
            val SUT = MoviesFileInterfaceImpl(moshi, fileReader)
            MatcherAssert.assertThat("", SUT.getMovies() is FileResponse.Error)
            Assert.assertEquals(
                (SUT.getMovies() as FileResponse.Error).cause,
                "JsonDataException"
            )
        } catch (exc: Exception) {
        }
    }
}