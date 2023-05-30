package com.example.qwerty

/*
Just for long ext function instead of collection average ext function
 */
fun ArrayList<Long>.calculateAverage(): Long {
    if (isEmpty()) {
        return 0L
    }

    val sum = sum()
    return sum / size
}