package com.example.marathimatrimony

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteInMyOwnWords
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class InMyOwnWords : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String

    lateinit var editInMyOwnWords : EditText
    lateinit var inMyOwnWordSaveBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_my_own_words)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

        editInMyOwnWords=findViewById(R.id.editInMyOwnWords)
        inMyOwnWordSaveBtn=findViewById(R.id.inMyOwnWordSaveBtn)

        inMyOwnWordSaveBtn.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val inMyOwnWords = editInMyOwnWords.text.toString().trim()

        if (editInMyOwnWords.toString().isEmpty()) {
            editInMyOwnWords.error = "Please Enter In MyOwnWords"
            editInMyOwnWords.requestFocus()
            return
        }
        userID = auth.currentUser!!.uid
        docRef = db.collection("Users").document(userID)
        val write = WriteInMyOwnWords(inMyOwnWords)
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