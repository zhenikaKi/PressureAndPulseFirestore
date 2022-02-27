package ru.kirea.pressureandpulsefirestore.windows.main

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import ru.kirea.pressureandpulsefirestore.data.AppData
import ru.kirea.pressureandpulsefirestore.data.AppTitleData
import ru.kirea.pressureandpulsefirestore.data.AppUser
import ru.kirea.pressureandpulsefirestore.data.ItemType
import ru.kirea.pressureandpulsefirestore.extensions.asString
import ru.kirea.pressureandpulsefirestore.extensions.toMapForStore
import java.util.*

class MainService(private val appUser: AppUser) {
    private val usersCollection: CollectionReference
    private val rowsCollection: CollectionReference

    companion object {
        private const val USER_COLLECTION = "users"
        private const val ROW_COLLECTION = "rows"
    }

    init {
        val firebaseFirestore = FirebaseFirestore.getInstance()
        usersCollection = firebaseFirestore.collection(USER_COLLECTION)
        saveUserUid()
        rowsCollection = usersCollection
            .document(appUser.uid)
            .collection(ROW_COLLECTION)
    }

    /**
     * Получить все значения пульса и давления.
     * @param success обработчик полученного списка записей.
     */
    fun getData(success: (MutableList<ItemType>) -> Unit) {
        rowsCollection
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                val appDataList = it.toObjects(AppData::class.java)
                success(generateTitleWith(appDataList))
            }
    }

    /**
     * Сохранить значения пульса и давления.
     * @param appData данные пульса и давления.
     * @param success обработчик полученного списка записей.
     */
    fun saveData(appData: AppData, success: (MutableList<ItemType>) -> Unit) {
        rowsCollection
            .add(appData.toMapForStore())
            .addOnSuccessListener {
                getData(success)
            }
    }

    /**
     * Преобразовать список значений пульса и давления к списку, которые еще содержит заголовок в виде дня.
     * @param appDataList список значений пульса и давления.
     * @return список заголовков, значений пульса и давления
     */
    private fun generateTitleWith(appDataList: List<AppData>): MutableList<ItemType> {
        var oldTitle = "*"
        val result: MutableList<ItemType> = mutableListOf()
        appDataList.forEach { item ->
            val newTitle = Date(item.date).asString("d MMMM")
            if (newTitle != oldTitle) {
                result.add(AppTitleData(newTitle))
            }
            result.add(item)
            oldTitle = newTitle
        }
        return result
    }

    /** Сохранить в хранилище уникальный id пользователя */
    private fun saveUserUid() {
        usersCollection
            .document(appUser.uid)
            .set(mapOf("uuid" to appUser.uid))
    }
}