package ninja.harmless.mad.http

import ninja.harmless.mad.geolocation.Geolocation
import org.junit.Assert
import org.junit.Test

/**
 * @author bnjm@harmless.ninja - 5/7/17.
 */
class HTTPRequestImplTest {

    val classUnderTest : HTTPRequest<Array<Geolocation>> = HTTPRequestImpl(Array<Geolocation>::class.java)

    @Test
    fun getByAddressThrowsNoException() {
        val response = classUnderTest.get("http://nominatim.openstreetmap.org/search/6,%20Bredh%C3%B8jvej,%20Bollersleben,%20Aabenraa%20Municipality,%20Region%20of%20Southern%20Denmark,%206392,%20Denmark?format=json")
        Assert.assertNotNull(response)
    }
}