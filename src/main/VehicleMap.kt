import Navigator.EAST
import Navigator.NORTH
import Navigator.SOUTH
import Navigator.WEST

object VehicleMap {
    private val leftOf = mapOf(
        EAST to NORTH,
        SOUTH to EAST,
        NORTH to WEST,
        WEST to SOUTH
    )

    private val rightOf = mapOf(
        EAST to SOUTH,
        SOUTH to WEST,
        WEST to NORTH,
        NORTH to EAST
    )

    private val oppositeOf = mapOf(
        EAST to WEST,
        WEST to EAST,
        SOUTH to NORTH,
        NORTH to SOUTH
    )

    private val directions = listOf(
        EAST, WEST, NORTH, SOUTH
    )

    fun isValidDirection(direction: String) = directions.contains(direction)

    private fun validate(direction: String) {
        if (!isValidDirection(direction))
            throw InvalidDirectionError()
    }

    fun rightOf(direction: String): String {
        validate(direction)
        return rightOf[direction]!!
    }

    fun leftOf(direction: String): String {
        validate(direction)
        return leftOf[direction]!!
    }

    fun oppositeOf(direction: String): String {
        validate(direction)
        return oppositeOf[direction]!!
    }
}
