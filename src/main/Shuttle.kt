import Navigator.BACKWARD
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

    private val rightOf = mapOf(
        EAST to SOUTH,
        SOUTH to WEST,
        WEST to NORTH,
        NORTH to EAST
    )

    private val leftOf = mapOf(
        EAST to NORTH,
        SOUTH to EAST,
        NORTH to WEST,
        WEST to SOUTH
    )

    private val oppositeOf = mapOf(
        EAST to WEST,
        WEST to EAST,
        SOUTH to NORTH,
        NORTH to SOUTH
    )

    fun execute(mission: String) = when(mission) {
        RIGHT -> bearing = rightOf[bearing]!!
        LEFT -> bearing = leftOf[bearing]!!
        FORWARD -> position = router.from(position).navigate(bearing)
        BACKWARD -> position = router.from(position).navigate(oppositeOf[bearing]!!)
        else -> throw Error("Invalid mission $mission")
    }
}
