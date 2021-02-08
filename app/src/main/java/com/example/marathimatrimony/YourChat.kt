package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class YourChat : AppCompatActivity() {

    private lateinit var viewOnlineMember:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_chat)



        viewOnlineMember=findViewById(R.id.viewOnlineMember)

        viewOnlineMember.setOnClickListener{

            val intent = Intent(this, ViewOnlineMembersActivity::class.java)
            startActivity(intent)
        }
    }
}