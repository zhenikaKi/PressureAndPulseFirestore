package ru.kirea.pressureandpulsefirestore.windows.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kirea.pressureandpulsefirestore.data.ItemType
import ru.kirea.pressureandpulsefirestore.databinding.MainItemBinding
import ru.kirea.pressureandpulsefirestore.databinding.MainTitleItemBinding
import ru.kirea.pressureandpulsefirestore.windows.main.holders.MainItemViewHolder
import ru.kirea.pressureandpulsefirestore.windows.main.holders.MainTitleItemViewHolder
import ru.kirea.pressureandpulsefirestore.windows.main.holders.MainViewHolder

class MainAdapter(var items: MutableList<ItemType> = mutableListOf()): RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return when (viewType) {
            ItemType.ITEM_TITLE -> {
                val binding = MainTitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MainTitleItemViewHolder(binding)
            }
            else -> {
                val binding = MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MainItemViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int = items[position].getType()
}