package nasserkhosravi.la.geometry.model

import nasserkhosravi.la.geometry.assertDoubleEquals
import nasserkhosravi.la.geometry.create1dMat
import org.junit.Assert.assertEquals
import org.junit.Test

class MatTest {

    @Test
    fun at() {
        val m = create1dMat(3, 3, arrayOf(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        ))

        assertDoubleEquals(1.0, m.at(0, 0))
        assertDoubleEquals(3.0, m.at(0, 2))
        assertDoubleEquals(9.0, m.at(2, 2))

        val m2 = create1dMat(2, 3, arrayOf(
                2, 3, 7,
                4, 5, 6
        ))
        assertDoubleEquals(2.0, m2.at(0, 0))
        assertDoubleEquals(3.0, m2.at(0, 1))
        assertDoubleEquals(7.0, m2.at(0, 2))
        assertDoubleEquals(7.0, m2.at(0, 2))
        assertDoubleEquals(4.0, m2.at(1, 0))
        assertDoubleEquals(5.0, m2.at(1, 1))
        assertDoubleEquals(6.0, m2.at(1, 2))
    }

    @Test
    fun getElemInfo() {
        val m = create1dMat(3, 3, arrayOf(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        ))
        val result = MatElemInfo()

        Mat.getElemInfo(m, 1, result)
        assertEquals(0, result.row)
        assertEquals(1, result.col)

        Mat.getElemInfo(m, 3, result)
        assertEquals(1, result.row)
        assertEquals(0, result.col)

        Mat.getElemInfo(m, 8, result)
        assertEquals(2, result.row)
        assertEquals(2, result.col)

    }

    @Test
    fun makeSquare() {
        val mat2 = create1dMat(3, 5, arrayOf(
                6, 1, 1, 1, 1,
                4, 5, 1, 1, 1,
                2, 8, 3, 1, 1
        ))
        val mat3 = create1dMat(6, 3, arrayOf(
                6, -1, 8,
                4, 2, 1,
                7, 3, 9,
                90, 0, 0,
                10, 0, 0,
                101, 0, 0
        ))
        mat2.square()
        mat2.printPretty()
        println()
        mat3.square()
        mat3.printPretty()
    }

    @Test
    fun col() {
        val mat = create1dMat(4, 3, arrayOf(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9,
                10, 11, 12
        ))
        assertEquals(mat.col(0), Vec(1, 4, 7, 1))
        assertEquals(mat.col(1), Vec(2, 5, 8, 11))

        val mat2 = create1dMat(3, 5, arrayOf(
                6, 1, 1, 1, 1,
                4, 5, 1, 1, 1,
                2, 8, 3, 1, 0
        ))

        assertEquals(mat2.col(0), Vec(6, 4, 2))
        assertEquals(mat2.col(4), Vec(1, 1, 0))
    }

    @Test
    fun hadamardProduct() {
        val data1 = arrayOf(
                1, 2, 3,
                4, 5, 6
        )
        val mat1 = create1dMat(2, 3, data1.clone())
        val data2 = arrayOf(
                2, 3, 5,
                1, 3, 0
        )
        val mat2 = create1dMat(2, 3, data2.clone())
        val result = mat1.hadamardProduct(mat2)
        result.printPretty()

        println()

//        create2dMat(3,data1.clone())
//        assertArrayEquals(arrayOf(
//                1 * 2, 2 * 3, 3 * 5,
//                4 * 1, 5 * 3, 6 * 0).toDouble(),
//
//        )
    }

    @Test
    fun trace() {
        val mat = create1dMat(2, 6, arrayOf(
                2, 3, 7,7,7,7,
                4, 5, 6,6,6,6
        ))
        assertDoubleEquals(7.0, mat.trace())

        val mat2 = create1dMat(3, 3, arrayOf(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        ))
        assertDoubleEquals(15.0, mat2.trace())

        val mat3 = create1dMat(7, 2, arrayOf(
                6, 2,
                7, 4,
                3, 6,
                3, 6,
                3, 6,
                3, 6,
                3, 6
        ))
        assertDoubleEquals(10.0, mat3.trace())
    }
}

