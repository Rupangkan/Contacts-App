package com.example.contacts_app.feature_contacts.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.contacts_app.core.Constants
import com.example.contacts_app.core.Resource
import com.example.contacts_app.feature_contacts.domain.model.Contacts
import com.example.contacts_app.feature_contacts.domain.model.Messages
import com.example.contacts_app.feature_contacts.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AndroidViewModel @Inject constructor(
    private val messageUseCases: MessageUseCases,
): ViewModel() {

    var contactsList: Contacts? = null
    var errorContacts: String? = null

    private var _messageList = messageUseCases.getMessages()
    private var _messages: MutableLiveData<List<Messages>> = MutableLiveData()
    var messages = _messages


    private var _contactMessages: MutableLiveData<List<Messages>> = MutableLiveData()
    var contactMessages = _contactMessages

    private var _isLoading = MutableLiveData<Boolean>(false)

    var errorMessages: String? = null

    private val _phone: MutableLiveData<String>? = null
    var phone = _phone

    private val _messageSaved = MutableLiveData<Messages>(null)
    var messageSaved = _messageSaved

    var sentMessage: String? = null

    var insertMessage: String? = null

    init {
        getMessages()
    }


    fun getContactMessages(phone: String) {
        viewModelScope.launch {
            val result = messageUseCases.getContactMessages(phone)
            _contactMessages.value = result.data!!
        }


//        val result = messageUseCases.getContactMessages(phone)
//        _contactMessages = result.data?.asLiveData() as MutableLiveData<List<Messages>>
//        val result = messageUseCases.getContactMessages(phone)
//        val messages = result.data?.asLiveData()
//        Log.d("ContactMessages", "getContactMessages: ${messages?.value}")

    }

    fun getContacts(context: Context) {
        val store = GetContacts()

        viewModelScope.launch {
            when(val result = store.invoke(context)) {
                is Resource.Success -> {
                    contactsList = result.data!!
                }
                is Resource.Error -> {
                    errorContacts = result.message
                }
            }
        }
    }

//    fun getMessagesList() {
//        _messages = messageUseCases.getMessages().asLiveData() as MutableLiveData<List<Messages>>
//        Log.d("LiveDataCheck", "getMessages: ${_messages.value?.get(0)?.timestamp}")
//    }

    fun getMessages() {
        when(_messageList) {
            is Resource.Success -> {
                messages = _messageList.data?.asLiveData() as MutableLiveData<List<Messages>>
            }
            is Resource.Error -> {
                errorMessages = _messageList.message
            }
        }

    }

    fun sendMessage(phone: String, body: String, name: String) {
        viewModelScope.launch {
            when(val sentResult = messageUseCases.sendMessage.invoke( phone, body, name)) {
                is Resource.Success -> {
                    sentMessage = Constants.SUCCESS
                    _messageSaved.value = sentResult.data!!
                }
                is Resource.Error -> {
                    sentMessage = sentResult.message
                }
            }
        }
    }

    fun insertMessage(message: Messages) {
        viewModelScope.launch {
            when(val insertResult = messageUseCases.insertMessage.invoke(message)) {
                is Resource.Error -> {
                    insertMessage = insertResult.message
                }
                is Resource.Success -> {
                    insertMessage = Constants.SUCCESS
                }
            }
        }
    }


}