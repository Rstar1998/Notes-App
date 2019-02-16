package com.example.austrindabre.notes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var make_a_note_button:Button
    lateinit var view_notes_button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        make_a_note_button=findViewById(R.id.button)
        view_notes_button=findViewById(R.id.button2)

        make_a_note_button.setOnClickListener{
            var intent:Intent= Intent(applicationContext,InsertNote::class.java)
            startActivity(intent)
        }

        view_notes_button.setOnClickListener{
            var intent:Intent= Intent(applicationContext,ViewNote::class.java)
            startActivity(intent)
        }
    }
}
