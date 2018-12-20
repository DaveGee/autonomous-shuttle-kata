import Navigator.EAST
import Navigator.NORTH
import Navigator.SOUTH
import Navigator.WEST
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse

class VehicleMapShould {
    private val INVALID_DIR = "F"

    @Test
    fun `define what's on the right`() {
        assertEquals(SOUTH, VehicleMap.rightOf(EAST))
        assertEquals(NORTH, VehicleMap.rightOf(WEST))
        assertEquals(EAST, VehicleMap.rightOf(NORTH))
        assertEquals(WEST, VehicleMap.rightOf(SOUTH))
    }

    @Test
    fun `define what's on the left`() {
        assertEquals(NORTH, VehicleMap.leftOf(EAST))
        assertEquals(SOUTH, VehicleMap.leftOf(WEST))
        assertEquals(WEST, VehicleMap.leftOf(NORTH))
        assertEquals(EAST, VehicleMap.leftOf(SOUTH))
    }

    @Test
    fun `define what's on the opposite direction`() {
        assertEquals(NORTH, VehicleMap.oppositeOf(SOUTH))
        assertEquals(SOUTH, VehicleMap.oppositeOf(NORTH))
        assertEquals(WEST, VehicleMap.oppositeOf(EAST))
        assertEquals(EAST, VehicleMap.oppositeOf(WEST))
    }

    @Test
    fun `validate directions by throwing the right errors`() {
        assertFailsWith<InvalidDirectionError> { VehicleMap.rightOf(INVALID_DIR) }
        assertFailsWith<InvalidDirectionError> { VehicleMap.leftOf(INVALID_DIR) }
        assertFailsWith<InvalidDirectionError> { VehicleMap.oppositeOf(INVALID_DIR) }
    }

    @Test
    fun `know which directions are valid`() {
        assert(VehicleMap.isValidDirection(NORTH))
        assert(VehicleMap.isValidDirection(EAST))
        assert(VehicleMap.isValidDirection(WEST))
        assert(VehicleMap.isValidDirection(SOUTH))
        assertFalse(VehicleMap.isValidDirection(INVALID_DIR))
    }
}