import Navigator.BACKWARD
import Navigator.FORWARD
import Navigator.LEFT
import Navigator.NORTH
import Navigator.RIGHT

class Shuttle(
    private val router: Router,
    private val map: VehicleMap = VehicleMap
) {
    var position: Position = Position(0, 0)
        private set
    var bearing: String = NORTH
        private set

    fun execute(mission: String) = when(mission) {
        RIGHT -> bearing = map.rightOf(bearing)
        LEFT -> bearing = map.leftOf(bearing)
        FORWARD -> position = router.from(position).navigate(bearing)
        BACKWARD -> position = router.from(position).navigate(map.oppositeOf(bearing))
        else -> throw Error("Invalid mission $mission")
    }
}
