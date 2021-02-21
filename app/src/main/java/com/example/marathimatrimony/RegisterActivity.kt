package com.example.marathimatrimony

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteRegistration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


class RegisterActivity : AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String

    var radioGroup: RadioGroup? = null
    lateinit var radioButton1: RadioButton
    lateinit var radioButton2: RadioButton


    var cal = Calendar.getInstance()
    lateinit var editUsername: EditText
    lateinit var editEmail: EditText
    lateinit var editPhoneNo: EditText
    lateinit var editPassword: EditText
    lateinit var btnRRegister: Button
    lateinit var btnRLogin: Button
    lateinit var editProfileCreatedFor:Spinner
    lateinit var editDateOfBirth: TextView
    lateinit var editReligion:Spinner
    lateinit var editMotherTongue:Spinner
    lateinit var editCountryCode:Spinner



     var spinnerSelectedItemOne: String? =null
     var spinnerSelectedItemTwo: String? =null
     var spinnerSelectedItemThree:String? =null
     var spinnerSelectedItemFour:String? =null
     var gender: String? =null
     private var DOB: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()



        editUsername = findViewById(R.id.editUsername)
        editEmail = findViewById(R.id.editEmail)
        editDateOfBirth = findViewById(R.id.editDateOfBirth)
        editPhoneNo = findViewById(R.id.editPhoneNo)
        editPassword = findViewById(R.id.editPassword)
        btnRRegister = findViewById(R.id.btnRRegister)
        btnRLogin = findViewById(R.id.btnRLogin)
        editProfileCreatedFor=findViewById(R.id.editProfileCreatedFor)
        editReligion=findViewById(R.id.editReligion)
        editMotherTongue=findViewById(R.id.editMotherTongue)
        editCountryCode=findViewById(R.id.editCountryCode)
        radioGroup = findViewById(R.id.radioGroup)
        radioButton1=findViewById(R.id.radioBtn1)
        radioButton2=findViewById(R.id.radioBtn2)


        btnRLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btnRRegister.setOnClickListener {
            registerUser()
        }

        radioButton1.setOnClickListener {
            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            radioButton1 = findViewById(intSelectButton)
            Toast.makeText(this, radioButton1.text, Toast.LENGTH_SHORT).show()
            gender=radioButton1.text.toString()
        }
        radioButton2.setOnClickListener {
            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            radioButton2 = findViewById(intSelectButton)
            Toast.makeText(this, radioButton2.text, Toast.LENGTH_SHORT).show()
            gender=radioButton2.text.toString()

        }
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
            DOB="$dayOfMonth/$monthOfYear/$year"
        }

        editDateOfBirth.setOnClickListener {
            DatePickerDialog(this@RegisterActivity, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }



        val profileCreatedFor= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter1=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,profileCreatedFor)
        editProfileCreatedFor.adapter=arrayAdapter1
        editProfileCreatedFor.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                     spinnerSelectedItemOne=null
                }else{
                    spinnerSelectedItemOne=editProfileCreatedFor.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }



        val religion= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter2=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,religion)
        editReligion.adapter=arrayAdapter2
        editReligion.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemTwo=null
                }else{
                    spinnerSelectedItemTwo=editReligion.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){TODO("Not yet implemented")}
        }

        val motherTongue= arrayOf("--Select--", "Marathi","Hindi","Bengali","Telugu","Tamil","Gujarati","Urdu","Bhojpuri","Kannada","Malayalam",
                "Odia","Punjabi","Rajasthani","Chhattisgarhi","Assamese","Assamese","Maithili","Magadhi/Magahi","Haryanvi","Khortha/Khotta","Marwari"
                ,"Santali","Kashmiri","Bundeli/Bundel khandi","Malvi","Sadan/Sadri","Mewari","Awadhi","Wagdi","Lamani/Lambadi","Pahari[c]","Bhili/Bhilodi",
                "Hara/Harauti","Nepali","Gondi","Bagheli/Baghel Khandi","Sambalpuri","Dogri","Garhwali","Nimadi","Surjapuri","Konkani","Kumauni","Kurukh/Oraon",
                "Tulu","Manipuri","Surgujia","Sindhi","Bagri","Ahirani","Banjari","Brajbhasha","Dhundhari","Bodo/Boro","ojri/Gujjari/Gujar",
                "Mundari","Garo","Kangri","Khasi","Kachchhi")
        val arrayAdapter3=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,motherTongue)
        editMotherTongue.adapter=arrayAdapter3
        editMotherTongue.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemThree=null
                }else{
                    spinnerSelectedItemThree=editMotherTongue.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){TODO("Not yet implemented")}
        }

        val countryCode= arrayOf( "Select your country","India+91",
                "Afghanistan+93", "Albania+355", "Algeria+213", "American Samoa+1-684", "Andorra+376", "Angola+244", "Anguilla+1-264", "Antarctica+672", "Antigua+1-268", "Argentina+54",
                "Armenia+374", "Aruba+297", "Ascension+247", "Australia+61", "Australian External Territories+672", "Austria+43", "Azerbaijan+994", "Bahamas+1-242", "Bahrain+973",
                "Bangladesh+880", "Barbados+1-246", "Barbuda+1-268","Belarus+375", "Belgium+32", "Belize+501", "Benin+229", "Bermuda+1-441", "Bhutan+975", "Bolivia+591", "Bosnia&Herzegovina+387",
                "Botswana+267", "Brazil+55", "British Virgin Islands+1-284", "Brunei Darussalam+673", "Bulgaria+359", "Burkina Faso +226", "Burundi+257", "Cambodia+855", "Cameroon+237",
                "Canada+1", "Cape Verde Islands+238", "Cayman Islands+1-345", "Central African Republic+236", "Chad+235", "Chatham Island (New Zealand)+64", "Chile+56", "China (PRC)+86",
                "Christmas Island+61-8", "Cocos-Keeling Islands+61", "Colombia+57", "Comoros+269", "Congo+242", "Congo, Dem. Rep. of (former Zaire)+243", "Cook Islands+682", "Costa Rica+506",
                "Côte d'Ivoire (Ivory Coast)+225", "Croatia+385", "Cuba+53", "Cuba(Guantanamo Bay)+5399", "Curaçao+599", "Cyprus+357", "Czech Republic+420", "Denmark+45", "Diego Garcia+246",
                "Djibouti+253", "Dominica +1-767", "Dominican Republic+1-809 and +1-829", "East Timor+670", "Easter Island+56", "Ecuador+593", "Egypt+20", "El Salvador+503", "Ellipso " +
                "(Mobile Satellite service)+8812 and +8813", "EMSAT (Mobile Satellite service)+88213", "Equatorial Guinea+240", "Eritrea+291", "Estonia+372","Ethiopia+251",
                "Falkland Islands(Malvinas)+500", "Faroe Islands+298", "Fiji Islands+679", "Finland+358", "France+33", "French Antilles+596", "French Guiana+594", "French Polynesia+689",
                "Gabonese Republic+241", "Gambia+220", "Georgia+995", "Germany+49", "Ghana+233", "Gibraltar+350", "Global Mobile Satellite System (GMSS)+881", "ICO Global+8810 and +8811",
                "Ellipso 8812+8813", "Iridium 8816+8817", "Globalstar8818 and 8819", "Globalstar(Mobile Satellite Service)+8818 and +8819",
                "Greece+30", "Greenland+299", "Grenada+1-473", "Guadeloupe+590", "Guam+1-671", "Guantanamo Bay+5399", "Guatemala+502", "Guinea-Bissau+245", "Guinea+224", "Guyana+592",
                "Haiti+509", "Honduras+504", "Hong Kong+852", "Hungary+36", "ICO Global(Mobile Satellite Service)+8810and+8811", "Iceland+354", "Indonesia+62",
                "Inmarsat(Atlantic Ocean-East)+871", "Inmarsat(Atlantic Ocean-West)+874", "Inmarsat(Indian Ocean)+873", "Inmarsat (Pacific Ocean)+872", "Inmarsat SNAC+870",
                "International Freephone Service+800", "International Shared Cost Service (ISCS)+808", "Iran+98",
                "Iraq+964", "Ireland+353", "Iridium(Mobile Satellite service)+8816 and +8817", "Israel+972", "Italy+39", "Ivory Coast+225", "Jamaica+1-876", "Japan+81", "Jordan+962",
                "Kazakhstan+7", "Kenya+254", "Kiribati+686", "Korea(North)+850","Korea(South)+82", "Kuwait+96", "Kyrgyz Republic+996", "Laos+856", "Latvia+371", "Lebanon+961",
                "Lesotho+266", "Liberia+231", "Libya+218", "Liechtenstein+423", "Lithuania+370", "Luxembourg+352", "Macao+853", "Macedonia(Former Yugoslav Rep of.)+389", "Madagascar+261",
                "Malawi+265", "Malaysia+60", "Maldives+960", "Mali Republi+223", "Malta+356", "Marshall Islands+692", "Martinique+596", "Mauritania+222", "Mauritius+230", "Mayotte Island+269",
                "Mexico+52", "Micronesia(Federal States of)+691", "Midway Island+1-808", "Moldova+373", "Monaco+377", "Mongolia+976", "Montenegro+382", "Montserrat+1-664", "Morocco+212",
                "Mozambique+258", "Myanmar+95", "Namibia+264", "Nauru+674", "Nepal+977", "Netherlands+31", "Netherlands Antilles+599", "Nevis+1-869", "New Caledonia+687", "New Zealand+64",
                "Nicaragua+505", "Niger+227", "Nigeria+234", "Niue+683", "Norfolk Island+672", "Northern Marianas Islands (Saipan, Rota & Tinian)+1-670", "Norway+47", "Oman+968", "Pakistan+92",
                "Palau+680", "Palestinian Settlements+970", "Panama+507", "Papua New Guinea+675", "Paraguay+595", "Peru+51", "Philippines+63", "Poland+48", "Portugal+351",
                "Puerto Rico+1-787 or +1-939", "Qatar+974", "Réunion Island+262", "Romania+40", "Russia+7", "Rwandese Republic+250", "St.Helena+290", "St.Kitts/Nevis+1-869", "St.Lucia+1-758",
                "St.Pierre & Miquelon+508", "St.Vincent & Grenadines+1-784", "Samoa+685", "San Marino+378", "São Tomé and Principe+239", "Saudi Arabia+966", "Senegal+221", "Serbia+381",
                "Seychelles Republic+248", "Sierra Leone+232", "Singapore+65", "Slovak Republic+421", "Slovenia+386", "Solomon Islands+677", "Somali Democratic Republic+252", "South Africa+27",
                "Spain+34", "Sri Lanka+94", "Sudan+249", "Suriname+597", "Swaziland+268", "Sweden+46", "Switzerland+41", "Syria+963", "Taiwan+886", "Tajikistan+992", "Tanzania+255",
                "Thailand+66", "Thuraya(Mobile Satellite service)+88216", "Timor Leste+670", "Togolese Republic+228", "Tokelau+690", "Tonga Islands+676", "Trinidad & Tobago+1-868", "Tunisia+216",
                "Turkey+90", "Turkmenistan+993", "Turks and Caicos Islands+1-649", "Tuvalu+688", "Uganda+256", "Ukraine+380", "United Arab Emirates+971", "United Kingdom+44",
                "United States of America+1", "US Virgin Islands+1-340", "Universal Personal Telecommunications(UPT)+878", "Uruguay+598", "Uzbekistan +998", "Vanuatu+678",
                "Vatican City+39and+379", "Venezuela+58", "Vietnam+84", "Wake Island+808", "Wallis and Futuna Islands+681", "Yemen+967", "Zambia+260", "Zanzibar+255", "Zimbabwe+263")
        val arrayAdapter4=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,countryCode)
        editCountryCode.adapter=arrayAdapter4
        editCountryCode.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFour=null
                }else{
                    spinnerSelectedItemFour=editCountryCode.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){TODO("Not yet implemented")}
        }

    }
    private fun registerUser() {

        val profileCreatedFor=spinnerSelectedItemOne
        val name=editUsername.text.toString().trim()
        val dateOfBirth=DOB
        val gender=gender
        val religion=spinnerSelectedItemTwo
        val motherTongue=spinnerSelectedItemThree
        val countryCode=spinnerSelectedItemFour
        val phoneNumber: String =editPhoneNo.text.toString()
        val email:String=editEmail.text.toString()
        val password:String=editPassword.text.toString().trim()

        if (spinnerSelectedItemOne==null)
        {
            Toast.makeText(this, "Please Select Profile Created For ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemTwo==null)
        {
            Toast.makeText(this, "Please Select Religion  ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemThree==null)
        {
            Toast.makeText(this, "Please Select Mother Tongue ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemFour==null)
        {
            Toast.makeText(this, "Please Select Country Code  ", Toast.LENGTH_SHORT).show()
            return
        }
        if(gender==null)
        {
            Toast.makeText(this, "Please Select gender ", Toast.LENGTH_SHORT).show()
             return
        }

        if (editUsername.text.trim().toString().isEmpty())
        {
            editUsername.error = "Please Enter Full Name"
            editUsername.requestFocus()
            return
        }
        if (editDateOfBirth.text.trim().toString().isEmpty())
        {
            Toast.makeText(this, "Please Date Of Birth ", Toast.LENGTH_SHORT).show()
            return

        }
        if (editPhoneNo.text.trim().toString().isEmpty())
        {
            editPhoneNo.error = "Please Enter 10 Digit Mobile No"
            editPhoneNo.requestFocus()
            return
        }
        if (editEmail.text.trim().toString().isEmpty()) {
            editEmail.error = "Please Enter Email"
            editEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(editEmail.text.toString()).matches()) {
            editEmail.error = "Please Enter Correct Email"
            editEmail.requestFocus()
            return
        }
        if (editPassword.text.trim().toString().isEmpty()) {
            editPassword.error = "Please Enter Password"
            editPassword.requestFocus()
            return
        }
        if (editPassword.length() < 6 || editPassword.length() > 8) {
            editPassword.error = "Password required Minimum 8 Characters"
            editPassword.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(editEmail.text.toString(), editPassword.text.toString()).addOnCompleteListener(this)
        { task ->

            if (task.isSuccessful)
            {
                userID= auth.currentUser!!.uid
                docRef=db.collection("Users").document(userID)
                val write= WriteRegistration(profileCreatedFor,name,dateOfBirth,gender,religion,motherTongue,countryCode,phoneNumber,email,password)

                docRef.set(write).addOnCompleteListener{
                    if (it.isSuccessful)
                    {

                        startActivity(Intent(this, LoginActivity::class.java))
                        Toast.makeText(this, "Registration Successfully.",Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this, "Registration Failed.",Toast.LENGTH_SHORT).show()

                    }
                }
            }
            else
            {
                Toast.makeText(this, "Already Registered", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        editDateOfBirth.text = sdf.format(cal.time)
    }


}





