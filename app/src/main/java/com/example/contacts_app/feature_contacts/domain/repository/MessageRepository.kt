package com.example.contacts_app.feature_contacts.domain.repository

import com.example.contacts_app.feature_contacts.domain.model.Messages
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    fun getMessages(): Flow<List<Messages>>

    suspend fun insertMessage(message: Messages)

    suspend fun updateMessage(content: String, contact: String)

    suspend fun getSingleContactMessages2(contact: String): List<Messages>

}