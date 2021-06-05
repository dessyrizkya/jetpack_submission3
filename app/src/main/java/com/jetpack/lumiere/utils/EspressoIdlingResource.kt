package com.jetpack.lumiere.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val resource = "GLOBAL"

    val idlingResource = CountingIdlingResource(resource)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}