fun main() {
    println("Welcome! Each player starts with 1000 euros in their accounts.")
    val deck = Deck()
    deck.shuffle()

    val player1 = Player("Player 1")
    val player2 = Player("Player 2")

    // Draw 3 cards for each player
    repeat(3) {
        player1.drawCard(deck)
        player2.drawCard(deck)
    }

    var player1Turn = true

    val currentPlayer = if (player1Turn) player1 else player2
    val otherPlayer = if (player1Turn) player2 else player1

    println("${currentPlayer.name}'s Hand: [Hidden, Hidden, Hidden]\n Choose one of the following options: fling, read, exit")

    val currentPlayerChoice = readLine()?.toLowerCase()

    when (currentPlayerChoice) {
        "fling" -> {
            handleFling(currentPlayer, otherPlayer, deck)
        }
        "read" -> {
            handleRead(currentPlayer, otherPlayer, player1Turn)
        }
        "exit" -> {
            println("${currentPlayer.name} has chosen to exit. ${otherPlayer.name} is the winner!")
        }
        else -> {
            println("Invalid choice for ${currentPlayer.name}. Please choose a valid option.")
        }
    }
}

fun handleFling(currentPlayer: Player, otherPlayer: Player, deck: Deck) {
    println("${currentPlayer.name} has chosen to fling.")
    println("How much euro do you want to fling?")
    val flingAmount = readLine()?.toIntOrNull()

    if (flingAmount != null) {
    if (flingAmount < 10) {
     println("${currentPlayer.name}, you must fling at least 10 euro to allow ${otherPlayer.name} to double.")
    }
    else{

    currentPlayer.money -= flingAmount
    println("${currentPlayer.name} has flung $flingAmount euro.")
    // Other player's turn to double, read, or exit
    println("${otherPlayer.name}, ${currentPlayer.name} has flung $flingAmount euro. Choose one of the following options: Double, read, exit")
    val otherPlayerChoice = readLine()?.toLowerCase()
    when (otherPlayerChoice) {
    "double" -> {
     val doubleAmount = flingAmount * 2
     if (doubleAmount <= otherPlayer.money) {
      otherPlayer.money -= doubleAmount
       println("${otherPlayer.name} has chosen to double the round. ${otherPlayer.name} pays $doubleAmount euro.")

       // Current player's turn to pay the stick
    println("${currentPlayer.name}, pay a stick to reveal cards or continue.")
     val stickAmount = readLine()?.toInt()

     if (stickAmount != null) {
     if (stickAmount <= currentPlayer.money) {
      currentPlayer.money -= stickAmount
      currentPlayer.stickAmount = stickAmount
         println("${currentPlayer.name} has paid $stickAmount euro as a stick.")

      // Reveal cards for both players
     currentPlayer.revealCards()
     otherPlayer.revealCards()

     // Calculate hand values
    val player1HandValue = calculateHandValue(currentPlayer.hand)
    val player2HandValue = calculateHandValue(otherPlayer.hand)
    // show the winner or loser
    if (player1HandValue > player2HandValue) {
     println("${currentPlayer.name} is the winner!")
    }
    else if (player2HandValue > player1HandValue) {
        println("${otherPlayer.name} is the winner!")
         }
         } else
         {
     println("${currentPlayer.name} doesn't have enough money to pay for the stick.")
     }
     }
     }
     else
     {
      println("${otherPlayer.name} doesn't have enough money to double the round.")
      }
     }
        "read"->{
            handleReadPlayer2(otherPlayer, currentPlayer)

        }
     "exit" -> {
       println("${otherPlayer.name} has chosen to exit. ${currentPlayer.name} is the winner!")
         }
       else -> {
            println("Invalid choice for ${otherPlayer.name}. Please choose a valid option.")
                }
            }
        }
    }
    else {
        println("Invalid input. Please enter a valid number for the fling amount.")
    }
}
fun handleRead(currentPlayer: Player, otherPlayer: Player, isFirstPlayer: Boolean) {
    println("${currentPlayer.name} has chosen to read.")
    println("You decided to read. Now, choose one of the following options: pay stick to continue or exit")

    val readChoice = readLine()?.toLowerCase()
    println("${currentPlayer.name}, how much do you want to pay?")
    val paystickAmount = readLine()?.toIntOrNull()

  when (readChoice) {
    "pay stick" -> {
     if (paystickAmount != null && paystickAmount >= currentPlayer.stickAmount) {
      currentPlayer.money -= paystickAmount
      println("${currentPlayer.name} paid $paystickAmount as a stick.")

       // Check if the other player can continue
      if (otherPlayer.money >= currentPlayer.stickAmount) {
      println("${otherPlayer.name}, ${currentPlayer.name} has paid $paystickAmount. Choose one of the following options: pay stick to continue or pay stick to reveal hand.")

      val otherPlayerChoice = readLine()?.toLowerCase()

      when (otherPlayerChoice) {
        "pay stick to continue" -> {
      val otherPaystickAmount = readLine()?.toIntOrNull()
        if (otherPaystickAmount != null && otherPaystickAmount >= currentPlayer.stickAmount) {
       otherPlayer.money -= otherPaystickAmount
       println("${otherPlayer.name} paid $otherPaystickAmount to continue.")

        // Now reveal both hands and show the winner
       currentPlayer.revealCards()
        otherPlayer.revealCards()
        val player1HandValue = calculateHandValue(currentPlayer.hand)
        val player2HandValue = calculateHandValue(otherPlayer.hand)

        if (player1HandValue > player2HandValue) {
        println("${currentPlayer.name} is the winner!")
         } else if (player2HandValue > player1HandValue) {
         println("${otherPlayer.name} is the winner!")
          }
        else {
       println("It's a tie!")
        }
       }
        else {
       println("Invalid input for ${otherPlayer.name}. Please enter a valid amount to continue.")
       }
        }
      "pay stick to reveal hand" -> {
      // Reveal both hands and show the winner
       currentPlayer.revealCards()
       otherPlayer.revealCards()
         val player1HandValue = calculateHandValue(currentPlayer.hand)
         val player2HandValue = calculateHandValue(otherPlayer.hand)

          if (player1HandValue > player2HandValue) {
          println("${currentPlayer.name} is the winner!")
           } else if (player2HandValue > player1HandValue) {
          println("${otherPlayer.name} is the winner!")
           }
          else {
          println("It's a tie!")
          }
          }
           else -> {
          println("Invalid choice for ${otherPlayer.name}. Please choose a valid option.")
           }
       }
         } else {
        println("${otherPlayer.name} doesn't have enough money to continue.")
        println("${otherPlayer.name} is the winner!")
         }
            }
     else {
      println("Invalid input for ${currentPlayer.name}. Please enter a valid amount to continue.")
            }
        }
        "exit" -> {
      println("${currentPlayer.name} has chosen to exit. ${otherPlayer.name} is the winner!")
        }
        else -> {
      println("Invalid choice for ${currentPlayer.name}. Please choose a valid option.")
        }
    }
}


