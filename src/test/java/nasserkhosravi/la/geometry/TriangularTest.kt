package nasserkhosravi.la.geometry

import nasserkhosravi.la.geometry.model.Mat
import org.junit.Test

class TriangularTest {

    @Test
    fun run() {

        val mat = create1dMat(3, 3, arrayOf(
                1, 5, 0,
                2, 1, 0,
                1, 0, 3
        ))

        val mat2 = create1dMat(4, 4, arrayOf(
                2, 4, -2, 6,
                1, 2, 5, 4,
                1, 1, 2, 4,
                3, 2, -6, 3
        ))

//        val mat = arrayOf(intArrayOf(2, -1, -2), intArrayOf(-4, 6, 3), intArrayOf(-4, -2, 8))
        val mat3 = create1dMat(3, 3, arrayOf(
                2, -1, -2,
                -4, 6, 3,
                -4, -2, 8
        ))
        val mat4 = create1dMat(4, 4, arrayOf(
                5, -7, 2, 2,
                0, 3, 0, 4,
                -5, -8, 0, 3,
                0, 5, 0, -6
        ))
        val mat5 = create1dMat(5, 5, arrayOf(
                0, 6, -2, -1, 5,
                0, 0, 0, -9, -7,
                0, 15, 35, 0, 0,
                0, -1, -11, -2, 1,
                -2, -2, 3, 0, -2

        ))

        LU_Crout(mat2).run()
//
//        1 5 0
//        2 1 0
//        1 0 3
    }
}