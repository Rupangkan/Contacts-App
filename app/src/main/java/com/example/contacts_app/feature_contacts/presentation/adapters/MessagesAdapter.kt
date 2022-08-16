package com.example.contacts_app.feature_contacts.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_app.R
import com.example.contacts_app.feature_contacts.domain.model.Messages
import java.sql.Timestamp
import java.util.*

class MessagesAdapter(private val context: Context, private val messages: List<Messages>): RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.name)
        val timeStamp: TextView = view.findViewById(R.id.timestamp)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesAdapter.ViewHolder {
        var adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.contact_single_messages, parent, false)
        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MessagesAdapter.ViewHolder, position: Int) {
        val item = messages[position]
        holder.name.text = item.content
        val stamp = Timestamp(item.timestamp)
        val date = Date(stamp.time)
        holder.timeStamp.text = date.toString()
    }

    override fun getItemCount(): Int {
        return messages.size
    }

}