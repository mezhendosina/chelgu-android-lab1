package com.mezhendosina.lab1

import android.os.Bundle
import android.transition.TransitionManager
import android.util.TypedValue
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.mezhendosina.lab1.databinding.FragmentItemBinding

class FragmentItem : Fragment(R.layout.fragment_item) {

    private var binding: FragmentItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    private val isChecked = MutableLiveData<Boolean>(false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemBinding.bind(view)

        val itemId = this.arguments?.getInt(ITEM_ID)

        binding!!.itemToolbar.setupWithNavController(findNavController())

        binding!!.buttonWithId.text = itemId.toString()
        binding!!.backgroundSwitcher.setOnCheckedChangeListener { _, b ->
            isChecked.value = b
        }
        observeIsChecked()
    }

    private fun observeIsChecked(){
        isChecked.observe(viewLifecycleOwner){
            val backgroundColor = TypedValue()
            val colorResource = if (it)
                com.google.android.material.R.attr.colorPrimaryContainer
            else com.google.android.material.R.attr.colorTertiaryContainer
            requireContext().theme.resolveAttribute(
                colorResource, backgroundColor, true
            )


            val transition = MaterialFadeThrough()
            TransitionManager.beginDelayedTransition(binding!!.text, transition)

            binding!!.text.setBackgroundColor(backgroundColor.data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val ITEM_ID = "item_id"
    }
}