package com.example.contacts_app.feature_contacts.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.contacts_app.R
import com.example.contacts_app.core.Constants
import com.example.contacts_app.feature_contacts.presentation.adapters.ViewPagerAdapter
import com.example.contacts_app.feature_contacts.presentation.contacts.fragments.ContactFragment
import com.example.contacts_app.feature_contacts.presentation.messages.fragments.MessageFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var tabs: TabLayout
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabs = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.viewPager)

        requestSmsPermission()
        setTabs()
    }

    private fun requestSmsPermission() {
        // check permission is given
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // request permission (see result in onRequestPermissionsResult() method)
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.SEND_SMS),
                Constants.PERMISSION_SEND_SMS
            )
        }
    }

    private fun setTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ContactFragment(), "")
        adapter.addFragment(MessageFragment(), "")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_contacts)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_message_24)
    }
}