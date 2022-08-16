package com.example.contacts_app.feature_contacts.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contacts_app.feature_contacts.domain.model.Messages

@Database(
    entities = [Messages::class],
    version = 1
)
abstract class MessageDatabase: RoomDatabase() {
    abstract val messageDao: MessageDao

    companion object {
        const val DATABASE_NAME = "messages_db"
    }
}