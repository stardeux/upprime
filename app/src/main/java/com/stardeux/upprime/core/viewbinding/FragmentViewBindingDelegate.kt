package com.stardeux.upprime.core.viewbinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Auto-cleared [ViewBinding]. Can be used in [Fragment] like so:
 * - first, pass the layout as part of the [Fragment] constructor:
 * ```
 * class MyFragment: Fragment(R.layout.my_fragment) { }
 * ```
 * - then, declare a variable which uses this delegate:
 * ```
 * val viewBinding by viewBinding(MyLayoutBinding::bind)
 * ```
 * [bindingFactory]: lambda responsible for underline variable initialization.
 *
 * [onDestroyBindingCallback]: called before ViewBinding is destroyed, giving a chance to nullify element (like
 * [androidx.recyclerview.widget.RecyclerView.Adapter] or unregister a listener for example).
 *
 * Important: DO NOT rely on [Fragment.onDestroyView] for binding operation, because it will have been nullify at this point
 * ([DefaultLifecycleObserver.onDestroy] happens before [Fragment.onDestroyView]).
 *
 * References:
 * - [https://funkymuse.dev/posts/view-binding-for-the-lazy/]
 * - [https://itnext.io/an-update-to-the-fragmentviewbindingdelegate-the-bug-weve-inherited-from-autoclearedvalue-7fc0a89fcae1]
 */

fun <Binding : ViewBinding> Fragment.viewBinding(
    bindingFactory: (View) -> Binding,
    onDestroyBindingCallback: (Binding) -> Unit = { },
): ReadOnlyProperty<Fragment, Binding> = FragmentViewBindingDelegate(
    fragment = this,
    bindingFactory = bindingFactory,
    onDestroyBindingCallback = onDestroyBindingCallback,
)

private class FragmentViewBindingDelegate<out Binding : ViewBinding>(
    private val fragment: Fragment,
    private val bindingFactory: (View) -> Binding,
    private val onDestroyBindingCallback: (Binding) -> Unit,
) : ReadOnlyProperty<Fragment, Binding> {

    private var binding: Binding? = null

    init {
        val lifecycleOwnerObserver = Observer<LifecycleOwner?> { owner: LifecycleOwner? ->
            val lifecycleOwner = owner ?: return@Observer
            lifecycleOwner.lifecycle.addObserver(
                object : DefaultLifecycleObserver {
                    override fun onDestroy(owner: LifecycleOwner) {
                        binding?.let(onDestroyBindingCallback::invoke)
                        binding = null
                    }
                },
            )
        }

        fragment.lifecycle.addObserver(
            object : DefaultLifecycleObserver {
                override fun onCreate(owner: LifecycleOwner) =
                    fragment.viewLifecycleOwnerLiveData.observeForever(lifecycleOwnerObserver)

                override fun onDestroy(owner: LifecycleOwner) =
                    fragment.viewLifecycleOwnerLiveData.removeObserver(lifecycleOwnerObserver)
            },
        )
    }

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>,
    ): Binding {
        val viewBinding = binding
        if (viewBinding != null) return viewBinding
        if (!fragment.viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("FragmentViewBindingDelegate#getValue(): Should not attempt to get binding when fragment view is destroyed !")
        }
        return bindingFactory(fragment.requireView()).also { binding = it }
    }
}
