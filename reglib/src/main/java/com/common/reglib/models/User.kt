package com.common.reglib.models

import com.google.firebase.firestore.PropertyName

class User {
    @JvmField @PropertyName(UID)
    var uid: String? = null

    @JvmField @PropertyName(EMAIL)
    var email: String? = null

    @JvmField @PropertyName(NAME)
    var name: String? = null

//    @JvmField @PropertyName(IS_INPUT_LOCK)
//    var isInputLock: Boolean = false

    constructor()

    constructor(uid: String, email: String, name: String) {
        this.uid = uid
        this.email = email
        this.name = name
    }

     companion object {
//        const val COLLECTION = "Drivers"
        const val UID = "uid"
        const val EMAIL = "email"
        const val NAME = "name"
//        const val IS_INPUT_LOCK = "isInputLock"
    }
}
