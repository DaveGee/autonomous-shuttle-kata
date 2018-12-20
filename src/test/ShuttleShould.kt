import Navigator.BACKWARD
import Navigator.EAST
import Navigator.FORWARD
import Navigator.LEFT
import Navigator.NORTH
import Navigator.RIGHT
import Navigator.SOUTH
import Navigator.WEST
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ShuttleShould {

    lateinit var shuttle: Shuttle

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
        shuttle.execute(RIGHT)
        assertEquals(EAST, shuttle.bearing)

        shuttle.execute(RIGHT)
        assertEquals(SOUTH, shuttle.bearing)

        shuttle.execute(RIGHT)
        assertEquals(WEST, shuttle.bearing)

        shuttle.execute(RIGHT)
        assertEquals(NORTH, shuttle.bearing)
    }

    @Test
    fun `left turns makes the shuttle go through all directions`() {
        shuttle.execute(LEFT)
        assertEquals(WEST, shuttle.bearing)

        shuttle.execute(LEFT)
        assertEquals(SOUTH, shuttle.bearing)

        shuttle.execute(LEFT)
        assertEquals(EAST, shuttle.bearing)

        shuttle.execute(LEFT)
        assertEquals(NORTH, shuttle.bearing)
    }

    @Test
    fun `end up north when moving 1 step forward`() {
        val oneUpNorth = Position(0, -1)

        shuttle.execute(FORWARD)

        assertEquals(oneUpNorth, shuttle.position)
        assertEquals(NORTH, shuttle.bearing)
    }
}