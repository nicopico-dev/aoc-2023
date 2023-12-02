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
