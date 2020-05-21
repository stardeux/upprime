package com.stardeux.upprime.tmdb.credit.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.tmdb.credit.ui.CreditItemView
import com.stardeux.upprime.tmdb.credit.ui.model.CreditUi

class CreditItemAdapter : RecyclerView.Adapter<CreditItemViewHolder>() {

    private val creditsList = mutableListOf<CreditUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditItemViewHolder {
        return CreditItemViewHolder(CreditItemView(parent.context))
    }

    override fun onBindViewHolder(holder: CreditItemViewHolder, position: Int) {
        holder.bind(creditsList[position])
    }

    override fun getItemCount(): Int {
        return creditsList.size
    }

    fun submitList(credits: List<CreditUi>) {
        creditsList.clear()
        creditsList.addAll(credits)
        notifyDataSetChanged()
    }

}