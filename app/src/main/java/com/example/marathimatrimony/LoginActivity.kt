package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private  lateinit var auth: FirebaseAuth

     lateinit var edUsername:EditText
     lateinit var edPassword:EditText
     lateinit var btnLLogin:Button
     lateinit var btnLRegister: Button
     lateinit var btnForgotPassword:TextView
     lateinit var btnLoginWithOtp:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        edUsername=findViewById(R.id.edUsername)
        edPassword=findViewById(R.id.edPassword)
        btnLLogin=findViewById(R.id.btnLLogin)
        btnLRegister=findViewById(R.id.btnLRegister)
        btnForgotPassword=findViewById(R.id.btnForgotPassword)
        btnLoginWithOtp=findViewById(R.id.btnLoginWithOtp)

        btnLRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }
        btnLoginWithOtp.setOnClickListener {
            startActivity(Intent(this,LoginViaOtp::class.java))
            finish()
        }

        btnForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        btnLLogin.setOnClickListener {
            loginUser()
        }



    }


    private fun loginUser() {
        if(edUsername.text.toString().isEmpty())
        {
            edUsername.error="Please Enter Email"
            edUsername.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(edUsername.text.toString()).matches())
        {
            edUsername.error="Please Enter Email"
            edUsername.requestFocus()
            return
        }
        if (edPassword.text.toString().isEmpty())
        {
            edPassword.error="Please Enter Password"
            edPassword.requestFocus()
            return
        }
        if (edPassword.length()<6)
        {
            edPassword.error="Password required Minimum 6 Characters"
            edPassword.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(edUsername.text.toString(), edPassword.text.toString())
            .addOnCompleteListener(this)
               { task ->
                   if (task.isSuccessful)
                       {
                        val user:FirebaseUser? = auth.currentUser
                           if (user!!.isEmailVerified)
                           {
                               startActivity(Intent(this, NavigationActivity::class.java))
                               Toast.makeText(baseContext, "Login Successfully.",Toast.LENGTH_SHORT).show()
                               finish()
                           }
                           else
                           {
                               Toast.makeText(baseContext, "Please Check Your Email Verify Your Account!.",Toast.LENGTH_SHORT).show()
                           }
                       }
                        else
                         {
                             Toast.makeText(this, "Please Enter Correct Information.", Toast.LENGTH_SHORT).show()
                         }
                }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser:FirebaseUser? = auth.currentUser
        if(currentUser != null){
            updateUI(currentUser)
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser!=null)
        {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, NavigationActivity::class.java))
                finish()
            }
        }
    }
}