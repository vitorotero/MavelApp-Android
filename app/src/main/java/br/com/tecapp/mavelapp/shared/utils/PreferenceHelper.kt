package br.com.tecapp.mavelapp.shared.utils

import android.content.Context
import android.content.SharedPreferences
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class SharedPreferencesHelper(private val context: Context) {

    fun put(key: String, value: String) {
        val sp: SharedPreferences = context.getSharedPreferences(Companion.FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(key, value)
        SharedPreferencesCompat.apply(editor)
    }

    fun put(key: String, value: Boolean) {
        val sp: SharedPreferences = context.getSharedPreferences(Companion.FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putBoolean(key, value)
        SharedPreferencesCompat.apply(editor)
    }

    fun put(key: String, value: Int) {
        val sp: SharedPreferences = context.getSharedPreferences(Companion.FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putInt(key, value)
        SharedPreferencesCompat.apply(editor)
    }

    operator fun get(key: String, default: String?): String? {
        val sp: SharedPreferences = context.getSharedPreferences(Companion.FILE_NAME, Context.MODE_PRIVATE)
        return sp.getString(key, default)
    }

    operator fun get(key: String, default: Boolean): Boolean {
        val sp: SharedPreferences = context.getSharedPreferences(Companion.FILE_NAME, Context.MODE_PRIVATE)
        return sp.getBoolean(key, default)
    }

    operator fun get(key: String, default: Int): Int? {
        val sp: SharedPreferences = context.getSharedPreferences(Companion.FILE_NAME, Context.MODE_PRIVATE)
        return sp.getInt(key, default)
    }

    fun remove(key: String) {
        val sp: SharedPreferences = context.getSharedPreferences(Companion.FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        SharedPreferencesCompat.apply(editor)
    }

    fun clearAll(context: Context) {
        val sp: SharedPreferences = context.getSharedPreferences(Companion.FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        SharedPreferencesCompat.apply(editor)
    }

    private object SharedPreferencesCompat {
        private val sApplyMethod: Method? = findApplyMethod()
        private fun findApplyMethod(): Method? {
            try {
                val clz: Class<*> = SharedPreferences.Editor::class.java
                return clz.getMethod("apply")
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            }
            return null
        }

        fun apply(editor: SharedPreferences.Editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor)
                    return
                }
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
            editor.commit()
        }
    }

    companion object {
        private const val FILE_NAME = "APP_PREFERENCES"
        const val AUTH_TOKEN = "AUTH_TOKEN"
    }
}