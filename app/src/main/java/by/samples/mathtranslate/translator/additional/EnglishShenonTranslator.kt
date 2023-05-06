package by.samples.mathtranslate.translator.additional

import by.samples.mathtranslate.R
import by.samples.mathtranslate.translator.Translator
import by.samples.mathtranslate.translator.basic.EnglishMorzeTranslator
import by.samples.mathtranslate.translator.basic.RussianMorzeTranslator
import by.samples.mathtranslate.translator.basic.RussianShenonTranslator
import by.samples.mathtranslate.translator.basic.Util.isBinary

object EnglishShenonTranslator : Translator {

    override val titleId: Int = R.string.english_shenon

    override fun translate(text: String): String {
        var index = 0
        while (index < text.length && !text[index].isBinary() && !text[index].isLetter()) index++
        return when {
            index == text.length -> text
            text[index].isBinary() -> EnglishMorzeTranslator.translate(
                RussianMorzeTranslator.translate(RussianShenonTranslator.translate(text)))
            else -> RussianShenonTranslator.translate(
                RussianMorzeTranslator.translate(EnglishMorzeTranslator.translate(text)))
        }
    }
}