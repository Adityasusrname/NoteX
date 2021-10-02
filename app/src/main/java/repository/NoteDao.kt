package repository

import repository.Note
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table")
    fun getAll():LiveData<List<Note>>

    @Insert
    fun insert( note:Note)

    @Delete
    fun delete(note:Note)

    @Update
    fun update(note:Note)


}