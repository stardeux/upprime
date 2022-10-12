package com.stardeux.upprime.core.viewbinding

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Binding delegate for activity. Can be used in [AppCompatActivity] like so:
 * - first, pass the layout as part of the [AppCompatActivity] constructor:
 * ```
 * class MyActivity: AppCompatActivity(R.layout.my_layout) { }
 * ```
 * - then, declare a variable which uses this delegate:
 * ```
 * val binding by viewBinding(MyLayoutBinding::bind)
 * ```
 *
 * [bindingFactory]: lambda responsible for underline variable initialization.
 *
 * References:
 * - [https://funkymuse.dev/posts/view-binding-for-the-lazy/]
 * - [https://itnext.io/an-update-to-the-fragmentviewbindingdelegate-the-bug-weve-inherited-from-autoclearedvalue-7fc0a89fcae1]
 */

// Note: AppCompatActivity is not used, but leave as receiver, so this delegate is "scoped" to AppCompatActivity sub-classes
@Suppress("unused")
fun <Binding : ViewBinding> AppCompatActivity.viewBinding(
    bindingFactory: (View) -> Binding,
): ReadOnlyProperty<AppCompatActivity, Binding> = ActivityViewBindingDelegate(bindingFactory)

private class ActivityViewBindingDelegate<out Binding : ViewBinding>(
    private val bindingFactory: (View) -> Binding,
) : ReadOnlyProperty<AppCompatActivity, Binding> {

    private var binding: Binding? = null

    override fun getValue(
        thisRef: AppCompatActivity,
        property: KProperty<*>,
    ): Binding {
        // https://stackoverflow.com/a/60545362
        return binding ?: bindingFactory(thisRef.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)).apply {
            binding = this
        }
    }
}
