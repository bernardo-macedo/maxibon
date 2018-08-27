import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

const val INITIAL_CAPACITY = 10

class KitchenTest : StringSpec({

    "the kitchen must have 10 maxibons by default" {
        val kitchen = Kitchen()
        kitchen.maxibons shouldBe INITIAL_CAPACITY
    }

    "the kitchen should init with a given number of maxibons" {
        val initValue = 4
        val kitchen = Kitchen(initValue)
        kitchen.maxibons shouldBe initValue
    }

    "the kitchen should not provide more maxibons than it has" {
        val kitchen = Kitchen()
        val numberOfMaxibonsRequested = 11
        val returnedMaxibons = kitchen.pickMaxibons(numberOfMaxibonsRequested)
        returnedMaxibons shouldBe INITIAL_CAPACITY
    }

    "the kitchen should provide zero maxibons if requested number is negative" {
        val kitchen = Kitchen()
        val numberOfMaxibonsRequested = -1
        val returnedMaxibons = kitchen.pickMaxibons(numberOfMaxibonsRequested)
        returnedMaxibons shouldBe 0
    }

    "the number of maxibons should decrease by the number of maxibons already provided" {
        forAll(Gen.int()) { numberOfMaxibonsRequested: Int ->
            val kitchen = Kitchen()
            val providedMaxibons = kitchen.pickMaxibons(numberOfMaxibonsRequested)
            val numberOfMaxibonsAvailable = kitchen.maxibons
            numberOfMaxibonsAvailable == (INITIAL_CAPACITY - providedMaxibons)
        }
    }

})