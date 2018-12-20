import Navigator.EAST
import Navigator.FORWARD
import Navigator.LEFT
import Navigator.NORTH
import Navigator.RIGHT
import Navigator.SOUTH
import Navigator.WEST

class Shuttle(
    private val router: Router
) {
    var position: Position = Position(0, 0)
        private set
    var bearing: String = NORTH
        private set

    private val right = mapOf(
        EAST to SOUTH,
        SOUTH to WEST,
        WEST to NORTH,
        NORTH to EAST
    )

    private val left = mapOf(
        EAST to NORTH,
        SOUTH to EAST,
        NORTH to WEST,
        WEST to SOUTH
    )

    fun execute(mission: String) = when(mission) {
        RIGHT -> bearing = right[bearing]!!
        LEFT -> bearing = left[bearing]!!
        FORWARD -> position = router.from(position).navigate(bearing)
        else -> throw Error("Invalid mission $mission")
    }
}
