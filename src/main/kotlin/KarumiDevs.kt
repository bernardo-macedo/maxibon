class KarumiDevs(private val messagingApp: MessagingApp? = null) {

    val kitchen = Kitchen()

    val team: Map<String, Dev> = mapOf(
            "PEDRO" to Dev(numberOfMaxibonsNeeded = 3),
            "FRAN" to Dev(numberOfMaxibonsNeeded = 1),
            "DAVIDE" to Dev(numberOfMaxibonsNeeded = 0),
            "SERGIO" to Dev(numberOfMaxibonsNeeded = 2),
            "JORGE" to Dev(numberOfMaxibonsNeeded = 1)
    )

    fun goGrabMaxibons(devName: String) {
        val dev = team[devName]
        dev?.let {
            kitchen.pickMaxibons(dev.numberOfMaxibonsNeeded)
            if (kitchen.maxibons <= 2) {
                sendMessage(devName)
                kitchen.maxibons += 10
            }
        }
    }

    fun numberOfMaxibonsAvailable() = kitchen.maxibons

    private fun sendMessage(devName: String) {
        val message = "Hi guys, I'm $devName. We need more maxibons!"
        messagingApp?.sendMessage(devName, message)
    }

}
