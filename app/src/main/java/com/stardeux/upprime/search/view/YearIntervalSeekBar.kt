package com.stardeux.upprime.search.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.NumberPicker
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import com.stardeux.upprime.search.ui.model.YearIntervalUi


class YearIntervalSeekBar : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private var minYear: Int? = null

    private val yearText: TextView by lazy { findViewById<TextView>(R.id.yearText) }
    private val yearValue: TextView by lazy { findViewById<TextView>(R.id.yearValue) }
    private val yearMinText: TextView by lazy { findViewById<TextView>(R.id.yearMinText) }
    private val yearMaxText: TextView by lazy { findViewById<TextView>(R.id.yearMaxText) }
    private val yearSeekbar: NumberPicker by lazy { findViewById<NumberPicker>(R.id.yearSeekbar) }

    init {
        setLayout(R.layout.view_year_interval_seekbar)
        initLayout()
    }

    private fun initLayout() {
        //yearSeekbar.incrementProgressBy(1)
    }

    fun setText(@StringRes textId: Int, @IdRes connectedEndText: Int? = null) {
        yearText.text = context.getString(textId)
        connectedEndText?.let {
            connectEndTextTo(it)
        }
    }

    private fun connectEndTextTo(@IdRes connectedEndTextIt: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        constraintSet.connect(
            R.id.yearText,
            ConstraintSet.END,
            connectedEndTextIt,
            ConstraintSet.END,
            0
        )
        constraintSet.applyTo(this)
    }

    fun bind(yearIntervalUi: YearIntervalUi) {
        with(yearIntervalUi) {
            minYear = yearStart

            yearValue.text = selectedYear.toString()
            yearSeekbar.maxValue = yearEnd - yearStart
            yearMinText.text = yearStart.toString()
            yearMaxText.text = yearEnd.toString()
        }
    }

    fun setOnValueChanged(onValueChanged: (Int) -> Unit) {
        /*
        yearSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                minYear?.let {
                    onValueChanged(it + progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })*/
    }
}