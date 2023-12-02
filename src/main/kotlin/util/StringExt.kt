package util

fun String.replaceLast(oldValue: String, newValue: String, ignoreCase: Boolean = false): String {
    return this
        .reversed()
        .replaceFirst(
            oldValue.reversed(),
            newValue.reversed(),
            ignoreCase
        )
        .reversed()
}

fun String.allIndexesOf(word: String): List<Int> = buildList {
    var index = indexOf(word, startIndex = 0)
    while (index >= 0) {
        add(index)
        index = indexOf(word, startIndex = index + 1)
    }
}
