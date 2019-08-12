package com.example.srijithn.understandingcoroutines

sealed class Result<T>

class Success<T>(val value: T): Result<T>()

class Failure<T>(val t: Throwable): Result<T>()