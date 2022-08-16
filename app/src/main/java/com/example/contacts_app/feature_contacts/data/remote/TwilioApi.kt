package com.example.contacts_app.feature_contacts.data.remote

import android.os.Message
import com.example.contacts_app.core.Constants
import com.example.contacts_app.feature_contacts.domain.model.Contacts
import com.example.contacts_app.feature_contacts.domain.model.Messages
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface TwilioApi {


    @FormUrlEncoded
    @POST("{acc_sid}/Messages")
    fun sendMessage(@Path(value = "acc_sid") acc_sid: String, @Header("Authorization") auth: String, @FieldMap message: Map<String, String>): Call<ResponseBody>


    companion object {

        private val interceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor) // same for .addInterceptor(...)
            .build()


        operator fun invoke(): TwilioApi {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TwilioApi::class.java)
        }
    }
}