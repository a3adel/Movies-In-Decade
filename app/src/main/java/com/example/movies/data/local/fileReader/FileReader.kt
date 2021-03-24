package com.example.movies.data.local.fileReader

import com.example.movies.data.local.moviesFileManager.FileResponse

interface FileReader {
    fun getTextFromAssetFile(fileName:String): String
}