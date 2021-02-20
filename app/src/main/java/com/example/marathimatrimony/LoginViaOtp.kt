package com.example.marathimatrimony

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LoginViaOtp : AppCompatActivity() {

//    lateinit var btnGetOTP:Button
//    lateinit var btnLogin:Button
//
//    lateinit var enterPhoneNumber: EditText
//    lateinit var enterOTP: EditText
//
//    lateinit var phoneNumberLayout: LinearLayout
//    lateinit var otpLayout:LinearLayout
//
//    private lateinit var auth:FirebaseAuth
//    private lateinit var callbacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks
//    var storedVerificationId:String=""
//    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_via_otp)
//        enterPhoneNumber=findViewById(R.id.enterPhoneNumber)
//        enterOTP=findViewById(R.id.enterOTP)
//        btnGetOTP=findViewById(R.id.btnGetOTP)
//        btnLogin=findViewById(R.id.btnLogin)
//        auth= FirebaseAuth.getInstance()
//
//
//        btnGetOTP.setOnClickListener {
//            val phoneNumber=enterPhoneNumber.toString().trim()
//            if (phoneNumber.isNotEmpty())
//            {
//                sendVerificationCode("+91$phoneNumber")
//            }
//            else
//            {
//                Toast.makeText(this, "Enter Register Mobile Number", Toast.LENGTH_SHORT).show()
//            }
//        }
//        btnLogin.setOnClickListener {
//            val otp=enterOTP.toString().trim()
//            if(otp.isNotEmpty())
//            {
//                verifyVerificationCode(otp)
//            }
//            else
//            {
//                Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//
//                val code=credential.smsCode
//                if (code!=null)
//                {
//                    enterOTP.setText(code)
//                }
//
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                Toast.makeText(applicationContext, "Enter Register Mobile Number", Toast.LENGTH_SHORT).show()
//
//            }
//
//            override fun onCodeSent(
//                verificationId: String,
//                token: PhoneAuthProvider.ForceResendingToken
//            ) {
//
//                storedVerificationId = verificationId
//                resendToken = token
//                phoneNumberLayout.visibility= View.GONE
//                otpLayout.visibility=View.VISIBLE
//            }
//        }
//
//    }
//    private fun sendVerificationCode(phoneNumber:String)
//    {
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber)
//            .setTimeout(60L, TimeUnit.SECONDS)
//            .setActivity(this)
//            .setCallbacks(callbacks)
//            .build()
//        PhoneAuthProvider.verifyPhoneNumber(options)
//    }
//    private fun verifyVerificationCode(code:String)
//    {
//        val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
//        signUp(credential)
//    }
//    private fun signUp(credential: PhoneAuthCredential)
//    {
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//
//                    Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_SHORT).show()
//                    val intent=Intent(this,NavigationActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                    val user = task.result?.user
//
//                }
//                else
//                {
//                    if (task.exception is FirebaseAuthInvalidCredentialsException)
//                    {
//                        Toast.makeText(applicationContext, "Enter Correct OTP", Toast.LENGTH_SHORT).show()
//                        enterOTP.setText("")
//
//
//                    }
//                }
//            }
    }


}


