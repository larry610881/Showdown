import java.util.*;

public class Deck {
    private Stack<Card> cards;
    public Deck(){
        this.cards = new Stack<Card>();
        Card.Suit[] suits = Card.Suit.values();
        Card.Rank[] ranks = Card.Rank.values();
        for(Card.Suit suit:suits){
            for (Card.Rank rank:ranks){
                Card card = new Card(rank,suit);
                cards.add(card);
            }
        }
        shuffle();
    }
    public void shuffle(){
        Collections.shuffle(this.cards);
    }
    public Card pop(){
        return this.cards.pop();
    }
    public void push(Stack<Card> cards){
        while (cards.size()>0){
            this.cards.push(cards.pop());
        }
    }
    public int size(){
        return this.cards.size();
    }
}
