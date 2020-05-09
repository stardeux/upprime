package com.stardeux.upprime.latest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.ui.SpacesItemDecoration
import com.stardeux.upprime.latest.ui.model.MediaAdapter
import com.stardeux.upprime.tmdb.movie.usecase.GetMovieDetailsUseCase
import kotlinx.android.synthetic.main.fragment_latest.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestMediaFragment : Fragment(R.layout.fragment_latest) {

    private val latestViewModel : LatestMediaViewModel by viewModel()

    private val getMovieDetails: GetMovieDetailsUseCase by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerMedia.adapter = MediaAdapter()
        recyclerMedia.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        val decoration = SpacesItemDecoration(resources.getDimensionPixelOffset(R.dimen.media_list_item_spacing))
        recyclerMedia.addItemDecoration(decoration)

        latestViewModel.mediaItems.observeNotNull(viewLifecycleOwner) {
            getMediaAdapter().submitList(it)
        }

        latestViewModel.load()

        lifecycle.coroutineScope.launch {
            val a = getMovieDetails(
                GetMovieDetailsUseCase.TmdbDetailsRequest(
                    "tt0137523", "fr"
                )
            )
            val b = ""
        }

    }

    private fun getMediaAdapter(): MediaAdapter {
        return recyclerMedia.adapter as MediaAdapter
    }

    companion object {
        fun newInstance(): LatestMediaFragment {
            return LatestMediaFragment()
        }
    }
}