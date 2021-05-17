package by.samples.mathtranslate.translator.basic

object Util {
    fun Char.isBinary() = this == '0' || this == '1'

    fun Char.isMorze(): Boolean = this == '.' || this == '-'
}