package by.samples.mathtranslate.translator.basic

import by.samples.mathtranslate.translator.Translator

object EnglishMorzeTranslator : Translator {
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
            if (englishToMorze.containsKey(it.toLowerCase())) "${englishToMorze[it.toLowerCase()]} "
            else it)
        }
        return result.toString()
    }

    private fun Char.isMorze(): Boolean = this == '.' || this == '-'

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
        'z' to "--..")

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
        "--.." to 'z')
}