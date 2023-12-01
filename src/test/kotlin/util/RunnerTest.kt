package util

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemErr
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class RunnerTest {

    @Test
    fun testPrintDay() {
        val day1 = tapSystemOut { Runner.main(arrayOf("1")) }
        day1 shouldMatch
            """
            
            === DAY 1 ===
            Part 1: THIS IS      \(.*\)
            Part 2: FILE         \(.*\)
            
            """.trimIndent()
    }

    @Test
    fun testPrintErrors() {
        val dayNotAString = tapSystemErr { Runner.main(arrayOf("one")) }
        dayNotAString shouldBe "\n=== ERROR ===\nDay argument must be an integer\n"

        val dayNotExists = tapSystemErr { Runner.main(arrayOf("99")) }
        dayNotExists shouldBe "\n=== ERROR ===\nDay 99 not found\n"
    }
}
