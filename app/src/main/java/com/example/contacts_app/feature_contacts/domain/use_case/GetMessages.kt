package com.example.contacts_app.feature_contacts.domain.use_case

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.contacts_app.core.Resource
import com.example.contacts_app.feature_contacts.domain.model.Messages
import com.example.contacts_app.feature_contacts.domain.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.net.HttpRetryException

class GetMessages(
    private val repository: MessageRepository
) {
    operator fun invoke(): Resource<Flow<List<Messages>>> {
        try {
            val result = repository.getMessages()
//            Resource.Success(result)
//            return result
            return Resource.Success(result)

        } catch (e: Exception) {
//                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred!"))
            e.printStackTrace()
            return Resource.Error(e.localizedMessage ?: "Error while getting messages!")

//            return emptyFlow()
        }

    }
}