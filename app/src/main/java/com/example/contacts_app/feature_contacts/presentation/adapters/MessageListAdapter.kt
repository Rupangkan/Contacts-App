package com.example.contacts_app.feature_contacts.presentation.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_app.R
import com.example.contacts_app.feature_contacts.domain.model.Messages
import com.example.contacts_app.feature_contacts.presentation.MessagesActivity
import java.sql.Timestamp
import java.util.*

class MessageListAdapter(private val context: Context, private val messages: List<Messages>): RecyclerView.Adapter<MessageListAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val contact: TextView = view.findViewById(R.id.message)
        val timeStamp: TextView = view.findViewById(R.id.timestamp)
        val card: CardView = view.findViewById(R.id.card)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageListAdapter.ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.single_message, parent, false)
        return MessageListAdapter.ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MessageListAdapter.ViewHolder, position: Int) {
        val item = messages[position]
        holder.name.text = item.name
        holder.contact.text = item.number
        val stamp = Timestamp(item.timestamp)
        val date = Date(stamp.time)
        holder.timeStamp.text = date.toString()
        holder.card.setOnClickListener{
            val intent = Intent(context, MessagesActivity::class.java)
            intent.putExtra("phone", item.number)
            intent.putExtra("name", item.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }
}