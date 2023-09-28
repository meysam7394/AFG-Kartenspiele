class Player(val name: String) {
    val hand = mutableListOf<Card>()
    var money = 1000 // set money for each player
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
fun calculateHandValue(hand: List<Card>): Int {
    var handValue = 0
    var numberOfAces = 0

    for (card in hand) {
        val cardValue = when (card.value) {
            "Ace" -> {
                numberOfAces++
                12
            }
            "2" -> 11 // "2" is higher than "King," "Queen," "Jack," and "10"
            "3", "4", "5", "6", "7", "8", "9" -> card.value.toInt()
            else -> 10  // For King, Queen, Jack, and 10
        }
        handValue += cardValue
    }

    // Check if we need to subtract 10 for Aces
    while (handValue > 21 && numberOfAces > 0) {
        handValue -= 10
        numberOfAces--
    }

    return handValue
}

