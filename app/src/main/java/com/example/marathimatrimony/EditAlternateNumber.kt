package com.example.marathimatrimony

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteAlternateNumber
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class EditAlternateNumber : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String
    lateinit var editParentContactNumber:EditText
    lateinit var alternateNumberSaveBtn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_alternate_number)

        editParentContactNumber = findViewById(R.id.editParentContactNumber)
        alternateNumberSaveBtn = findViewById(R.id.alternateNumberSaveBtn)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userID = auth.currentUser!!.uid
        docRef = db.collection("Users").document(userID)


        alternateNumberSaveBtn.setOnClickListener {
            save()
        }


    }

    private fun save() {
        val alternateNumber=editParentContactNumber.text.toString().trim()

        if (editParentContactNumber.text.trim().toString().isEmpty())
        {
            editParentContactNumber.error = "Please Enter AlternateNumber "
            editParentContactNumber.requestFocus()
            return
        }
        val write= WriteAlternateNumber(alternateNumber)
        docRef.set(write, SetOptions.merge()).addOnCompleteListener{
            if (it.isSuccessful)
            {
                Toast.makeText(this, "Save Successfully.", Toast.LENGTH_SHORT).show()
                finish()
            }
            else
            {
                Toast.makeText(this, "Save Failed.", Toast.LENGTH_SHORT).show()

            }
        }
    }
}