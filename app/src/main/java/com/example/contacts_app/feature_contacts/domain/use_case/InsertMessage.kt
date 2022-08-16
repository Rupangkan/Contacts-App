package com.example.contacts_app.feature_contacts.domain.use_case

import com.example.contacts_app.core.Constants
import com.example.contacts_app.core.Resource
import com.example.contacts_app.feature_contacts.domain.model.Messages
import com.example.contacts_app.feature_contacts.domain.repository.MessageRepository
import java.lang.Exception

class InsertMessage(
    private val repository: MessageRepository
) {
    suspend operator fun invoke(message: Messages): Resource<Unit> {
        return try {
            repository.insertMessage(message)
            Resource.Success(data = Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "There was a problem storing the message!")
        }
    }
}