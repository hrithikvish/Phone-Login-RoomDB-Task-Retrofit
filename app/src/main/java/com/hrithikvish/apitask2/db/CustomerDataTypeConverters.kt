package com.hrithikvish.apitask2.db

import androidx.room.TypeConverter
import com.google.gson.Gson

class CustomerDataTypeConverters {
    @TypeConverter
    fun fromObjectToString(obj: Any?): String? {
        return Gson().toJson(obj)
    }

    @TypeConverter
    fun fromStringToObject(json: String?): Any? {
        return Gson().fromJson(json, Any::class.java)
    }
}