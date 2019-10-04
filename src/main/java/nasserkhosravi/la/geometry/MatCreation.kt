package nasserkhosravi.la.geometry

import nasserkhosravi.la.geometry.model.Mat
import nasserkhosravi.la.geometry.model.Mat2D

fun create2dMat(array: Array<Array<Int>>): Mat2D {
    val doubleArray = Array(array.size) { DoubleArray(array[0].size) }
    array.forEachIndexed { i, child ->
        child.forEachIndexed { j, value ->
            doubleArray[i][j] = value.toDouble()
        }
    }
    return create2dMat(doubleArray)
}

fun create2dMat(array: Array<IntArray>): Mat2D {
    val doubleArray = Array(array.size) { DoubleArray(array[0].size) }
    array.forEachIndexed { i, child ->
        child.forEachIndexed { j, value ->
            doubleArray[i][j] = value.toDouble()
        }
    }
    return create2dMat(doubleArray)
}

fun create2dMat(array: Array<DoubleArray>): Mat2D {
    return Mat2D(array.size, array[0].size, array)
}

fun create2dMat(rowSize: Int, colSize: Int, data: Array<Int>): Mat2D {
    val values = DoubleArray(data.size) { index -> data[index].toDouble() }
    return create2dMat(rowSize,colSize,values)
}

fun create2dMat(rowSize: Int,colSize: Int, data: DoubleArray): Mat2D {
    val doubleArray = Array(rowSize) { DoubleArray(colSize) }
    var colIndex = 0
    var rowIndex = 0
    var endIndex = 0
    for (i in data.indices) {
        doubleArray[rowIndex][colIndex] = data[i]
        val isOnLastColumn = (i + 1) / (colSize) == 1 + endIndex
        colIndex++
        if (isOnLastColumn) {
            endIndex++
            colIndex = 0
            rowIndex++
        }
    }
    return Mat2D(rowSize,colSize,doubleArray)
}

//########## ONE D MATRIX ###########
fun create1dMat(rowSize: Int, colSize: Int, data: DoubleArray): Mat {
    require((rowSize * colSize) == data.size)
    return Mat().also {
        it.data = data
        it.rowSize = rowSize
        it.colSize = colSize
    }
}

fun create1dMat(rowSize: Int, colSize: Int, data: IntArray): Mat {
    return create1dMat(rowSize, colSize, data.toDouble())
}

fun create1dMat(rowSize: Int, colSize: Int, data: Array<Int>): Mat {
    val values = DoubleArray(data.size) { index -> data[index].toDouble() }
    return create1dMat(rowSize, colSize, values)
}

fun create1dMat(array: Array<DoubleArray>): Mat {
    val rowSize = array.size
    val colSize = array[0].size
    val data = DoubleArray(rowSize * colSize)
    var counter = 0
    array.forEach {
        require(it.size == colSize) { "conflict in col size" }
        it.forEach { value ->
            data[counter] = value
            counter++
        }
    }
    return create1dMat(rowSize, colSize, data)
}

fun create1dMat(array: Array<Array<Double>>): Mat {
    val rowSize = array.size
    val colSize = array[0].size
    val data = DoubleArray(rowSize * colSize)
    var counter = 0
    array.forEach {
        require(it.size == colSize) { "conflict in col size" }
        it.forEach { value ->
            data[counter] = value
            counter++
        }
    }
    return create1dMat(rowSize, colSize, data)
}
