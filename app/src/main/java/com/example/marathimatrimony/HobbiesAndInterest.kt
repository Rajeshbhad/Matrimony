package com.example.marathimatrimony
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteHobbiesAndInterest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.*

class HobbiesAndInterest: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID: String

    lateinit var editHobbies: TextView
    lateinit var editInterests: TextView
    lateinit var editFavouriteMusic: TextView
    lateinit var editFavouriteReads: TextView
    lateinit var editPreferredMovies: TextView
    lateinit var editSportsAndFitnessActivities: TextView
    lateinit var editFavouriteCuisine: TextView
    lateinit var editPreferredDressStyle: TextView
    lateinit var editSpokenLanguages:TextView

    lateinit var hobbiesAndInterestSaveBtn: Button

    lateinit var selectedHobbies: BooleanArray
    lateinit var selectedInterests: BooleanArray
    lateinit var selectedFavouriteMusic: BooleanArray
    lateinit var selectedFavouriteReads: BooleanArray
    lateinit var selectedPreferredMovies: BooleanArray
    lateinit var selectedSportsAndFitnessActivities: BooleanArray
    lateinit var selectedFavouriteCuisine: BooleanArray
    lateinit var selectedPreferredDressStyle: BooleanArray
    lateinit var selectedSpokenLanguages: BooleanArray


    var hobbiesList: ArrayList<Int> = ArrayList()
    var interestsList: ArrayList<Int> = ArrayList()
    var favouriteMusicList: ArrayList<Int> = ArrayList()
    var favouriteReadsList: ArrayList<Int> = ArrayList()
    var preferredMoviesList: ArrayList<Int> = ArrayList()
    var sportsAndFitnessActivitiesList: ArrayList<Int> = ArrayList()
    var favouriteCuisineList: ArrayList<Int> = ArrayList()
    var preferredDressStyleList: ArrayList<Int> = ArrayList()
    var spokenLanguagesList: ArrayList<Int> = ArrayList()

    private var hobbiesArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var interestsArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday")
    private var favouriteMusicArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var favouriteReadsArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var preferredMoviesArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var sportsAndFitnessActivitiesArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var favouriteCuisineArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var preferredDressStyleArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")
    private var spokenLanguagesArray = arrayOf("Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday", "Sunday", "monday", "Tuesday", "wednesday", "Thursday", "friday", "saturday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobbies_and_interest)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        editHobbies = findViewById(R.id.editHobbies)
        editInterests = findViewById(R.id.editInterests)
        editFavouriteMusic = findViewById(R.id.editFavouriteMusic)
        editFavouriteReads = findViewById(R.id.editFavouriteReads)
        editPreferredMovies = findViewById(R.id.editPreferredMovies)
        editSportsAndFitnessActivities = findViewById(R.id.editSportsAndFitnessActivities)
        editFavouriteCuisine = findViewById(R.id.editFavouriteCuisine)
        editPreferredDressStyle = findViewById(R.id.editPreferredDressStyle)
        editSpokenLanguages= findViewById(R.id.editSpokenLanguages)

        hobbiesAndInterestSaveBtn = findViewById(R.id.hobbiesAndInterestSaveBtn)
        hobbiesAndInterestSaveBtn.setOnClickListener {

            save()
        }
        hobbies()
        interests()
        favouriteMusic()
        favouriteReads()
        preferredMovies()
        sportsAndFitnessActivities()
        favouriteCuisine()
        preferredDressStyle()
        spokenLanguages()

    }


    private fun save() {
        val hobbies = editHobbies.text.toString().trim()
        val interests = editInterests.text.toString().trim()
        val favouriteReads = editFavouriteReads.text.toString().trim()
        val favouriteMusic = editFavouriteMusic.text.toString().trim()
        val preferredMovies = editPreferredMovies.text.toString().trim()
        val sportsAndFitnessActivities = editSportsAndFitnessActivities.text.toString().trim()
        val favouriteCuisine = editFavouriteCuisine.text.toString().trim()
        val preferredDressStyle = editPreferredDressStyle.text.toString().trim()
        val spokenLanguages = editSpokenLanguages.text.toString().trim()

        if (editHobbies.text.trim().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Hobbies", Toast.LENGTH_SHORT).show()
            return
        }

        userID = auth.currentUser!!.uid
        docRef = db.collection("Users").document(userID)
        val write = WriteHobbiesAndInterest(hobbies,interests,favouriteReads,favouriteMusic,preferredMovies,sportsAndFitnessActivities,favouriteCuisine,preferredDressStyle,spokenLanguages)
        docRef.set(write, SetOptions.merge()).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Save Successfully.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Save Failed.", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun hobbies() {
        selectedHobbies = BooleanArray(hobbiesArray.size)
        editHobbies.setOnClickListener {
            val builder = AlertDialog.Builder(this@HobbiesAndInterest)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(hobbiesArray, selectedHobbies) { _, i, b ->
                if (b) {
                    hobbiesList.add(i)
                } else {
                    hobbiesList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until hobbiesList.size) {
                    stringBuilder.append(hobbiesArray[hobbiesList[j]])

                    if (j in -1 until hobbiesList.size) {
                        stringBuilder.append(",")
                    }
                }
                editHobbies.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until hobbiesList.size) {
                    selectedHobbies[j] = false

                    hobbiesList.clear();

                    editHobbies.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun interests() {
        selectedInterests = BooleanArray(interestsArray.size)
        editInterests.setOnClickListener {
            val builder = AlertDialog.Builder(this@HobbiesAndInterest)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(interestsArray, selectedInterests) { _, i, b ->
                if (b) {
                    interestsList.add(i)
                } else {
                    interestsList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until interestsList.size) {
                    stringBuilder.append(interestsArray[interestsList[j]])

                    if (j in -1 until interestsList.size) {
                        stringBuilder.append(",")
                    }
                }
                editInterests.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until interestsList.size) {
                    selectedInterests[j] = false

                    interestsList.clear();

                    editInterests.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun favouriteReads() {
        selectedFavouriteMusic = BooleanArray(favouriteMusicArray.size)
        editFavouriteMusic.setOnClickListener {
            val builder = AlertDialog.Builder(this@HobbiesAndInterest)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(favouriteMusicArray, selectedFavouriteMusic) { _, i, b ->
                if (b) {
                    favouriteMusicList.add(i)
                } else {
                    favouriteMusicList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until favouriteMusicList.size) {
                    stringBuilder.append(favouriteMusicArray[favouriteMusicList[j]])

                    if (j in -1 until favouriteMusicList.size) {
                        stringBuilder.append(",")
                    }
                }
                editFavouriteMusic.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until favouriteMusicList.size) {
                    selectedFavouriteMusic[j] = false

                    favouriteMusicList.clear();

                    editFavouriteMusic.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun favouriteMusic() {
        selectedFavouriteReads = BooleanArray(favouriteReadsArray.size)
        editFavouriteReads.setOnClickListener {
            val builder = AlertDialog.Builder(this@HobbiesAndInterest)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(favouriteReadsArray, selectedFavouriteReads) { _, i, b ->
                if (b) {
                    favouriteReadsList.add(i)
                } else {
                    favouriteReadsList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until favouriteReadsList.size) {
                    stringBuilder.append(favouriteReadsArray[favouriteReadsList[j]])

                    if (j in -1 until favouriteReadsList.size) {
                        stringBuilder.append(",")
                    }
                }
                editFavouriteReads.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until favouriteReadsList.size) {
                    selectedFavouriteReads[j] = false

                    favouriteReadsList.clear();

                    editFavouriteReads.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun preferredMovies() {
        selectedPreferredMovies = BooleanArray(preferredMoviesArray.size)
        editPreferredMovies.setOnClickListener {
            val builder = AlertDialog.Builder(this@HobbiesAndInterest)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(preferredMoviesArray, selectedPreferredMovies) { _, i, b ->
                if (b) {
                    preferredMoviesList.add(i)
                } else {
                    preferredMoviesList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until preferredMoviesList.size) {
                    stringBuilder.append(preferredMoviesArray[preferredMoviesList[j]])

                    if (j in -1 until preferredMoviesList.size) {
                        stringBuilder.append(",")
                    }
                }
                editPreferredMovies.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until preferredMoviesList.size) {
                    selectedPreferredMovies[j] = false

                    preferredMoviesList.clear();

                    editPreferredMovies.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun sportsAndFitnessActivities() {
        selectedSportsAndFitnessActivities = BooleanArray(sportsAndFitnessActivitiesArray.size)
        editSportsAndFitnessActivities.setOnClickListener {
            val builder = AlertDialog.Builder(this@HobbiesAndInterest)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(sportsAndFitnessActivitiesArray, selectedSportsAndFitnessActivities) { _, i, b ->
                if (b) {
                    sportsAndFitnessActivitiesList.add(i)
                } else {
                    sportsAndFitnessActivitiesList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until sportsAndFitnessActivitiesList.size) {
                    stringBuilder.append(sportsAndFitnessActivitiesArray[sportsAndFitnessActivitiesList[j]])

                    if (j in -1 until sportsAndFitnessActivitiesList.size) {
                        stringBuilder.append(",")
                    }
                }
                editSportsAndFitnessActivities.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until sportsAndFitnessActivitiesList.size) {
                    selectedSportsAndFitnessActivities[j] = false

                    sportsAndFitnessActivitiesList.clear();

                    editSportsAndFitnessActivities.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun favouriteCuisine() {
        selectedFavouriteCuisine = BooleanArray(favouriteCuisineArray.size)
        editFavouriteCuisine.setOnClickListener {
            val builder = AlertDialog.Builder(this@HobbiesAndInterest)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(favouriteCuisineArray, selectedFavouriteCuisine) { _, i, b ->
                if (b) {
                    favouriteCuisineList.add(i)
                } else {
                    favouriteCuisineList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until favouriteCuisineList.size) {
                    stringBuilder.append(favouriteCuisineArray[favouriteCuisineList[j]])

                    if (j in -1 until favouriteCuisineList.size) {
                        stringBuilder.append(",")
                    }
                }
                editFavouriteCuisine.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until favouriteCuisineList.size) {

                    selectedFavouriteCuisine[j] = false

                    favouriteCuisineList.clear();

                    editFavouriteCuisine.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun preferredDressStyle() {
        selectedPreferredDressStyle = BooleanArray(preferredDressStyleArray.size)
        editPreferredDressStyle.setOnClickListener {
            val builder = AlertDialog.Builder(this@HobbiesAndInterest)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(preferredDressStyleArray, selectedPreferredDressStyle) { _, i, b ->
                if (b) {
                    preferredDressStyleList.add(i)
                } else {
                    preferredDressStyleList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until preferredDressStyleList.size) {
                    stringBuilder.append(preferredDressStyleArray[preferredDressStyleList[j]])

                    if (j in -1 until preferredDressStyleList.size) {
                        stringBuilder.append(",")
                    }
                }
                editPreferredDressStyle.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until preferredDressStyleList.size) {

                    selectedPreferredDressStyle[j] = false

                    preferredDressStyleList.clear();

                    editPreferredDressStyle.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun spokenLanguages() {
        selectedSpokenLanguages = BooleanArray(spokenLanguagesArray.size)
        editSpokenLanguages.setOnClickListener {
            val builder = AlertDialog.Builder(this@HobbiesAndInterest)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(spokenLanguagesArray, selectedSpokenLanguages) { _, i, b ->
                if (b) {
                    spokenLanguagesList.add(i)
                } else {
                    spokenLanguagesList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until spokenLanguagesList.size) {
                    stringBuilder.append(spokenLanguagesArray[spokenLanguagesList[j]])

                    if (j in -1 until spokenLanguagesList.size) {
                        stringBuilder.append(",")
                    }
                }
                editSpokenLanguages.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until spokenLanguagesList.size) {

                    selectedSpokenLanguages[j] = false

                    spokenLanguagesList.clear();

                    editSpokenLanguages.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}