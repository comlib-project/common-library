package com.common.reglib.builder

import androidx.lifecycle.LifecycleOwner
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.Query
import com.common.reglib.config.FirebaseConfig

object FirebaseBuilder {

    // Fire store paging option
    fun <T> fireStorePagingOption(context: LifecycleOwner, query: Query, model: Class<T>): FirestorePagingOptions<T> {
        return FirestorePagingOptions.Builder<T>()
            .setLifecycleOwner(context)
            .setQuery(query, FirebaseConfig.getPagedListConfiguration(), model)
            .build()
    }
}