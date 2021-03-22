package ezlink.project.movieapps.Util

import android.content.Context
import android.preference.PreferenceManager

class DataSession(val context: Context) {
    companion object{
        private const val Session = "session"
    }

    private val pref = PreferenceManager.getDefaultSharedPreferences(context)
    var namaSession = pref.getString(Session, "")
    var numberSession = pref.getInt(Session, 0)
    var pilihSession = pref.getBoolean(Session, false)

    set(value) = pref.edit().putBoolean(Session, value).apply()
}