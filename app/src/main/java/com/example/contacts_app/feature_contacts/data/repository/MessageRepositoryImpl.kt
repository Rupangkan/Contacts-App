package com.example.contacts_app.feature_contacts.data.repository

import com.example.contacts_app.feature_contacts.data.data_source.MessageDao
import com.example.contacts_app.feature_contacts.domain.model.Messages
import com.example.contacts_app.feature_contacts.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow

class MessageRepositoryImpl(
    private val dao: MessageDao
): MessageRepository {

    override fun getMessages(): Flow<List<Messages>> {
        return dao.getMessages()
    }

    override suspend fun insertMessage(message: Messages) {
        dao.insertMessage(message)
    }

    override suspend fun updateMessage(content: String, contact: String) {
        dao.updateMessage(content, contact)
    }

    override suspend fun getSingleContactMessages2(contact: String): List<Messages> {
        return dao.getSingleContactMessages2(contact)
    }


}