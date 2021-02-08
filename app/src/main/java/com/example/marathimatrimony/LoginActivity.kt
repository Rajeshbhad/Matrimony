package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edUsername = findViewById<EditText>(R.id.edUsername)
        val edPassword = findViewById<EditText>(R.id.edPassword)
        val btnLogin   = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        btnLogin.setOnClickListener {
            if (edUsername.text.trim().isEmpty() || edPassword.text.isEmpty())
            {
                Toast.makeText(this, "Input Required", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "login successfully", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, NavigationActivity::class.java)
                startActivity(intent)
            }
        }
            tvRegister.setOnClickListener {
                val intent= Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}