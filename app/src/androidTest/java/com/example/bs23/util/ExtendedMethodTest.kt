package com.example.bs23.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class ExtendedMethodTest {
    @Test
    fun dateFormatWithValidInput(){
        val formattedDate = ExtendedMethod.formatDateTime("2023-12-01T12:45:09Z")
        val result :Boolean = formattedDate?.length!! >6

        assertThat(result).isTrue()
    }

    @Test
    fun dateFormatWithInValidInput(){
        val formattedDate = ExtendedMethod.formatDateTime(null)
        val result :Boolean = formattedDate?.length!! >6

        assertThat(result).isFalse()
    }
}