package by.samples.mathtranslate.translator.basic

import by.samples.mathtranslate.R
import by.samples.mathtranslate.translator.Translator
import by.samples.mathtranslate.translator.basic.Util.isBinary

object RussianHuffmanTranslator : Translator {

    override val titleId: Int = R.string.russian_huffman

    override fun translate(text: String): String {
        var index = 0
        while (index < text.length && !text[index].isLetter() && !text[index].isBinary()) index++
        return when {
            index == text.length -> text
            text[index].isLetter() -> toHuffman(text)
            else -> fromHuffman(text)
        }
    }

    private fun fromHuffman(text: String): String {
        val result = StringBuilder()
        var i = 0
        while (i < text.length) {
            if (text[i].isBinary()) {
                val temp = StringBuilder()
                while (temp.length < 9 && i < text.length && text[i].isBinary()
                    && !huffmanToRussian.containsKey(temp.toString())) {
                    temp.append(text[i])
                    i++
                }
                result.append(huffmanToRussian[temp.toString()] ?: '?')
            } else {
                result.append(text[i])
                i++
            }
        }
        return result.toString()
    }

    private fun toHuffman(text: String): String {
        val result = StringBuilder()
        text.replace("0", "ноль")
            .replace("1", "один")
            .toLowerCase()
            .forEach { result.append(russianToHuffman[it] ?: it) }
        return result.toString()
    }

    private val huffmanToRussian = hashMapOf(
        "000" to ' ',
        "111" to 'о',
        "0100" to 'е',
        "0110" to 'а',
        "0111" to 'и',
        "1001" to 'т',
        "1010" to 'н',
        "1101" to 'с',
        "00101" to 'р',
        "00111" to 'в',
        "01010" to 'л',
        "10001" to 'к',
        "10111" to 'м',
        "11000" to 'д',
        "001000" to 'п',
        "001001" to 'у',
        "001101" to 'я',
        "010110" to 'ы',
        "010111" to 'з',
        "100001" to 'ь',
        "1011000" to 'б',
        "101101" to 'г',
        "110011" to 'ч',
        "0011001" to 'й',
        "1000000" to 'х',
        "1000001" to 'ж',
        "1100101" to 'ю',
        "00110000" to 'ш',
        "11001000" to 'ц',
        "11001001" to 'щ',
        "001100010" to 'э',
        "001100011" to 'ф'
    )

    private val russianToHuffman = hashMapOf(
        ' ' to "000",
        'о' to "111",
        'е' to "0100",
        'а' to "0110",
        'и' to "0111",
        'т' to "1001",
        'н' to "1010",
        'с' to "1101",
        'р' to "00101",
        'в' to "00111",
        'л' to "01010",
        'к' to "10001",
        'м' to "10111",
        'д' to "11000",
        'п' to "001000",
        'у' to "001001",
        'я' to "001101",
        'ы' to "010110",
        'з' to "010111",
        'ь' to "100001",
        'б' to "1011000",
        'г' to "101101",
        'ч' to "110011",
        'й' to "0011001",
        'х' to "1000000",
        'ж' to "1000001",
        'ю' to "1100101",
        'ш' to "00110000",
        'ц' to "11001000",
        'щ' to "11001001",
        'э' to "001100010",
        'ф' to "001100011"
    )
}