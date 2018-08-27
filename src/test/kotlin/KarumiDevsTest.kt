import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class KarumiDevsTest : StringSpec({

    "dev team should have five members" {
        val devs = KarumiDevs()
        devs.team.size shouldBe 5
    }

    "each dev should request a given number of maxibons" {
        forall(
                row("PEDRO", 3),
                row("FRAN", 1),
                row("DAVIDE", 0),
                row("SERGIO", 2),
                row("JORGE", 1)

        ) { developer, numberOfMaxibonsNeeded ->
            val devs = KarumiDevs()
            devs.team[developer]?.numberOfMaxibonsNeeded shouldBe numberOfMaxibonsNeeded
        }
    }

    "when the number of maxibons reaches 2 or less, the dev should restock" {
        val devs = KarumiDevs()
        val initialNumberOfMaxibons = 2
        val expectedNumberOfMaxibons = initialNumberOfMaxibons - devs.team["PEDRO"]?.numberOfMaxibonsNeeded!! + 10
        devs.kitchen.maxibons = initialNumberOfMaxibons
        devs.goGrabMaxibons("PEDRO")
        devs.numberOfMaxibonsAvailable() shouldBe expectedNumberOfMaxibons
    }

    "when the number of maxibons reached 2 or less, the dev should warn the team" {
        var hasPedroSentAnyMessage = false
        val messagingApp: MessagingApp = object : MessagingApp {
            override fun sendMessage(from: String, message: String) {
                if (from.toUpperCase() == "PEDRO") {
                    hasPedroSentAnyMessage = true
                }
            }
        }

        val devs = KarumiDevs(messagingApp)
        val initialNumberOfMaxibons = 2
        devs.kitchen.maxibons = initialNumberOfMaxibons
        devs.goGrabMaxibons("PEDRO")
        hasPedroSentAnyMessage shouldBe true
    }

})