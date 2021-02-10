package com.example.marathimatrimony

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private  lateinit var auth: FirebaseAuth
    private lateinit var etUsername:EditText


    private lateinit var btnSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()

        btnSubmit=findViewById(R.id.btnSubmit)
        etUsername=findViewById(R.id.etUsername)


        btnSubmit.setOnClickListener {

                    forgotPassword(etUsername)
              }

    }
    private fun forgotPassword(etUsername: EditText) {
        if(etUsername.text.toString().isEmpty())
        {
            Toast.makeText(this, "Please Enter Valid Email Id.", Toast.LENGTH_SHORT).show()
            return

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(etUsername.text.toString()).matches())
        {
            Toast.makeText(this, " Invalid Email Address.", Toast.LENGTH_SHORT).show()
            return
        }


        auth.sendPasswordResetEmail(etUsername.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, " Email Sent.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this, "Please Enter Registered Email Id.", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}