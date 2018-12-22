import Navigator.BACKWARD
import Navigator.EAST
import Navigator.FORWARD
import Navigator.LEFT
import Navigator.NORTH
import Navigator.RIGHT
import Navigator.SOUTH
import Navigator.WEST
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import kotlin.test.assertEquals

class ShuttleShould {

    lateinit var shuttle: Shuttle

    val AHEAD = Position(0, -1)
    val BEHIND= Position(0, 1)

    @Before
    fun initialise() {
        shuttle = Shuttle(
            Router(),
            mock()
        )
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
        shuttle.execute(FORWARD)

        assertEquals(AHEAD, shuttle.position)
        assertEquals(NORTH, shuttle.bearing)
    }


    @Test
    fun `end up south when moving 1 step backward`() {
        shuttle.execute(BACKWARD)

        assertEquals(BEHIND, shuttle.position)
        assertEquals(NORTH, shuttle.bearing)
    }

    @Test
    fun `report if there's an obstacle ahead`() {
        val sensorMock = mock<Sensor> {
            on { sense(any()) } doReturn true
        }

        shuttle = Shuttle(Router(), obstacleSensor = sensorMock)

        assertEquals(true, shuttle.hasObstacleAhead())
        verify(sensorMock).sense(AHEAD)
    }

    @Test
    fun `report if there's an obstacle behind`() {
        val sensorMock = mock<Sensor> {
            on { sense(any()) } doReturn true
        }

        shuttle = Shuttle(Router(), obstacleSensor = sensorMock)

        assertEquals(true, shuttle.hasObstacleBehind())
        verify(sensorMock).sense(BEHIND)
    }
}