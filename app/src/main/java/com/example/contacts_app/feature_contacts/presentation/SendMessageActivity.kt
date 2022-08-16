
package com.example.contacts_app.feature_contacts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.contacts_app.R
import com.example.contacts_app.core.Constants
import com.twilio.twiml.Body
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendMessageActivity : AppCompatActivity() {
    lateinit var phone: String
    lateinit var name: String
    lateinit var nameField: TextView
    lateinit var phoneField: TextView
    lateinit var messageField: TextView
    lateinit var sendMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)
        val sharedViewModel: AndroidViewModel by viewModels()


        nameField = findViewById(R.id.name)
        phoneField = findViewById(R.id.contactNo)
        messageField = findViewById(R.id.messageField)
        sendMessage = findViewById(R.id.sendMessage)

        phone = intent.getStringExtra("phone").toString()
        name = intent.getStringExtra("name").toString()

        nameField.text = name
        phoneField.text = phone
        messageField.text = Constants.MESSAGE + (100000..999999).random()


        try {
            sendMessage.setOnClickListener{
                sharedViewModel.sendMessage(phone, messageField.text.toString(), name)
                val sentMessage = sharedViewModel.messageSaved.value
                if (sentMessage != null) {
                    sharedViewModel.insertMessage(sentMessage)
                }
                Toast.makeText(applicationContext, sharedViewModel.sentMessage, Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}