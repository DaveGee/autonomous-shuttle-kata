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
    fun `see all directions when turning right multiple times`() {
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
    fun `see all directions when turning left multiple times`() {
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
    fun `check and report if it sees an obstacle`() {
        val sensorMock = mock<Sensor> {
            on { sense(any()) } doReturn true
        }

        shuttle = Shuttle(Router(), sensorMock)

        assertEquals(true, shuttle.hasObstacleAhead())
        assertEquals(true, shuttle.hasObstacleBehind())
        verify(sensorMock).sense(AHEAD)
        verify(sensorMock).sense(BEHIND)
    }

    @Test
    fun `abort mission if it detects an obstacle ahead`() {
        val sensorMock = mock<Sensor> {
            on { sense(Position(4, 2)) } doReturn true
        }

        shuttle = Shuttle(Router(), sensorMock)
        shuttle.execute("BBRFFFFFF")

        assertEquals(Position(3, 2), shuttle.position)
        assertEquals(EAST, shuttle.bearing)
        assertEquals(true, shuttle.hasObstacleAhead())
        assertEquals(false, shuttle.hasObstacleBehind())
    }

    @Test
    fun `abort mission if it detects an obstacle behind`() {
        val sensorMock = mock<Sensor> {
            on { sense(Position(4, 2)) } doReturn true
        }

        shuttle = Shuttle(Router(), sensorMock)
        shuttle.execute("RFFFFFRFFLBBBBBB")

        assertEquals(Position(5, 2), shuttle.position)
        assertEquals(EAST, shuttle.bearing)
        assertEquals(true, shuttle.hasObstacleBehind())
        assertEquals(false, shuttle.hasObstacleAhead())
    }
}