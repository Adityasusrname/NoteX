package repository

import repository.Note
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import repository.NoteDao


@Database(entities = arrayOf(Note::class),version = 1,exportSchema = false)

abstract class NotesDatabase:RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "NotesDatabase"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}