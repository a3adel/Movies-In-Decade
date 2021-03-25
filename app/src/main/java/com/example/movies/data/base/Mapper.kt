package com.example.movies.data.base

abstract class Mapper<T, E> {
    abstract fun mapFrom(from: T): E
}