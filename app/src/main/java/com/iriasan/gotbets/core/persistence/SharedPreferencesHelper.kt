package com.iriasan.gotbets.core.persistence

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.iriasan.gotbets.AndroidApplication
import com.iriasan.gotbets.features.domain.models.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.reflect.KFunction1

class SharedPreferencesHelper {
    private val SHARED_PREFERENCE_FILE = "data"
    private var sharedPreferences: SharedPreferences? = null
    private val KEY_DEVICE_ID = "DEVICE_ID"
    val KEY_USER_MODEL = "USER_MODEL"

    fun save(key: String, value: Any?) {
        sharedPreferences =
                AndroidApplication().getContext()?.getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            is Any -> {
                val gson = Gson()
                val json = gson.toJson(value)
                editor.putString(key, json)
            }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
        editor.apply()
    }

    fun load(key: String): Any? {
        sharedPreferences =
                AndroidApplication().getContext()?.getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE)
        val keys = sharedPreferences?.all
        return keys?.get(key)
    }

    fun loadUserModel(key: String): Any? {
        return try {
            sharedPreferences =
                    AndroidApplication().getContext()
                        ?.getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE)
            val keys = sharedPreferences?.all
            Gson().fromJson(keys?.get(key).toString(), UserModel::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun loadDeviceId(): String? {
        val deviceId: String? = load(key = KEY_DEVICE_ID) as? String
        return when {
            deviceId.isNullOrEmpty() || deviceId.isNullOrBlank() -> {
                val uniqueId = UUID.randomUUID().toString()
                save(KEY_DEVICE_ID, uniqueId)
                uniqueId
            }
            else -> deviceId
        }
    }

    fun clearUserModel(
        context: Context,
        closeApp: KFunction1<@ParameterName(name = "activity") Activity, Unit>,
        activity: Activity
    ) {
        context.getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE)?.let {
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
                // This is because ... the SharedPreferences.OnSharedPreferenceChangeListener is called before the data has been deleted, it is an android failure.
                GlobalScope.launch(Dispatchers.Main) {
                    delay(1000)
                    closeApp.invoke(activity)
                }
            }
            it.registerOnSharedPreferenceChangeListener(listener)
            it.edit().remove(KEY_USER_MODEL).apply()
        }
    }

    fun clear(context: Context) {
        context.getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE)?.let {

            it.edit()?.clear()?.apply()
        }
    }

    companion object {
        var KEY_FIRST_REQUEST: String = "KEY_FIRST_REQUEST"
        var KEY_FIRST_SENT_BUDGET: String = "KEY_FIRST_REQUEST"
    }
}
