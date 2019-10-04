package nasserkhosravi.la.geometry.model

import nasserkhosravi.la.geometry.printPretty
import org.junit.Assert.assertEquals
import org.junit.Test

class VecTest {

    @Test
    fun sumUp() {
        Vec(1, 2, 3).apply {
            assertEquals(6.0, sumUp(), 0.0001)
        }
    }

    @Test
    fun multiply() {
        val v1 = Vec(2, 3, 4)
        val v2 = Vec(1, 3, 5)
        val result = v1 * v2
        assertEquals(Vec(2 * 1, 3 * 3, 4 * 5), result)
    }
}