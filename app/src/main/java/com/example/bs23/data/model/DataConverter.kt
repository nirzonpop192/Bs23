package com.example.bs23.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class DataConverter {
    @TypeConverter
    fun fromLicenseList(license: List<License?>?): String? {
        if (license == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<License?>?>() {}.type
        return gson.toJson(license, type)
    }

    @TypeConverter
    fun toLicenseList(countryLangString: String?): List<License>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<License?>?>() {}.type
        return gson.fromJson<List<License>>(countryLangString, type)
    }

    @TypeConverter
    fun fromOwnerList(owner: List<Owner?>?): String? {
        if (owner == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Owner?>?>() {}.type
        return gson.toJson(owner, type)
    }

    @TypeConverter
    fun toOwnerList(owner: String?): List<Owner>? {
        if (owner == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<License?>?>() {}.type
        return gson.fromJson<List<Owner>>(owner, type)
    }
}