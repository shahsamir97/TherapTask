package com.apps.therapassignment.model

data class Permissions(
    val admin: Boolean,
    val maintain: Boolean,
    val pull: Boolean,
    val push: Boolean,
    val triage: Boolean
)