package ninja.harmless.mad.geolocation

import ninja.harmless.mad.http.HTTPRequestImpl

class OSMGeolocationProvider : GeolocationProvider {

    val OSM_URL = "https://nominatim.openstreetmap.org/"

    override fun get(address: String): Geolocation {
        val request : HTTPRequestImpl<Array<Geolocation>> = HTTPRequestImpl(Array<Geolocation>::class.java)
        val sanitizedAddress = address.replace(" ", "%20")
        val response : Array<Geolocation> = request.get("${OSM_URL}search/$sanitizedAddress?format=json")

        if (response.isNotEmpty()) {
            return response[0]
        }
        throw GeolocationException("Could not get Geolocation")
    }

    override fun get(lat: Float, lon: Float): Geolocation {
        val request : HTTPRequestImpl<Geolocation> = HTTPRequestImpl(Geolocation::class.java)
        val response : Geolocation = request.get("${OSM_URL}reverse?format=json&lat=${lat}&lon=${lon}&zoom=18&addressdetails=1") ?: throw GeolocationException("Could not get Geolocation")

        return response
    }
}