package nasserkhosravi.la.geometry

import nasserkhosravi.la.geometry.another.MatDiagonalInfo
import nasserkhosravi.la.geometry.model.BaseMat
import nasserkhosravi.la.geometry.model.Mat
import nasserkhosravi.la.geometry.model.MatElemInfo
import nasserkhosravi.la.geometry.model.Vec

object MatOp {


    fun det2x2(m: Mat): Double {
        Mat.assertIsSquare(m)
        Mat.assertSizeIs(m, 2)
        return (m.atRowCol(11) * m.atRowCol(22)) - (m.atRowCol(12) * m.atRowCol(21))
    }

    fun det3x3(m: Mat): Double {
        Mat.assertIsSquare(m)
        Mat.assertSizeIs(m, 3)
        val a = m.atRowCol(11) * (m.atRowCol(22) * m.atRowCol(33) - (m.atRowCol(23) * m.atRowCol(32)))
        val b = m.atRowCol(12) * (m.atRowCol(21) * m.atRowCol(33) - (m.atRowCol(23) * m.atRowCol(31)))
        val c = m.atRowCol(13) * (m.atRowCol(21) * m.atRowCol(32) - (m.atRowCol(22) * m.atRowCol(31)))
        return a - b + c
    }

    fun detDiagonal(m: Mat): Double {
        require(m.isSquare())
        //todo: check it's a diagonal matrix
        val vec = MatDiagonalInfo(m).findDiagonalElements()
        return vec.data.reduce { d: Double, d1: Double ->
            d * d1
        }
    }

//    fun add(m0: Mat, m1: Mat, mOut: Mat) {
//        Mat.assertSameSize(m0, m1)
//        Mat.assertSameSize(m0, mOut)
//        val size = m0.data.size
//        for (i in 0 until size) {
//            mOut[i] = m0[i] + m1[i]
//        }
//    }

    fun add2(m1: BaseMat, m2: BaseMat) {
    }

//    fun sub(m0: Mat, m1: Mat, mOut: Mat) {
//        Mat.assertSameSize(m0, m1)
//        Mat.assertSameSize(m0, mOut)
//        val size = m0.data.size
//
//        for (i in 0 until size) {
//            mOut[i] = m0[i] - m1[i]
//        }
//    }

    fun multiply(m1: Mat, m2: Mat): Mat {
        Mat.assertMultiplySize(m1, m2)
        val out = DoubleArray(m1.rowSize * m2.colSize)

        for (i in 0 until m1.rowSize) {
            for (j in 0 until m2.colSize) {
                val sum = (m1.row(i) * m2.col(j)).sumUp()
                out[i * m1.rowSize + j] = sum
            }
        }
        return create1dMat(m1.rowSize, m2.colSize, out)
    }

    fun transpose(m0: Mat, mOut: Mat): Mat {
        for (i in 0 until m0.rowSize) {
            val r = m0.row(i)
            mOut.setCol(i, r)
        }
        return mOut
    }

    //    fun concat(m1: Mat, m2: Mat): Mat {
//        Mat.assertSameSize(m1, m2)
//        val newRowSize = m1.rowSize + m2.rowSize
//        val newColSize = m1.colSize + m2.colSize
//        val data = Array(newRowSize * newColSize) { 0.0 }
//        val result = Mat.create1dMat(newRowSize, newColSize, data)
//        return result
//    }


}