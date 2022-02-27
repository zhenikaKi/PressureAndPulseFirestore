package ru.kirea.pressureandpulsefirestore.data

import android.content.Context
import ru.kirea.pressureandpulsefirestore.AppConstants
import ru.kirea.pressureandpulsefirestore.extensions.getStringPreference
import ru.kirea.pressureandpulsefirestore.extensions.setStringPreference
import java.util.*

/** Пользователь приложения. */
data class AppUser(
    /** Уникальный идентификатор пользователя. */
    val uid: String
) {
    companion object {
        /**
         * Получить сохраненный uid пользователя или сформировать новый.
         * @param context приложения.
         * @return пользователь приложения.
         */
        fun generate(context: Context): AppUser {
            val id = context.getStringPreference(AppConstants.USER_UID) ?: generateUid(context)
            return AppUser(id)
        }

        /**
         * сформировать uid пользователя.
         * @param context приложения.
         * @return уникальный идентификатор пользователя.
         */
        private fun generateUid(context: Context): String {
            val id = UUID.randomUUID().toString()
            context.setStringPreference(AppConstants.USER_UID, id)
            return id
        }
    }
}
