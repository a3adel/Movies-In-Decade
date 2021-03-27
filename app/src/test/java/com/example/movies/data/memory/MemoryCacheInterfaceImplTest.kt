package com.example.movies.data.memory

import com.example.movies.Values
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class MemoryCacheInterfaceImplTest{
    lateinit var SUT:MemoryCacheInterface
    @Before
    fun setup(){
        SUT = MemoryCacheInterfaceImpl()
    }

    @Test
    fun `pass movies should set movies  in memory`(){
        SUT.setMovies(Values.UN_SORTED_RATING_MOVIES)
        assertEquals(SUT.getMovies(),Values.UN_SORTED_RATING_MOVIES)
    }

    @Test
    fun `clear movies from memory should st its list to null`(){
        SUT.destroyMovies()
        assertNull(SUT.getMovies())
    }
}