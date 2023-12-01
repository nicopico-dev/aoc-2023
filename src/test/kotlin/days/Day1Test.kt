package days

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day1Test {

    private val dayOne = Day1()

    @Test
    fun partOne() {
        dayOne.partOne() shouldBe 142
    }

    @Test
    fun partTwo() {
        dayOne.partTwo() shouldBe "TODO"
    }

}
