package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tvLogin=findViewById<TextView>(R.id.tvLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val editUsername =findViewById<EditText>(R.id.editUsername)
        val editEmail =findViewById<EditText>(R.id.editEmail)
        val editPassword =findViewById<EditText>(R.id.editPassword)
        val editCPassword =findViewById<EditText>(R.id.editCPassword)

        btnRegister.setOnClickListener {
            if (editUsername.text.trim().isEmpty() || editEmail.text.isEmpty() || editPassword.text.isEmpty() || editCPassword.text.isEmpty())
            {
                Toast.makeText(this, "Input Required", Toast.LENGTH_SHORT).show()

            }
            else
            {
                val intent=Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }

        tvLogin.setOnClickListener {
            val intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}