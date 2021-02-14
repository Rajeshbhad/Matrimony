package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var auth: FirebaseAuth

    lateinit var editUsername: EditText
    lateinit var editEmail: EditText
    lateinit var editAge: EditText
    lateinit var editPhoneNo: EditText
    lateinit var editPassword: EditText
    lateinit var btnRRegister: Button
    lateinit var btnRLogin: Button
    lateinit var spinner_view:Spinner
    lateinit var mySelectedItemOne:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()


        editUsername = findViewById(R.id.editUsername)
        editEmail = findViewById(R.id.editEmail)
        editAge = findViewById(R.id.editAge)
        editPhoneNo = findViewById(R.id.editPhoneNo)
        editPassword = findViewById(R.id.editPassword)
        btnRRegister = findViewById(R.id.btnRRegister)
        btnRLogin = findViewById(R.id.btnRLogin)
        spinner_view=findViewById(R.id.spinner_view)

        btnRLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btnRRegister.setOnClickListener {
            registerUser()
        }
         val person= arrayOf( "--Select--","Son","Brother","Relative","Friend")
         val arrayAdapter=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,person)

        spinner_view.adapter=arrayAdapter

        spinner_view.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mySelectedItemOne=spinner_view.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


    }


    private fun registerUser() {

        if (editEmail.text.trim().toString().isEmpty()) {
            editEmail.error = "Please Enter Email"
            editEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(editEmail.text.toString()).matches()) {
            editEmail.error = "Please Enter Email"
            editEmail.requestFocus()
            return
        }
        if (editPassword.text.trim().toString().isEmpty()) {
            editPassword.error = "Please Enter Password"
            editPassword.requestFocus()
            return
        }
        if (editPassword.length() < 6 || editPassword.length() > 8) {
            editPassword.error = "Password required Minimum 8 Characters"
            editPassword.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(editEmail.text.toString(), editPassword.text.toString()).addOnCompleteListener(this)
        { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this, "Registration Successfully.",Toast.LENGTH_SHORT).show()
                finish()
            }
            else
            {
                Toast.makeText(this, "Already Registered", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

}





