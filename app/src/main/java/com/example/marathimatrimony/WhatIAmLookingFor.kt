package com.example.marathimatrimony

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteWhatIAmLookingFor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class WhatIAmLookingFor : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID: String

    lateinit var editWhatIAmLooking: EditText
    lateinit var whatIAmLookingForBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_what_i_am_looking_for)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

        editWhatIAmLooking=findViewById(R.id.editWhatIAmLooking)
        whatIAmLookingForBtn=findViewById(R.id.whatIAmLookingForBtn)

        whatIAmLookingForBtn.setOnClickListener {
            save()
        }

    }

    private fun save() {
        val iAmLookingFor = editWhatIAmLooking.text.toString().trim()

        if (editWhatIAmLooking.toString().isEmpty()) {
            editWhatIAmLooking.error = "Please Enter In MyOwnWords"
            editWhatIAmLooking.requestFocus()
            return
        }
        userID = auth.currentUser!!.uid
        docRef = db.collection("Users").document(userID)
        val write = WriteWhatIAmLookingFor(iAmLookingFor)
        docRef.set(write, SetOptions.merge()).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Save Successfully.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Save Failed.", Toast.LENGTH_SHORT).show()

            }
        }
    }
}