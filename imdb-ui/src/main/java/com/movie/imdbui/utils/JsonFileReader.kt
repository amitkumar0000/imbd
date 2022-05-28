package com.movie.imdbui.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonFileReader {
    inline fun<reified T> getObjectFromJsonFile(context: Context, fileName: String): T? {
        var obj: T? = null
        try {
            val inputStream = context.assets.open(fileName)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()

            obj = Gson().fromJson<T>(String(buffer, Charsets.UTF_8), object: TypeToken<T>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return obj
    }
}