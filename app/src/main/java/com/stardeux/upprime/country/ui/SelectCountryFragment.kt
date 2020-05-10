package com.stardeux.upprime.country.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.country.ui.adapter.CountryAdapter
import com.stardeux.upprime.country.ui.model.CountryUi
import kotlinx.android.synthetic.main.fragment_country.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectCountryFragment : Fragment(R.layout.fragment_country) {

    private val selectCountryViewModel: SelectCountryViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(countriesRecycler) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = CountryAdapter()
        }

        selectCountryViewModel.countries.observeNotNull(viewLifecycleOwner, ::onCountries)
    }

    private fun onCountries(countriesUi: List<CountryUi>) {
        getCountryAdapter().submitList(countriesUi)
    }

    private fun getCountryAdapter(): CountryAdapter {
        return countriesRecycler.adapter as CountryAdapter
    }

    companion object {

        fun newInstance(): SelectCountryFragment {
            return SelectCountryFragment()
        }
    }

}