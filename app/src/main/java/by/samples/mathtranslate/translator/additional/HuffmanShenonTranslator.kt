package by.samples.mathtranslate.translator.additional

import by.samples.mathtranslate.translator.Translator
import by.samples.mathtranslate.translator.basic.RussianHuffmanTranslator
import by.samples.mathtranslate.translator.basic.RussianShenonTranslator

object HuffmanShenonTranslator : Translator {
    override fun translate(text: String): String =
        RussianShenonTranslator.translate(RussianHuffmanTranslator.translate(text))
}