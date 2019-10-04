package nasserkhosravi.la.geometry.model

import java.text.NumberFormat

interface ModelFormat{

    fun toPretty(numberFormat: NumberFormat? = null): String

    fun printPretty(formatter: NumberFormat? = null) {
        println(toPretty(formatter))
    }
}