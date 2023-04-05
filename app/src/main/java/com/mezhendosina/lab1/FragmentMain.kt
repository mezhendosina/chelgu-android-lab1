package com.mezhendosina.lab1

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.mezhendosina.lab1.databinding.FragmentMainBinding

class FragmentMain : Fragment(R.layout.fragment_main) {


    private val adapter = ItemsAdapter(object : OnItemClickListener {
        override fun invoke(id: Int) {
            findNavController().navigate(
                R.id.action_fragmentMain_to_fragmentItem,
                bundleOf(FragmentItem.ITEM_ID to id)
            )
        }
    })

    private var binding: FragmentMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getList()

        exitTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)


        binding!!.recyclerView.adapter = adapter
        binding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Singleton.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun getList() {
        Singleton.list.value = generateList()
    }
}