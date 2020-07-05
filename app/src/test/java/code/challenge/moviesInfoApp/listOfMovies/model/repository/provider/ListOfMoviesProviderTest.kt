package code.challenge.moviesInfoApp.listOfMovies.model.repository.provider

import android.os.Build.VERSION_CODES.O_MR1
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.listOfMovies.model.entities.ListOfMovies
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [O_MR1])
class ListOfMoviesProviderTest {


    @Test
    fun loadUpComingMovies_serviceWorks() {
        val testhelper = { loader: ComunicationProtocolModel ->
            when (loader.load) {
                false -> {
                    assertNotNull(loader.result)
                    assertTrue(loader.result is ListOfMovies)
                }
            }
        }
        val provider = ListOfMoviesProvider(ApplicationProvider.getApplicationContext(), testhelper)
        provider.loadUpComingMovies()
    }
}