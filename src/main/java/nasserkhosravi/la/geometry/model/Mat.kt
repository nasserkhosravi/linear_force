package nasserkhosravi.la.geometry.model

import nasserkhosravi.la.geometry.MatOp
import nasserkhosravi.la.geometry.OneDimIterator
import nasserkhosravi.la.geometry.create1dMat
import nasserkhosravi.la.geometry.isPositive
import org.la4j.Matrix
import java.text.NumberFormat

/**
 * Dense row-major matrix that store data in one array
 *
 * @see https://en.wikipedia.org/wiki/Row-_and_column-major_order
 */
open class Mat : BaseMat() {

    override fun iterator() = OneDimIterator(data)

    override var rowSize: Int = 0
    override var colSize: Int = 0

    lateinit var data: DoubleArray

    operator fun get(v: Int) = data[v]

    operator fun set(i: Int, value: Double) {
        data[i] = value
    }

    override fun at(row: Int, col: Int) = data[row  * colSize + col]

    override fun setAt(row: Int, col: Int, value: Double) {
        data[row * rowSize + col] = value
    }

    override fun col(index: Int): Vec {
        val array = Array(rowSize) { 0.00 }
        val stepSize = colSize

        for ((counter, elementIndex) in (index until data.size step stepSize).withIndex()) {
            array[counter] = data[elementIndex]
        }
        return Vec(array)
    }

    override fun row(index: Int): Vec {
        val startIndex = colSize * index
        val endIndex = startIndex + colSize - 1

        val array = Array(colSize) { 0.00 }
        for ((counter, i) in (startIndex..endIndex).withIndex()) {
            array[counter] = data[i]
        }
        return Vec(array)
    }

    override fun setCol(index: Int, v: Vec) {
        for (i in 0 until rowSize) {
            val indexData = index + (i * colSize)
            data[indexData] = v[i]
        }
    }

    override fun setRow(index: Int, v: Vec) {
        for (i in 0 until colSize) {
            val from = (index * rowSize) + i
            data[from] = v[i]
        }
    }

    /**
     * easy access to matrix elements
     * If you assume first row as 10 and second row as 20 then we can access to elements as following
     * [ a11,a12,a13
     *   a21,a22,a23 ]
     */
    fun atRowCol(index: Int): Double {
        val isValid = index < 11 || index > 99
        require(!isValid)
        val row = index / 10
        val col = index % 10
        return at(row - 1, col - 1)
    }

    fun swapRow(fromIndex: Int, toIndex: Int) {
        val from = row(fromIndex)
        val to = row(toIndex)

        setRow(toIndex, from)
        setRow(fromIndex, to)
    }

    fun swapCol(fromIndex: Int, toIndex: Int) {
        val from = col(fromIndex)
        val to = col(toIndex)

        setCol(toIndex, from)
        setCol(fromIndex, to)
    }

    fun allRows(): ArrayList<Vec> {
        val result = ArrayList<Vec>(rowSize)
        for (i in 0 until rowSize) {
            result.add(row(i))
        }
        return result
    }

    fun allCols(): ArrayList<Vec> {
        val result = ArrayList<Vec>(colSize)
        for (i in 0 until colSize) {
            result.add(col(i))
        }
        return result
    }

    fun subMat(fromRow: Int, fromCol: Int, untilRow: Int, untilCol: Int) {
        //todo: take a look atRowCol square()
    }

    fun square(): Mat {
        val minSideSize = getMinSideSize()
        val values = ArrayList<Double>(minSideSize * minSideSize)
        for (i in 0 until minSideSize) {
            val row = row(i)
            for (j in 0 until minSideSize) {
                values.add(row.data[j])
            }
        }
        return create1dMat(minSideSize, minSideSize, values.toTypedArray().toDoubleArray())
    }

