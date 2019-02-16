package com.example.austrindabre.notes

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomAdapter(val notes_list:MutableList<Notes>) :RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v=LayoutInflater.from(p0?.context).inflate(R.layout.list_item,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return notes_list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val note:Notes=notes_list[p1]
        p0?.title_h?.text=note.title
        p0?.description_h?.text=note.description
        p0.itemView.setOnClickListener {
            val intent=Intent(p0.itemView.context,detailed_note::class.java)
                    .putExtra("note",note)
            p0.itemView.context.startActivity(intent)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val title_h=itemView.findViewById(R.id.textView) as TextView
        val description_h=itemView.findViewById(R.id.textView2) as TextView
        val constraint_layout=itemView.findViewById(R.id.constraintLayout) as ConstraintLayout
        val card_layout=itemView.findViewById(R.id.cardLayout) as CardView
        /*init {
            itemView.setOnClickListener {
                val intent=Intent(itemView.context,detailed_note::class.java)
                itemView.context.startActivity(intent)
            }
        }*/
    }
}