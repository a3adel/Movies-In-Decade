package com.example.movies.data.remote

object ApiConstants {
    const val BASE_URL = "https://api.flickr.com/services/rest"
    const val METHOD_QUERY_KEY = "method"
    const val METHOD_QUERY_VALUE = "flickr.photos.search"

    const val API_QUERY_KEY = "api_key"

    const val FORMAT_QUERY_KEY = "format"
    const val FORMAT_QUERY_VALUE = "json"

    const val NO_JSON_CALL_BACK_QUERY_KEY = "nojsoncallback"
    const val NO_JSON_CALL_BACK_QUERY_VALUE = "1"

    const val SEARCH_QUERY_KEY="text"

    const val PAGE_QUERY_KEY = "page"

    const val PER_PAGE_QUERY_KEY= "per_page"
    const val PER_PAGE_QUERY_VALUE= "10"


}
