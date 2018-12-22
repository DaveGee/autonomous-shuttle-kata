import Navigator.BACKWARD
import Navigator.FORWARD
import Navigator.LEFT
import Navigator.NORTH
import Navigator.RIGHT

class Shuttle(
    private val router: Router,
    private val obstacleSensor: Sensor,
    private val map: VehicleMap = VehicleMap
) {
    var position: Position = Position(0, 0)
        private set
    var bearing: String = NORTH
        private set

    private fun ahead() = router.from(position).navigate(bearing)
    private fun behind() = router.from(position).navigate(map.oppositeOf(bearing))

    fun execute(mission: String) {
        for (step in mission) {
            val instruction = step.toString()

            if (instruction == FORWARD && hasObstacleAhead()
                || instruction == BACKWARD && hasObstacleBehind())
                break

            when (instruction) {
                RIGHT -> bearing = map.rightOf(bearing)
                LEFT -> bearing = map.leftOf(bearing)
                FORWARD -> position = ahead()
                BACKWARD -> position = behind()
            }
        }
    }

    fun hasObstacleAhead() = obstacleSensor.sense(ahead())
    fun hasObstacleBehind() = obstacleSensor.sense(behind())
}
