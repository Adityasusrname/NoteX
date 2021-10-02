package com.learning.notex.ui.Notes

import android.content.Intent
import repository.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.learning.notex.R
import com.learning.notex.ui.EditNote


class RecyclerViewAdapter(private val notes:List<Note>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val text: TextView

init {
    text=view.findViewById(R.id.note)
}

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.note_container,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.setText(notes[position].note_title+"\n\n"+notes[position].note_content)

        holder.text.setOnClickListener{
            val i=Intent(it.getContext(),EditNote::class.java).apply{
                putExtra("note_content",notes[position].note_content)
                putExtra("note_title",notes[position].note_title)
                putExtra("uid",notes[position].uid)

            }
            it.context.startActivity(i)

            
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}