package templa.master.templateproject.util.extension

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken

fun Any.toJson(): String {
    val gson: Gson = GsonBuilder()
            //allow serialize 'NaN' for double/float
            .serializeSpecialFloatingPointValues()
            .create()
    return gson.toJson(this)
}

inline fun <reified T> String.fromJson(): T = Gson().fromJson(this, object : TypeToken<T>() {}.type)

inline fun <reified T> Any?.fromRawToList(): List<T> {
    if (this == null) return emptyList()

    if (this is LinkedTreeMap<*, *>) {
        //means list {} is empty
        return emptyList()
    }

    val listOfMaps = this as List<LinkedTreeMap<*, *>>
    val listOfJsons = mutableListOf<String>()
    listOfMaps.forEach { listOfJsons.add(it.toJson()) }
    val result = arrayListOf<T>()
    listOfJsons.forEach { result.add(it.fromJson()) }
    return result
}

fun String.fromJsonToMap(): Map<String, Any> {
    var result = mutableMapOf<String, Any>()
    result = Gson().fromJson(this, result.javaClass)
    return result
}