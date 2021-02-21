package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class EditProfileActivity : AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String
       //   Basic Details
       var ProfileCreatedFor: TextView?=null
       var Name:TextView?=null
       var Age:TextView?=null
       var Height: TextView?=null
       var Weight: TextView?=null
       var MaritalStatus: TextView?=null
       var BodyType: TextView?=null
       var MotherTongue: TextView?=null
       var eatingHabits: TextView?=null
       var drinkingHabits: TextView?=null
       var smokinghabits: TextView?=null


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
        ProfileCreatedFor=findViewById(R.id.ProfileCreatedFor)
        Name=findViewById(R.id.Name)
        Age=findViewById(R.id.Age)
        Height=findViewById(R.id.Height)
        Weight=findViewById(R.id.Weight)
        MaritalStatus=findViewById(R.id.MaritalStatus)
        BodyType=findViewById(R.id.BodyType)
        MotherTongue=findViewById(R.id.MotherTongue)
        eatingHabits=findViewById(R.id.eatingHabits)
        drinkingHabits=findViewById(R.id.drinkingHabits)
        smokinghabits=findViewById(R.id.smoking_habits)

        auth= FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()
        userID= auth.currentUser!!.uid
        docRef = db.collection("Users").document(userID)

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

           basicDetails()



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

    private fun basicDetails() {
        docRef.addSnapshotListener{ snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {
                    ProfileCreatedFor!!.text = snapshot.getString("profileCreatedFor")
                    Name!!.text= snapshot.getString("name")
                    Age!!.text= snapshot.getString("dateOfBirth")
                    Height!!.text=snapshot.getString("height")
                    Weight!!.text=snapshot.getString("weight")
                    MaritalStatus!!.text=snapshot.getString("maritalStatus")
                    BodyType!!.text=snapshot.getString("bodyType")
                    MotherTongue!!.text=snapshot.getString("motherTongue")
                    eatingHabits!!.text=snapshot.getString("eatingHabits")
                    drinkingHabits!!.text=snapshot.getString("drinkingHabits")
                    smokinghabits!!.text=snapshot.getString("smokingHabits")

                }
            }
        }
    }
}