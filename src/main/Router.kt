import Navigator.EAST
import Navigator.NORTH
import Navigator.SOUTH
import Navigator.WEST

class Router {
    private var origin = Position(0, 0)

    fun from(origin: Position): Router {
        this.origin = origin
        return this
    }

    fun navigate(bearing: String): Position {
        val (X, Y) = origin
        return when(bearing) {
            NORTH -> Position(X, Y - 1)
            SOUTH -> Position(X, Y + 1)
            EAST -> Position(X + 1, Y)
            WEST -> Position(X - 1, Y)
            else -> throw Error("Invalid bearing $bearing")
        }
    }
}
