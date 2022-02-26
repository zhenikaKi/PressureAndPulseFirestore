package ru.kirea.pressureandpulsefirestore.windows.main.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.kirea.pressureandpulsefirestore.data.ItemType

abstract class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
    /** Отобразить данные пульса и давления */
    abstract fun setData(appData: ItemType)
}