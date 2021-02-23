package com.example.marathimatrimony

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteFamilyDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FamilyDetails : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String


    lateinit var editFamilyValues : Spinner
    lateinit var editFamilyType : Spinner
    lateinit var editFamilyStatus  : Spinner
    lateinit var editFathersOccupation  : Spinner
    lateinit var editMothersOccupation : Spinner
    lateinit var editBrothers : Spinner
    lateinit var editBrothersMarried  : Spinner
    lateinit var editSisters  : Spinner
    lateinit var editSistersMarried  : Spinner
    lateinit var editEnterAncestralOrigin  : EditText

    lateinit var FamilyDetailsSaveBtn: Button

    var spinnerSelectedItemOne: String? =null
    var spinnerSelectedItemTwo: String? =null
    var spinnerSelectedItemThree:String? =null
    var spinnerSelectedItemFour:String? =null
    var spinnerSelectedItemFive: String? =null
    var spinnerSelectedItemSix: String? =null
    var spinnerSelectedItemSeven:String? =null
    var spinnerSelectedItemEight:String? =null
    var spinnerSelectedItemNine:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_details)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

        editFamilyValues=findViewById(R.id.editFamilyValues)
        editFamilyType=findViewById(R.id.editFamilyType)
        editFamilyStatus=findViewById(R.id.editFamilyStatus)
        editFathersOccupation=findViewById(R.id.editFathersOccupation)
        editMothersOccupation=findViewById(R.id.editMothersOccupation)
        editBrothers=findViewById(R.id.editBrothers)
        editBrothersMarried=findViewById(R.id.editBrothersMarried)
        editSisters=findViewById(R.id.editSisters)
        editSistersMarried=findViewById(R.id.editSistersMarried)
        editEnterAncestralOrigin=findViewById(R.id.editEnterAncestralOrigin)
        FamilyDetailsSaveBtn=findViewById(R.id.FamilyDetailsSaveBtn)
        FamilyDetailsSaveBtn.setOnClickListener {
            save()
        }
        val editFamilyValuesArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter1= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editFamilyValuesArray)
        editFamilyValues.adapter=arrayAdapter1
        editFamilyValues.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemOne=null
                }else{
                    spinnerSelectedItemOne=editFamilyValues.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }

        val editFamilyTypeArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter2= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editFamilyTypeArray)
        editFamilyType.adapter=arrayAdapter2
        editFamilyType.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemTwo=null
                }else{
                    spinnerSelectedItemTwo=editFamilyType.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editFamilyStatusArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter3= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editFamilyStatusArray)
        editFamilyStatus.adapter=arrayAdapter3
        editFamilyStatus.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemThree=null
                }else{
                    spinnerSelectedItemThree=editFamilyStatus.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editFathersOccupationArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter4= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editFathersOccupationArray)
        editFathersOccupation.adapter=arrayAdapter4
        editFathersOccupation.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFour=null
                }else{
                    spinnerSelectedItemFour=editFathersOccupation.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editMothersOccupationArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter5= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editMothersOccupationArray)
        editMothersOccupation.adapter=arrayAdapter5
        editMothersOccupation.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFive=null
                }else{
                    spinnerSelectedItemFive=editMothersOccupation.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }

        val editBrothersArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter6= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editBrothersArray)
        editBrothers.adapter=arrayAdapter6
        editBrothers.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemSix=null
                }else{
                    spinnerSelectedItemSix=editBrothers.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editBrothersMarriedArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter7= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editBrothersMarriedArray)
        editBrothersMarried.adapter=arrayAdapter7
        editBrothersMarried.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemSeven=null
                }else{
                    spinnerSelectedItemSeven=editBrothersMarried.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editSistersArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter8= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editSistersArray)
        editSisters.adapter=arrayAdapter8
        editSisters.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemEight=null
                }else{
                    spinnerSelectedItemEight=editSisters.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editSistersMarriedArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter9= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editSistersMarriedArray)
        editSistersMarried.adapter=arrayAdapter9
        editSistersMarried.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemNine=null
                }else{
                    spinnerSelectedItemNine=editSistersMarried.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
    }


    private fun save() {
        val familyValues=spinnerSelectedItemOne
        val familyType=spinnerSelectedItemTwo
        val familyStatus=spinnerSelectedItemThree
        val fathersOccupation =spinnerSelectedItemFour
        val mothersOccupation=spinnerSelectedItemFive
        val brothers=spinnerSelectedItemSix
        val brothersMarried=spinnerSelectedItemSeven
        val sisters =spinnerSelectedItemEight
        val sistersMarried =spinnerSelectedItemNine
        val ancestralOrigin=editEnterAncestralOrigin.text.toString().trim()

        if (spinnerSelectedItemOne==null)
        {
            Toast.makeText(this, "Please Select Family Values ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemTwo==null)
        {
            Toast.makeText(this, "Please Select Family Type  ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemThree==null)
        {
            Toast.makeText(this, "Please Select Family Status ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemFour==null)
        {
            Toast.makeText(this, "Please Select Fathers Occupation ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemFive==null)
        {
            Toast.makeText(this, "Please Select Mothers Occupation ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemSix==null)
        {
            Toast.makeText(this, "Please Select Brothers", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemSeven==null)
        {
            Toast.makeText(this, "Please Select Brothers Married ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemEight==null)
        {
            Toast.makeText(this, "Please Select Sisters ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemNine==null)
        {
            Toast.makeText(this, "Please Select Sisters Married ", Toast.LENGTH_SHORT).show()
            return
        }
        if (ancestralOrigin.trim().toString().isEmpty())
        {
            editEnterAncestralOrigin.error = "Please Enter Enter Enter Ancestral Origin"
            editEnterAncestralOrigin.requestFocus()
            return
        }
        userID= auth.currentUser!!.uid
        docRef=db.collection("Users").document(userID)
        val write= WriteFamilyDetails(familyValues,familyType,familyStatus,fathersOccupation,mothersOccupation,brothers,brothersMarried,sisters,sistersMarried,ancestralOrigin)
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