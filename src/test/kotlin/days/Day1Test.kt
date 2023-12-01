package days

import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.types.instanceOf
import org.junit.jupiter.api.Test

class Day1Test {

    private val dayOne = Day1()

    @Test
    fun testPartOne() {
        dayOne.partOne() shouldBe "THIS IS"
    }

    @Test
    fun testPartTwo() {
        dayOne.partTwo() should {
            it shouldNot beNull()
            it shouldBe instanceOf<String>()
            it shouldBe "FILE"
        }
    }
}
