package com.example.contacts_app.di

import android.app.Application
import androidx.room.Room

import com.example.contacts_app.feature_contacts.data.data_source.MessageDatabase
import com.example.contacts_app.feature_contacts.data.repository.MessageRepositoryImpl
import com.example.contacts_app.feature_contacts.domain.repository.MessageRepository
import com.example.contacts_app.feature_contacts.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMessageDatabase(app: Application): MessageDatabase {
        return Room.databaseBuilder(
            app,
            MessageDatabase::class.java,
            MessageDatabase.DATABASE_NAME,
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providesMessageRepository(db: MessageDatabase): MessageRepository {
        return MessageRepositoryImpl(db.messageDao)
    }

    @Provides
    @Singleton
    fun provideMessageUseCases(repository: MessageRepository): MessageUseCases {
        return MessageUseCases(
            getMessages = GetMessages(repository),
            insertMessage = InsertMessage(repository),
            sendMessage = SendMessage(),
            getContactMessages = GetContactMessages(repository)
        )
    }

}