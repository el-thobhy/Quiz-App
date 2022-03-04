package com.elthobhy.quizkuy.repository

import android.content.Context
import com.elthobhy.quizkuy.model.Content
import com.elthobhy.quizkuy.model.Contents
import com.google.gson.Gson
import java.io.IOException

object Repository {

    fun getData(context: Context?): List<Contents>?{
        val json: String?
        try {
            val inputStream = context?.assets?.open("content/content.json")
            json = inputStream?.bufferedReader().use { it?.readText() }
        }catch (e: IOException){
            return null
        }
        val contents = Gson().fromJson(json, Content::class.java)
        return contents.contents

    }
}