package com.vitoria.task.ui.data.model

import android.os.Parcelable
import com.vitoria.task.ui.util.FirebaseHelper
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    var id: String = "",
    var description: String = "",
    var status: Status = Status.TODO
) : Parcelable {
    init {
        if (this.id.isEmpty()) {
            this.id = FirebaseHelper.getDatabase().push().key ?: ""
        }
    }
}