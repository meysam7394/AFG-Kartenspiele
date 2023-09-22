class Card(val suit: String, val value: String) {
    override fun toString(): String {
        return "$value of $suit"
    }
}
