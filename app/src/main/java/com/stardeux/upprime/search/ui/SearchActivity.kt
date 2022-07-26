package com.stardeux.upprime.search.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.commit
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.applyCommonBack
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.viewbinding.viewBinding
import com.stardeux.upprime.databinding.ActivitySearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(R.layout.activity_search) {

    private val binding by viewBinding(ActivitySearchBinding::bind)
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        applyCommonBack(binding.toolbar)

        binding.searchEditText.addTextChangedListener {
            it?.let {
                searchViewModel.onQueryTextChanged(it.toString())
            }
        }
        binding.searchEditText.requestFocus()

        searchViewModel.searchQuery.observeNotNull(this, ::handleQuery)

        with(supportFragmentManager) {
            if (findFragmentByTag(SEARCH_FRAGMENT_TAG) == null) {
                commit {
                    replace(R.id.searchContent, SearchFragment.newInstance(), SEARCH_FRAGMENT_TAG)
                }
            }
        }
    }

    private fun handleQuery(query: String) {
        if (query == binding.searchEditText.text.toString()) {
            return
        }

        binding.searchEditText.setText(query)
    }

    companion object {
        private const val SEARCH_FRAGMENT_TAG = "SEARCH_FRAGMENT_TAG"
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

}