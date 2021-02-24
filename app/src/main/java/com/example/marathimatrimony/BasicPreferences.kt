package com.example.marathimatrimony

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteBasicPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.*

class BasicPreferences : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String

    lateinit var editAgeFrom  : Spinner
    lateinit var editAgeTo  : Spinner
    lateinit var editHaveChildren  : Spinner
    lateinit var editHeightFrom : Spinner
    lateinit var editHeightTo : Spinner
    lateinit var editPhysicalStatus : Spinner
    lateinit var editMaritalStatus: TextView
    lateinit var editEatingHabits: TextView
    lateinit var editDrinkingHabits: TextView
    lateinit var editSmokingHabits: TextView

    lateinit var selectedMaritalStatus: BooleanArray
    lateinit var selectedEatingHabits: BooleanArray
    lateinit var selectedDrinkingHabits: BooleanArray
    lateinit var selectedSmokingHabits: BooleanArray

    var maritalStatusList: ArrayList<Int> = ArrayList()
    var eatingHabitsList: ArrayList<Int> = ArrayList()
    var drinkingHabitsList: ArrayList<Int> = ArrayList()
    var smokingHabitsList: ArrayList<Int> = ArrayList()

    private var maritalStatusArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var eatingHabitsArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday")
    private var drinkingHabitsArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var smokingHabitsArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")

    var spinnerSelectedItemOne: String? =null
    var spinnerSelectedItemTwo: String? =null
    var spinnerSelectedItemThree:String? =null
    var spinnerSelectedItemFour:String? =null
    var spinnerSelectedItemFive: String? =null
    var spinnerSelectedItemSix:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_prefereces)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()



        editAgeFrom = findViewById(R.id.editAgeFrom)
        editAgeTo = findViewById(R.id.editAgeTo)
        editHaveChildren= findViewById(R.id.editHaveChildren)
        editHeightFrom = findViewById(R.id.editHeightFrom)
        editHeightTo = findViewById(R.id.editHeightTo)
        editPhysicalStatus = findViewById(R.id.editPhysicalStatus)
        editMaritalStatus = findViewById(R.id.editMaritalStatus)
        editEatingHabits = findViewById(R.id.editEatingHabits)
        editDrinkingHabits= findViewById(R.id.editDrinkingHabits)
        editSmokingHabits = findViewById(R.id.editSmokingHabits)

                 save()

        val editAgeFromArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter1= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editAgeFromArray)
        editAgeFrom.adapter=arrayAdapter1
        editAgeFrom.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemOne=null
                }else{
                    spinnerSelectedItemOne=editAgeFrom.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editAgeToArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter2= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editAgeToArray)
        editAgeTo.adapter=arrayAdapter2
        editAgeTo.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemTwo=null
                }else{
                    spinnerSelectedItemTwo=editAgeTo.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }

        val editHaveChildrenArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter3= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editHaveChildrenArray)
        editHaveChildren.adapter=arrayAdapter3
        editHaveChildren.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemThree=null
                }else{
                    spinnerSelectedItemThree=editHaveChildren.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }

        val editHeightFromArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter4= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editHeightFromArray)
        editHeightFrom.adapter=arrayAdapter4
        editHeightFrom.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFour=null
                }else{
                    spinnerSelectedItemFour=editHeightFrom.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editHeightToArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter5= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editHeightToArray)
        editHeightTo.adapter=arrayAdapter5
        editHeightTo.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemFive=null
                }else{
                    spinnerSelectedItemFive=editHeightTo.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        val editPhysicalStatusArray= arrayOf( "--Select--","Son","Brother","Relative","Friend")
        val arrayAdapter6= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editPhysicalStatusArray)
        editPhysicalStatus.adapter=arrayAdapter6
        editPhysicalStatus.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemSix=null
                }else{
                    spinnerSelectedItemSix=editPhysicalStatus.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }
        maritalStatus()
        eatingHabits()
        drinkingHabits()
        smokingHabits()
    }


    private fun save() {
        val bPAgeFrom=spinnerSelectedItemOne
        val bPAgeTo=spinnerSelectedItemTwo
        val bPHaveChildren=spinnerSelectedItemThree
        val bPHeightFrom=spinnerSelectedItemFour
        val bPHeightTo=spinnerSelectedItemFive
        val bPPhysicalStatus=spinnerSelectedItemSix
        val bPMaritalStatus = editMaritalStatus.text.toString().trim()
        val bPEatingHabits = editEatingHabits.text.toString().trim()
        val bPDrinkingHabits = editDrinkingHabits.text.toString().trim()
        val bPSmokingHabits = editSmokingHabits.text.toString().trim()

        userID= auth.currentUser!!.uid
        docRef=db.collection("Users").document(userID)
        val write= WriteBasicPreferences(bPAgeFrom,bPAgeTo,bPHaveChildren,bPHeightFrom,bPHeightTo,bPPhysicalStatus,bPMaritalStatus,bPEatingHabits,bPDrinkingHabits,bPSmokingHabits)
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

    private fun smokingHabits() {
        selectedSmokingHabits = BooleanArray(smokingHabitsArray.size)
        editSmokingHabits.setOnClickListener {
            val builder = AlertDialog.Builder(this@BasicPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(smokingHabitsArray, selectedSmokingHabits) { _, i, b ->
                if (b) {
                    smokingHabitsList.add(i)
                } else {
                    smokingHabitsList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until smokingHabitsList.size) {
                    stringBuilder.append(smokingHabitsArray[smokingHabitsList[j]])

                    if (j in -1 until smokingHabitsList.size) {
                        stringBuilder.append(",")
                    }
                }
                editSmokingHabits.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until smokingHabitsList.size) {
                    selectedSmokingHabits[j] = false

                    smokingHabitsList.clear();

                    editSmokingHabits.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun drinkingHabits() {
        selectedDrinkingHabits = BooleanArray(drinkingHabitsArray.size)
        editDrinkingHabits.setOnClickListener {
            val builder = AlertDialog.Builder(this@BasicPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(drinkingHabitsArray, selectedDrinkingHabits) { _, i, b ->
                if (b) {
                    drinkingHabitsList.add(i)
                } else {
                    drinkingHabitsList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until drinkingHabitsList.size) {
                    stringBuilder.append(drinkingHabitsArray[drinkingHabitsList[j]])

                    if (j in -1 until drinkingHabitsList.size) {
                        stringBuilder.append(",")
                    }
                }
                editDrinkingHabits.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until drinkingHabitsList.size) {
                    selectedDrinkingHabits[j] = false

                    drinkingHabitsList.clear();

                    editDrinkingHabits.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun eatingHabits() {
        selectedEatingHabits = BooleanArray(eatingHabitsArray.size)
        editEatingHabits.setOnClickListener {
            val builder = AlertDialog.Builder(this@BasicPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(eatingHabitsArray, selectedEatingHabits) { _, i, b ->
                if (b) {
                    eatingHabitsList.add(i)
                } else {
                    eatingHabitsList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until eatingHabitsList.size) {
                    stringBuilder.append(eatingHabitsArray[eatingHabitsList[j]])

                    if (j in -1 until eatingHabitsList.size) {
                        stringBuilder.append(",")
                    }
                }
                editEatingHabits.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until eatingHabitsList.size) {
                    selectedEatingHabits[j] = false

                    eatingHabitsList.clear();

                    editEatingHabits.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun maritalStatus() {
        selectedMaritalStatus = BooleanArray(maritalStatusArray.size)
        editMaritalStatus.setOnClickListener {
            val builder = AlertDialog.Builder(this@BasicPreferences)
            builder.setTitle("Select")
                .setCancelable(false)
            builder.setMultiChoiceItems(maritalStatusArray, selectedMaritalStatus) { _, i, b ->
                if (b) {
                    maritalStatusList.add(i)
                } else {
                    maritalStatusList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until maritalStatusList.size) {
                    stringBuilder.append(maritalStatusArray[maritalStatusList[j]])

                    if (j in -1 until maritalStatusList.size) {
                        stringBuilder.append(",")
                    }
                }
                editMaritalStatus.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until maritalStatusList.size) {
                    selectedMaritalStatus[j] = false

                    maritalStatusList.clear();

                    editMaritalStatus.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}