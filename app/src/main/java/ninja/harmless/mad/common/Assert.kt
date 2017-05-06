package ninja.harmless.mad.common

/**
 * @author bnjm@harmless.ninja - 5/6/17.
 */
class Assert {
    companion object {
        fun isTrue(item: Boolean) {
            if(!item) {
                throw IllegalStateException("Assertion failed: $item is not true")
            }
        }
    }
}