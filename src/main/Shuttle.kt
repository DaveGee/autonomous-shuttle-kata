class Shuttle {
    var position: Position = Position(0, 0)
        private set
    var bearing: String = "N"
        private set

    private val right = mapOf(
        "E" to "S",
        "S" to "W",
        "W" to "N",
        "N" to "E"
    )

    private val left = mapOf(
        "E" to "N",
        "S" to "E",
        "N" to "W",
        "W" to "S"
    )

    fun execute(mission: String) = when(mission) {
        "R" -> bearing = right[bearing]!!
        "L" -> bearing = left[bearing]!!
        "F" -> goForward()
        else -> throw Error("Invalid mission $mission")
    }

    private fun goForward() {
        val (X, Y) = position
        when(bearing) {
            "N" -> position = Position(X, Y - 1)
            "S" -> position = Position(X, Y + 1)
            "E" -> position = Position(X + 1, Y)
            "W" -> position = Position(X - 1, Y)
            else -> throw Error("Invalid bearing $bearing")
        }
    }
}
