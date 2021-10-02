package com.learning.notex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Room
import com.learning.notex.R
import com.learning.notex.databinding.ActivityEditNoteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import repository.Note
import repository.NotesDatabase
import repository.NotesRepository

class EditNote : AppCompatActivity() {
    private lateinit var binding:ActivityEditNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val note_title=intent.getStringExtra("note_title").toString()
        val note_content=intent.getStringExtra("note_content").toString()
        val uid=intent.getIntExtra("uid",0)
        binding.title.setText(note_title)
        binding.noteContent.setText(note_content)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            this,
            NotesDatabase::class.java, "NotesDatabase"
        ).build()
        val note_dao = db.noteDao()
        val dao = NotesDatabase.getDatabase(this).noteDao()
        val repository = NotesRepository(dao)


        binding.save.setOnClickListener {
            val note_content_edited=binding.noteContent.text.toString()
            val note_title_edited=binding.title.text.toString()
            val note = Note(note_title_edited, note_content_edited)
            note.uid=uid

            if (note_title != null) {
                if(!note_content_edited.isEmpty() && !note_title_edited.isEmpty()) {
                    runBlocking {
                        launch(Dispatchers.IO) {

                            repository.update(note)

                            val i= Intent(it.context,MainActivity::class.java)
                            startActivity(i)

                        }
                    }

                }
            }
        }

binding.delete.setOnClickListener {
    val note_content_edited=binding.noteContent.text.toString()
    val note_title_edited=binding.title.text.toString()
    val note = Note(note_title_edited, note_content_edited)
    note.uid=uid
runBlocking {
    launch(Dispatchers.IO){
        repository.delete(note)
        val i=Intent(it.context,MainActivity::class.java)
        startActivity(i)
    }

}


}
        binding.share.setOnClickListener {

            Toast.makeText(it.context,"Hellooo",Toast.LENGTH_LONG)
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, note_title+"\n\n"+note_content)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }
    }
}