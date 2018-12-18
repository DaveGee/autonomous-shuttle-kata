class Shuttle(
    val position: Position = Position(0, 0)
) {
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
        else -> throw Error("Invalid mission $mission")
    }
}
