package code.challenge.moviesInfoApp.infrastructure.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import code.challenge.moviesInfoApp.BuildConfig.*
import code.challenge.moviesInfoApp.R.drawable.ic_launcher_background
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


private val customLifecycle = object : Lifecycle() {
    override fun addObserver(observer: LifecycleObserver) {}

    override fun removeObserver(observer: LifecycleObserver) {}

    override fun getCurrentState() = State.CREATED
}

fun Context.checkNetworkConnection(): Boolean {
    return try {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        cm?.isDefaultNetworkActive ?: false
    } catch (e: Exception) {
        false
    }
}

fun Context.getLifecycle(): Lifecycle {
    return when (this) {
        is AppCompatActivity -> this.lifecycle
        else -> customLifecycle
    }
}

fun Context.buildDrawableAsset(source: Bitmap?): Drawable {
    return source?.let {
        BitmapDrawable(this.resources, source)
    } ?: ic_launcher_background.getDrawable(this)
}

fun buildBitampFromStream(byteStrean: InputStream): Bitmap {
    val buffer = BufferedInputStream(byteStrean)
    return BitmapFactory.decodeStream(buffer)
}

fun isInternetAccessible(): Boolean = try {
    val urlc = URL("http://www.google.com").openConnection() as? HttpURLConnection
    urlc?.let {
        it.setRequestProperty("User-Agent", "Test")
        it.setRequestProperty("Connection", "close")
        it.connectTimeout = 1500
        it.connect()
        it.responseCode == 200
    } ?: false
} catch (e: Exception) {
    false
}


fun buildMovieServiceUrl() = movie_api_url
fun buildMoviePictureUrl() = movie_picture_url
fun buildApiAccessKey() = "$api_key_type $api_key"



