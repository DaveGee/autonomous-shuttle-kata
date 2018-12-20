import Navigator.EAST
import Navigator.NORTH
import Navigator.SOUTH
import Navigator.WEST
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class RouterShould (
    private val origin: Position,
    private val bearing: String,
    private val destination: Position
) {

    lateinit var router: Router

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun routes() = listOf(
            arrayOf(Position(0, 0), NORTH, Position(0, -1)),
            arrayOf(Position(0, 0), SOUTH, Position(0, 1)),
            arrayOf(Position(0, 0), EAST, Position(1, 0)),
            arrayOf(Position(0, 0), WEST, Position(-1, 0)),
            arrayOf(Position(4, 27), WEST, Position(3, 27)),
            arrayOf(Position(6, 7), NORTH, Position(6, 6))
        )
    }

    @Before
    fun initialise() {
        router = Router()
    }

    @Test
    fun `find the next waypoint when moving forward`() {
        val nextWaypoint = router.from(origin).navigate(bearing)
        assertEquals(destination, nextWaypoint)
    }
}