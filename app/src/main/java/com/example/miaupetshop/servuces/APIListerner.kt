package com.example.miaupetshop.servuces

interface APIListener<T> {
    fun onSuccess(result: T, statusCode: Int)
    fun onFailure(message: String)
}