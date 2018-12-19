class Shuttle(
    private val router: Router
) {
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
        "F" -> position = router.from(position).navigate(bearing)
        else -> throw Error("Invalid mission $mission")
    }
}
