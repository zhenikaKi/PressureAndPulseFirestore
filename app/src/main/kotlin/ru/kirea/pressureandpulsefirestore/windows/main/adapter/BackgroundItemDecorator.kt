package ru.kirea.pressureandpulsefirestore.windows.main.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.kirea.pressureandpulsefirestore.R
import ru.kirea.pressureandpulsefirestore.data.ItemType

/** Декоратор фона для элементов с данными */
class BackgroundItemDecorator: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect,
                                view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val adapter = parent.adapter as MainAdapter
        val position = parent.getChildAdapterPosition(view)
        if (position < 0) {
            return
        }
        val currentItem = adapter.items[position]
        if (currentItem.getType() == ItemType.ITEM_TITLE) {
            view.setBackgroundResource(R.drawable.item_title_background)
            return
        }

        //в адаптере могут быть разные типы, а фон нужно менять только у элементов с данными
        val data = adapter.items.filter { item -> item.getType() == ItemType.ITEM_DATA }.toList()
        data.forEachIndexed { index, itemType ->
            if (currentItem == itemType) {
                val resId = if (index % 2 == 0) R.drawable.item_even_background else R.drawable.item_odd_background
                view.setBackgroundResource(resId)
                return
            }
        }
    }
}