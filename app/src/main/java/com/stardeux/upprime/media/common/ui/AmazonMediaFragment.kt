package com.stardeux.upprime.media.common.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.R
import com.stardeux.upprime.media.common.ui.adapter.MediaAdapter
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.ui.EndlessRecyclerViewScrollListener
import com.stardeux.upprime.core.ui.SpacesItemDecoration
import com.stardeux.upprime.media.fiche.ui.MediaFicheActivity
import kotlinx.android.synthetic.main.fragment_media_listing.*

abstract class AmazonMediaFragment : Fragment(R.layout.fragment_media_listing) {

    private val latestViewModel by lazy { getViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        mediaListingRecycler.adapter = MediaAdapter()
        mediaListingRecycler.layoutManager = linearLayoutManager

        mediaListingRecycler.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                latestViewModel.loadNext()
            }
        })

        val decoration =
            SpacesItemDecoration(resources.getDimensionPixelOffset(R.dimen.media_list_item_spacing))
        mediaListingRecycler.addItemDecoration(decoration)

        latestViewModel.navigationEvent.observeNotNull(viewLifecycleOwner, ::handleNavigationEvent)
        latestViewModel.datedMediaItems.observeNotNull(viewLifecycleOwner) {
            getMediaAdapter().submitList(it)
        }

        latestViewModel.loadingDataState.observeNotNull(viewLifecycleOwner, ::onDataLoadingState)

        latestViewModel.loadNext()
    }

    private fun handleNavigationEvent(navigationEvent: AmazonMediaViewModel.NavigationEvent) {
        when (navigationEvent) {
            is AmazonMediaViewModel.NavigationEvent.MediaDetailsFiche -> {
                goToMediaFiche(navigationEvent)
            }
        }
    }

    private fun goToMediaFiche(mediaDetailsFiche: AmazonMediaViewModel.NavigationEvent.MediaDetailsFiche) {
        with(mediaDetailsFiche.mediaItemUi) {
            val intent = MediaFicheActivity.newIntent(requireContext(), shortMedia)
            startActivity(intent)
        }
    }

    private fun onDataLoadingState(dataLoading: AmazonMediaViewModel.DataLoading) {
        mediaListingProgress.isVisible = dataLoading is AmazonMediaViewModel.DataLoading.Loading
        mediaListingRecycler.isVisible = dataLoading is AmazonMediaViewModel.DataLoading.Success
        mediaListingError.isVisible = dataLoading is AmazonMediaViewModel.DataLoading.Error
    }

    private fun getMediaAdapter(): MediaAdapter {
        return mediaListingRecycler.adapter as MediaAdapter
    }

    abstract fun getViewModel(): AmazonMediaViewModel
}