    fun power(pow: Int): Mat {

        require(pow.isPositive())
        return when (pow) {
            0 -> eye(rowSize)
            1 -> this.copy()
            else -> {
                var mat = MatOp.multiply(this, this)
                for (i in 2 until pow) {
                    mat = MatOp.multiply(mat, this)
                }
                mat
            }
        }
    }

    override fun createSelf(rowSize: Int, colSize: Int, result: DoubleArray): BaseMat {
        return create1dMat(rowSize, colSize, result)
    }

    fun copy(): Mat {
        return create1dMat(rowSize, colSize, data.clone())
    }

    override fun toPretty(numberFormat: NumberFormat?): String {
        val builder = StringBuilder()
        var colIndex = 0
        for (i in data.indices) {
            if (numberFormat != null) {
                builder.append("${numberFormat.format(data[i])}  ")
            } else {
                builder.append("${data[i]}  ")
            }


            val isOnLastColumn = (i + 1) / (colSize) == 1 + colIndex
            if (isOnLastColumn) {
                builder.appendln()
                colIndex++
            }
        }
        return builder.toString()
    }

    fun to2DArray(): Array<Array<Double>> {
        val array = Array(rowSize) {
            Array(colSize) { 0.0 }
        }
        array.forEachIndexed { index, doubles ->
            row(index).data.forEachIndexed { index, value ->
                doubles[index] = value
            }
        }
        return array
    }

    fun toLA4j() = Matrix.from1DArray(rowSize, colSize, data)

    companion object {
        //todo: move to matrix creation package
        fun toEigenLibMat(m: Mat): String {
            var colIndex = 0
            val res = StringBuilder()
            for (i in m.data.indices) {
                res.append("${m.data[i]}  ")
                val isOnLastColumn = (i + 1) / (m.colSize) == 1 + colIndex
                if (isOnLastColumn) {
                    res.append(";")
                    colIndex++
                }
            }
            return res.toString()
        }

        fun to2dDoubleArray(m: Mat): Array<DoubleArray> {
            var colIndex = 0
            var rowIndex = 0
            var endIndex = 0
            val res = Array(m.rowSize) {
                DoubleArray(m.colSize) { 0.0 }
            }
            for (i in 0 until m.data.size) {
                res[rowIndex][colIndex] = m.data[i]
                val isOnLastColumn = (i + 1) / (m.colSize) == 1 + endIndex
                colIndex++
                if (isOnLastColumn) {
                    endIndex++
                    colIndex = 0
                    rowIndex++
                }
            }
            return res
        }


        fun createEmpty(rowSize: Int, colSize: Int): Mat {
            val array = DoubleArray(rowSize * colSize)
            return create1dMat(rowSize, colSize, array)
        }

        fun eye(size: Int): Mat {
            require(size > 0)
            val pattern = size + 1
            val data = DoubleArray(size * size)
            for (i in 0 until size) {
                data[pattern * i] = 1.0
            }
            return create1dMat(size, size, data)
        }

        fun assertMultiplySize(m1: Mat, m2: Mat) {
            if (m1.colSize != m2.rowSize) {
                throw IllegalArgumentException("Matrices is not multiply able because m1.colSize != m2.rowSize : ${m1.colSize} != ${m2.rowSize}")
            }
        }

        fun assertIsSquare(m: Mat) {
            if (!m.isSquare()) {
                throw AssertionError("Mat is not square rowSize=$m.rowSize colSize=$m.colSize")
            }
        }

        fun getElemInfo(m: Mat, index: Int, infoOut: MatElemInfo) {
            require(index > -1)
            val rowIndex = index / m.rowSize
            val colIndex = index % m.colSize
            infoOut.row = rowIndex
            infoOut.col = colIndex
            infoOut.value = m.at(rowIndex, colIndex)
        }

        fun assertSizeIs(m: Mat, size: Int) {
            if (m.rowSize != size || m.colSize != size) {
                throw IllegalStateException("Matrix $size x $size needed")
            }
        }

    }

}

