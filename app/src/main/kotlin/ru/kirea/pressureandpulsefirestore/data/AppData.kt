package ru.kirea.pressureandpulsefirestore.data

/** Сущность основных данных */
data class AppData(
    /** Дата и время измерения */
    val date: Long = System.currentTimeMillis(),

    /** Верхнее давление */
    val upPressure: Int = 0,

    /** Нижнее давление */
    val downPressure: Int = 0,

    /** Пульс */
    val pulse: Int = 0
): ItemType {
    /** получить тип элемента */
    override fun getType(): Int = ItemType.ITEM_DATA
}
