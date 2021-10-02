package com.learning.notex.ui.Notes


import repository.Note
import repository.NotesDatabase
import repository.NotesRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val all_notes:LiveData<List<Note>>

    init {
        val dao=NotesDatabase.getDatabase(application).noteDao()
        val repository=NotesRepository(dao)
        all_notes=repository.all_notes
    }


}