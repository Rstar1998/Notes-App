package com.example.austrindabre.notes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class InsertNote : AppCompatActivity() {

    lateinit var title:EditText
    lateinit var description:EditText
    lateinit var add_note_to_firebase:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_note)
        title=findViewById(R.id.editText)
        description=findViewById(R.id.editText2)
        add_note_to_firebase=findViewById(R.id.button3)

        add_note_to_firebase.setOnClickListener{
            save_data()
        }
    }

    private fun save_data()
    {
        val title_received=title.text.toString().trim()
        val description_received=description.text.toString().trim()
        if(title_received.isEmpty()) {
            title.error = "Enter title"
            return
        }
        if(description_received.isEmpty()){
            description.error="Enter Description"
            return
        }
        val mydatabase=FirebaseDatabase.getInstance().getReference("Notes")
        val note_id=mydatabase.push().key.toString()
        val a_note=Notes(note_id,title_received,description_received)
        mydatabase.child(note_id).setValue(a_note).addOnCompleteListener {
            Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show()
            var intent: Intent = Intent(applicationContext,ViewNote::class.java)
            startActivity(intent)
        }


    }
}
