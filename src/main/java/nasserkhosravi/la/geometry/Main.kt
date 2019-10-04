package nasserkhosravi.la.geometry

class Main {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val mat2x2 = create1dMat(2, 2, arrayOf(
                    4, 7,
                    2, 6
            ))
            val mat3x3 = create1dMat(
                    3, 3, arrayOf(
                    6, 1, 1,
                    4, -2, 5,
                    2, 8, 7
            ))

            val mat2D = create2dMat(3, 2, arrayOf(
                    4, 7,
                    2, 6,
                    3, 9
            ))
            val matOne = create1dMat(4, 4, arrayOf(
                    1, 2, 3, 4,
                    5, 6, 7, 8,
                    9, 10, 11, 12,
                    13, 14, 15, 16
            ))
            for (i in 0 until matOne.rowSize) {
                for (j in i until matOne.colSize) {
                    print("${matOne.at(i, j)} ")
                }
                println()
            }
            for (i in 0 until matOne.rowSize) {
                for (j in i until matOne.colSize) {
                    print("${matOne.at(j, i)} ")
                }
                println()
            }
        }

    }
}