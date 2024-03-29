package by.samples.mathtranslate.translator.basic

import by.samples.mathtranslate.R
import by.samples.mathtranslate.translator.basic.Util.isMorze
import by.samples.mathtranslate.translator.Translator

object EnglishMorzeTranslator : Translator {

    override val titleId: Int = R.string.english_morze
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
                result.append(morzeToEnglish[temp.toString()] ?: '?')
            } else { result.append(text[i]) }
            i++
        }
        return result.toString()
    }

    private fun toMorze(text: String): String {
        val result = StringBuilder()
        text.forEach { result.append(
            if (englishToMorze.containsKey(it.lowercaseChar()))
                "${englishToMorze[it.lowercaseChar()]} "
            else it)
        }
        return result.toString()
    }

    private val englishToMorze = hashMapOf(
        'a' to ".-",
        'b' to "-...",
        'c' to "-.",
        'd' to "-..",
        'e' to ".",
        'f' to "..-.",
        'g' to "--.",
        'h' to "....",
        'i' to "..",
        'j' to ".---",
        'k' to "-.-",
        'l' to ".-..",
        'm' to "--",
        'n' to "-.",
        'o' to "---",
        'p' to ".--.",
        'q' to "--.-",
        'r' to ".-.",
        's' to "...",
        't' to "-",
        'u' to "..-",
        'v' to "...-",
        'w' to ".--",
        'x' to "-..-",
        'y' to "-.--",
        'z' to "--..",
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
        '+' to ".-.-."
    )

    private val morzeToEnglish = hashMapOf(
        ".-" to 'a',
        "-..." to 'b',
        "-." to 'c',
        "-.." to 'd',
        "." to 'e',
        "..-." to 'f',
        "--." to 'g',
        "...." to 'h',
        ".." to 'i',
        ".---" to 'j',
        "-.-" to 'k',
        ".-.." to 'l',
        "--" to 'm',
        "-." to 'n',
        "---" to 'o',
        ".--." to 'p',
        "--.-" to 'q',
        ".-." to 'r',
        "..." to 's',
        "-" to 't',
        "..-" to 'u',
        "...-" to 'v',
        ".--" to 'w',
        "-..-" to 'x',
        "-.--" to 'y',
        "--.." to 'z',
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
        ".-.-." to '+'
    )
}