package ninja.harmless.mad.http

/**
 * @author bnjm@harmless.ninja - 5/7/17.
 */
interface HTTPRequest<T> {
    fun get(url: String) : T
}