import org.junit.Test
import kotlin.test.assertEquals

class ShuttleShould {
    @Test
    fun `report an initial position and bearing`() {
        val shuttle = Shuttle()
        assertEquals(0, shuttle.position.X)
        assertEquals(0, shuttle.position.Y)
        assertEquals("N", shuttle.bearing)
    }

    @Test
    fun `right turns updates bearing`() {
        val shuttle = Shuttle()

        shuttle.execute("R")
        assertEquals("E", shuttle.bearing)
    }
}