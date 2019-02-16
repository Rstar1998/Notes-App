package com.example.austrindabre.notes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class detailed_note : AppCompatActivity() {
    lateinit var title_dd:EditText
    lateinit var description_d:EditText
    lateinit var delete_button:Button
    lateinit var update_button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_note)
        title_dd=findViewById(R.id.editText1d)
        description_d=findViewById(R.id.editText2d)
        update_button=findViewById(R.id.button1d)
        delete_button=findViewById(R.id.button2d)

        val note=intent.getSerializableExtra("note") as Notes
        title_dd.setText(note.title)
        description_d.setText(note.description)
        title_dd.isEnabled=false
        description_d.isEnabled=false

        delete_button.setOnClickListener {
            delete_info(note)
        }
        update_button.setOnClickListener {
            var intent: Intent = Intent(applicationContext,update_page::class.java).putExtra("note",note)
            startActivity(intent)
        }

    }

    private fun delete_info(note:Notes)
    {
        val mydatabase= FirebaseDatabase.getInstance().getReference("Notes")
        mydatabase.child(note.id).removeValue()
        Toast.makeText(applicationContext,"Note deleted",Toast.LENGTH_SHORT).show()
        var intent: Intent = Intent(applicationContext,ViewNote::class.java)
        startActivity(intent)
    }
}
