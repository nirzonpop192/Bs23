package com.example.bs23.util

import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class NetworkManagerTest{
    @Test
    fun testConnectionWithValidContext(){

        val context = InstrumentationRegistry.getInstrumentation().context
        val result = NetworkManager.isNetConnectionAvailable(context)

        assertThat(result).isTrue()
    }

    @Test
    fun testConnectionWithInvalidContext(){
        val result = NetworkManager.isNetConnectionAvailable(null)
        assertThat(result).isFalse()
    }
}