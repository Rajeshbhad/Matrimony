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
        val editCountryLivingInArray= arrayOf("--Select--", "Any","India" ,"United States of America","United Arab Emirates","Saudi Arabia","Kuwait "
            ,"United Kingdom","Malaysia","Singapore","Australia","Canada","Afghanistan","Albania","Algeria","American Samoa","Andorra","Angola","Anguilla"
            ,"Antarctica","Antigua and Barbuda"," Argentina","Armenia", "Aruba","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus",
            "Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Bouvet Island","Brazil","British indian ocean Territory",
            "British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Cape Verde","Cayman Island","Central African Republic",
            "Chad","Chile","China","Christmas Island","Cocos Island","Colombia","Comoros","Congo","Cook Island","Costa Rica","Croatia","cuba","Cyprus","Czech Republic"
            ,"Denmark","Djibouti","Dominica","Dominican Republic","East Timor","Ecuador","Egypt","EI Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia",
            "Falkland Islands","Faroe Islands","Fiji","Finland","France","French Guiana","French Polynesia","French Southern Territories","Gabon","Gambia","Georgia",
            "Germany","Ghana","gibraltar","Greece","Greenland","Grenada","Guadeloupe","Guam","Gautemala","Guinea","Guinea-Bissau","Guyana","Haiti",
            "Heard and McDonald Islands","Honduras","Hong Kong","Hungary","Iceland","Indonesia","Iran","Iraq","Ireland","Israel","Italy","Ivory Coast","Jamaica",
            "Japan","Jordan","Kazakhstan","Kenya","Kiribati","Korea,North","Korea,South","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya",
            "Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Maldives","Mali","Marshall Islands","Martinique","Mauritania",
            " Mauritius","Mayotte"," Mexico","Micronesia,Federated States of","Moldova","Monaco","Mongolia","Montserrat"," Morocco","Mozambique","Myanmar",
            "Namibia","Nauru"," Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Niue"," Norfolk Island",
            "Northern Mariana Island","Norway","Oman"," Pakistan"," Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Pitcairn Island","Poland",
            "Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","S.Georgia and S.Sandwich ISIS","Saint Kitts &amp; Nevis","Saint Lucia",
            "Saint Vincent and The Grenadines","Samoa","San Marino","Sao Tome and Principe","Senegal","Seychelles","Sierra Leone","Slovenia","Somalia","South Africa",
            "Spain","Sri Lanka","St.Helena","St. Pierre and Miquelon","Sudan","Suriname","Svalbard and Jan Mayen Islands","Swaziland","Sweden","Switzerland","Syria",
            "Taiwan","Tajikistan","Tanzania ","Thailand","Togo ","Tokelau","Tonga","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan","Turks and caicos Islands",
            "Tuvalu","Uganda"," Ukraine","Uruguay","uzbekistan","Vanuatu","Vatican City"," Venezuela","Vietnam","Virgin Islands","Wallis and Futuna Islands",
            "Western Sahara","Yemen","Yugoslavia(Former)","Zaire","Zambia","Zimbabwe","DR Congo" )
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