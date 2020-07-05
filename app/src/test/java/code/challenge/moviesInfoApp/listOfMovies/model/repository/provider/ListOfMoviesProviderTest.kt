package code.challenge.moviesInfoApp.listOfMovies.model.repository.provider

import android.os.Build.VERSION_CODES.O_MR1
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [O_MR1])
class ListOfMoviesProviderTest {



    @Test
    fun loadUpComingMovies_serviceWorks() {
        val provider = ListOfMoviesProvider(ApplicationProvider.getApplicationContext())
        provider.loadUpComingMovies()
    }
}