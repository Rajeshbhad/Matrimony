package com.example.marathimatrimony

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class EditEmail : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID: String

    lateinit var currentMail: TextView
    lateinit var editEmailSaveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_email)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userID = auth.currentUser!!.uid
        docRef = db.collection("Users").document(userID)

        currentMail = findViewById(R.id.currentMail)

//        editEmailSaveBtn.setOnClickListener {
//
//        }

        currentMail()

    }

    private fun currentMail() {
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {

                    currentMail.text = snapshot.getString("email")

                }
            }
        }
    }
}