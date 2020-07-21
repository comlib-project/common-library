package com.common.reglib.builder

import androidx.lifecycle.LifecycleOwner
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.Query
import com.common.reglib.config.FirebaseConfig

/**
 * Object class for creating firebase components.
 */
object FirebaseBuilder {

    /**
     * This method is used to get firestore paging option setup.
     * @param owner The Owner in which the activity is running.
     * @param query The Query that perform as selecting data on firestore.
     * @param model The Class model object that perform as host of object data return.
     */
    fun <T> fireStorePagingOption(owner: LifecycleOwner, query: Query, model: Class<T>): FirestorePagingOptions<T> {
        return FirestorePagingOptions.Builder<T>()
            .setLifecycleOwner(owner)
            .setQuery(query, FirebaseConfig.getPagedListConfiguration(), model)
            .build()
    }
}