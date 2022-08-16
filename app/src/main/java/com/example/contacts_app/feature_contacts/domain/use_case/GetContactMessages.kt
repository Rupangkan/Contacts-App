package com.example.contacts_app.feature_contacts.domain.use_case

import android.util.Log
import androidx.lifecycle.asLiveData
import com.example.contacts_app.core.Constants
import com.example.contacts_app.core.Resource
import com.example.contacts_app.feature_contacts.domain.model.Messages
import com.example.contacts_app.feature_contacts.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class GetContactMessages(
    private val repository: MessageRepository
) {
    suspend operator fun invoke(phone: String): Resource<List<Messages>> {
        try {
//            val result = repository.getSingleContactMessages(phone)
//            val check = result.asLiveData()
//            Log.d("Check", "invoke: ${check.value}")
            val result = repository.getSingleContactMessages2(phone)
            return Resource.Success(result)
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: Constants.FAILURE)
        }
    }
}