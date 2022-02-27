package ru.kirea.pressureandpulsefirestore.windows.main

import ru.kirea.pressureandpulsefirestore.data.ItemType

sealed class MainState {
    object Loading: MainState()
    data class Success(val appDataList: MutableList<ItemType>) : MainState()
}
