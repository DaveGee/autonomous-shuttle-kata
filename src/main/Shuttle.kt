import Navigator.BACKWARD
import Navigator.FORWARD
import Navigator.LEFT
import Navigator.NORTH
import Navigator.RIGHT

class Shuttle(
    private val router: Router,
    private val map: VehicleMap = VehicleMap,
    private val obstacleSensor: Sensor = ObstacleSensor()
) {
    var position: Position = Position(0, 0)
        private set
    var bearing: String = NORTH
        private set

    private fun ahead() = router.from(position).navigate(bearing)
    private fun behind() = router.from(position).navigate(map.oppositeOf(bearing))

    fun execute(mission: String) = mission.forEach {step ->
        when (step.toString()) {
            RIGHT -> bearing = map.rightOf(bearing)
            LEFT -> bearing = map.leftOf(bearing)
            FORWARD -> position = ahead()
            BACKWARD -> position = behind()
        }
    }

    fun hasObstacleAhead() = obstacleSensor.sense(ahead())
    fun hasObstacleBehind() = obstacleSensor.sense(behind())
}
