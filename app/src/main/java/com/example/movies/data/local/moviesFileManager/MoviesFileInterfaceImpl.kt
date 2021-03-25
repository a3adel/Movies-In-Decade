package com.example.movies.data.local.moviesFileManager

import com.example.movies.data.entities.MovieEntity
import com.example.movies.data.entities.MoviesEntity
import com.example.movies.data.local.fileReader.FileReader
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import java.io.FileNotFoundException
import java.io.IOException
import javax.inject.Inject

/**
 * MoviesFileManagerImpl :- Its purpose is to get the data from the local source and parse it to the entity form
 * The local source and the Parsing technique are injected in the constructor of the class
 */


class MoviesFileInterfaceImpl @Inject constructor(
    private val moshi: Moshi,
    private val fileReader: FileReader
) : MoviesFileInterface {
    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun getMovies(): FileResponse<List<MovieEntity>> =
        try {
            val text = fileReader.getTextFromAssetFile("movies.json")
            val moviesEntity = moshi.adapter(MoviesEntity::class.java).fromJson(text)
            moviesEntity?.movies?.let {
                FileResponse.FileText(moviesEntity.movies)
            } ?: kotlin.run {
                FileResponse.Error("there are no movies in the set", Causes.NULL_EXCEPTION)
            }
        } catch (exc: FileNotFoundException) {
            exc.message?.let { FileResponse.Error(it, Causes.FILE_NOT_FOUND) } ?: run {
                FileResponse.Error("file not found", Causes.FILE_NOT_FOUND)
            }
        } catch (exc: JsonDataException) {
            exc.message?.let { FileResponse.Error(it, Causes.PARSE_EXCEPTION) } ?: kotlin.run {
                FileResponse.Error("json parsing error", Causes.PARSE_EXCEPTION)
            }
        } catch (exc: IOException) {
            exc.message?.let { FileResponse.Error(it, Causes.IO_EXCEPTION) } ?: kotlin.run {
                FileResponse.Error("json parsing error", Causes.IO_EXCEPTION)
            }
        }


}