package ru.kirea.pressureandpulsefirestore.extensions

import android.content.Context
import ru.kirea.pressureandpulsefirestore.AppConstants

/** Получить строковое значение из настроек */
fun Context.getStringPreference(preferenceName: String): String? {
        val sharedPreferences = this.getSharedPreferences(AppConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(preferenceName, null)
}

/** Сохранить строковое значение в настройки */
fun Context.setStringPreference(preferenceName: String, value: String) {
        val editor = this.getSharedPreferences(AppConstants.PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(preferenceName, value)
        editor.apply()
}