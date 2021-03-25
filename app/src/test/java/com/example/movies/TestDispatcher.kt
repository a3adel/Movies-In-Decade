package com.example.movies

import com.example.movies.presentation.base.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestDispatcher : Dispatcher {
    override fun main(): CoroutineDispatcher = TestCoroutineDispatcher()

    override fun io(): CoroutineDispatcher = TestCoroutineDispatcher()

    override fun default(): CoroutineDispatcher = TestCoroutineDispatcher()
}