import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ShuttleShould {

    lateinit var shuttle: Shuttle

    val RIGHT_TURN = "R"
    val LEFT_TURN= "L"
    val NORTH = "N"
    val EAST = "E"
    val WEST = "W"
    val SOUTH= "S"
    val FORWARD = "F"

    @Before
    fun initialise() {
        shuttle = Shuttle(Router())
    }

    @Test
    fun `report an initial position and bearing`() {
        assertEquals(0, shuttle.position.X)
        assertEquals(0, shuttle.position.Y)
        assertEquals(NORTH, shuttle.bearing)
    }

    @Test
    fun `right turns makes the shuttle go through all directions`() {
        shuttle.execute(RIGHT_TURN)
        assertEquals(EAST, shuttle.bearing)

        shuttle.execute(RIGHT_TURN)
        assertEquals(SOUTH, shuttle.bearing)

        shuttle.execute(RIGHT_TURN)
        assertEquals(WEST, shuttle.bearing)

        shuttle.execute(RIGHT_TURN)
        assertEquals(NORTH, shuttle.bearing)
    }

    @Test
    fun `left turns makes the shuttle go through all directions`() {
        shuttle.execute(LEFT_TURN)
        assertEquals(WEST, shuttle.bearing)

        shuttle.execute(LEFT_TURN)
        assertEquals(SOUTH, shuttle.bearing)

        shuttle.execute(LEFT_TURN)
        assertEquals(EAST, shuttle.bearing)

        shuttle.execute(LEFT_TURN)
        assertEquals(NORTH, shuttle.bearing)
    }

    @Test
    fun `move 1 step north`() {
        val oneUpNorth = Position(0, -1)

        shuttle.execute(FORWARD)

        assertEquals(oneUpNorth, shuttle.position)
        assertEquals(NORTH, shuttle.bearing)
    }
}