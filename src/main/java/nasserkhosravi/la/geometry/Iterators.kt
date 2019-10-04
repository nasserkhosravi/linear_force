package nasserkhosravi.la.geometry

/**
 * One dimension data iterator
 * it's used by [Vec],[Mat]
 */
class OneDimIterator(val data: DoubleArray) : Iterator<Double> {
    private var index = 0

    override fun hasNext(): Boolean {
        return data.size > index
    }

    override fun next(): Double {
        val value = data[index]
        index++
        return value
    }

}

/**
 * two dimension data iterator
 * it's used by [Mat2D]
 */
class TwoDimIterator(val data: Array<DoubleArray>, val rowSize:Int,val colSize: Int) : Iterator<Double> {
    private var index = 0

    override fun hasNext(): Boolean {
        return (rowSize * colSize) > index
    }

    override fun next(): Double {
        //row
        val i = index / colSize
        //col
        val j = index % colSize
        val value = data[i][j]
        index++
        return value
    }

}

