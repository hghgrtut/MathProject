package by.samples.mathtranslate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var translatorIndex = 0
        val translators = arrayOf(RussianMorzeTranslator, EnglishMorzeTranslator,
            RussianHuffmanTranslator, RussianShenonTranslator, HuffmanShenonTranslator,
            ShenonHuffmanTranslator, EnglishHuffmanTranslator, EnglishShenonTranslator)
        setTitle(translators[translatorIndex])
        binding.swapText.setOnClickListener {
            val tempString = binding.input.editText!!.text.toString()
            binding.input.editText!!.setText(binding.translated.text.toString())
            binding.translated.text = tempString
        }
        binding.coding.setOnClickListener {
            translatorIndex++
            if (translatorIndex == translators.size) translatorIndex = 0
            setTitle(translators[translatorIndex])
            showTranslatedText(translators[translatorIndex])
        }
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                showTranslatedText(translators[translatorIndex])
            }
        }
        binding.input.editText?.addTextChangedListener(textWatcher)
        setContentView(binding.root)
    }

    private fun setTitle(translator: Translator) = setTitle(when (translator) {
        is EnglishMorzeTranslator -> R.string.english_morze
        is EnglishHuffmanTranslator -> R.string.english_huffman
        is EnglishShenonTranslator -> R.string.english_shenon
        is RussianHuffmanTranslator -> R.string.russian_huffman
        is RussianShenonTranslator -> R.string.russian_shenon
        is RussianMorzeTranslator -> R.string.russian_morze
        is HuffmanShenonTranslator -> R.string.huffman_shenon
        else -> R.string.shenon_huffman
    })

    private fun showTranslatedText(translator: Translator) {
        binding.translated.text = translator.translate(binding.input.editText!!.text.toString())
    }
}