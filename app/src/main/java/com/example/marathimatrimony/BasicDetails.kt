package com.example.marathimatrimony

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteBasicDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.text.SimpleDateFormat
import java.util.*

class BasicDetails : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String

    lateinit var editProfileCreatedFor :Spinner
    lateinit var editName :EditText
    lateinit var editDateOfBirth: TextView
    lateinit var editHeight  :Spinner
    lateinit var editWeight  :Spinner
    lateinit var editMaritalStatus  :Spinner
    lateinit var editBodyType :Spinner
    lateinit var editMotherTongue :Spinner
    lateinit var editEatingHabits :Spinner
    lateinit var editDrinkingHabits  :Spinner
    lateinit var editSmokingHabits  :Spinner

    var cal = Calendar.getInstance()
    var DOB: String? =null

    var spinnerSelectedItemOne: String? =null
    var spinnerSelectedItemTwo: String? =null
    var spinnerSelectedItemThree:String? =null
    var spinnerSelectedItemFour:String? =null
    var spinnerSelectedItemFive: String? =null
    var spinnerSelectedItemSix:String? =null
    var spinnerSelectedItemSeven:String? =null
    var spinnerSelectedItemEight:String? =null
    var spinnerSelectedItemNine:String? =null

    lateinit var basicDetailsSaveBtn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_details)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

        editProfileCreatedFor = findViewById(R.id.editProfileCreatedFor)
        editDateOfBirth = findViewById(R.id.editDateOfBirth)
        editName= findViewById(R.id.editName)
        editHeight = findViewById(R.id.editHeight)
        editWeight = findViewById(R.id.editWeight)
        editMaritalStatus = findViewById(R.id.editMaritalStatus)
        editBodyType = findViewById(R.id.editBodyType)
        editMotherTongue = findViewById(R.id.editMotherTongue)
        editEatingHabits = findViewById(R.id.editEatingHabits)
        editDrinkingHabits = findViewById(R.id.editDrinkingHabits)
        editSmokingHabits = findViewById(R.id.editSmokingHabits)

        basicDetailsSaveBtn = findViewById(R.id.basicDetailsSaveBtn)

        basicDetailsSaveBtn.setOnClickListener {
                 save()
        }


        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
            DOB="$dayOfMonth/$monthOfYear/$year"
        }

        editDateOfBirth.setOnClickListener {
            DatePickerDialog(this@BasicDetails, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        val editProfileCreatedForArray= arrayOf( "--Select--","My Self","Son","Brother","Relative","Friend")
        val arrayAdapter1= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editProfileCreatedForArray)
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
        val editHeightArray= arrayOf("4 Ft 6 In","4 Ft 7 In","4 Ft 8 In","4 Ft 9 In","4 Ft 10 In","4 Ft 11 In","5 Ft", "5 Ft 1 In","5 Ft 2 In","5 Ft 3 In",
            "5 Ft 4 In","5 Ft 5 In","5 Ft 6 In","5 Ft 7 In","5 Ft 8 In","5 Ft 9 In","5 Ft 10 In","5 Ft 11 In","6 Ft","6 Ft 1 In","6 Ft 1 In","6 Ft 2 In","6 Ft 3 In"
            ,"6 Ft 4 In","6 Ft 5 In","6 Ft 6 In","6 Ft 7 In","6 Ft 8 In","6 Ft 9 In","6 Ft 10 In","6 Ft 11 In","7 Ft")
        val arrayAdapter2= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editHeightArray)
        editHeight.adapter=arrayAdapter2
        editHeight.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemTwo=null
                }else{
                    spinnerSelectedItemTwo=editHeight.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }

        val editWeightArray= arrayOf( "--Select--", "21 kgs","22 kgs","23 kgs","24 kgs","25 kgs","26 kgs","27 kgs","28 kgs","29 kgs","30 kgs","31 kgs"
            ,"32 kgs","33 kgs","34kgs","35 kgs", "36 kgs", "37 kgs", "38 kgs", "39 kgs","40 kgs","41 kgs","42 kgs","43 kgs","44 kgs","45 kgs","46 kgs"
            ,"47 kgs","48 kgs","49kgs","50 kgs","51 kgs","52 kgs","53 kgs","54 kgs","55 kgs","56 kgs","57 kgs","58 kgs","59 kgs","60 kgs","61 kgs","62 kgs"
            ,"63 kgs", "64 kgs","65kgs","66 kgs","67 kgs","68 kgs","69 kgs","70 kgs")
        val arrayAdapter3= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editWeightArray)
        editWeight.adapter=arrayAdapter3
        editWeight.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemThree=null
                }else{
                    spinnerSelectedItemThree=editWeight.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }

        val editMaritalStatusArray= arrayOf("--Select--", "Married","Widower", "Divorced", "Awaiting Divorce")
        val arrayAdapter4= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editMaritalStatusArray)
        editMaritalStatus.adapter=arrayAdapter4
        editMaritalStatus.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFour=null
                }else{
                    spinnerSelectedItemFour=editMaritalStatus.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editBodyTypeArray= arrayOf( "--Select--", "Average","Athletic","Slim","Heavy")
        val arrayAdapter5= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editBodyTypeArray)
        editBodyType.adapter=arrayAdapter5
        editBodyType.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFive=null
                }else{
                    spinnerSelectedItemFive=editBodyType.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editMotherTongueArray= arrayOf( "--Select--", "Marathi","Hindi","Bengali","Telugu","Tamil","Gujarati","Urdu","Bhojpuri","Kannada","Malayalam",
            "Odia","Punjabi","Rajasthani","Chhattisgarhi","Assamese","Assamese","Maithili","Magadhi/Magahi","Haryanvi","Khortha/Khotta","Marwari"
            ,"Santali","Kashmiri","Bundeli/Bundel khandi","Malvi","Sadan/Sadri","Mewari","Awadhi","Wagdi","Lamani/Lambadi","Pahari[c]","Bhili/Bhilodi",
            "Hara/Harauti","Nepali","Gondi","Bagheli/Baghel Khandi","Sambalpuri","Dogri","Garhwali","Nimadi","Surjapuri","Konkani","Kumauni","Kurukh/Oraon",
            "Tulu","Manipuri","Surgujia","Sindhi","Bagri","Ahirani","Banjari","Brajbhasha","Dhundhari","Bodo/Boro","ojri/Gujjari/Gujar",
            "Mundari","Garo","Kangri","Khasi","Kachchhi")
        val arrayAdapter6= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editMotherTongueArray)
        editMotherTongue.adapter=arrayAdapter6
        editMotherTongue.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemSix=null
                }else{
                    spinnerSelectedItemSix=editMotherTongue.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editEatingHabitsArray= arrayOf( "--Select--", "Vegetarian","Non-Vegetarian","Eggetarian")
        val arrayAdapter7= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editEatingHabitsArray)
        editEatingHabits.adapter=arrayAdapter7
        editEatingHabits.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemSeven=null
                }else{
                    spinnerSelectedItemSeven=editEatingHabits.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editDrinkingHabitsArray= arrayOf( "--Select--", "No","Occasionally","Yes")
        val arrayAdapter8= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editDrinkingHabitsArray)
        editDrinkingHabits.adapter=arrayAdapter8
        editDrinkingHabits.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemEight=null
                }else{
                    spinnerSelectedItemEight=editDrinkingHabits.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editSmokingHabitsArray= arrayOf( "--Select--", "No","Occasionally","Yes")
        val arrayAdapter9= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editSmokingHabitsArray)
        editSmokingHabits.adapter=arrayAdapter9
        editSmokingHabits.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemNine=null
                }else{
                    spinnerSelectedItemNine=editSmokingHabits.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }

    }

    private fun save() {
        val profileCreatedFor=spinnerSelectedItemOne
        val name=editName.text.toString().trim()
        val dateOfBirth=DOB
        val height=spinnerSelectedItemTwo
        val weight=spinnerSelectedItemThree
        val maritalStatus=spinnerSelectedItemFour
        val bodyType=spinnerSelectedItemFive
        val motherTongue=spinnerSelectedItemSix
        val eatingHabits=spinnerSelectedItemSeven
        val drinkingHabits=spinnerSelectedItemEight
        val smokingHabits=spinnerSelectedItemNine

        if (spinnerSelectedItemOne==null)
        {
            Toast.makeText(this, "Please Select Profile Created For ", Toast.LENGTH_SHORT).show()
            return
        }
        if (editName.text.trim().toString().isEmpty())
        {
            editName.error = "Please Enter Full Name"
            editName.requestFocus()
            return
        }
        if (editDateOfBirth.text.trim().toString().isEmpty())
        {
            Toast.makeText(this, "Please Date Of Birth ", Toast.LENGTH_SHORT).show()
            return

        }
        if (spinnerSelectedItemTwo==null)
        {
            Toast.makeText(this, "Please Select Height  ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemThree==null)
        {
            Toast.makeText(this, "Please Select Weight ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemFour==null)
        {
            Toast.makeText(this, "Please Select Marital Status  ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemFive==null)
        {
            Toast.makeText(this, "Please Select BodyType ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemSix==null)
        {
            Toast.makeText(this, "Please Select MotherTongue", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemSeven==null)
        {
            Toast.makeText(this, "Please Select Eating Habits ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemEight==null)
        {
            Toast.makeText(this, "Please Select Drinking Habits  ", Toast.LENGTH_SHORT).show()
            return
        }
        if (spinnerSelectedItemNine==null)
        {
            Toast.makeText(this, "Please Select Smoking Habits", Toast.LENGTH_SHORT).show()
            return
        }

        userID= auth.currentUser!!.uid
        docRef=db.collection("Users").document(userID)
         val write=WriteBasicDetails(profileCreatedFor,name,dateOfBirth,height,weight,maritalStatus,bodyType,motherTongue,eatingHabits,drinkingHabits,smokingHabits)
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

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        editDateOfBirth.text = sdf.format(cal.time)
    }


}

