package by.samples.mathtranslate.translator.basic

import by.samples.mathtranslate.translator.Translator
import by.samples.mathtranslate.translator.basic.Util.isMorze

object RussianMorzeTranslator : Translator {
    override fun translate(text: String): String {
        var index = 0
        while (index < text.length && !text[index].isLetter() && !text[index].isMorze()) index++
        return when {
            index == text.length -> text
            text[index].isLetter() -> toMorze(text)
            else -> fromMorze(text)
        }
    }

    private fun fromMorze(text: String): String {
        val result = StringBuilder()
        var i = 0
        while (i < text.length) {
            if (text[i].isMorze()) {
                val temp = StringBuilder()
                while (temp.length < 4 && i < text.length && text[i].isMorze()) {
                    temp.append(text[i])
                    i++
                }
                result.append(morzeToRussian[temp.toString()] ?: '?')
            } else { result.append(text[i]) }
            i++
        }
        return result.toString()
    }

    private fun toMorze(text: String): String {
        val result = StringBuilder()
        text.forEach { result.append(
            if (russianToMorze.containsKey(it.toLowerCase())) "${russianToMorze[it.toLowerCase()]} "
            else it)
        }
        return result.toString()
    }

    private val russianToMorze = hashMapOf(
        'а' to ".-",
        'б' to "-...",
        'ц' to "-.",
        'д' to "-..",
        'е' to ".",
        'ф' to "..-.",
        'г' to "--.",
        'х' to "....",
        'и' to "..",
        'й' to ".---",
        'к' to "-.-",
        'л' to ".-..",
        'м' to "--",
        'н' to "-.",
        'о' to "---",
        'п' to ".--.",
        'щ' to "--.-",
        'р' to ".-.",
        'с' to "...",
        'т' to "-",
        'у' to "..-",
        'ж' to "...-",
        'в' to ".--",
        'ь' to "-..-",
        'ы' to "-.--",
        'з' to "--..",
        'ч' to "---.",
        'ш' to "----",
        'э' to "..-..",
        'ю' to "..--",
        'я' to ".-.-",
        '1' to ".----",
        '2' to "..---",
        '3' to "...--",
        '4' to "...._",
        '5' to ".....",
        '6' to "-....",
        '7' to "--...",
        '8' to "---..",
        '9' to "----.",
        '0' to "-----",
        ':' to "---...",
        ';' to "-.-.-.",
        '-' to "-....-",
        '_' to "..--.-",
        '?' to "..--..",
        '+' to ".-.-.")

    private val morzeToRussian = hashMapOf(
        ".-" to 'а',
        "-..." to 'б',
        "-." to 'ц',
        "-.." to 'д',
        "." to 'е',
        "..-." to 'ф',
        "--." to 'г',
        "...." to 'х',
        ".." to 'и',
        ".---" to 'й',
        "-.-" to 'к',
        ".-.." to 'л',
        "--" to 'м',
        "-." to 'н',
        "---" to 'о',
        ".--." to 'п',
        "--.-" to 'щ',
        ".-." to 'р',
        "..." to 'с',
        "-" to 'т',
        "..-" to 'у',
        "...-" to 'ж',
        ".--" to 'в',
        "-..-" to 'ь',
        "-.--" to 'ы',
        "--.." to 'з',
        "---." to 'ч',
        "----" to 'ш',
        "..-.." to 'э',
        "..--" to 'ю',
        ".-.-" to 'я',
        ".----" to '1',
        "..---" to '2',
        "...--" to '3',
        "...._" to '4',
        "....." to '5',
        "-...." to '6',
        "--..." to '7',
        "---.." to '8',
        "----." to '9',
        "-----" to '0',
        "---..." to ':',
        "-.-.-." to ';',
        "-....-" to '-',
        "..--.-" to '_',
        "..--.." to '?',
        ".-.-." to '+')
}