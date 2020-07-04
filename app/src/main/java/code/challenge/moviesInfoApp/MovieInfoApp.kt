package code.challenge.moviesInfoApp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class MovieInfoApp: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}