package com.stardeux.upprime.search.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.commit
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.applyCommonBack
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.extension.openKeyboard
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
        searchEditText.requestFocus()

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
        if (query == searchEditText.text.toString()) {
            return
        }

        searchEditText.setText(query)
    }

    companion object {
        private const val SEARCH_FRAGMENT_TAG = "SEARCH_FRAGMENT_TAG"
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

}