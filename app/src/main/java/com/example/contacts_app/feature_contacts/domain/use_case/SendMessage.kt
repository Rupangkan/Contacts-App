package com.example.contacts_app.feature_contacts.domain.use_case

import android.R
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.util.Base64
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.contacts_app.core.Constants
import com.example.contacts_app.core.Resource
import com.example.contacts_app.feature_contacts.data.remote.TwilioApi
import com.example.contacts_app.feature_contacts.domain.model.Messages
import okhttp3.ResponseBody
import okio.internal.commonAsUtf8ToByteArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class SendMessage {
    operator fun invoke( phone: String, body: String, name: String): Resource<Messages> {
        return try {
            val smsManager: SmsManager = SmsManager.getDefault()
            // on below line we are sending text message.
            smsManager.sendTextMessage(phone, null, body, null, null)
            val timestamp = System.currentTimeMillis()
            Resource.Success(
                Messages(name, phone, body, timestamp)
            )

        } catch (e: IOException) {
            Resource.Error(e.localizedMessage ?: "Check your internet connection!")
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "There occurred an error while sending the message!")
        }

    }

}