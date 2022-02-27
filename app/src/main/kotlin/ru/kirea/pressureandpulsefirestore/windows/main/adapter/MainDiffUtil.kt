package ru.kirea.pressureandpulsefirestore.windows.main.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.kirea.pressureandpulsefirestore.data.AppData
import ru.kirea.pressureandpulsefirestore.data.AppTitleData
import ru.kirea.pressureandpulsefirestore.data.ItemType

class MainDiffUtil(private val oldList: List<ItemType>,
                   private val newList: List<ItemType>): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[oldItemPosition]

        return when {
            oldItem.getType() != newItem.getType() ->
                false
            oldItem is AppData && newItem is AppData ->
                oldItem.date == newItem.date
            oldItem is AppTitleData && newItem is AppTitleData ->
                oldItem.date == newItem.date
            else -> false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[oldItemPosition]

        return when {
            oldItem.getType() != newItem.getType() ->
                false
            oldItem is AppData && newItem is AppData ->
                oldItem == newItem
            oldItem is AppTitleData && newItem is AppTitleData ->
                oldItem.date == newItem.date
            else -> false
        }
    }
}