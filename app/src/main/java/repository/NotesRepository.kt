package repository



import android.util.Log
import androidx.lifecycle.LiveData

class NotesRepository( private val note_dao:NoteDao) {

    val all_notes:LiveData<List<Note>> = note_dao.getAll()

           suspend fun insert(note:Note)
            {
                note_dao.insert(note)
            }

            suspend fun delete(note:Note)
            {
                note_dao.delete(note)
            }
            suspend fun update(note:Note)
            {
                Log.i("UID",note.uid.toString())
                Log.i("Note_Title",note.note_title)
                note_dao.update(note)

            }

}