package by.samples.mathtranslate.translator.additional

import by.samples.mathtranslate.R
import by.samples.mathtranslate.translator.Translator
import by.samples.mathtranslate.translator.basic.RussianHuffmanTranslator
import by.samples.mathtranslate.translator.basic.RussianShenonTranslator

object ShenonHuffmanTranslator : Translator {

    override val titleId: Int = R.string.shenon_huffman

    override fun translate(text: String): String =
        RussianHuffmanTranslator.translate(RussianShenonTranslator.translate(text))
}