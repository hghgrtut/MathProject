package by.samples.mathtranslate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.samples.mathtranslate.translator.Translator
import by.samples.mathtranslate.translator.additional.EnglishHuffmanTranslator
import by.samples.mathtranslate.translator.additional.EnglishShenonTranslator
import by.samples.mathtranslate.translator.additional.HuffmanShenonTranslator
import by.samples.mathtranslate.translator.additional.ShenonHuffmanTranslator
import by.samples.mathtranslate.translator.basic.EnglishMorzeTranslator
import by.samples.mathtranslate.translator.basic.RussianHuffmanTranslator
import by.samples.mathtranslate.translator.basic.RussianMorzeTranslator
import by.samples.mathtranslate.translator.basic.RussianShenonTranslator
import by.samples.mathtranslate.ui.theme.MathTranslateTheme
import kotlin.random.Random

class ComposeMainActivity : ComponentActivity() {

    private var translator = randomTranslator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MathTranslateTheme { MyScreen() } }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MyScreen() {
        var title by remember { mutableStateOf(getString(translator.titleId)) }
        var textToTranslate by remember { mutableStateOf("") }
        val translatedText = translator.translate(textToTranslate)
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                modifier = defaultModifier
            )
            Spacer(modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = textToTranslate,
                onValueChange = { textToTranslate = it },
                label = { Text(text = stringResource(id = R.string.hint_default_text)) },
                maxLines = 8,
                modifier = defaultModifier
            )
            Row(modifier = defaultModifier) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_swap_text),
                    contentDescription = stringResource(id = R.string.swap_text),
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { textToTranslate = translatedText }
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_change_translator),
                    contentDescription = stringResource(id = R.string.change_translator),
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            translator = randomTranslator()
                            title = getString(translator.titleId)
                        }
                )
            }
            Text(
                text = translatedText,
                modifier = defaultModifier.padding(vertical = 0.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }

    private fun randomTranslator() = arrayOf(
        RussianMorzeTranslator, EnglishMorzeTranslator,
        RussianHuffmanTranslator, RussianShenonTranslator, HuffmanShenonTranslator,
        ShenonHuffmanTranslator, EnglishHuffmanTranslator, EnglishShenonTranslator
    ).random()
}

private val defaultModifier = Modifier
    .fillMaxWidth()
    .padding(16.dp)