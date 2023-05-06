package by.samples.mathtranslate.translator.additional

import by.samples.mathtranslate.R
import by.samples.mathtranslate.translator.Translator
import by.samples.mathtranslate.translator.basic.EnglishMorzeTranslator
import by.samples.mathtranslate.translator.basic.RussianHuffmanTranslator
import by.samples.mathtranslate.translator.basic.RussianMorzeTranslator
import by.samples.mathtranslate.translator.basic.Util.isBinary

object EnglishHuffmanTranslator : Translator {

    override val titleId: Int = R.string.english_huffman

    override fun translate(text: String): String {
        var index = 0
        while (index < text.length && !text[index].isBinary() && !text[index].isLetter()) index++
        return when {
            index == text.length -> text
            text[index].isBinary() -> EnglishMorzeTranslator.translate(
                RussianMorzeTranslator.translate(RussianHuffmanTranslator.translate(text)))
            else -> RussianHuffmanTranslator.translate(
                RussianMorzeTranslator.translate(EnglishMorzeTranslator.translate(text)))
        }
    }
}