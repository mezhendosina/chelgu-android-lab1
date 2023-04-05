package com.mezhendosina.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mezhendosina.lab1.databinding.Item1Binding
import com.mezhendosina.lab1.databinding.Item2Binding

class DiffCallback : DiffUtil.ItemCallback<DataEntity>() {

    override fun areItemsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
        return oldItem == newItem
    }
}

typealias OnItemClickListener = (id: Int) -> Unit

class ItemsAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<DataEntity, RecyclerView.ViewHolder>(DiffCallback()), View.OnClickListener {

    class ItemType1ViewHolder(private val binding: Item1Binding) :
        RecyclerView.ViewHolder(binding.root)

    class ItemType2ViewHolder(private val binding: Item2Binding) :
        RecyclerView.ViewHolder(binding.root)


    override fun getItemViewType(position: Int): Int {
        return getItem(position).type

    }

    override fun onClick(v: View) {
        onItemClickListener.invoke(v.tag as Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            CardType.BIG_CARD -> {
                val binding = Item1Binding.inflate(layoutInflater, parent, false)
                binding.root.setOnClickListener(this)
                ItemType1ViewHolder(binding)
            }
            CardType.SMALL_CARD -> {
                val binding = Item2Binding.inflate(layoutInflater, parent, false)
                binding.root.setOnClickListener(this)
                ItemType2ViewHolder(binding)
            }
            else -> {
                val binding = Item1Binding.inflate(layoutInflater, parent, false)
                binding.root.setOnClickListener(this)
                ItemType1ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = getItem(position)
        holder.itemView.tag = dataItem.id
    }


}
