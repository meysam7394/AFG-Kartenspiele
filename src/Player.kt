class Player(val name: String) {
    val hand = mutableListOf<Card>()
    var money = 100 //  money for each player
    var stickAmount = 0 // Amount paid as a stick to reveal cards

    fun drawCard(deck: Deck) {
        val card = deck.draw()
        card?.let {
            hand.add(it)
        }
    }

    fun revealCards() {
        println("$name's Hand: [${hand.joinToString(", ")}]")
    }
}
//methods
    fun calculateHandValue(hand: List<Card>): Int {
    val cardValues = mapOf(
        "Ace" to 15, "2" to 14, "King" to 13, "Queen" to 12, "Jack" to 11, "10" to 10,  "9" to 9, "8" to 7, "7" to 6, "6" to 5, "5" to 4,
        "4" to 3, "3" to 2, "2" to 1,
    )

    // Sort the hand based on custom ranking
    val sortedHand = hand.sortedBy { cardValues[it.value] }

    // Check for three Aces
    if (sortedHand.count { it.value == "Ace" } == 3) {
        return 1000 // Three Aces is the highest hand
    }

    // Check for combinations of Jack cards
    val jackCards = listOf("2", "10", "Ace", "King", "Queen", "Jack")
    if (sortedHand.all { it.value in jackCards }) {
        var handValue = 0
        for (card in sortedHand) {
            val cardValue = cardValues[card.value] ?: 0
            handValue += cardValue
            if (handValue > 15) {
                handValue -= 15
            }
        }
        return handValue
    }

    // Calculate the hand value based on numeric values
    val numericValue = sortedHand.sumBy { cardValues[it.value] ?: 0 }

    return numericValue
}
