package com.example.contacts_app.feature_contacts.domain.use_case

import android.content.Context
import com.example.contacts_app.core.Resource
import com.example.contacts_app.feature_contacts.domain.model.Contacts
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class GetContacts {
    lateinit var jsonString: String
    operator fun invoke(context: Context): Resource<Contacts> {
        try {
            jsonString = context.assets.open("contacts.json")
                .bufferedReader()
                .use{it.readText()}

        } catch (ioException: IOException) {
            ioException.printStackTrace()
            // message you need to send
            return Resource.Error("Could not get Contacts")
        }
        val contacts = object : TypeToken<Contacts>() {}.type
        return Resource.Success(Gson().fromJson(jsonString, contacts))
    }
}