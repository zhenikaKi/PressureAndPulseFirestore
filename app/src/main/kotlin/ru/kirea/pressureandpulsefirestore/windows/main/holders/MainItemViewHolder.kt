package ru.kirea.pressureandpulsefirestore.windows.main.holders

import ru.kirea.pressureandpulsefirestore.data.AppData
import ru.kirea.pressureandpulsefirestore.data.ItemType
import ru.kirea.pressureandpulsefirestore.databinding.MainItemBinding
import ru.kirea.pressureandpulsefirestore.extensions.asString
import java.util.*

/** Отображение данных */
class MainItemViewHolder(private val binding: MainItemBinding): MainViewHolder(binding.root) {

    /** Отобразить данные пульса и давления */
    override fun setData(appData: ItemType) {
        with(binding) {
            val data = appData as AppData
            time.text = Date(data.date).asString("HH:mm")
            upPressure.text = data.upPressure.toString()
            downPressure.text = data.downPressure.toString()
            pulse.text = data.pulse.toString()
        }
    }
}