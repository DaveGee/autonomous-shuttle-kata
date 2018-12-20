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
class ValidMissionsTests(
    private val mission: String,
    private val destination: Position,
    private val bearing: String
) {
    lateinit var shuttle: Shuttle

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun missions() = listOf(
            arrayOf("FFRFF", Position(2, -2), EAST),
            arrayOf("BLFFFRRFFLB", Position(-1, 2), NORTH),
            arrayOf("RRRFFFFFFFFLFFFFF", Position(-8, 5), SOUTH),
            arrayOf("BBBBBBBBBBLFF", Position(-2, 10), WEST)
        )
    }

    @Before
    fun initialise() {
        shuttle = Shuttle(Router())
    }

    @Test
    fun `validate final position`() {
        shuttle.execute(mission)
        assertEquals(destination, shuttle.position)
        assertEquals(bearing, shuttle.bearing)
    }
}