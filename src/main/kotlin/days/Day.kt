package days

import util.InputReader

abstract class Day(inputFileName: String) {

    constructor(
        day: Int,
        inputFileNameOverride: String? = null,
    ) : this(inputFileNameOverride ?: "input_day_$day.txt")

    private val inputReader = InputReader(inputFileName)

    // lazy delegate ensures the property gets computed only on first access
    protected val inputList: List<String> by lazy { inputReader.getInputAsList() }
    protected val inputString: String by lazy { inputReader.getInputAsString() }

    abstract fun partOne(): Any

    open fun partTwo(): Any = "TODO"
}
