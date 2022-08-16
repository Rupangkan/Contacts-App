package com.example.contacts_app.feature_contacts.presentation.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_app.R
import com.example.contacts_app.feature_contacts.domain.model.Contacts
import com.example.contacts_app.feature_contacts.presentation.SendMessageActivity

class ContactListAdapter(private val contacts: Contacts, private val ctx: Context): RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val first: TextView = view.findViewById(R.id.first)
        val last: TextView = view.findViewById(R.id.last)
        val card: CardView = view.findViewById(R.id.card)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactListAdapter.ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_contact, parent, false)

        return ContactListAdapter.ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ContactListAdapter.ViewHolder, position: Int) {
        val item = contacts.contacts[position]
        holder.first.text = item.first
        holder.last.text = item.last
        holder.card.setOnClickListener {
            val intent = Intent(ctx, SendMessageActivity::class.java)
            intent.putExtra("phone", item.phone)
            intent.putExtra("name", item.first + " " + item.last)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return contacts.contacts.size
    }
}