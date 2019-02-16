package com.example.austrindabre.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.google.firebase.database.*

class ViewNote : AppCompatActivity() {
    lateinit var ref :DatabaseReference
    lateinit var notes_list:MutableList<Notes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_note)

        val recycler_view=findViewById(R.id.recycler_view1) as RecyclerView
        recycler_view.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)

        notes_list= mutableListOf()
        ref=FirebaseDatabase.getInstance().getReference("Notes")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists())
                {
                    notes_list.clear()
                    for(note in p0.children)
                    {
                        val a_note=note.getValue(Notes::class.java)
                        notes_list.add(a_note!!)
                    }
                    val adapter=CustomAdapter(notes_list)
                    recycler_view.adapter=adapter
                }
            }
        })
    }
}
