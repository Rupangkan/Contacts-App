
package com.example.contacts_app.feature_contacts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_app.R
import com.example.contacts_app.feature_contacts.domain.model.Messages
import com.example.contacts_app.feature_contacts.presentation.adapters.MessagesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesActivity : AppCompatActivity() {
    private lateinit var name: TextView
    private lateinit var contact: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        val sharedViewModel: AndroidViewModel by viewModels()

        val phone = intent.extras?.get("phone").toString()
        val contactName = intent.extras?.get("name").toString()

        name = findViewById(R.id.name)
        contact = findViewById(R.id.contactNo)
        recyclerView = findViewById(R.id.recyclerView)

        name.text = contactName
        contact.text = phone

        setUpRecyclerView(sharedViewModel, phone)
    }

    private fun setUpRecyclerView(sharedViewModel: AndroidViewModel, phone: String) {
        val layoutManager =  LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager
        var result = arrayListOf<Messages>()
        sharedViewModel.getContactMessages(phone)
        sharedViewModel.contactMessages.observe(this) {
            recyclerView.adapter = MessagesAdapter(applicationContext, it!!)
            Log.d("ContactMessageActivity", "setUpRecyclerView: $it")
            result = it as ArrayList<Messages>
        }
        Log.d("ContactMessageActivity", "setUpRecyclerView: $result")
//        recyclerView.adapter = MessagesAdapter(applicationContext, result.value!!)
    }

}