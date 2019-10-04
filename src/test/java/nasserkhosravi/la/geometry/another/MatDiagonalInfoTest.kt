package nasserkhosravi.la.geometry.another

import nasserkhosravi.la.geometry.create1dMat
import nasserkhosravi.la.geometry.model.Mat
import nasserkhosravi.la.geometry.printPretty
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MatDiagonalInfoTest {

    @Test
    fun isElementOnDiagonal() {
        val mat3x3 = create1dMat(3, 3, arrayOf(
                6, 1, 1,
                4, -2, 5,
                2, 8, 7
        ))
        val detector = MatDiagonalInfo(mat3x3)

        assertTrue(detector.isElementOnDiagonal(0))
        assertTrue(detector.isElementOnDiagonal(4))
        assertTrue(detector.isElementOnDiagonal(8))
        assertFalse(detector.isElementOnDiagonal(1))
        assertFalse(detector.isElementOnDiagonal(2))
        assertFalse(detector.isElementOnDiagonal(3))
        assertFalse(detector.isElementOnDiagonal(5))
        assertFalse(detector.isElementOnDiagonal(6))
        assertFalse(detector.isElementOnDiagonal(7))
    }

    @Test
    fun isDiagonal() {
        val mat = create1dMat(3, 3, arrayOf(
                6, 1, 1,
                4, -2, 5,
                2, 8, 7
        ))
        val mat2 = create1dMat(3, 3, arrayOf(
                6, 0, 0,
                0, 1, 0,
                0, 0, 7
        ))

        var detector = MatDiagonalInfo(mat)
        assertFalse(detector.isDiagonal())

        detector = MatDiagonalInfo(mat2)

        assertTrue(detector.isDiagonal())

    }


    @Test
    fun computeDiagonalElements() {
        val mat = create1dMat(3, 3, arrayOf(
                6, 0, 0,
                0, 1, 0,
                0, 0, 7
        ))
        val mat2 = create1dMat(3, 5, arrayOf(
                6, 1, 1, 1, 1,
                4, 5, 1, 1, 1,
                2, 8, 3, 1, 1
        ))
        val mat3 = create1dMat(6, 3, arrayOf(
                6, 1, 1,
                4, 2, 1,
                7, 0, 9,
                90, 0, 0,
                10, 0, 0,
                101, 0, 0
        ))
        println()
        var detector = MatDiagonalInfo(mat)
        detector.findDiagonalElements().printPretty()
        println()
        detector = MatDiagonalInfo(mat2)
        detector.findDiagonalElements().printPretty()
        println()
        detector = MatDiagonalInfo(mat3)
        detector.findDiagonalElements().printPretty()
        println()

    }
}