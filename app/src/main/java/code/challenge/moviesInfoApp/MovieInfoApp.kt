package code.challenge.moviesInfoApp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class MovieInfoApp : Application() {

    companion object {
        @JvmStatic
        private lateinit var instance: MovieInfoApp

        @JvmStatic
        var APP
            get() = instance
            set(value) = takeIf { it::instance.isInitialized.not() }?.run {
                instance = value
            } ?: Unit

    }

    override fun onCreate() {
        super.onCreate()
        APP = this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}