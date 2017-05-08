package ninja.harmless.mad.http

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request

class HTTPRequestImpl<T>(domainClass : Class<T>)  : HTTPRequest<T> {

    val domainClass : Class<T> = domainClass


    override fun get(url: String): T {
        val client = OkHttpClient()
        val request : Request = Request.Builder()
                .url(url)
                .addHeader("Accept","application/json")
                .addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36")
                .addHeader("ccept-Charset", "UTF-8").build()

        val response = client.newCall(request).execute()
        val objectMapper = ObjectMapper()
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return objectMapper.readValue(response.body().string(), domainClass)
    }
}