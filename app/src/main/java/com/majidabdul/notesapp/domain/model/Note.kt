package com.majidabdul.notesapp.domain.model

import com.google.firebase.firestore.DocumentId

data class Note(
    @DocumentId val id: String = "",
    var title: String = "",
    var body: String = "",
    var favourite: Boolean = false
)