package nasserkhosravi.la.geometry.model

open class Mat private constructor() : MatAbstract() {

    private var rows: Int = 0
    private var cols: Int = 0

    fun setSize(rowSize: Int, colSize: Int) {
        this.rows = rowSize
        this.cols = colSize
    }

    fun at(row: Int, col: Int): Double {
        return data[row * rows + col]
    }

    fun printData() {
        var colIndex = 0
        for (i in 0 until data.size) {
            print("${data[i]}  ")
            val isOnLastColumn = (i + 1) / (cols) == 1 + colIndex
            if (isOnLastColumn) {
                println()
                colIndex++
            }
        }
    }

    fun col(index: Int): Vec {
        val array = Array(rows) { 0.00 }
        var counterArray = 0
        for (i in 0 until rows) {
            val d = data[index + (i * cols)]
            array[counterArray] = d
            counterArray++
        }
        return Vec(array)
    }

    fun row(index: Int): Vec {
        val startIndex = cols * index
        val endIndex = startIndex + cols - 1

        val array = Array(cols) { 0.00 }
        var counterArray = 0
        for (i in startIndex..endIndex) {
            array[counterArray] = data[i]
            counterArray++
        }
        return Vec(array)
    }

    fun isSquare(): Boolean {
        return rows == cols
    }

    fun setCol(index: Int, v: Vec) {
        for (i in 0 until rows) {
            val indexData = index + (i * cols)
            data[indexData] = v[i]
        }
    }

    fun at(s: Char): Double {
        val ascii = s.toInt() - 97
        return data[ascii]
    }

    companion object {

        fun arrayOfDuble(vararg e: Int): Array<Double> {
            return Array(e.size) { e[it].toDouble() }
        }

        fun constructForEigenLib(m: Mat): String {
            var colIndex = 0
            val res = StringBuilder()
            for (i in 0 until m.data.size) {
                res.append("${m.data[i]}  ")
                val isOnLastColumn = (i + 1) / (m.cols) == 1 + colIndex
                if (isOnLastColumn) {
                    res.append(";")
                    colIndex++
                }
            }
            return res.toString()
        }

        fun to2DiscontinueArray(m: Mat): Array<DoubleArray> {
            var colIndex = 0
            var rowIndex = 0
            var endIndex = 0
            val res = Array<DoubleArray>(m.rows) {
                DoubleArray(m.cols) { 0.0 }
            }
            for (i in 0 until m.data.size) {
                res[rowIndex][colIndex] = m.data[i]
                val isOnLastColumn = (i + 1) / (m.cols) == 1 + endIndex
                colIndex++
                if (isOnLastColumn) {
                    endIndex++
                    colIndex = 0
                    rowIndex++
                }
            }
            return res
        }

        fun create(rowSize: Int, colSize: Int, data: Array<Double>): Mat {
            val tMat = Mat()
            tMat.data = (data)
            tMat.setSize(rowSize, colSize)
            return tMat
        }

        fun scale(mat: MatAbstract, scalar: Int) {
            val data = mat.data
            for (i in 0 until data.size) {
                data[i] = scalar * data[i]
            }
        }

        fun add(m0: Mat, m1: Mat, mOut: Mat) {
            assertSameSize(m0, m1)
            val size = m0.data.size
            for (i in 0 until size) {
                mOut[i] = m0[i] + m1[i]
            }
        }

        //you should check also mOut matrix size
        fun sub(m0: Mat, m1: Mat, mOut: Mat) {
            assertSameSize(m0, m1)
            val size = m0.data.size
            for (i in 0 until size) {
                mOut[i] = m0[i] - m1[i]
            }
        }

        fun multiply(m1: Mat, m2: Mat): Mat {
            assertMultiplySize(m1, m2)
            val result = Array(m1.rows * m2.cols) { 0.0 }
            for (i in 0 until m1.rows) {
                val m1Row = m1.row(i)
                for (j in 0 until m2.cols) {
                    val m2Col = m2.col(j)
                    result[i * m1.rows + j] = (m1Row * m2Col).sumUp()
                }
            }
            return create(m1.rows, m2.cols, result)
        }

        fun transpose(m0: Mat) {
            val data = Array(m0.data.size) { 0.0 }
            val mOut = create(m0.cols, m0.rows, data)
            for (i in 0 until m0.rows) {
                val r = m0.row(i)
                mOut.setCol(i, r)
            }

            mOut.printData()
        }

        fun det2x2(m: Mat): Double {
            assertIsSquare(m)
            assertSizeIs(m, 2)
            return (m.at('a') * m.at('d')) - (m.at('b') * m.at('c'))
        }

        fun det3x3(m: Mat): Double {
            assertIsSquare(m)
            assertSizeIs(m, 3)
            val a = m.at('a') * (m.at('e') * m.at('i') - (m.at('f') * m.at('h')))
            val b = m.at('b') * (m.at('d') * m.at('i') - (m.at('f') * m.at('g')))
            val c = m.at('c') * (m.at('d') * m.at('h') - (m.at('e') * m.at('g')))
            return a - b + c
        }

        private fun assertMultiplySize(m1: Mat, m2: Mat) {
            if (m1.cols != m2.rows) {
                throw IllegalArgumentException("Matrices is not multiply able because m1.cols != m2.rows : ${m1.cols} != ${m2.rows}")
            }
        }

        private fun assertIsSquare(m: Mat) {
            if (!m.isSquare()) {
                throw AssertionError("Mat is not square rows=$m.rows cols=$m.cols")
            }
        }

        private fun assertSameSize(m0: Mat, m1: Mat) {
            if (m0.rows != m1.rows) {
                throw AssertionError("Mat size are not same m0.rows != m1.rows= ${m0.rows} != ${m1.rows}")
            }

            if (m0.cols != m1.cols) {
                throw AssertionError("Mat size are not same m0.cols != m1.cols= ${m0.cols} != ${m1.cols}")
            }

        }

        private fun assertSizeIs(m: Mat, size: Int) {
            if (m.rows != size || m.cols != size) {
                throw IllegalStateException("Matrix $size x $size needed")
            }
        }

    }

}