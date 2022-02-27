package ru.kirea.pressureandpulsefirestore.windows.main.holders

import ru.kirea.pressureandpulsefirestore.data.AppTitleData
import ru.kirea.pressureandpulsefirestore.data.ItemType
import ru.kirea.pressureandpulsefirestore.databinding.MainTitleItemBinding

/** Отображение данных */
class MainTitleItemViewHolder(private val binding: MainTitleItemBinding): MainViewHolder(binding.root) {

    /** Отобразить заголовок */
    override fun setData(appData: ItemType) {
        val data = appData as AppTitleData
        binding.date.text = data.date
    }
}