interface Sensor {
    fun sense(position: Position): Boolean
}

class ObstacleSensor : Sensor {
    override fun sense(position: Position): Boolean = false
}
