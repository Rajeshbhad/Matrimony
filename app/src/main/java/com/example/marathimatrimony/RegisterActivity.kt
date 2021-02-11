package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {

   private lateinit var auth: FirebaseAuth

//    var databaseReference :  DatabaseReference? = null
//    var database: FirebaseDatabase? = null

    lateinit var editUsername:EditText
    lateinit var editEmail:EditText
    lateinit var editAge:EditText
    lateinit var editPhoneNo:EditText
    lateinit var editPassword:EditText
    lateinit var btnRRegister:Button
    lateinit var btnRLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

//        database = FirebaseDatabase.getInstance()
//        databaseReference = database?.reference!!.child("profile")

        editUsername=findViewById(R.id.editUsername)
        editEmail=findViewById(R.id.editEmail)
        editAge=findViewById(R.id.editAge)
        editPhoneNo=findViewById(R.id.editPhoneNo)
        editPassword=findViewById(R.id.editPassword)
        btnRRegister=findViewById(R.id.btnRRegister)
        btnRLogin=findViewById(R.id.btnRLogin)

        btnRLogin.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
        btnRRegister.setOnClickListener {
            registerUser()
        }


    }

    private fun registerUser() {

        if (editEmail.text.trim().toString().isEmpty())
        {
            editEmail.error="Please Enter Email"
            editEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(editEmail.text.toString()).matches())
        {
            editEmail.error="Please Enter Email"
            editEmail.requestFocus()
            return
        }
        if (editPassword.text.trim().toString().isEmpty())
        {
            editPassword.error="Please Enter Password"
            editPassword.requestFocus()
            return
        }
        if (editPassword.length()<6||editPassword.length()>8)
        {
            editPassword.error="Password required Minimum 8 Characters"
            editPassword.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(editEmail.text.toString(), editPassword.text.toString()).addOnCompleteListener(this)
        {  task ->
                if (task.isSuccessful)
                {
                    val user:FirebaseUser? = auth.currentUser
                        user?.sendEmailVerification()
                            ?.addOnCompleteListener {
                                if (task.isSuccessful)
                                {
                                    Toast.makeText(this, "Registration Successfully", Toast.LENGTH_SHORT).show()
                                    Toast.makeText(this, "Please Check Your Email Verify Your Account!", Toast.LENGTH_SHORT).show()

//                                    val currentUSerDb = databaseReference?.child((user.uid))
//                                    currentUSerDb?.child("editUsername")?.setValue(editUsername.text.toString())
//                                    currentUSerDb?.child("editEmail")?.setValue(editAge.text.toString())
//                                    currentUSerDb?.child("editAge")?.setValue(editEmail.text.toString())
//                                    currentUSerDb?.child("editPhoneNo")?.setValue(editPhoneNo.text.toString())

                                    startActivity(Intent(this,LoginActivity::class.java))
                                    finish()
                                }
                                else
                                {
                                    Toast.makeText(this, "Already Registered", Toast.LENGTH_SHORT).show()

                                }
                            }
                } else {
                    Toast.makeText(this, "Already Registered", Toast.LENGTH_SHORT).show()
                }

            }
      }



}