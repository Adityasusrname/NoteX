package com.learning.notex.ui.Add

import repository.NotesDatabase
import repository.Note
import repository.NotesRepository
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.learning.notex.databinding.AddFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class AddFragment : Fragment() {

    companion object {
        fun newInstance() = AddFragment()
    }

    private lateinit var viewModel: AddViewModel

  private lateinit var binding:AddFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= AddFragmentBinding.inflate(layoutInflater)
        val view=binding.root
        binding.save.setOnClickListener {


            val note_content=binding.noteContent.text.toString()
            val note_title=binding.noteTitle.text.toString()
            if(!note_content.isBlank() && !note_title.isBlank())
            {
                runBlocking {
                    launch(Dispatchers.IO) {
                        val db = Room.databaseBuilder(
                            requireContext(),
                            NotesDatabase::class.java, "NotesDatabase"
                        ).build()
                        val note_dao = db.noteDao()
                        val note = Note(note_title, note_content)
                        val dao = NotesDatabase.getDatabase(requireContext()).noteDao()
                        val repository = NotesRepository(dao)
                        repository.insert(note)
                    }
                    }



            }


        }


       return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)

    }



}