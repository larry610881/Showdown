public class Card {
    private Rank rank;
    private Suit suit;
    public  Card(Rank rank,Suit suit){
        this.rank =rank;
        this.suit =suit;
    }
    public enum  Suit{
        Club,
        Diamond,
        Heart,
        Spade
    }
    public enum Rank{
        two,
        three,
        four,
        five,
        six,
        seven,
        eight,
        nine,
        ten,
        J,
        Q,
        K,
        A;
    }

    public Rank getRank() {
        return rank;
    }
    public Suit getSuit() {
        return suit;
    }

}