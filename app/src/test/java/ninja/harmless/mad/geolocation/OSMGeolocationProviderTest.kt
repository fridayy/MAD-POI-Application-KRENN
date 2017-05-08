package ninja.harmless.mad.geolocation

import org.junit.Assert
import org.junit.Test

/**
 * @author bnjm@harmless.ninja - 5/7/17.
 */
class OSMGeolocationProviderTest {
    @Test
    fun getByAddress() {
        val classUnderTest : GeolocationProvider = OSMGeolocationProvider()
        val response = classUnderTest.get("6, Bredhøjvej, Bollersleben, Aabenraa Municipality, Region of Southern Denmark, 6392, Denmark")
        Assert.assertNotNull(response)
    }

    @Test
    fun getByLonLat() {
        val classUnderTest : GeolocationProvider = OSMGeolocationProvider()
        val response = classUnderTest.get(54.982375f, 9.283268f)
        Assert.assertNotNull(response)
    }

}