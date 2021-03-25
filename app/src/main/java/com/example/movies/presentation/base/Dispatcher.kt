package com.example.movies.presentation.base

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    fun main(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}