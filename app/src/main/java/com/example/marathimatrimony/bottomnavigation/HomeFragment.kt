package com.example.marathimatrimony.bottomnavigation

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.marathimatrimony.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {

            private lateinit var auth: FirebaseAuth
            private lateinit var db: FirebaseFirestore
            private lateinit var docRef: DocumentReference
            private lateinit var userID:String
            private lateinit var fullName:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

            auth= FirebaseAuth.getInstance()
            db= FirebaseFirestore.getInstance()
            userID= auth.currentUser!!.uid



        val view:View =inflater.inflate(R.layout.fragment_home, container, false)
        fullName=view.findViewById(R.id.fullName)
        docRef = db.collection("Users").document(userID)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {

                fullName.text = snapshot.getString("name")
            } else {
                Log.d(TAG, "Current data: null")

            }
        }
        return view
    }
    override fun onStart() {
        super.onStart()
        docRef.addSnapshotListener{ snapshot, e ->

            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {
                    fullName.text = snapshot.getString("name")
                } else {
                }
            }
        }

    }


}


