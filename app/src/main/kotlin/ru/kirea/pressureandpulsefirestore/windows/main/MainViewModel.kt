package ru.kirea.pressureandpulsefirestore.windows.main

import kotlinx.coroutines.launch
import ru.kirea.pressureandpulsefirestore.base.BaseViewModel
import ru.kirea.pressureandpulsefirestore.data.AppData

class MainViewModel(private val service: MainService) : BaseViewModel<MainState>() {

    /** Загрузить все значения пульса и давления. */
    fun loadData() {
        liveData.postValue(MainState.Loading)
        coroutineScope.launch {
            service.getData { liveData.postValue(MainState.Success(it)) }
        }
    }

    /**
     * Сохранить значения пульса и давления, а затем получить обновленный список пульса и давления.
     * @param appData данные пульса и давления.
     */
    fun saveData(appData: AppData) {
        coroutineScope.launch {
            service.saveData(appData) { liveData.postValue(MainState.Success(it)) }
        }
    }
}