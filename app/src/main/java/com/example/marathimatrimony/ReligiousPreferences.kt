package com.example.marathimatrimony

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteReligiousPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.*


class ReligiousPreferences : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String

    lateinit var pPEditReligion  : Spinner
    lateinit var pPEditDosh  : Spinner

    lateinit var pPEditMotherTongue: TextView
    lateinit var pPEditCaste: TextView
    lateinit var pPEditSubCaste: TextView
    lateinit var pPEditStar: TextView

    var spinnerSelectedItemOne: String? =null
    var spinnerSelectedItemTwo: String? =null

    lateinit var selectedPPEditMotherTongue: BooleanArray
    lateinit var selectedPPEditCaste: BooleanArray
    lateinit var selectedPPEditSubCaste: BooleanArray
    lateinit var selectedPPEditStar: BooleanArray

    var pPEditMotherTongueList: ArrayList<Int> = ArrayList()
    var pPEditCasteList: ArrayList<Int> = ArrayList()
    var pPEditSubCasteList: ArrayList<Int> = ArrayList()
    var pPEditStarList: ArrayList<Int> = ArrayList()

    private var pPEditMotherTongueArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var pPEditCasteArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday")
    private var pPEditSubCasteArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var pPEditStarArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_religious_preferences)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

        pPEditReligion=findViewById(R.id.pPEditReligion)
        pPEditDosh=findViewById(R.id.pPEditDosh)
        pPEditMotherTongue=findViewById(R.id.pPEditMotherTongue)
        pPEditCaste=findViewById(R.id.pPEditCaste)
        pPEditSubCaste=findViewById(R.id.pPEditSubCaste)
        pPEditStar=findViewById(R.id.pPEditStar)


        save()

        val pPEditReligionArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter1= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,pPEditReligionArray)
        pPEditReligion.adapter=arrayAdapter1
        pPEditReligion.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemOne=null
                }else{
                    spinnerSelectedItemOne=pPEditReligion.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val pPEditDoshArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter2= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,pPEditDoshArray)
        pPEditDosh.adapter=arrayAdapter2
        pPEditDosh.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemTwo=null
                }else{
                    spinnerSelectedItemTwo=pPEditDosh.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        motherTongue()
        caste()
        subCaste()
        star()
    }



    private fun motherTongue() {
        selectedPPEditMotherTongue = BooleanArray(pPEditMotherTongueArray.size)
        pPEditMotherTongue.setOnClickListener {
            val builder = AlertDialog.Builder(this@ReligiousPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(pPEditMotherTongueArray, selectedPPEditMotherTongue) { _, i, b ->
                if (b) {
                    pPEditMotherTongueList.add(i)
                } else {
                    pPEditMotherTongueList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until pPEditMotherTongueList.size) {
                    stringBuilder.append(pPEditMotherTongueArray[pPEditMotherTongueList[j]])

                    if (j in -1 until pPEditMotherTongueList.size) {
                        stringBuilder.append(",")
                    }
                }
                pPEditMotherTongue.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until pPEditMotherTongueList.size) {
                    selectedPPEditMotherTongue[j] = false

                    pPEditMotherTongueList.clear();

                    pPEditMotherTongue.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun caste() {
        selectedPPEditCaste = BooleanArray(pPEditCasteArray.size)
        pPEditCaste.setOnClickListener {
            val builder = AlertDialog.Builder(this@ReligiousPreferences)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(pPEditCasteArray, selectedPPEditCaste) { _, i, b ->
                if (b) {
                    pPEditCasteList.add(i)
                } else {
                    pPEditCasteList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until pPEditCasteList.size) {
                    stringBuilder.append(pPEditCasteArray[pPEditCasteList[j]])

                    if (j in -1 until pPEditCasteList.size) {
                        stringBuilder.append(",")
                    }
                }
                pPEditCaste.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until pPEditCasteList.size) {
                    selectedPPEditCaste[j] = false

                    pPEditCasteList.clear();

                    pPEditCaste.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun subCaste() {
        selectedPPEditSubCaste = BooleanArray(pPEditSubCasteArray.size)
        pPEditSubCaste.setOnClickListener {
            val builder = AlertDialog.Builder(this@ReligiousPreferences)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(pPEditSubCasteArray, selectedPPEditSubCaste) { _, i, b ->
                if (b) {
                    pPEditSubCasteList.add(i)
                } else {
                    pPEditSubCasteList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until pPEditSubCasteList.size) {
                    stringBuilder.append(pPEditSubCasteArray[pPEditSubCasteList[j]])

                    if (j in -1 until pPEditSubCasteList.size) {
                        stringBuilder.append(",")
                    }
                }
                pPEditSubCaste.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until pPEditSubCasteList.size) {
                    selectedPPEditSubCaste[j] = false

                    pPEditSubCasteList.clear();

                    pPEditSubCaste.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }    }

    private fun star() {
        selectedPPEditStar = BooleanArray(pPEditStarArray.size)
        pPEditStar.setOnClickListener {
            val builder = AlertDialog.Builder(this@ReligiousPreferences)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(pPEditStarArray, selectedPPEditStar) { _, i, b ->
                if (b) {
                    pPEditStarList.add(i)
                } else {
                    pPEditStarList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until pPEditStarList.size) {
                    stringBuilder.append(pPEditStarArray[pPEditStarList[j]])

                    if (j in -1 until pPEditStarList.size) {
                        stringBuilder.append(",")
                    }
                }
                pPEditStar.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until pPEditStarList.size) {
                    selectedPPEditStar[j] = false

                    pPEditStarList.clear();

                    pPEditStar.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun save() {

        val pPReligion=spinnerSelectedItemOne
        val pPDosh=spinnerSelectedItemTwo
        val pPEditMotherTongue= pPEditMotherTongue.text.toString().trim()
        val pPEditCaste= pPEditCaste.text.toString().trim()
        val pPEditSubCaste= pPEditSubCaste.text.toString().trim()
        val pPEditStar= pPEditStar.text.toString().trim()
        userID= auth.currentUser!!.uid
        docRef=db.collection("Users").document(userID)
        val write= WriteReligiousPreferences(pPReligion,pPDosh,pPEditMotherTongue,pPEditCaste,pPEditSubCaste,pPEditStar)
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
}