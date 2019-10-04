package nasserkhosravi.la.geometry.another

import nasserkhosravi.la.geometry.model.Mat
import nasserkhosravi.la.geometry.model.Vec

class MatDiagonalInfo(val mat: Mat) {

    private val isSquare = mat.isSquare()
    private var isDiagonal: Boolean? = null
    private var diagonalElems: Vec? = null

    private fun compute(): Boolean {
        if (!isSquare) {
            throw UnsupportedOperationException()
        }
        mat.data.forEachIndexed { index, d ->
            if (!checkOnDiagonal(mat, index)) {
                if (d != 0.0) {
                    return false
                }
            }
        }
        return true
    }

    /**
     * works with square and non square matrix
     */
    fun findDiagonalElements(): Vec {
        //todo: what are these shit. check trace() method
        val square = if (!isSquare) {
            mat.square()
        } else {
            mat
        }

        val skipSize = square.colSize + 1
        val size = square.data.size
        val data = ArrayList<Double>(square.colSize)

        for (index in 0..size step skipSize) {
            data.add(square.data[index])
        }
        return Vec(data.toTypedArray())
    }

    fun getDiagonalElements(): Vec {
        if (diagonalElems == null) {
            diagonalElems = findDiagonalElements()
        }
        return diagonalElems!!
    }

    fun isDiagonal(): Boolean {
        if (isDiagonal == null) {
            isDiagonal = compute()
        }
        return isDiagonal!!
    }


    /**
     * checking iIndex lie on diagonal of matrix.
     * it's works with square matrix
     */
    fun isElementOnDiagonal(index: Int): Boolean {
        return isElementOnDiagonal(mat, index)
    }

    companion object {

        private fun checkOnDiagonal(m: Mat, index: Int): Boolean {
            val size = m.colSize + 1
            val i = index % size
            return i == 0
        }

        /**
         * checking iIndex lie on diagonal of matrix.
         * it's works with square matrix
         */
        fun isElementOnDiagonal(m: Mat, index: Int): Boolean {
            if (m.isSquare()) {
                //check if iIndex is multiple of colSize
                return checkOnDiagonal(m, index)
            } else {
                throw UnsupportedOperationException()
            }
        }
    }
}