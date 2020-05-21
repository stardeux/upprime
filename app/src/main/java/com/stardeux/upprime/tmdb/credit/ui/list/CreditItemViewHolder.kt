package com.stardeux.upprime.tmdb.credit.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.tmdb.credit.ui.CreditItemView
import com.stardeux.upprime.tmdb.credit.ui.model.CreditUi

class CreditItemViewHolder(creditItemView: CreditItemView)  : RecyclerView.ViewHolder(creditItemView) {

    fun bind(creditUi: CreditUi) {
        (itemView as CreditItemView).bind(creditUi)
    }
}