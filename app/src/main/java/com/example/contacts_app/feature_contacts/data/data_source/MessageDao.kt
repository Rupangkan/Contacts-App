package com.example.contacts_app.feature_contacts.data.data_source

import androidx.room.*
import com.example.contacts_app.feature_contacts.domain.model.Messages
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
//    @Query("select * from messages order by timestamp desc")
//    suspend fun getMessages(): List<Messages>

    @Query("select * from messages order by timestamp desc")
    fun getMessages(): Flow<List<Messages>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMessage(message: Messages)

    @Query("update messages set content = :content where number = :contact")
    suspend fun updateMessage(content: String, contact: String)


    @Query("select * from messages where number = :contact")
    suspend fun getSingleContactMessages2(contact: String): List<Messages>

//    @Query("select  from messages group by name")
//    fun getListMessages(): Flow<List<Messages>>


}