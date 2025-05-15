package info.shibafu528.emo

// ported from https://github.com/mstdn-plusminus-io/mastodon/blob/develop/app/javascript/mastodon/utils/emotional.js
object Emotional {
    fun textToEmotional(text: String, style: Style): String {
        return text
            .replace(RE_UPPER) { style.mapping[it.value[0]]?.toString() ?: Character.toString(it.value[0].code + style.upperA - Style.BASE.upperA) }
            .replace(RE_LOWER) { style.mapping[it.value[0]]?.toString() ?: Character.toString(it.value[0].code + style.lowerA - Style.BASE.lowerA) }
    }
}

enum class Style(val upperA: Int, val lowerA: Int, val mapping: Map<Char, Char> = emptyMap()) {
    BASE(0x41, 0x61),
    BOLD_SERIF(0x1D400, 0x1D41A),
    ITALIC_SERIF(0x1D434, 0x1D44E, mapOf('h' to 'ℎ')),
    BOLD_ITALIC_SERIF(0x1D468, 0x1D482),
    BOLD_SANS_SERIF(0x1D5D4, 0x1D5EE),
    ITALIC_SANS_SERIF(0x1D608, 0x1D622),
    BOLD_ITALIC_SANS_SERIF(0x1D63C, 0x1D656),
    BOLD_SCRIPT(0x1D4D0, 0x1D4EA),
    FRAKTUR(0x1D504, 0x1D51E, mapOf('C' to 'ℭ', 'H' to 'ℌ', 'I' to 'ℑ', 'R' to 'ℜ', 'Z' to 'ℨ')),
    FRAKTUR_BOLD(0x1D56C, 0x1D586),
    MONOSPACE(0x1D670, 0x1D68A),
}

private val RE_UPPER = "[A-Z]".toRegex()
private val RE_LOWER = "[a-z]".toRegex()