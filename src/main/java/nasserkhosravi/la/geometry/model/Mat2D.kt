package nasserkhosravi.la.geometry.model

import nasserkhosravi.la.geometry.TwoDimIterator
import nasserkhosravi.la.geometry.create2dMat
import java.text.NumberFormat

class Mat2D(override var rowSize: Int, override var colSize: Int, val data: Array<DoubleArray>) : BaseMat() {

    operator fun get(index: Int): DoubleArray = data[index]

    operator fun set(i: Int, value: DoubleArray) {
        data[i] = value
    }

    override fun at(row: Int, col: Int) = data[row][col]

    override fun setAt(row: Int, col: Int, value: Double) {
        data[row][col] = value
    }

    override fun col(index: Int): Vec {
        val result = DoubleArray(colSize)
        for (i in 0 until rowSize) {
            result[i] = data[i][index]
        }
        return Vec(result)
    }

    override fun row(index: Int) = Vec(data[index])

    override fun setRow(index: Int, v: Vec) {
        data[index] = v.data
    }

    override fun setCol(index: Int, v: Vec) {
        for (i in 0 until rowSize) {
            data[index][i] = v[i]
        }
    }

    override fun iterator() = TwoDimIterator(data, rowSize,colSize)

    override fun toPretty(numberFormat: NumberFormat?): String {
        val builder = StringBuilder()
        data.forEach {
            it.forEach { value ->
                if (numberFormat != null) {
                    builder.append("${numberFormat.format(value)} ")
                } else {
                    builder.append("$value ")
                }
            }
            builder.appendln()
        }
        return builder.toString()
    }

    override fun createSelf(rowSize: Int, colSize: Int, result: DoubleArray): BaseMat {
        return create2dMat(rowSize, colSize, result)
    }

}

