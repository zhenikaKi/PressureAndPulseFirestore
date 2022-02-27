package ru.kirea.pressureandpulsefirestore.data

/** Сущность заголовков */
data class AppTitleData(
    /** Дата измерений */
    val date: String
): ItemType {
    /** получить тип элемента */
    override fun getType(): Int = ItemType.ITEM_TITLE
}