fun handleReadPlayer2(currentPlayer: Player, otherPlayer: Player) {
    println("${currentPlayer.name}, you have chosen to read. Now, choose one of the following options: pay to continue or pay to reveal your hand")

    val readChoice = readLine()?.toLowerCase()

    when (readChoice) {
         "pay to continue" -> {
    println("How much euro do you want to pay to continue?")
    val paymentAmount = readLine()?.toIntOrNull()

    if (paymentAmount != null) {
    if (paymentAmount > 0 && paymentAmount > currentPlayer.stickAmount) {
      currentPlayer.money -= paymentAmount
       println("${currentPlayer.name} paid $paymentAmount euros to continue.")

        // Reveal cards for both players
     currentPlayer.revealCards()
     otherPlayer.revealCards()

     // Calculate hand values
     val player1HandValue = calculateHandValue(currentPlayer.hand)
     val player2HandValue = calculateHandValue(otherPlayer.hand)

     // show the winner or loser
     if (player1HandValue > player2HandValue) {
     println("${currentPlayer.name} is the winner!")
     } else if (player2HandValue > player1HandValue) {
     println("${otherPlayer.name} is the winner!")
     }
     } else
     {
       println("Invalid input. Payment amount must be greater than 0 and greater than the stick amount.")
                }
            } else {
                println("Invalid input. Please enter a valid number for the payment amount.")
            }
        }
        "pay to reveal your hand" -> {
            println("How much euro do you want to pay to reveal your hand?")
       val paymentAmount = readLine()?.toIntOrNull()

       if (paymentAmount != null) {
       if (paymentAmount > 0 && paymentAmount > currentPlayer.stickAmount) {
       currentPlayer.money -= paymentAmount
        println("${currentPlayer.name} paid $paymentAmount euros to reveal their hand.")

        // Reveal cards for both players
        currentPlayer.revealCards()
        otherPlayer.revealCards()

        // Calculate hand values
         val player1HandValue = calculateHandValue(currentPlayer.hand)
         val player2HandValue = calculateHandValue(otherPlayer.hand)

         // show the winner or loser
          if (player1HandValue > player2HandValue) {
           println("${currentPlayer.name} is the winner!")
           } else if (player2HandValue > player1HandValue) {
            println("${otherPlayer.name} is the winner!")
              }
                }
       else {
        println("Invalid input. Payment amount must be greater than 0 and greater than the stick amount.")
                }
            }
       else {
           println("Invalid input. Please enter a valid number for the payment amount.")
            }
        }
       else -> {
            println("Invalid choice for ${currentPlayer.name}. Please choose a valid option.")
        }
    }
}
