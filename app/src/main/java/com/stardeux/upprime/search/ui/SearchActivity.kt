package com.stardeux.upprime.search.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.commit
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.applyCommonBack
import kotlinx.android.synthetic.main.activity_media_fiche.*
import kotlinx.android.synthetic.main.activity_media_fiche.toolbar
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(R.layout.activity_search) {

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        applyCommonBack(toolbar)

        searchEditText.addTextChangedListener {
            it?.let {
                searchViewModel.onQueryTextChanged(it.toString())
            }
        }

        with(supportFragmentManager) {
            if (findFragmentByTag(SEARCH_FRAGMENT_TAG) == null) {
                commit {
                    replace(R.id.searchContent, SearchFragment.newInstance(), SEARCH_FRAGMENT_TAG)
                }
            }
        }
    }

    companion object {
        private const val SEARCH_FRAGMENT_TAG = "SEARCH_FRAGMENT_TAG"
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

}