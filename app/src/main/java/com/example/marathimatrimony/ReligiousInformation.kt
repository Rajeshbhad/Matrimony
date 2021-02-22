package com.example.marathimatrimony

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteReligiousInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class ReligiousInformation : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String

    lateinit var editReligion : Spinner
    lateinit var editCaste : Spinner
    lateinit var editSubCaste: EditText
    lateinit var editGothraM  : EditText
    lateinit var editStar  : Spinner
    lateinit var editRaasiAndMoonSign  : Spinner
    lateinit var editZodicAndStarSign : Spinner
    lateinit var editHavingDosham : Spinner
    lateinit var ReligiousInformationSaveBtn:Button

    var spinnerSelectedItemOne: String? =null
    var spinnerSelectedItemTwo: String? =null
    var spinnerSelectedItemThree:String? =null
    var spinnerSelectedItemFour:String? =null
    var spinnerSelectedItemFive: String? =null
    var spinnerSelectedItemSix:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_religious_information)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

        editReligion = findViewById(R.id.editReligion)
        editCaste = findViewById(R.id.editCaste)
        editSubCaste= findViewById(R.id.editSubCaste)
        editGothraM = findViewById(R.id.editGothraM)
        editStar = findViewById(R.id.editStar)
        editRaasiAndMoonSign = findViewById(R.id.editRaasiAndMoonSign)
        editZodicAndStarSign = findViewById(R.id.editZodicAndStarSign)
        editHavingDosham = findViewById(R.id.editHavingDosham)
        ReligiousInformationSaveBtn = findViewById(R.id.ReligiousInformationSaveBtn)

        ReligiousInformationSaveBtn.setOnClickListener {
            save()
        }
        val editReligionArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter1= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editReligionArray)
        editReligion.adapter=arrayAdapter1
        editReligion.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemOne=null
                }else{
                    spinnerSelectedItemOne=editReligion.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }

        val editCasteArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter2= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editCasteArray)
        editCaste.adapter=arrayAdapter2
        editCaste.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemTwo=null
                }else{
                    spinnerSelectedItemTwo=editCaste.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editStarArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter3= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editStarArray)
        editStar.adapter=arrayAdapter3
        editStar.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemThree=null
                }else{
                    spinnerSelectedItemThree=editStar.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editRaasiAndMoonSignArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter4= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editRaasiAndMoonSignArray)
        editRaasiAndMoonSign.adapter=arrayAdapter4
        editRaasiAndMoonSign.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFour=null
                }else{
                    spinnerSelectedItemFour=editRaasiAndMoonSign.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editZodicAndStarSignArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter5= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editZodicAndStarSignArray)
        editZodicAndStarSign.adapter=arrayAdapter5
        editZodicAndStarSign.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFive=null
                }else{
                    spinnerSelectedItemFive=editZodicAndStarSign.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editHavingDoshamArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter6= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editHavingDoshamArray)
        editHavingDosham.adapter=arrayAdapter6
        editHavingDosham.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemSix=null
                }else{
                    spinnerSelectedItemSix=editHavingDosham.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
    }

    private fun save() {
        val religion=spinnerSelectedItemOne
        val caste=spinnerSelectedItemTwo
        val subCaste=editSubCaste.text.toString().trim()
        val GothraM=editGothraM.text.toString().trim()
        val star=spinnerSelectedItemThree
        val raasiAndMoonSign =spinnerSelectedItemFour
        val zodicAndStarSign=spinnerSelectedItemFive
        val havingDosham=spinnerSelectedItemSix


        if (spinnerSelectedItemOne==null)
        {
            Toast.makeText(this, "Please Select Religion ", Toast.LENGTH_SHORT).show()
            return
        }
        if (editSubCaste.text.trim().toString().isEmpty())
        {
            editSubCaste.error = "Please Enter SubCaste"
            editSubCaste.requestFocus()
            return
        }
        if (editGothraM.text.trim().toString().isEmpty())
        {
            editGothraM.error = "Please Enter GothraM"
            editGothraM.requestFocus()
            return
        }
        if (spinnerSelectedItemTwo==null)
        {
            Toast.makeText(this, "Please Select Star  ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemThree==null)
        {
            Toast.makeText(this, "Please Select Raasi And MoonSign ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemFive==null)
        {
            Toast.makeText(this, "Please Select Zodic And StarSign ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemSix==null)
        {
            Toast.makeText(this, "Please Select Having Dosham", Toast.LENGTH_SHORT).show()
            return
        }
        userID= auth.currentUser!!.uid
        docRef=db.collection("Users").document(userID)
        val write= WriteReligiousInformation(religion,caste,subCaste,GothraM,star,raasiAndMoonSign,zodicAndStarSign,havingDosham)
        docRef.set(write, SetOptions.merge()).addOnCompleteListener{
            if (it.isSuccessful)
            {
                Toast.makeText(this, "Save Successfully.",Toast.LENGTH_SHORT).show()
                finish()
            }
            else
            {
                Toast.makeText(this, "Save Failed.",Toast.LENGTH_SHORT).show()

            }
        }
    }
}