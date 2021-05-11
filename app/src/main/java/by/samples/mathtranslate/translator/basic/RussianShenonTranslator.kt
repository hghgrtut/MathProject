package by.samples.mathtranslate.translator.basic

import by.samples.mathtranslate.translator.Translator
import by.samples.mathtranslate.translator.basic.Util.isBinary

object RussianShenonTranslator : Translator {
    override fun translate(text: String): String {
        var index = 0
        while (index < text.length && !text[index].isLetter() && !text[index].isBinary()) index++
        return when {
            index == text.length -> text
            text[index].isLetter() -> toShenon(text)
            else -> fromShenon(text)
        }
    }

    private fun fromShenon(text: String): String {
        val result = StringBuilder()
        var i = 0
        while (i < text.length) {
            if (text[i].isBinary()) {
                val temp = StringBuilder()
                while (temp.length < 9 && i < text.length && text[i].isBinary()
                    && !shenonToRussian.containsKey(temp.toString())) {
                    temp.append(text[i])
                    i++
                }
                result.append(shenonToRussian[temp.toString()] ?: '?')
            } else {
                result.append(text[i])
                i++
            }
        }
        return result.toString()
    }

    private fun toShenon(text: String): String {
        val result = StringBuilder()
        text.replace("0", "ноль")
            .replace("1", "один")
            .toLowerCase()
            .forEach { result.append(shenonToHuffman[it] ?: it) }
        return result.toString()
    }

    private val shenonToRussian = hashMapOf(
        "000" to ' ',
        "001" to 'о',
        "0100" to 'е',
        "0101" to 'а',
        "0110" to 'и',
        "0111" to 'т',
        "1000" to 'н',
        "1010" to 'с',
        "10011" to 'р',
        "10010" to 'в',
        "10110" to 'л',
        "10111" to 'к',
        "11000" to 'м',
        "11010" to 'д',
        "110010" to 'п',
        "110011" to 'у',
        "110110" to 'я',
        "110111" to 'ы',
        "111000" to 'з',
        "111001" to 'ь',
        "111010" to 'б',
        "111011" to 'г',
        "111100" to 'ч',
        "1111010" to 'й',
        "1111011" to 'х',
        "1111100" to 'ж',
        "1111101" to 'ю',
        "11111100" to 'ш',
        "11111101" to 'ц',
        "11111110" to 'щ',
        "111111110" to 'э',
        "111111111" to 'ф'
    )

    private val shenonToHuffman = hashMapOf(
        ' ' to "000",
        'о' to "001",
        'е' to "0100",
        'а' to "0101",
        'и' to "0110",
        'т' to "0111",
        'н' to "1000",
        'с' to "1010",
        'р' to "10011",
        'в' to "10010",
        'л' to "10110",
        'к' to "10111",
        'м' to "11000",
        'д' to "11010",
        'п' to "110010",
        'у' to "110011",
        'я' to "110110",
        'ы' to "110111",
        'з' to "111000",
        'ь' to "111001",
        'б' to "111010",
        'г' to "111011",
        'ч' to "111100",
        'й' to "1111010",
        'х' to "1111011",
        'ж' to "1111100",
        'ю' to "1111101",
        'ш' to "11111100",
        'ц' to "11111101",
        'щ' to "11111110",
        'э' to "111111110",
        'ф' to "111111111"
    )
}