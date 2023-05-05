import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player {
    private String name;
    private Card selectedCard;
    private int point =0;
    private boolean isUsedPrivilege =false;
    public List<Card> hands;
    public Scanner scanner;
    public List<Exchange> exchange;
    public Game game;
    public Player(Game game){
        this.game = game;
        this.hands = new ArrayList<Card>();
        this.exchange = new ArrayList<Exchange>();
        this.scanner = new Scanner(System.in);
    }
    public  abstract void takeTurn();
    public  void exchangeHands(Player player){
        Exchange ex = new Exchange(this,player);
        this.exchange.add(ex);
    }
    public void radomCardByDeck(){
        show(this.game.radomCardByDeck());
    }
    public  void show(Card card){
        this.selectedCard =card;
        this.hands.remove(card);
        System.out.printf("玩家:%s 已選擇一張牌\n",this.name);
    }
    public String getName(){
        return  name;
    }
    public List<Card> getHands(){
        return this.hands;
    }
    public void exchangeCard(List<Card> cards){
        this.hands =cards;
    }
    public Card getSelectedCard() {
        return selectedCard;
    }
    public int getPoint(){
        return point;
    }
    public void  setPoint(){
        this.point++;
    }
    public void setName(String name){
        this.name = name;
    }
    public boolean isUsedPrivilege() {
        return isUsedPrivilege;
    }
    public void setIsUsedPrivilege(){
        this.isUsedPrivilege =true;
    }
    public void addHands(Card card){
        this.hands.add(card);
    }
}
