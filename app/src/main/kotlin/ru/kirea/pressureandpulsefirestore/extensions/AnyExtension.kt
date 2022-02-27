package ru.kirea.pressureandpulsefirestore.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/** Преобразовать объект к мапе */
fun Any.toMapForStore(): Map<String, Any> {
    val gson = Gson()
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
}
