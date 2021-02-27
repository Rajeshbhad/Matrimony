package com.example.marathimatrimony

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteContactPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class EditContactPreferences : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID:String

    lateinit var editWhomToContact : Spinner
    lateinit var editContactPersonName:TextView
    lateinit var editAvailableTimeToCalled:TextView
    lateinit var editComments:TextView
    lateinit var editContactPreferencesSaveBtn:TextView
    var spinnerSelectedItemOne: String? =null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact_preferences)

        editWhomToContact=findViewById(R.id.editWhomToContact)
        editContactPersonName=findViewById(R.id.editContactPersonName)
        editAvailableTimeToCalled=findViewById(R.id.editAvailableTimeToCalled)
        editComments=findViewById(R.id.editComments)
        editContactPreferencesSaveBtn=findViewById(R.id.editContactPreferencesSaveBtn)


        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

        editContactPreferencesSaveBtn.setOnClickListener {
            save()
        }

        val editWhomToContactArray= arrayOf( "--Select--","Self","Parents","Brother","Sister","Friend","Aunt/Uncle")
        val arrayAdapter1= ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,editWhomToContactArray)
        editWhomToContact.adapter=arrayAdapter1
        editWhomToContact.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0) {
                    spinnerSelectedItemOne=null
                }else{
                    spinnerSelectedItemOne=editWhomToContact.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
        }


    }

    private fun save() {
        val whomToContact=spinnerSelectedItemOne
        val contactPersonName=editContactPersonName.text.toString().trim()
        val availableTimeToCall=editAvailableTimeToCalled.text.toString().trim()
        val comments=editComments.text.toString().trim()


        if (spinnerSelectedItemOne==null)
        {
            Toast.makeText(this, "Please Select Profile Created For ", Toast.LENGTH_SHORT).show()
            return
        }
        if (editContactPersonName.text.trim().toString().isEmpty())
        {
            editContactPersonName.error = "Please Enter Contact Person Name"
            editContactPersonName.requestFocus()
            return
        }
        if (editAvailableTimeToCalled.text.trim().toString().isEmpty())
        {
            editAvailableTimeToCalled.error = "Please Enter Available Time To Called"
            editAvailableTimeToCalled.requestFocus()
            return
        }
        userID= auth.currentUser!!.uid
        docRef=db.collection("Users").document(userID)
        val write= WriteContactPreferences(whomToContact,contactPersonName,availableTimeToCall,comments)
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