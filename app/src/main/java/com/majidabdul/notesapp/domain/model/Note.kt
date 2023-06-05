package com.majidabdul.notesapp.domain.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Note(
    @DocumentId val id: String = "",
    var title: String = "",
    var body: String = "",
    /*
     * Setting note as favourite is not implemented due to time constraints
     * One way to do is to allow users to mark notes as favourites and then
     * create either a client side or serverside filter to show them in a different lost
     */
    var favourite: Boolean = false,
    /*
     * Sorting notes can be implemented by createdDate to show most/least recent ones
     */
    @ServerTimestamp
    var createdDate: Date? = null
)