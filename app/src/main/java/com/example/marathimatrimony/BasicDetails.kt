package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BasicDetails : AppCompatActivity() {


    lateinit var spinOne :Spinner
    lateinit var spinTwo :Spinner
    lateinit var spinThree  :Spinner
    lateinit var spinFour  :Spinner
    lateinit var spinFive :Spinner
    lateinit var spinSix :Spinner
    lateinit var spinSeven :Spinner
    lateinit var spinEight  :Spinner
    lateinit var spinNine  :Spinner


    lateinit var mySelectedItemOne:String
    lateinit var mySelectedItemTwo:String
    lateinit var mySelectedItemThree:String
    lateinit var mySelectedItemFour:String
    lateinit var mySelectedItemFive:String
    lateinit var mySelectedItemSix:String
    lateinit var mySelectedItemSeven:String
    lateinit var mySelectedItemEight:String
    lateinit var mySelectedItemNine:String

    lateinit var basicDetailsSaveBtn:Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_details)

        spinOne = findViewById(R.id.editProfileCreatedFor)
        spinTwo = findViewById(R.id.editHeight)
        spinThree = findViewById(R.id.editWeight)
        spinFour = findViewById(R.id.editMaritalStatus)
        spinFive = findViewById(R.id.editBodyType)
        spinSix = findViewById(R.id.editMotherTongue)
        spinSeven = findViewById(R.id.editEatingHabits)
        spinEight = findViewById(R.id.editDrinkingHabits)
        spinNine = findViewById(R.id.editSmokingHabits)

        basicDetailsSaveBtn = findViewById(R.id.basicDetailsSaveBtn)


        spinOne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                mySelectedItemOne=parent?.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinTwo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                mySelectedItemTwo=spinTwo.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinThree.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                mySelectedItemThree=spinThree.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinFour.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                mySelectedItemFour=spinFour.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinFive.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                mySelectedItemFive=spinFive.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinSix.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                mySelectedItemSix=spinSix.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinSeven.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                mySelectedItemSeven=spinSeven.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinEight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                mySelectedItemEight=spinEight.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinNine.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                mySelectedItemNine=spinNine.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }




        basicDetailsSaveBtn.setOnClickListener {

            val intent = Intent(this@BasicDetails,EditProfileActivity::class.java)
            intent.putExtra("MessageOne",mySelectedItemOne)
            intent.putExtra("MessageTwo",mySelectedItemTwo)
            intent.putExtra("MessageThree",mySelectedItemThree)
            intent.putExtra("MessageFour",mySelectedItemFour)
            intent.putExtra("MessageFive",mySelectedItemFive)
            intent.putExtra("MessageSix",mySelectedItemSix)
            intent.putExtra("MessageSeven",mySelectedItemSeven)
            intent.putExtra("MessageEight",mySelectedItemEight)
            intent.putExtra("MessageNine",mySelectedItemNine)
            startActivity(intent)
              saveValue(mySelectedItemOne)


        }

    }

    private fun saveValue(mySelectedItemOne: String) {
        if (mySelectedItemOne == "--Select--")
        {
             Toast.makeText(this,"select any one",Toast.LENGTH_SHORT).show()
        }
        else
        {
             this.mySelectedItemOne =mySelectedItemOne

        }
    }

}

