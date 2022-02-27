package ru.kirea.pressureandpulsefirestore.windows

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import ru.kirea.pressureandpulsefirestore.R
import ru.kirea.pressureandpulsefirestore.data.AppData

object AlertDialogAdd {
    /**
     * Сформировать диалоговое окно для добавления пульса и давления.
     * @param context приложения.
     * @param success обработчик введенных данных .
     */
    fun createDialog(context: Context, success: (AppData) -> Unit): AlertDialog {
        val inflater = LayoutInflater.from(context)
        val viewDialog = inflater.inflate(R.layout.add_dialog, null)

        return AlertDialog.Builder(context)
            .setTitle(R.string.add_title)
            .setView(viewDialog)
            .setPositiveButton(context.getString(R.string.dialog_positive_button)) { dialog, _ ->
                applyDialog(viewDialog, success)
                dialog.cancel()
            }
            .setNegativeButton(context.getString(R.string.dialog_negative_button)) { dialog, _ ->
                dialog.cancel()
            }
            .create()
    }

    /** Обработка кнопки сохранения */
    private fun applyDialog(viewDialog: View, success: (AppData) -> Unit) {
        if (!checkParam(viewDialog)) {
            return
        }
        val appData = AppData(
            upPressure = getValue(viewDialog, R.id.edit_up_pressure_value),
            downPressure = getValue(viewDialog, R.id.edit_down_pressure_value),
            pulse = getValue(viewDialog, R.id.edit_pulse_value))
        success(appData)
    }

    /** Проверить заполненность полей */
    private fun checkParam(viewDialog: View): Boolean {
        val fields: List<Pair<Int, Int>> = listOf(
            Pair(R.id.input_up_pressure_value, R.id.edit_up_pressure_value),
            Pair(R.id.input_down_pressure_value, R.id.edit_down_pressure_value),
            Pair(R.id.input_pulse_value, R.id.edit_pulse_value)
        )
        var check = true
        val errorText = viewDialog.context.getString(R.string.error_field)
        fields.forEach { field ->
            val input: TextInputLayout = viewDialog.findViewById(field.first)
            val edit: EditText = viewDialog.findViewById(field.second)
            if (edit.text.toString().isEmpty() || edit.text.toString().toInt() <= 0) {
                check = false
                input.error = errorText
            }
            else {
                input.error = null
            }
        }

        return check
    }

    /**
     * Получить числовое значение из поля ввода.
     * @param view, на котором расположено поле ввода.
     * @param editTextId идентификатор поля, из которого нужно достать значение.
     * @return значение поля ввода, приведенное к числу.
     */
    private fun getValue(view: View, editTextId: Int): Int {
        return view.findViewById<EditText>(editTextId).text.toString().toInt()
    }
}