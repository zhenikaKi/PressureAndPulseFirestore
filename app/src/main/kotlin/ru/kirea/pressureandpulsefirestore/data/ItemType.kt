package ru.kirea.pressureandpulsefirestore.data

interface ItemType {
    companion object {
        const val ITEM_TITLE = 1
        const val ITEM_DATA = 2
    }

    /** получить тип элемента */
    fun getType(): Int
}