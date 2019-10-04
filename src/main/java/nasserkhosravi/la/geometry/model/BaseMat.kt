package nasserkhosravi.la.geometry.model

import org.la4j.Matrix
import kotlin.math.min

abstract class BaseMat : Iterable<Double>, ModelFormat {

    open var rowSize: Int = 0
    open var colSize: Int = 0

    open fun getSize() = rowSize * colSize


    abstract fun createSelf(rowSize: Int, colSize: Int, result: DoubleArray): BaseMat
    /**
     * row and col Index, start by 0
     */
    abstract fun at(row: Int, col: Int): Double

    abstract fun setAt(row: Int, col: Int, value: Double)
    abstract fun col(index: Int): Vec
    abstract fun row(index: Int): Vec
    abstract fun setRow(index: Int, v: Vec)

    abstract fun setCol(index: Int, v: Vec)

    fun isSquare() = rowSize == colSize

    fun getMinSideSize() = min(rowSize, colSize)

    fun determinate(): Double {
        require(isSquare())
        return when (this) {
            is Mat -> Matrix.from1DArray(rowSize, colSize, data).determinant()
            is Mat2D -> Matrix.from2DArray(data).determinant()
            else -> throw IllegalStateException()
        }
    }

    fun hadamardProduct(to: BaseMat): BaseMat {
        assertSameSize(this, to)
        val result = DoubleArray(getSize())
        zip(to).forEachIndexed { index, pair ->
            result[index] = pair.first * pair.second
        }
        return createSelf(rowSize, colSize, result)
    }

    fun trace(): Double {
        var sum = 0.0
        //diagonal iteration
        for (i in 0 until getMinSideSize()) {
            sum += at(i, i)
        }
        return sum
    }

    fun toLA4J(): Matrix {
        return when (this) {
            is Mat -> Matrix.from1DArray(rowSize, colSize, data)
            is Mat2D -> Matrix.from2DArray(data)
            else -> throw IllegalStateException()
        }
    }

    fun assertSameSize(m1: BaseMat, m2: BaseMat) {
        if (m1.rowSize != m2.rowSize) {
            throw AssertionError("Mat size are not same m1.rowSize != m2.rowSize= ${m1.rowSize} != ${m2.rowSize}")
        }

        if (m1.colSize != m2.colSize) {
            throw AssertionError("Mat size are not same m1.colSize != m2.colSize= ${m1.colSize} != ${m2.colSize}")
        }
    }

}