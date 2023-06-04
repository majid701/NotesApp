package com.majidabdul.notesapp.domain.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Note(
    @DocumentId val id: String = "",
    var title: String = "",
    var body: String = "",
    var favourite: Boolean = false,
    @ServerTimestamp
    var createdDate: Date? = null
)