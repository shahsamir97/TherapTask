package com.apps.therapassignment.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val repoId : Int,
    val note: String
)
