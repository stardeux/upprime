package com.stardeux.upprime.amazon.common.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.R
import com.stardeux.upprime.amazon.latest.ui.adapter.MediaAdapter
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.ui.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_latest.*

abstract class AmazonMediaFragment : Fragment(R.layout.fragment_latest) {

    private val latestViewModel by lazy { getViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerMedia.adapter = MediaAdapter()
        recyclerMedia.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        val decoration =
            SpacesItemDecoration(resources.getDimensionPixelOffset(R.dimen.media_list_item_spacing))
        recyclerMedia.addItemDecoration(decoration)

        latestViewModel.datedMediaItems.observeNotNull(viewLifecycleOwner) {
            getMediaAdapter().submitList(it)
        }

        latestViewModel.load()
    }

    private fun getMediaAdapter(): MediaAdapter {
        return recyclerMedia.adapter as MediaAdapter
    }

    abstract fun getViewModel(): AmazonMediaViewModel
}