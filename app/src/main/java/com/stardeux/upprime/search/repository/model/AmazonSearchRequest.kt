package com.stardeux.upprime.search.repository.model

import com.stardeux.upprime.search.ui.model.MediaTypeFilter

data class AmazonSearchRequest(
    val title: String, val mediaTypeFilter: MediaTypeFilter, val yearStart: Int, val yearEnd: Int, val page: Int
)