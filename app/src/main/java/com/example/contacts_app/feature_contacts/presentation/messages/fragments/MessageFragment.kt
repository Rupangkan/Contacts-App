package com.example.contacts_app.feature_contacts.presentation.messages.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_app.R
import com.example.contacts_app.databinding.FragmentContactBinding
import com.example.contacts_app.databinding.FragmentMessageBinding
import com.example.contacts_app.feature_contacts.domain.model.Contacts
import com.example.contacts_app.feature_contacts.presentation.AndroidViewModel
import com.example.contacts_app.feature_contacts.presentation.adapters.MessageListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment : Fragment() {

    private val TAG = "ReposeMessageFragment"
    private var binding: FragmentMessageBinding? = null
    private lateinit var recyclerView: RecyclerView
    private val sharedViewModel: AndroidViewModel by activityViewModels()
    private lateinit var errorMessage: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentMessageBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.messageFragment = this
        errorMessage = binding?.errorMessage!!

        sharedViewModel.getMessages()
        if(sharedViewModel.messages != null) {
            val messages = sharedViewModel.messages!!
            recyclerView = binding?.messageList!!
//            recyclerView.layoutManager = GridLayoutManager(context, 1)
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            sharedViewModel.messages.observe(viewLifecycleOwner) {
                Log.d(TAG, "onViewCreated: ${messages.value} ${messages.value?.size} ")
                recyclerView.adapter = context?.let { MessageListAdapter(it, messages.value!!) }
            }
            recyclerView.setHasFixedSize(true)

        }
        else if(sharedViewModel.errorMessages != null) {
            errorMessage.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            errorMessage.text  = sharedViewModel.errorMessages
        }

    }

}