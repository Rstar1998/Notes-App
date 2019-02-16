package com.example.austrindabre.notes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class update_page : AppCompatActivity() {

    lateinit var title_u: EditText
    lateinit var description_u: EditText
    lateinit var save_button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_page)
        title_u=findViewById(R.id.editText1u)
        description_u=findViewById(R.id.editText2u)
        save_button=findViewById(R.id.button1u)
        val note=intent.getSerializableExtra("note") as Notes
        title_u.setText(note.title)
        description_u.setText(note.description)

        save_button.setOnClickListener {
            save_data(note)
        }
    }

    private fun save_data(note : Notes)
    {
        val title_received=title_u.text.toString().trim()
        val description_received=description_u.text.toString().trim()
        if(title_received.isEmpty()) {
            title_u.error = "Enter title"
            return
        }
        if(description_received.isEmpty()){
            description_u.error="Enter Description"
            return
        }
        val mydatabase= FirebaseDatabase.getInstance().getReference("Notes")
        mydatabase.child(note.id).removeValue()
        val note_id=mydatabase.push().key.toString()
        val a_note=Notes(note_id,title_received,description_received)
        mydatabase.child(note_id).setValue(a_note).addOnCompleteListener {
            Toast.makeText(this,"Note updated",Toast.LENGTH_SHORT).show()
            var intent: Intent = Intent(applicationContext,ViewNote::class.java)
            startActivity(intent)
        }


    }
}
