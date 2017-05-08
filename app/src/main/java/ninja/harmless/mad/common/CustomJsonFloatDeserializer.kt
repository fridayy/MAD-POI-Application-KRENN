package ninja.harmless.mad.common

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

/**
 * @author bnjm@harmless.ninja - 5/7/17.
 */
class CustomJsonFloatDeserializer : JsonDeserializer<Float>() {
    override fun deserialize(jsonParser: JsonParser?, context: DeserializationContext?): Float {
        val text = jsonParser?.getText()
        if (text != null) {
            return text.toFloat()
        }
        throw IllegalStateException("Could not convert String to Long!")
    }
}