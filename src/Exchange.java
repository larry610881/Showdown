import java.util.ArrayList;
import java.util.List;

public class Exchange {
    private int turn =1;
    private Player exchanger;
    private Player exchangee;
    public  Exchange(Player exchanger,Player exchangee){
        this.exchanger = exchanger;
        this.exchangee =exchangee;
        swap();
        this.exchanger.setIsUsedPrivilege();
        System.out.printf("玩家:%s 與玩家:%s 交換手牌\n",this.exchanger.getName(),this.exchangee.getName());
    }
    public void endExchange(){
        swap();
        System.out.printf("玩家:%s 與玩家:%s 三回合結束手牌再次交換\n",this.exchanger.getName(),this.exchangee.getName());
    }
    public void swap(){
        List<Card> tmp = new ArrayList<Card>();
        tmp = this.exchanger.getHands();
        this.exchanger.exchangeCard(this.exchangee.getHands());
        this.exchangee.exchangeCard(tmp);

    }
    public int getTurn() {
        return turn;
    }
    public void setTurn(){
        this.turn++;
    }
}
