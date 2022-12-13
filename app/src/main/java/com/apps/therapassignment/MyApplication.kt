package com.apps.therapassignment

import android.app.Application
import androidx.room.Room
import com.apps.therapassignment.database.NotesDatabase

class MyApplication: Application() {
    val db : NotesDatabase by lazy { Room.databaseBuilder(applicationContext, NotesDatabase::class.java, "notes-database").build() }
}
