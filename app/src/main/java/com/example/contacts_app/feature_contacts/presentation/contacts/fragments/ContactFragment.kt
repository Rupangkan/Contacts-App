package com.example.contacts_app.feature_contacts.presentation.contacts.fragments

import android.widget.TextView


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_app.databinding.FragmentContactBinding
import com.example.contacts_app.feature_contacts.domain.model.Contacts
import com.example.contacts_app.feature_contacts.presentation.AndroidViewModel
import com.example.contacts_app.feature_contacts.presentation.adapters.ContactListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : Fragment() {

    private val TAG = "ReposeContactFragment"
    private var binding: FragmentContactBinding? = null
    private lateinit var recyclerView: RecyclerView
    private val sharedViewModel: AndroidViewModel by activityViewModels()
    private lateinit var contacts: Contacts
    private lateinit var errorContacts: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentContactBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.contactFragment = this
        errorContacts = binding?.errorContacts!!


        val result: Unit? = context?.let { sharedViewModel.getContacts(it) }
        if (sharedViewModel.contactsList != null) {
            contacts = sharedViewModel.contactsList!!
            recyclerView = binding?.contactList!!
            recyclerView.layoutManager = GridLayoutManager(context, 1)
            recyclerView.adapter = context?.let { ContactListAdapter(contacts, it) }
            recyclerView.setHasFixedSize(true)
        } else if(sharedViewModel.errorContacts != null ) {
            errorContacts.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            errorContacts.text  = sharedViewModel.errorContacts
        }
    }

}