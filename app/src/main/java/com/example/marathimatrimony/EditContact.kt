package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class EditContact : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID: String

    lateinit var emailAddress: TextView
    lateinit var phoneNumber: TextView
    lateinit var alternateContactNumber: TextView
    lateinit var whomToContact: TextView
    lateinit var contactPersonName: TextView
    lateinit var availableTimeToCall: TextView
    lateinit var comments: TextView

    private lateinit var edit_mail_card_view : LinearLayout
    private lateinit var phone_number_card_view : LinearLayout
    private lateinit var alternate_phone_card_view : LinearLayout
    private lateinit var contact_preferences_card_view : LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userID = auth.currentUser!!.uid
        docRef = db.collection("Users").document(userID)

        emailAddress = findViewById(R.id.emailAddress)
        phoneNumber = findViewById(R.id.phoneNumber)
        alternateContactNumber = findViewById(R.id.alternateContactNumber)
        whomToContact = findViewById(R.id.whomToContact)
        contactPersonName = findViewById(R.id.contactPersonName)
        availableTimeToCall = findViewById(R.id.availableTimeToCall)
        comments = findViewById(R.id.comments)

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
        show()
    }
    private fun show() {
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {

                    emailAddress.text = snapshot.getString("email")
                    phoneNumber.text  = snapshot.getString("phoneNumber")
                    alternateContactNumber.text  = snapshot.getString("alternateNumber")
                    whomToContact.text = snapshot.getString("whomToContact")
                    contactPersonName.text = snapshot.getString("contactPersonName")
                    availableTimeToCall.text = snapshot.getString("availableTimeToCall")
                    comments.text = snapshot.getString("comments")
                }
            }
        }
    }
}