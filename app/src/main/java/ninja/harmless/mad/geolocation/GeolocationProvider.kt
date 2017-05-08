package ninja.harmless.mad.geolocation

/**
 * @author bnjm@harmless.ninja - 5/7/17.
 */
interface GeolocationProvider {
    fun get(address: String) : Geolocation
    fun get(lat: Float, lon: Float) : Geolocation
}