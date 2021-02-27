package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class EditProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID: String
    private lateinit var storageReference: StorageReference

    lateinit var profilePhoto: ImageView


    //   Basic Details
    var InMyOwnWord: TextView? = null

    var ProfileCreatedFor: TextView? = null
    var Name: TextView? = null
    var Age: TextView? = null
    var Height: TextView? = null
    var Weight: TextView? = null
    var MaritalStatus: TextView? = null
    var BodyType: TextView? = null
    var MotherTongue: TextView? = null
    var EatingHabits: TextView? = null
    var DrinkingHabits: TextView? = null
    var Smokinghabits: TextView? = null

    var Religion: TextView? = null
    var Caste: TextView? = null
    var SubCaste: TextView? = null
    var GothraM: TextView? = null
    var Star: TextView? = null
    var RaasiAndMoonSign: TextView? = null
    var ZodicAndStarSign: TextView? = null
    var HavingDosham: TextView? = null

    var EducationCategory: TextView? = null
    var CollageAndInstitution: TextView? = null
    var Occupation: TextView? = null
    var Organization: TextView? = null
    var EmployedIn:TextView? = null
    var AnnualIncome:TextView? = null

    var Country: TextView? = null
    var State: TextView? = null
    var City: TextView? = null
    var Citizenship: TextView? = null

    var FamilyValues: TextView? = null
    var familyType: TextView? = null
    var familyStatus: TextView? = null
    var fathersOccupation: TextView? = null
    var mothersOccupation: TextView? = null
    var Brothers: TextView? = null
    var brothersMarried: TextView? = null
    var Sisters: TextView? = null
    var sistersMarried: TextView? = null
    var ancestralOrigin: TextView? = null

    var Hobbies: TextView? = null
    var Interest: TextView? = null
    var Music: TextView? = null
    var Reads: TextView? = null
    var Movies: TextView? = null
    var Sports: TextView? = null
    var Cuisines: TextView? = null
    var dressStyle: TextView? = null
    var spokenLanguages: TextView? = null

    var BPAge: TextView? = null
    var BPHeight: TextView? = null
    var BPMaritalStatus: TextView? = null
    var BPHaveChildren: TextView? = null
    var BPPhysicalStatus: TextView? = null
    var BPEatingHabits: TextView? = null
    var BPDrinkingHabits: TextView? = null
    var BPSmokingHabits: TextView? = null


    lateinit var in_my_own_words_card_view: LinearLayout
    lateinit var basic_details_card_view: LinearLayout
    lateinit var religious_information_card_view: LinearLayout
    lateinit var professional_information_card_view: LinearLayout
    lateinit var location_card_view: LinearLayout
    lateinit var family_details_card_view: LinearLayout
    lateinit var about_my_family_card_view: LinearLayout
    lateinit var hobbies_and_interests_card_view: LinearLayout
    lateinit var basic_preferences_card_view: LinearLayout
    lateinit var religious_preferences_card_view: LinearLayout
    lateinit var professional_preferences_card_view: LinearLayout
    lateinit var location_preferences_card_view: LinearLayout
    lateinit var what_i_am_looking_for_card_view: LinearLayout
    lateinit var add_contacts: LinearLayout
    lateinit var add_horoscope: LinearLayout
    lateinit var add_photos: LinearLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        //   Basic Details

        InMyOwnWord = findViewById(R.id.InMyOwnWords)

        ProfileCreatedFor = findViewById(R.id.ProfileCreatedFor)
        Name = findViewById(R.id.Name)
        Age = findViewById(R.id.Age)
        Height = findViewById(R.id.Height)
        Weight = findViewById(R.id.Weight)
        MaritalStatus = findViewById(R.id.MaritalStatus)
        BodyType = findViewById(R.id.BodyType)
        MotherTongue = findViewById(R.id.MotherTongue)
        EatingHabits = findViewById(R.id.eatingHabits)
        DrinkingHabits = findViewById(R.id.drinkingHabits)
        Smokinghabits = findViewById(R.id.smoking_habits)

        Religion = findViewById(R.id.Religion)
        Caste = findViewById(R.id.Caste)
        GothraM = findViewById(R.id.GothraM)
        Star = findViewById(R.id.Star)
        ZodicAndStarSign = findViewById(R.id.Zodic)
        RaasiAndMoonSign = findViewById(R.id.Raasi)

        EducationCategory= findViewById(R.id.EducationCategory)
        CollageAndInstitution= findViewById(R.id.CollageInstitution)
        Occupation= findViewById(R.id.Occupation)
        Organization= findViewById(R.id.Organization)
        EmployedIn= findViewById(R.id.EmployedIn)
        AnnualIncome= findViewById(R.id.AnnualIncome)

        Country= findViewById(R.id.country)
        State= findViewById(R.id.state)
        City= findViewById(R.id.city)
        Citizenship= findViewById(R.id.citizenship)

        FamilyValues= findViewById(R.id.familyValues)
        familyType= findViewById(R.id.familyType)
        familyStatus= findViewById(R.id.familyStatus)
        fathersOccupation= findViewById(R.id.fathersOccupation)
        mothersOccupation= findViewById(R.id.mothersOccupation)
        Brothers= findViewById(R.id.Brothers)
        brothersMarried= findViewById(R.id.brothersMarried)
        Sisters= findViewById(R.id.Sisters)
        sistersMarried= findViewById(R.id.sistersMarried)
        ancestralOrigin= findViewById(R.id.ancestralOrigin)

        Hobbies= findViewById(R.id.Hobbies)
        Interest= findViewById(R.id.Interest)
        Music= findViewById(R.id.Music)
        Reads= findViewById(R.id.Reads)
        Movies= findViewById(R.id.Movies)
        Sports= findViewById(R.id.Sports)
        Cuisines= findViewById(R.id.Cuisines)
        dressStyle= findViewById(R.id.dressStyle)
        spokenLanguages= findViewById(R.id.spokenLanguages)

        BPAge= findViewById(R.id.bPAge)
        BPHeight= findViewById(R.id.bPHeight)
        BPMaritalStatus= findViewById(R.id.bPMaritalStatus)
        BPHaveChildren= findViewById(R.id.bPHaveChildren)
        BPPhysicalStatus= findViewById(R.id.bPPhysicalStatus)
        BPEatingHabits= findViewById(R.id.bPEatingHabits)
        BPDrinkingHabits= findViewById(R.id.bPDrinkingHabits)
        BPSmokingHabits= findViewById(R.id.bPSmokingHabits)

        profilePhoto= findViewById(R.id.profilePhoto)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userID = auth.currentUser!!.uid
        docRef = db.collection("Users").document(userID)
        storageReference= FirebaseStorage.getInstance().reference


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

        inMyOwnWords()
        basicDetails()
        religiousInformation()
        professionalInformation()
        location()
        familyDetails()
        hobbiesAndInterests()
        basicPreferences()



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
        getUserInfo()
    }
    private fun getUserInfo() {

        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {

                    val image: String? =snapshot.getString("imageUrl")
                    Picasso.get().load(image).into(profilePhoto)
                }
            }
        }
    }



    private fun inMyOwnWords() {
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {

                    InMyOwnWord!!.text = snapshot.getString("inMyOwnWords")

                }
            }
        }
    }


    private fun basicDetails() {
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {
                    ProfileCreatedFor!!.text = snapshot.getString("profileCreatedFor")
                    Name!!.text = snapshot.getString("name")
                    Age!!.text = snapshot.getString("dateOfBirth")
                    Height!!.text = snapshot.getString("height")
                    Weight!!.text = snapshot.getString("weight")
                    MaritalStatus!!.text = snapshot.getString("maritalStatus")
                    BodyType!!.text = snapshot.getString("bodyType")
                    MotherTongue!!.text = snapshot.getString("motherTongue")
                    EatingHabits!!.text = snapshot.getString("eatingHabits")
                    DrinkingHabits!!.text = snapshot.getString("drinkingHabits")
                    Smokinghabits!!.text = snapshot.getString("smokingHabits")


                }
            }
        }
    }

    private fun religiousInformation() {
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {
                    Religion!!.text = snapshot.getString("religion")
                    Caste!!.text = snapshot.getString("caste")
                    GothraM!!.text = snapshot.getString("gothraM")
                    Star!!.text = snapshot.getString("star")
                    RaasiAndMoonSign!!.text = snapshot.getString("raasiAndMoonSign")
                    ZodicAndStarSign!!.text = snapshot.getString("zodicAndStarSign")
                }
            }
        }
    }
    private fun professionalInformation() {
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {
                    EducationCategory!!.text = snapshot.getString("highestEducation")
                    CollageAndInstitution!!.text = snapshot.getString("collegeAndInstitution")
                    Occupation!!.text = snapshot.getString("occupation")
                    Organization!!.text = snapshot.getString("organization")
                    EmployedIn!!.text = snapshot.getString("employedIn")
                    AnnualIncome!!.text = snapshot.getString("annualIncome")
                }
            }
        }
    }
    private fun location() {
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {
                    Country!!.text = snapshot.getString("countryLivingIn")
                    State!!.text = snapshot.getString("residingState")
                    City!!.text = snapshot.getString("residingCity")
                    Citizenship!!.text = snapshot.getString("citizenship")

                }
            }
        }
    }
    private fun familyDetails() {
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {
                    FamilyValues!!.text = snapshot.getString("familyValues")
                    familyType!!.text = snapshot.getString("familyType")
                    familyStatus!!.text = snapshot.getString("familyStatus")
                    fathersOccupation!!.text = snapshot.getString("fathersOccupation")
                    mothersOccupation!!.text = snapshot.getString("mothersOccupation")
                    Brothers!!.text = snapshot.getString("brothers")
                    brothersMarried!!.text = snapshot.getString("brothersMarried")
                    Sisters!!.text = snapshot.getString("sisters")
                    sistersMarried!!.text = snapshot.getString("sistersMarried")
                    ancestralOrigin!!.text = snapshot.getString("ancestralOrigin")

                }
            }
        }
    }
    private fun hobbiesAndInterests() {
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {
                    Hobbies!!.text = snapshot.getString("hobbies")
                    Interest!!.text = snapshot.getString("interests")
                    Music!!.text = snapshot.getString("favouriteMusic")
                    Reads!!.text = snapshot.getString("favouriteReads")
                    Movies!!.text = snapshot.getString("preferredMovies")
                    Sports!!.text = snapshot.getString("sportsAndFitnessActivities")
                    Cuisines!!.text = snapshot.getString("favouriteCuisine")
                    dressStyle!!.text = snapshot.getString("preferredDressStyle")
                    spokenLanguages!!.text = snapshot.getString("spokenLanguages")

                }
            }
        }
    }

    private fun basicPreferences() {
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {
                    BPAge!!.text = snapshot.getString("bPAge")
                    BPHeight!!.text = snapshot.getString("bPHeight")
                    BPMaritalStatus!!.text = snapshot.getString("bPMaritalStatus")
                    BPHaveChildren!!.text = snapshot.getString("bPHaveChildren")
                    BPPhysicalStatus!!.text = snapshot.getString("bPPhysicalStatus")
                    BPEatingHabits!!.text = snapshot.getString("bPEatingHabits")
                    BPDrinkingHabits!!.text = snapshot.getString("bPDrinkingHabits")
                    BPSmokingHabits!!.text = snapshot.getString("bPSmokingHabits")
                }
            }
        }
    }
}
