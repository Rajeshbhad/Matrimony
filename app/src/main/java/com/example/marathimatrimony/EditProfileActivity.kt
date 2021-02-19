package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity(){
       //   Basic Details
       var deafaultOne: TextView?=null
       var deafaultTwo: TextView?=null
       var deafaultThree: TextView?=null
       var deafaultFour: TextView?=null
       var deafaultFive: TextView?=null
       var deafaultSix: TextView?=null
       var deafaultSeven: TextView?=null
       var deafaultEight: TextView?=null
       var deafaultNine: TextView?=null


     lateinit var in_my_own_words_card_view :LinearLayout
     lateinit var basic_details_card_view :LinearLayout
     lateinit var religious_information_card_view :LinearLayout
     lateinit var professional_information_card_view :LinearLayout
     lateinit var location_card_view :LinearLayout
     lateinit var family_details_card_view :LinearLayout
     lateinit var about_my_family_card_view :LinearLayout
     lateinit var hobbies_and_interests_card_view :LinearLayout
     lateinit var basic_preferences_card_view :LinearLayout
     lateinit var religious_preferences_card_view :LinearLayout
     lateinit var professional_preferences_card_view :LinearLayout
     lateinit var location_preferences_card_view :LinearLayout
     lateinit var what_i_am_looking_for_card_view :LinearLayout
     lateinit var add_contacts :LinearLayout
     lateinit var add_horoscope :LinearLayout
     lateinit var add_photos :LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        //   Basic Details
        deafaultOne=findViewById(R.id.ProfileCreatedFor)
        deafaultTwo=findViewById(R.id.Height)
        deafaultThree=findViewById(R.id.Weight)
        deafaultFour=findViewById(R.id.MaritalStatus)
        deafaultFive=findViewById(R.id.BodyType)
        deafaultSix=findViewById(R.id.MotherTongue)
        deafaultSeven=findViewById(R.id.eatingHabits)
        deafaultEight=findViewById(R.id.drinkingHabits)
        deafaultNine=findViewById(R.id.smoking_habits)


        add_contacts = findViewById(R.id.add_contacts)
        add_horoscope = findViewById(R.id.add_horoscope)
        add_photos = findViewById(R.id.add_photos)
        in_my_own_words_card_view = findViewById(R.id.in_my_own_words_card_view)
        basic_details_card_view = findViewById(R.id.basic_details_card_view)
        religious_information_card_view = findViewById(R.id.religious_information_card_view)
        professional_information_card_view = findViewById(R.id.professional_information_card_view)
        location_card_view = findViewById(R.id.location_card_view)
        family_details_card_view = findViewById(R.id.family_details_card_view)
        about_my_family_card_view = findViewById(R.id.about_my_family_card_view)
        hobbies_and_interests_card_view = findViewById(R.id.hobbies_and_interests_card_view)
        basic_preferences_card_view = findViewById(R.id.basic_preferences_card_view)
        religious_preferences_card_view = findViewById(R.id.religious_preferences_card_view)
        professional_preferences_card_view = findViewById(R.id.professional_preferences_card_view)
        location_preferences_card_view = findViewById(R.id.location_preferences_card_view)
        what_i_am_looking_for_card_view = findViewById(R.id.what_i_am_looking_for_card_view)

        //   Basic Details
        val msgOne=intent.getStringExtra("MessageOne")
        deafaultOne?.text = ":$msgOne"
//        val msgTwo=intent.getStringExtra("MessageTwo")
//        deafaultTwo?.text = ":$msgTwo"
//        val msgThree=intent.getStringExtra("MessageThree")
//        deafaultThree?.text = ":$msgThree"
//        val msgFour=intent.getStringExtra("MessageFour")
//        deafaultFour?.text = ":$msgFour"
//        val msgFive=intent.getStringExtra("MessageFive")
//        deafaultFive?.text = ":$msgFive"
//        val msgSix=intent.getStringExtra("MessageSix")
//        deafaultSix?.text = ":$msgSix"
//        val msgSeven=intent.getStringExtra("MessageSeven")
//        deafaultSeven?.text = ":$msgSeven"
//        val msgEight=intent.getStringExtra("MessageEight")
//        deafaultEight?.text = ":$msgEight"
//        val msgNine=intent.getStringExtra("MessageNine")
//        deafaultNine?.text = ":$msgNine"


        add_contacts.setOnClickListener {
            val intent = Intent(this, EditContact::class.java)
            startActivity(intent)
        }
        add_horoscope.setOnClickListener {
            val intent = Intent(this, AddHoroscope::class.java)
            startActivity(intent)
        }

        add_photos.setOnClickListener {
            val intent = Intent(this, AddPhotos::class.java)
            startActivity(intent)
        }
        in_my_own_words_card_view.setOnClickListener {
            val intent = Intent(this, InMyOwnWords::class.java)
            startActivity(intent)
        }
        basic_details_card_view.setOnClickListener {
            val intent = Intent(this, BasicDetails::class.java)
            startActivity(intent)
        }
        religious_information_card_view.setOnClickListener {
            val intent = Intent(this, ReligiousInformation::class.java)
            startActivity(intent)

        }
        professional_information_card_view.setOnClickListener {
            val intent = Intent(this, ProfessionalInformation::class.java)
            startActivity(intent)

        }
        location_card_view.setOnClickListener {
            val intent = Intent(this, Location::class.java)
            startActivity(intent)

        }
        family_details_card_view.setOnClickListener {
            val intent = Intent(this, FamilyDetails::class.java)
            startActivity(intent)
        }
        about_my_family_card_view.setOnClickListener {
            val intent = Intent(this, AboutMyFamily::class.java)
            startActivity(intent)

        }
        hobbies_and_interests_card_view.setOnClickListener {
            val intent = Intent(this, HobbiesAndInterest::class.java)
            startActivity(intent)
        }
        basic_preferences_card_view.setOnClickListener {
            val intent = Intent(this, BasicPreferences::class.java)
            startActivity(intent)
        }
        religious_preferences_card_view.setOnClickListener {
            val intent = Intent(this, ReligiousPreferences::class.java)
            startActivity(intent)
        }
        professional_preferences_card_view.setOnClickListener {
            val intent = Intent(this, ProfessionalPreferences::class.java)
            startActivity(intent)
        }
        location_preferences_card_view.setOnClickListener {
            val intent = Intent(this, LocationPreferences::class.java)
            startActivity(intent)
        }
        what_i_am_looking_for_card_view.setOnClickListener {
            val intent = Intent(this, WhatIAmLookingFor::class.java)
            startActivity(intent)
        }

    }
}