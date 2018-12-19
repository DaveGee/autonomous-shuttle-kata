class Router {
    private var origin = Position(0, 0)

    fun from(origin: Position): Router {
        this.origin = origin
        return this
    }

    fun navigate(bearing: String): Position {
        val (X, Y) = origin
        return when(bearing) {
            "N" -> Position(X, Y - 1)
            "S" -> Position(X, Y + 1)
            "E" -> Position(X + 1, Y)
            "W" -> Position(X - 1, Y)
            else -> throw Error("Invalid bearing $bearing")
        }
    }
}
