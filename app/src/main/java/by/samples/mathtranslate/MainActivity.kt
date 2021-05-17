package by.samples.mathtranslate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.samples.mathtranslate.databinding.ActivityMainBinding
import by.samples.mathtranslate.translator.Translator
import by.samples.mathtranslate.translator.additional.EnglishHuffmanTranslator
import by.samples.mathtranslate.translator.additional.EnglishShenonTranslator
import by.samples.mathtranslate.translator.additional.HuffmanShenonTranslator
import by.samples.mathtranslate.translator.additional.ShenonHuffmanTranslator
import by.samples.mathtranslate.translator.basic.EnglishMorzeTranslator
import by.samples.mathtranslate.translator.basic.RussianHuffmanTranslator
import by.samples.mathtranslate.translator.basic.RussianMorzeTranslator
import by.samples.mathtranslate.translator.basic.RussianShenonTranslator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var translatorIndex = 0
        val translators = arrayOf(RussianMorzeTranslator, EnglishMorzeTranslator,
            RussianHuffmanTranslator, RussianShenonTranslator, HuffmanShenonTranslator,
            ShenonHuffmanTranslator, EnglishHuffmanTranslator, EnglishShenonTranslator)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setTitle(translators[translatorIndex])
        binding.buttonTranslate.setOnClickListener {
            binding.translated.text =
                translators[translatorIndex].translate(binding.input.editText!!.text.toString())
        }
        binding.swapText.setOnClickListener {
            val tempString = binding.input.editText!!.text.toString()
            binding.input.editText!!.setText(binding.translated.text.toString())
            binding.translated.text = tempString
        }
        binding.buttonCoding.setOnClickListener {
            translatorIndex++
            if (translatorIndex == translators.size) translatorIndex = 0
            setTitle(translators[translatorIndex])
        }
        setContentView(binding.root)
    }

    fun setTitle(translator: Translator) = setTitle(when (translator) {
        is EnglishMorzeTranslator -> R.string.english_morze
        is EnglishHuffmanTranslator -> R.string.english_huffman
        is EnglishShenonTranslator -> R.string.english_shenon
        is RussianHuffmanTranslator -> R.string.russian_huffman
        is RussianShenonTranslator -> R.string.russian_shenon
        is RussianMorzeTranslator -> R.string.russian_morze
        is HuffmanShenonTranslator -> R.string.huffman_shenon
        else -> R.string.shenon_huffman
    })
}