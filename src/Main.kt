fun main() {
    val deck = Deck()
    deck.shuffle()

    val player1 = Player("Player 1")
    val player2 = Player("Player 2")

    // Draw 3 cards for each player
    repeat(3) {
        player1.drawCard(deck)
        player2.drawCard(deck)
    }

    // Print a message for Player 1 options
    println("${player1.name}'s Hand: [Hidden, Hidden, Hidden]")
    println("${player1.name}, choose one of the following options: fling, stay, exit")

    // code for handling player's choice
    val player1Choice = readLine()?.toLowerCase()
    when (player1Choice) {
        "fling" -> {
    println("${player1.name} has chosen to fling.")
    println("How much euro do you want to fling?")
    val flingAmount = readLine()?.toInt()
    if (flingAmount != null) {
    if (flingAmount < 10) {
     println("${player1.name}, you must fling at least 10 euro to allow Player 2 to double.")
    }
    else {
    player1.money -= flingAmount
     println("${player1.name} has flung $flingAmount euro.")
       // Player 2 turn to double, read, or exit
     println("${player2.name}, Player 1 has flung $flingAmount euro. Choose one of the following options: Double, read, exit")
      val player2Choice = readLine()?.toLowerCase()
      when (player2Choice) {
       "double" -> {
      val doubleAmount = flingAmount * 2
      if (doubleAmount <= player2.money) {
      player2.money -= doubleAmount
      println("${player2.name} has chosen to double the round. Player 2 pays $doubleAmount euro.")
      // Player 1 turn to pay the stick
      println("${player1.name}, pay a stick to reveal cards or continue.")
       val stickAmount = readLine()?.toInt()
          if (stickAmount != null) {
          if (stickAmount <= player1.money) {
      player1.money -= stickAmount
      player1.stickAmount = stickAmount
      println("${player1.name} has paid $stickAmount euro as a stick.")
    // Reveal cards for both players
     player1.revealCards()
     player2.revealCards()

   // Calculate hand values
     val player1HandValue = calculateHandValue(player1.hand)
     val player2HandValue = calculateHandValue(player2.hand)

   // Analyze and show the winner or loser
     if (player1HandValue > player2HandValue) {
              println("${player1.name} is the winner!")
     }
     else if (player2HandValue > player1HandValue) {
    println("${player2.name} is the winner!")
             } else {
       if (player1.stickAmount > 0 && player2.stickAmount == 0) {
    println("${player1.name} is the loser because they paid the stick and revealed the cards.")
    println("${player2.name} is the winner!")
    } else if (player2.stickAmount > 0 && player1.stickAmount == 0) {
    println("${player2.name} is the loser because they paid the stick and revealed the cards.")
    println("${player1.name} is the winner!")
    }
     }
     }
     else {
       println("${player1.name}, you don't have enough money to pay the stick.")
      }
      }
     else {
          println("Invalid input. Stick amount must be a valid number.")
   }
  }
    else {
   println("${player2.name}, you don't have enough money to double the round.")
      }
    }
    "read" -> {
    println("${player2.name} has chosen to read the cards.")

  }
     "exit" -> {
    println("${player2.name} has chosen to exit.")

   }
 else -> println("Invalid choice for ${player2.name}. Please choose one of the options: Double, read, exit.")
 }
}
 } else {
 println("Invalid input. Fling amount must be a valid number.")
  }
 }
  "stay" -> {
  println("${player1.name} has chosen to stay.")
      // Player 2's turn to double, read, or exit
   println("${player2.name}, choose one of the following options: Double, read, exit")
   val player2Choice = readLine()?.toLowerCase()
  when (player2Choice) {
    "double" -> {
      println("${player2.name}, you can only double if Player 1 flings.")
        }
      "read" -> {
      println("${player2.name} has chosen to read the cards.")

     }
      "exit" -> {
      println("${player2.name} has chosen to exit.")

  }
   else -> println("Invalid choice for ${player2.name}. Please choose one of the options: Double, read, exit.")
 }
 }
 "exit" -> {
  println("${player1.name} has chosen to exit.")
   // Handle exit logic here
  }
 else -> println("Invalid choice for ${player1.name}. Please choose one of the options: fling, stay, exit.")
 }
}
