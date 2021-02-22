package com.example.marathimatrimony

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteProfessionalInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class ProfessionalInformation : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String

    lateinit var editHighestEducation : Spinner
    lateinit var editCollegeAndInstitution : EditText
    lateinit var editEmployedIn: Spinner
    lateinit var editOccupation  : Spinner
    lateinit var editOrganizationName  : EditText
    lateinit var editCurrencyType  : Spinner
    lateinit var editEnterAnnualIncome : Spinner

    lateinit var professionalInformationSaveBtn: Button

    var spinnerSelectedItemOne: String? =null
    var spinnerSelectedItemTwo: String? =null
    var spinnerSelectedItemThree:String? =null
    var spinnerSelectedItemFour:String? =null
    var spinnerSelectedItemFive: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_information)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

        editHighestEducation = findViewById(R.id.editHighestEducation)
        editCollegeAndInstitution = findViewById(R.id.editCollegeAndInstitution)
        editEmployedIn= findViewById(R.id.editEmployedIn)
        editOccupation = findViewById(R.id.editOccupation)
        editOrganizationName = findViewById(R.id.editOrganizationName)
        editCurrencyType = findViewById(R.id.editCurrencyType)
        editEnterAnnualIncome = findViewById(R.id.editEnterAnnualIncome)
        professionalInformationSaveBtn = findViewById(R.id.professionalInformationSaveBtn)

        professionalInformationSaveBtn.setOnClickListener {
            save()
        }

        val editHighestEducationArray= arrayOf( "--Select--","1","2","3","4","5","6")
        val arrayAdapter1= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editHighestEducationArray)
        editHighestEducation.adapter=arrayAdapter1
        editHighestEducation.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemOne=null
                }else{
                    spinnerSelectedItemOne=editHighestEducation.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editEmployedInArray= arrayOf( "--Select--","1","2","3","4","5","6")
        val arrayAdapter2= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editEmployedInArray)
        editEmployedIn.adapter=arrayAdapter2
        editEmployedIn.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemTwo=null
                }else{
                    spinnerSelectedItemTwo=editEmployedIn.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editOccupationArray= arrayOf( "--Select--","1","2","3","4","5","6")
        val arrayAdapter3= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editOccupationArray)
        editOccupation.adapter=arrayAdapter3
        editOccupation.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemThree=null
                }else{
                    spinnerSelectedItemThree=editOccupation.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editCurrencyTypeArray= arrayOf( "--Select--","1","2","3","4","5","6")
        val arrayAdapter4= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editCurrencyTypeArray)
        editCurrencyType.adapter=arrayAdapter4
        editCurrencyType.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFour=null
                }else{
                    spinnerSelectedItemFour=editCurrencyType.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editEnterAnnualIncomeArray= arrayOf( "--Select--","1","2","3","4","5","6")
        val arrayAdapter5= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editEnterAnnualIncomeArray)
        editEnterAnnualIncome.adapter=arrayAdapter5
        editEnterAnnualIncome.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFive=null
                }else{
                    spinnerSelectedItemFive=editEnterAnnualIncome.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
    }

    private fun save() {

        val highestEducation=spinnerSelectedItemOne
        val collegeAndInstitution=editCollegeAndInstitution.text.toString().trim()
        val employedIn=spinnerSelectedItemTwo
        val occupation=spinnerSelectedItemThree
        val organization=editOrganizationName.text.toString().trim()
        val currencyType =spinnerSelectedItemFive
        val annualIncome =spinnerSelectedItemFour

        if (spinnerSelectedItemOne==null)
        {
            Toast.makeText(this, "Please Select HighestEducation ", Toast.LENGTH_SHORT).show()
            return
        }
        if (editCollegeAndInstitution.text.trim().toString().isEmpty())
        {
            editCollegeAndInstitution.error = "Please Enter College And Institution"
            editCollegeAndInstitution.requestFocus()
            return
        }
        if (editOrganizationName.text.trim().toString().isEmpty())
        {
            editOrganizationName.error = "Please Enter Enter OrganizationName"
            editOrganizationName.requestFocus()
            return
        }
        if (spinnerSelectedItemTwo==null)
        {
            Toast.makeText(this, "Please Select Employed In ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemThree==null)
        {
            Toast.makeText(this, "Please Select Occupation ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemFour==null)
        {
            Toast.makeText(this, "Please Select Enter Annual Income ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemFive==null)
        {
            Toast.makeText(this, "Please Select Currency Type ", Toast.LENGTH_SHORT).show()
            return
        }

        userID= auth.currentUser!!.uid
        docRef=db.collection("Users").document(userID)
        val write=WriteProfessionalInformation(highestEducation,collegeAndInstitution,employedIn,occupation,organization,annualIncome,currencyType)
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