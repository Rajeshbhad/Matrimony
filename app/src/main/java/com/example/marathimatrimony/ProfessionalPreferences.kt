package com.example.marathimatrimony

import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteProfessionalPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.*

class ProfessionalPreferences : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String

    lateinit var pPEditEducation: TextView
    lateinit var pPEditEmployedIn: TextView
    lateinit var pPEditOccupation: TextView
    lateinit var pPEditAnnualIncome: TextView

    lateinit var selectedPPEditEducation: BooleanArray
    lateinit var selectedPPEditEmployedIn: BooleanArray
    lateinit var selectedPPEditOccupation: BooleanArray
    lateinit var selectedPPEditAnnualIncome: BooleanArray

    var pPEditEducationList: ArrayList<Int> = ArrayList()
    var pPEditEmployedInList: ArrayList<Int> = ArrayList()
    var pPEditOccupationList: ArrayList<Int> = ArrayList()
    var pPEditAnnualIncomeList: ArrayList<Int> = ArrayList()

    private var pPEditEducationArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var pPEditEmployedInArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday")
    private var pPEditOccupationArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var pPEditAnnualIncomeArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_preferences)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()



        pPEditEducation = findViewById(R.id.pPEditEducation)
        pPEditEmployedIn = findViewById(R.id.pPEditEmployedIn)
        pPEditOccupation= findViewById(R.id.pPEditOccupation)
        pPEditAnnualIncome = findViewById(R.id.pPEditAnnualIncome)

           save()
        education()
        employedIn()
        occupation()
        annualIncome()
    }
    private fun save()
    {
        val pPEditEducation = pPEditEducation.text.toString().trim()
        val pPEditEmployedIn = pPEditEmployedIn.text.toString().trim()
        val pPEditOccupation = pPEditOccupation.text.toString().trim()
        val pPEditAnnualIncome = pPEditAnnualIncome.text.toString().trim()

        userID= auth.currentUser!!.uid
        docRef=db.collection("Users").document(userID)
        val write= WriteProfessionalPreferences(pPEditEducation,pPEditEmployedIn,pPEditOccupation,pPEditAnnualIncome)
        docRef.set(write, SetOptions.merge()).addOnCompleteListener{
            if (it.isSuccessful)
            {
                Toast.makeText(this, "Save Successfully.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Save Failed.", Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun education() {
        selectedPPEditEducation = BooleanArray(pPEditEducationArray.size)
        pPEditEducation.setOnClickListener {
            val builder = AlertDialog.Builder(this@ProfessionalPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(pPEditEducationArray, selectedPPEditEducation) { _, i, b ->
                if (b) {
                    pPEditEducationList.add(i)
                } else {
                    pPEditEducationList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until pPEditEducationList.size) {
                    stringBuilder.append(pPEditEducationArray[pPEditEducationList[j]])

                    if (j in -1 until pPEditEducationList.size) {
                        stringBuilder.append(",")
                    }
                }
                pPEditEducation.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until pPEditEducationList.size) {
                    selectedPPEditEducation[j] = false

                    pPEditEducationList.clear();

                    pPEditEducation.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun employedIn() {
        selectedPPEditEmployedIn = BooleanArray(pPEditEmployedInArray.size)
        pPEditEmployedIn.setOnClickListener {
            val builder = AlertDialog.Builder(this@ProfessionalPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(pPEditEmployedInArray, selectedPPEditEmployedIn) { _, i, b ->
                if (b) {
                    pPEditEmployedInList.add(i)
                } else {
                    pPEditEmployedInList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until pPEditEmployedInList.size) {
                    stringBuilder.append(pPEditEmployedInArray[pPEditEmployedInList[j]])

                    if (j in -1 until pPEditEmployedInList.size) {
                        stringBuilder.append(",")
                    }
                }
                pPEditEmployedIn.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until pPEditEmployedInList.size) {
                    selectedPPEditEmployedIn[j] = false

                    pPEditEmployedInList.clear();

                    pPEditEmployedIn.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun occupation() {
        selectedPPEditOccupation = BooleanArray(pPEditOccupationArray.size)
        pPEditOccupation.setOnClickListener {
            val builder = AlertDialog.Builder(this@ProfessionalPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(pPEditOccupationArray, selectedPPEditOccupation) { _, i, b ->
                if (b) {
                    pPEditOccupationList.add(i)
                } else {
                    pPEditOccupationList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until pPEditOccupationList.size) {
                    stringBuilder.append(pPEditOccupationArray[pPEditOccupationList[j]])

                    if (j in -1 until pPEditOccupationList.size) {
                        stringBuilder.append(",")
                    }
                }
                pPEditOccupation.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until pPEditOccupationList.size) {
                    selectedPPEditOccupation[j] = false

                    pPEditOccupationList.clear();

                    pPEditOccupation.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun annualIncome() {
        selectedPPEditAnnualIncome = BooleanArray(pPEditAnnualIncomeArray.size)
        pPEditAnnualIncome.setOnClickListener {
            val builder = AlertDialog.Builder(this@ProfessionalPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(pPEditAnnualIncomeArray, selectedPPEditAnnualIncome) { _, i, b ->
                if (b) {
                    pPEditAnnualIncomeList.add(i)
                } else {
                    pPEditAnnualIncomeList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until pPEditAnnualIncomeList.size) {
                    stringBuilder.append(pPEditAnnualIncomeArray[pPEditAnnualIncomeList[j]])

                    if (j in -1 until pPEditAnnualIncomeList.size) {
                        stringBuilder.append(",")
                    }
                }
                pPEditAnnualIncome.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until pPEditAnnualIncomeList.size) {
                    selectedPPEditAnnualIncome[j] = false

                    pPEditAnnualIncomeList.clear();

                    pPEditAnnualIncome.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}