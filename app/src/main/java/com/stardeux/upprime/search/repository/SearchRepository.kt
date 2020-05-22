package com.stardeux.upprime.search.repository

import com.stardeux.upprime.search.repository.api.SearchApi
import com.stardeux.upprime.search.repository.model.AmazonSearchMediaMapper
import com.stardeux.upprime.search.usecase.model.AmazonSearchResultContainer

class SearchRepository (private val searchApi: SearchApi, private val amazonSearchMediaMapper: AmazonSearchMediaMapper) {

    fun search(): AmazonSearchResultContainer {

    }

}