public class Card {

    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.value = value;
        this.suit = suit;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Value getValue() {
        return this.value;
    }

    public String toString() {
        return this.suit.toString() + "-" + this.value.toString();

    }

}