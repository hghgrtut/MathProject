package by.samples.mathtranslate.translator.additional

import by.samples.mathtranslate.R
import by.samples.mathtranslate.translator.Translator
import by.samples.mathtranslate.translator.basic.RussianHuffmanTranslator
import by.samples.mathtranslate.translator.basic.RussianShenonTranslator

object HuffmanShenonTranslator : Translator {

    override val titleId: Int = R.string.huffman_shenon

    override fun translate(text: String): String =
        RussianShenonTranslator.translate(RussianHuffmanTranslator.translate(text))
}