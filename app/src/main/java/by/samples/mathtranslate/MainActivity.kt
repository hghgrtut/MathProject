package by.samples.mathtranslate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import by.samples.mathtranslate.databinding.ActivityMainBinding
import by.samples.mathtranslate.translator.EnglishMorzeTranslator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val translator = EnglishMorzeTranslator
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.buttonTranslate.setOnClickListener {
            binding.translated.text = translator.translate(binding.input.editText!!.text.toString())
        }
        binding.swapText.setOnClickListener {
            val tempString = binding.input.editText!!.text.toString()
            binding.input.editText!!.setText(binding.translated.text.toString())
            binding.translated.text = tempString
        }
        setContentView(binding.root)
    }
}