package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class LoginViaOtp : AppCompatActivity() {

     lateinit var btnSubmit:Button
     lateinit var editPhoneNo: EditText
     lateinit var editOtp: EditText
     private lateinit var auth:FirebaseAuth
     private lateinit var callbacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks
     lateinit var storedVerificationId:String
     private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_via_otp)
        editPhoneNo=findViewById(R.id.editPhoneNo)
        editOtp=findViewById(R.id.editotp)
        btnSubmit=findViewById(R.id.btnSubmit)
        auth= FirebaseAuth.getInstance()

        val phoneNumber=editPhoneNo.toString().trim()
        val otp=editOtp.toString().trim()

        if (phoneNumber.isNotEmpty())
        {
            sendVerificationCode("+91$phoneNumber")
        }
        else
        {
            Toast.makeText(this, "Enter Register Mobile Number", Toast.LENGTH_SHORT).show()
        }
        if(otp.isNotEmpty())
        {
            verifyVerificationCode(otp)
        }
        else
        {
            Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show()
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                val code=credential.smsCode
                if (code!=null)
                {
                    editOtp.setText(code)
                }

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(applicationContext, "Enter Register Mobile Number", Toast.LENGTH_SHORT).show()

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                storedVerificationId = verificationId
                resendToken = token
            }
        }

    }
   private fun sendVerificationCode(phoneNumber:String)
   {
       val options = PhoneAuthOptions.newBuilder(auth)
           .setPhoneNumber(phoneNumber)
           .setTimeout(60L, TimeUnit.SECONDS)
           .setActivity(this)
           .setCallbacks(callbacks)
           .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
   }
    private fun verifyVerificationCode(code:String)
    {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
        signUp(credential)
    }
    private fun signUp(credential: PhoneAuthCredential)
    {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = task.result?.user
                    Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_SHORT).show()
                    val intent=Intent(this,NavigationActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                else
                {
                    if (task.exception is FirebaseAuthInvalidCredentialsException)
                    {
                        Toast.makeText(applicationContext, "Enter Correct OTP", Toast.LENGTH_SHORT).show()
                        editOtp.setText("")


                    }
                }
            }
    }

   
}


