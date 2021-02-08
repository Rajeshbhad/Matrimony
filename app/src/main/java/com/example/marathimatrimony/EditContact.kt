package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class EditContact : AppCompatActivity() {

    private lateinit var edit_mail_card_view : LinearLayout
    private lateinit var phone_number_card_view : LinearLayout
    private lateinit var alternate_phone_card_view : LinearLayout
    private lateinit var contact_preferences_card_view : LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        edit_mail_card_view = findViewById(R.id.edit_mail_card_view)
        phone_number_card_view = findViewById(R.id.phone_number_card_view)
        alternate_phone_card_view = findViewById(R.id.alternate_phone_card_view)
        contact_preferences_card_view = findViewById(R.id.contact_preferences_card_view)

        edit_mail_card_view.setOnClickListener {
            val intent = Intent(this, EditEmail::class.java)
            startActivity(intent)
        }
        phone_number_card_view.setOnClickListener {
            val intent = Intent(this, EditPhoneNumber::class.java)
            startActivity(intent)
        }

        alternate_phone_card_view.setOnClickListener {
            val intent = Intent(this, EditAlternateNumber::class.java)
            startActivity(intent)
        }

        contact_preferences_card_view.setOnClickListener {
            val intent = Intent(this, EditContactPreferences::class.java)
            startActivity(intent)
        }
    }
}