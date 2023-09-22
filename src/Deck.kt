class Deck {
    private val suits = listOf("Hearts", "Diamonds", "Clubs", "Spades")
    private val values = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace")
    private val cards = mutableListOf<Card>()

    init {
        for (suit in suits) {
            for (value in values) {
                cards.add(Card(suit, value))
            }
        }
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun draw(): Card? {
        return if (cards.isNotEmpty()) {
            cards.removeAt(0)
        } else {
            null
        }
    }
}