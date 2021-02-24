package com.example.marathimatrimony

import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteLocationPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.*

class LocationPreferences : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID: String

    lateinit var lPEditCountryLivingIn: TextView
    lateinit var lPEditResidingState: TextView
    lateinit var lPEditResidingCity: TextView

    lateinit var selectedLPEditCountryLivingIn: BooleanArray
    lateinit var selectedLPEditResidingState: BooleanArray
    lateinit var selectedLPEditResidingCity: BooleanArray

    var lPEditCountryLivingInList: ArrayList<Int> = ArrayList()
    var lPEditResidingStateList: ArrayList<Int> = ArrayList()
    var lPEditResidingCityList: ArrayList<Int> = ArrayList()

    private var lPEditCountryLivingInArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var lPEditResidingStateArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday")
    private var lPEditResidingCityArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_preferences)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        lPEditCountryLivingIn = findViewById(R.id.lPEditCountryLivingIn)
        lPEditResidingState = findViewById(R.id.lPEditResidingState)
        lPEditResidingCity = findViewById(R.id.lPEditResidingCity)

        countryLivingIn()
        residingState()
        residingCity()
        save()
    }

    private fun save() {
        val lPCountryLivingIn = lPEditCountryLivingIn.text.toString().trim()
        val lPResidingState = lPEditResidingState.text.toString().trim()
        val lPResidingCity = lPEditResidingCity.text.toString().trim()

        userID = auth.currentUser!!.uid
        docRef = db.collection("Users").document(userID)
        val write = WriteLocationPreferences(lPCountryLivingIn,lPResidingState,lPResidingCity)
        docRef.set(write, SetOptions.merge()).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Save Successfully.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Save Failed.", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun countryLivingIn() {
        selectedLPEditCountryLivingIn = BooleanArray(lPEditCountryLivingInArray.size)
        lPEditCountryLivingIn.setOnClickListener {
            val builder = AlertDialog.Builder(this@LocationPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(lPEditCountryLivingInArray, selectedLPEditCountryLivingIn) { _, i, b ->
                if (b) {
                    lPEditCountryLivingInList.add(i)
                } else {
                    lPEditCountryLivingInList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until lPEditCountryLivingInList.size) {
                    stringBuilder.append(lPEditCountryLivingInArray[lPEditCountryLivingInList[j]])

                    if (j in -1 until lPEditCountryLivingInList.size) {
                        stringBuilder.append(",")
                    }
                }
                lPEditCountryLivingIn.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until lPEditCountryLivingInList.size) {
                    selectedLPEditCountryLivingIn[j] = false

                    lPEditCountryLivingInList.clear();

                    lPEditCountryLivingIn.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun residingState() {
        selectedLPEditResidingState = BooleanArray(lPEditResidingStateArray.size)
        lPEditResidingState.setOnClickListener {
            val builder = AlertDialog.Builder(this@LocationPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(lPEditResidingStateArray, selectedLPEditResidingState) { _, i, b ->
                if (b) {
                    lPEditResidingStateList.add(i)
                } else {
                    lPEditResidingStateList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until lPEditResidingStateList.size) {
                    stringBuilder.append(lPEditResidingStateArray[lPEditResidingStateList[j]])

                    if (j in -1 until lPEditResidingStateList.size) {
                        stringBuilder.append(",")
                    }
                }
                lPEditResidingState.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until lPEditResidingStateList.size) {
                    selectedLPEditResidingState[j] = false

                    lPEditResidingStateList.clear();

                    lPEditResidingState.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun residingCity() {
        selectedLPEditResidingCity = BooleanArray(lPEditResidingCityArray.size)
        lPEditResidingCity.setOnClickListener {
            val builder = AlertDialog.Builder(this@LocationPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(lPEditResidingCityArray, selectedLPEditResidingCity) { _, i, b ->
                if (b) {
                    lPEditResidingCityList.add(i)
                } else {
                    lPEditResidingCityList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until lPEditResidingCityList.size) {
                    stringBuilder.append(lPEditResidingCityArray[lPEditResidingCityList[j]])

                    if (j in -1 until lPEditResidingCityList.size) {
                        stringBuilder.append(",")
                    }
                }
                lPEditResidingCity.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until lPEditResidingCityList.size) {
                    selectedLPEditResidingCity[j] = false

                    lPEditResidingCityList.clear();

                    lPEditResidingCity.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

}