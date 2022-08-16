package com.example.contacts_app.feature_contacts.domain.use_case

data class MessageUseCases(
    val getMessages: GetMessages,
    val insertMessage: InsertMessage,
    val sendMessage: SendMessage,
    val getContactMessages: GetContactMessages,
)