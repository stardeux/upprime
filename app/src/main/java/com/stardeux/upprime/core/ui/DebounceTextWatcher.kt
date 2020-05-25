package com.stardeux.upprime.core.ui

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher

class DebounceTextWatcher(
    private val DELAY: Long = 1000,
    private val onImmediateChanged: (String) -> Unit,
    private val onDebounceChanged: (String) -> Unit
) : TextWatcher {

    private val handler = Handler()

    override fun afterTextChanged(editable: Editable?) {
        handler.removeCallbacksAndMessages(null)

        editable?.let {
            val text = it.toString()
            onImmediateChanged(text)
            if (text.isEmpty()) {
                onDebounceChanged(text)
            } else {
                handler.postDelayed({ onDebounceChanged(text) }, DELAY)
            }
        }

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}