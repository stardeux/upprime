package com.stardeux.upprime.country.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.ui.SpacesItemDecoration
import com.stardeux.upprime.core.viewbinding.viewBinding
import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.country.ui.adapter.CountryAdapter
import com.stardeux.upprime.country.ui.model.CountryUi
import com.stardeux.upprime.databinding.FragmentCountryBinding
import com.stardeux.upprime.home.ui.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectCountryFragment : Fragment(R.layout.fragment_country) {

    private val binding by viewBinding(FragmentCountryBinding::bind)

    private val selectCountryViewModel: SelectCountryViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectCountryViewModel.trackScreen(requireActivity())

        with(binding.countriesRecycler) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = CountryAdapter()

            val decoration =
                SpacesItemDecoration(resources.getDimensionPixelOffset(R.dimen.country_list_item_spacing))
            addItemDecoration(decoration)
        }

        selectCountryViewModel.countries.observeNotNull(viewLifecycleOwner, ::onCountries)
        selectCountryViewModel.selectedCountry.observeNotNull(
            viewLifecycleOwner, ::onCountrySelected
        )
    }

    private fun onCountrySelected(countryUi: CountryUi) {
        getUserScope().close()
        startActivity(HomeActivity.newIntent(requireContext()))
    }

    private fun onCountries(countriesUi: List<CountryUi>) {
        getCountryAdapter().submitList(countriesUi)
    }

    private fun getCountryAdapter(): CountryAdapter {
        return binding.countriesRecycler.adapter as CountryAdapter
    }

    companion object {

        fun newInstance(): SelectCountryFragment {
            return SelectCountryFragment()
        }
    }

}