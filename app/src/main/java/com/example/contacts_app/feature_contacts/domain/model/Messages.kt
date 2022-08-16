package com.example.contacts_app.feature_contacts.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class Messages(
    val name: String,
    val number: String,
    val content: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null
)

class InvalidMessageException(message: String) : Exception(message)
