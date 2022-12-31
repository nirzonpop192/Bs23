package com.example.bs23.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class ExtendedMethodTest {
    @Test
    fun dateFormater(){
        // Pass the value to the function of RegistrationUtil class
        // since RegistrationUtil is an object/ singleton we do not need to create its object
        val formalter = ExtendedMethod.formatDateTime(
            "2023-12-01T12:45:09Z"
        )
        val result :Boolean = formalter?.length!! >5
        // assertThat() comes from the truth library that we added earlier
        // put result in it and assign the boolean that it should return
        assertThat(result).isTrue()
    }
}