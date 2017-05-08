package ninja.harmless.mad.geolocation

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import ninja.harmless.mad.common.CustomJsonFloatDeserializer

/**
 * @author bnjm@harmless.ninja - 5/7/17.
 */
class Geolocation {
    @JsonDeserialize(using = CustomJsonFloatDeserializer::class)
    var lat: Float = 0f
    @JsonDeserialize(using = CustomJsonFloatDeserializer::class)
    var lon: Float = 0f
    var display_name: String = ""
}