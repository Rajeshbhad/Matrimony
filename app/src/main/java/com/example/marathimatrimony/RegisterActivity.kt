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


   lateinit var editEmail:EditText
   lateinit var editPassword:EditText
   lateinit var editCPassword:EditText
   lateinit var btnRRegister:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        editEmail=findViewById(R.id.editEmail)
        editPassword=findViewById(R.id.editPassword)
        editCPassword=findViewById(R.id.editCPassword)
        btnRRegister=findViewById(R.id.btnRRegister)

        btnRRegister.setOnClickListener {
            registerUser()

        }

    }
    private fun registerUser() {

        if (editEmail.text.toString().isEmpty())
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
        if (editPassword.text.toString().isEmpty())
        {
            editPassword.error="Please Enter Password"
            editPassword.requestFocus()
            return
        }
        if (editPassword.length()<8)
        {
            editPassword.error="Password required Minimum 6 Characters"
            editPassword.requestFocus()
            return
        }
        if (editPassword.length()>5)
        {
            editPassword.error="Password required Minimum 6 Characters"
            editPassword.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(editEmail.text.toString(), editPassword.text.toString()).addOnCompleteListener(this)
        {  task ->
                if (task.isSuccessful)
                {
                    val user:FirebaseUser? = auth.currentUser
                        user?.sendEmailVerification()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful)
                                {
                                    Toast.makeText(this, "Registration Successfully", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this,LoginActivity::class.java))
                                    finish()
                                }
                            }
                } else {
                    Toast.makeText(this, "Registration Fail. Try Again", Toast.LENGTH_SHORT).show()
                }

            }
      }

}