import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

  public ArrayList<Card> cards;

  public Deck() {
    this.cards = new ArrayList<>();
  }

  public void CreateFullDeck(){
   for(Suit cardSuit : Suit.values()){
     for(Value cardValue : Value.values()){
     
       this.cards.add(new Card(cardSuit,cardValue));
     }
   }

  }

  public void shuffleDeck(){
    Collections.shuffle(cards, new Random());
}
public void addCard(Card card){
    cards.add(card);
}

public void removeCard(int i){
   this.cards.remove(i);
}

public Card getCard(int i){
   return this.cards.get(i);
}

public void draw(Deck comingFrom){
   this.cards.add(comingFrom.getCard(0));
   comingFrom.removeCard(0);
}

public int deckSize(){
   return this.cards.size();
}

public void moveAllToDeck(Deck moveTo){
   int thisDeckSize = this.cards.size();
   for (int i=0; i < thisDeckSize; i++){
       moveTo.addCard(this.getCard(i));
   }
   for (int i = 0; i< thisDeckSize; i++){
       this.removeCard(0);
   }
}
public int cardValue(){
   int totalValue =0;
   int aceCount =0;
   for(Card cardInDeck : this.cards){
       switch (cardInDeck.getValue()){
           case TWO: totalValue += 2; break;
           case THREE: totalValue += 3; break;
           case FOUR:totalValue +=4; break;
           case FIVE:totalValue +=5; break;
           case SIX:totalValue +=6; break;
           case SEVEN:totalValue +=7; break;
           case EIGHT:totalValue +=8; break;
           case NINE:totalValue +=9; break;
           case TEN:totalValue +=10; break;
           case JACK:totalValue+=10; break;
           case QUEEN:totalValue +=10; break;
           case KING:totalValue +=10; break;
           case ACE:aceCount += 1; break;
       }
   }
   for (int i =0; i <aceCount; i++){
       if (totalValue >10){
           totalValue +=1;
       } else{
           totalValue +=11;
       }
   }
   return totalValue;
}

  public String toString(){
    String cardListOutput = "";
    int i =0;
    for(Card aCard : this.cards){
      cardListOutput += "\n" + i + " " + aCard.toString();
      i++;
    }
    return cardListOutput;
  }

}
