class Kitchen(initValue: Int = 10) {

    var maxibons: Int = initValue

    fun pickMaxibons(numberOfMaxibonsRequested: Int): Int {
        val numberOfMaxibonsProvided = when {
            numberOfMaxibonsRequested > 10 -> 10
            numberOfMaxibonsRequested < 0 -> 0
            else -> numberOfMaxibonsRequested
        }
        maxibons -= numberOfMaxibonsProvided
        return numberOfMaxibonsProvided
    }

}
