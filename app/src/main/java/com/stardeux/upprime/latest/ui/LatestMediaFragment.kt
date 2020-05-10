package com.stardeux.upprime.latest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.ui.SpacesItemDecoration
import com.stardeux.upprime.latest.ui.adapter.MediaAdapter
import kotlinx.android.synthetic.main.fragment_latest.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestMediaFragment : Fragment(R.layout.fragment_latest) {

    private val latestViewModel: LatestMediaViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerMedia.adapter =
            MediaAdapter()
        recyclerMedia.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        val decoration =
            SpacesItemDecoration(resources.getDimensionPixelOffset(R.dimen.media_list_item_spacing))
        recyclerMedia.addItemDecoration(decoration)

        latestViewModel.mediaItems.observeNotNull(viewLifecycleOwner) {
            getMediaAdapter().submitList(it)
        }

        latestViewModel.load()
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