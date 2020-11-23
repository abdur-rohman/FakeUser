package id.refactory.fakeuser.localstorage

import android.content.Context
import androidx.core.content.edit

class LocalStorage(private val context: Context) {

    companion object {
        private const val SHARED_NAME = "id.refactory.fakeuser.localstorage"
        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_EMAIL = "KEY_NAME"
        private const val KEY_PASSWORD = "KEY_NAME"
        private const val KEY_IS_LOGGED = "KEY_IS_LOGGED"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    }

    fun saveName(value: String) {
        sharedPreferences.edit {
            putString(KEY_NAME, value)
        }
    }

    fun getName(): String {
        return sharedPreferences.getString(KEY_NAME, "") ?: ""
    }

    fun saveEmail(value: String) {
        sharedPreferences.edit {
            putString(KEY_EMAIL, value)
        }
    }

    fun getEmail(): String {
        return sharedPreferences.getString(KEY_EMAIL, "") ?: ""
    }

    fun savePassword(value: String) {
        sharedPreferences.edit {
            putString(KEY_PASSWORD, value)
        }
    }

    fun getPassword(): String {
        return sharedPreferences.getString(KEY_PASSWORD, "") ?: ""
    }

    fun saveAuth(value: Boolean) {
        sharedPreferences.edit {
            putBoolean(KEY_IS_LOGGED, value)
        }
    }

    fun getAuth(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED, false)
    }
}