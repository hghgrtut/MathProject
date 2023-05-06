package by.samples.mathtranslate.translator

import androidx.annotation.StringRes

interface Translator {

    @get:StringRes
    val titleId: Int

    fun translate(text: String): String
}