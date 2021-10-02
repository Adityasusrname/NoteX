package repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name="note_title") val note_title:String,
            @ColumnInfo(name="note_content")val note_content:String) {
    @PrimaryKey(autoGenerate = true) var uid=0





}