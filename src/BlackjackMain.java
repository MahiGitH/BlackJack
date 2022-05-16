import java.util.Scanner;

public class BlackjackMain {

    public static void main(String[] args) throws Exception {

        Deck playingDeck = new Deck();
        playingDeck.CreateFullDeck();
        playingDeck.shuffleDeck();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();
        double playerMoney = 100.00;

        try (Scanner userInput = new Scanner(System.in)) {
            while (playerMoney > 0) {
                System.out.println("You have $" + playerMoney + ", How much would you like to bet?");
                double playerBet = userInput.nextDouble();
                if (playerBet > playerMoney) {
                    System.out.println("You don't have enough money, you Can't bet");
                    break;
                }
                boolean endRound = false;
                playerDeck.draw(playingDeck);
                playerDeck.draw(playingDeck);

                dealerDeck.draw(playingDeck);
                dealerDeck.draw(playingDeck);

                // System.out.println(playingDeck);

                while (true) {
                    System.out.println("your hand");
                    System.out.println(playerDeck.toString());
                    System.out.println("Your hand valued at: " + playerDeck.cardValue());
                    System.out.println("Dealer hand value: " + dealerDeck.getCard(0).toString() + " and [hidden]");
                    System.out.println("Would you like to (1)Hit or (2)Stand? ");
                    int userResponse = userInput.nextInt();

                    if (userResponse == 1) {
                        playerDeck.draw(playingDeck);
                        System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
                        if (playerDeck.cardValue() > 21) {
                            System.out.println("You lose, Currently valued at: " + playerDeck.cardValue());
                            playerMoney -= playerBet;
                            endRound = true;
                            break;
                        }
                    }
                    if (userResponse == 2) {
                        break;
                    }
                }

                System.out.println("Dealer cards: " + dealerDeck.toString());
                if (dealerDeck.cardValue() > playerDeck.cardValue() && endRound == false) {
                    System.out.println("Dealer wins!");
                    playerMoney -= playerBet;
                    endRound = true;
                }
                while (dealerDeck.cardValue() < 17 && endRound == false) {
                    dealerDeck.draw(playingDeck);
                    System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
                }
                System.out.println("Dealer's Hand is valued at: " + dealerDeck.cardValue());
                if ((dealerDeck.cardValue() > 21) && endRound == false) {
                    System.out.println("Dealer loses! You win!");
                    playerMoney += playerBet;
                    endRound = true;
                }
                if (playerDeck.cardValue() == dealerDeck.cardValue() && endRound == false) {
                    System.out.println("Push");
                    endRound = true;
                }
                if (playerDeck.cardValue() > dealerDeck.cardValue() && endRound == false) {
                    System.out.println("You win the hand!");
                    playerMoney += playerBet;
                    endRound = true;
                } else if (endRound == false) {
                    System.out.println("You lose the hand");
                    playerMoney -= playerBet;
                    endRound = true;
                }
                playerDeck.moveAllToDeck(playingDeck);
                dealerDeck.moveAllToDeck(playingDeck);
                System.out.println("End of Hand.");
            }
        }
        System.out.println("Game Over!!!");
    }

}
