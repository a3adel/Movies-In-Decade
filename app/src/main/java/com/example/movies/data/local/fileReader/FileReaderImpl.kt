package com.example.movies.data.local.fileReader

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.FileNotFoundException
import javax.inject.Inject

class FileReaderImpl @Inject constructor(@ApplicationContext private val context: Context) :
    FileReader {
    override fun getTextFromAssetFile(fileName: String): String {
        try {

            return context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (exc: Exception) {
            throw (FileNotFoundException("No file named for the name $fileName make sure the file is in the assets folder"))
        }
    }
}