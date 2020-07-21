package com.common.reglib.interfaces

import com.google.firebase.firestore.DocumentSnapshot

interface OnItemClickListener {
    fun onItemClick(item: DocumentSnapshot, position: Int)
}