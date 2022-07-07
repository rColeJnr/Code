@file:Suppress("SameParameterValue")

import kotlin.math.floor

fun main() {

    val result = decryptData(
        intArrayOf(5, 100, 20, 66, 16),
        discount = 50,
        offset = 0,
        readLength = 3
    )

    println(result.contentToString())
}

/**
 * Метод "скидка". Применяет скидку discount к цене price, начиная с позиции offset
 * на количество позиций readLength. Новые цены округляем “вниз”,
 * до меньшего целого числа.
 * @param price - исходные цены, цена должна быть больше нуля
 * @param discount - % скидки, должен попадать в диапазон от 1 до 99
 * @param offset - номер позиции, с которой нужно применить скидку, должен быть больше или равен нулю
 * @param readLength - количество позиций, к которым нужно применить скидку, должно быть больше нуля
 * @return - массив новых цен.
 */

private fun decryptData(
    price: IntArray,
    discount: Int,
    offset: Int,
    readLength: Int
): IntArray {

    val result = IntArray(readLength)

    for (p in price) {
        if (p <= 0) throw UnsupportedOperationException("цена должна быть больше нуля")
    }

    if (discount < 1 || discount > 99)
        throw Exception("скидки, должен попадать в диапазон от 1 до 99")

    if (offset >= price.size || offset < 0)
        throw IndexOutOfBoundsException("offset должен быть больше 0 и меньше, чем price.size")

    if ( (readLength + offset) > price.size || readLength < 0)
        throw IndexOutOfBoundsException("readLength должно быть больше 0 и меньше чем price.size + offset")


    var i = 0
    var initialPosition = offset
    var length = readLength


    while (length > 0) {
        result[i] = discount(price = price[initialPosition], discount =  discount)

        i++
        initialPosition++
        length--
    }

    return result
}

private fun discount(price: Int, discount: Int): Int {

    val value: Double = price - ( (price.toDouble() * discount) / 100 )

    return floor(value).toInt()
}


