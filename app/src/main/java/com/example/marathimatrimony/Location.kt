package com.example.marathimatrimony

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteLocation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Location : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String

    lateinit var editCountryLivingIn : Spinner
    lateinit var editResidingState : Spinner
    lateinit var editResidingCity  : Spinner
    lateinit var editCitizenship  : Spinner
    lateinit var LocationSaveBtn: Button

    var spinnerSelectedItemOne: String? =null
    var spinnerSelectedItemTwo: String? =null
    var spinnerSelectedItemThree:String? =null
    var spinnerSelectedItemFour:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

        editCountryLivingIn=findViewById(R.id.editCountryLivingIn)
        editResidingState=findViewById(R.id.editResidingState)
        editResidingCity=findViewById(R.id.editResidingCity)
        editCitizenship=findViewById(R.id.editCitizenship)
        LocationSaveBtn=findViewById(R.id.LocationSaveBtn)

        LocationSaveBtn.setOnClickListener {
            save()
        }
        val editCountryLivingInArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter1= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editCountryLivingInArray)
        editCountryLivingIn.adapter=arrayAdapter1
        editCountryLivingIn.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemOne=null
                }else{
                    spinnerSelectedItemOne=editCountryLivingIn.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }

        val editResidingStateArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter2= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editResidingStateArray)
        editResidingState.adapter=arrayAdapter2
        editResidingState.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemTwo=null
                }else{
                    spinnerSelectedItemTwo=editResidingState.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editResidingCityArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter3= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editResidingCityArray)
        editResidingCity.adapter=arrayAdapter3
        editResidingCity.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemThree=null
                }else{
                    spinnerSelectedItemThree=editResidingCity.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editCitizenshipArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter4= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editCitizenshipArray)
        editCitizenship.adapter=arrayAdapter4
        editCitizenship.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFour=null
                }else{
                    spinnerSelectedItemFour=editCitizenship.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
    }

    private fun save() {
        val countryLivingIn=spinnerSelectedItemOne
        val residingState=spinnerSelectedItemTwo
        val residingCity=spinnerSelectedItemThree
        val citizenship =spinnerSelectedItemFour

        if (spinnerSelectedItemOne==null)
        {
            Toast.makeText(this, "Please Select Country Living In ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemTwo==null)
        {
            Toast.makeText(this, "Please Select Residing State  ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemThree==null)
        {
            Toast.makeText(this, "Please Select Residing City ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemFour==null)
        {
            Toast.makeText(this, "Please Select Citizenship ", Toast.LENGTH_SHORT).show()
            return
        }
        userID= auth.currentUser!!.uid
        docRef=db.collection("Users").document(userID)
        val write= WriteLocation(countryLivingIn,residingState,residingCity,citizenship)
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