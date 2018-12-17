class Shuttle(
    val position: Position = Position(0, 0)
) {
    var bearing: String = "N"
        private set

    fun execute(mission: String) {
        bearing = "E"
    }

}
