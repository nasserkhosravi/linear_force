package nasserkhosravi.la.geometry

import nasserkhosravi.la.geometry.model.Mat
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import org.la4j.Matrix

class MatOpTest {

    @Test
    fun identity() {
        val i = Mat.eye(2)
        assertEquals(2, i.colSize)
        assertEquals(2, i.rowSize)
        assertEquals(4, i.data.size)

    }

    @Test
    fun concat() {
        val mat1 = create1dMat(3, 3, arrayOf(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        )
        )
        val mat2 = Mat.eye(3)
//        val mat = MatOp.concat(mat1, mat2)
//        mat.printPretty()
    }

    @Test
    fun permutation() {
    }

    @Test
    fun multiply() {
        val data = arrayOf(
                1, 2,
                3, 4
        )

        val mat = create1dMat(2, 2, data.clone())
        val result = MatOp.multiply(mat, mat)
//        assertArrayEquals(result.data, arrayOf(
//                7, 10,
//                15, 22)
//        )

    }

    @Test
    fun pow() {
        val data = arrayOf(
                1, 5, 0,
                2, 1, 0,
                1, 0, 3
        )
        val powTest = 5
        var mat = create1dMat(3, 3, data)
        mat = mat.power(powTest)
//        var la4j = Matrix.from1DArray(3, 3, data.clone().toDoubleArray())
//        la4j = la4j.power(powTest)
    }

    @Test
    fun detDiagonal() {
        val mat = create1dMat(3, 3, arrayOf(
                6, 0, 0,
                0, 1, 0,
                0, 0, 7
        ))

        val actual = MatOp.detDiagonal(mat)
        assertEquals(42.0, actual, 0.0001)
        val mla4j = Matrix.from1DArray(mat.rowSize, mat.colSize, mat.data)
        assertEquals(42.0, mla4j.determinant(), 0.0001)

    }

}