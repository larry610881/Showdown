import java.util.*;
import java.util.stream.Collectors;

public class Game {
    // region Attributes
    private int turn=0;
    private List<Player> players;
    private Deck deck;
    private Scanner scanner;
    // endregion

    // region Constructor
    public Game(){
        this.players = new ArrayList<Player>();
        this.deck = new Deck();
        this.scanner = new Scanner(System.in);
        System.out.println("成功建立一場遊戲");
    }
    // endregion

    // region Behavior
    public void isAIPlayer(boolean bool){
        if(bool){
            int count = (int) this.players.stream().count()+1;
            String AIPlayerName =String.format("P%d",count);
            AIPlayer aiPlayer = new AIPlayer(this,AIPlayerName);
            this.players.add(aiPlayer);
        }else{
            System.out.print("請輸入玩家姓名:");
            String name = scanner.nextLine();
            HumanPlayer humanPlayer = new HumanPlayer(this,name);
            this.players.add(humanPlayer);
        }
    }
    public Card radomCardByDeck(){
        this.deck.shuffle();
        return this.deck.pop();
    }
    public void drawCard(){
        int tag =0;
        while (this.deck.size()>0){
            this.players.get(tag).addHands(this.deck.pop());
            tag++;
            tag%=4;
        }
        System.out.println("已發完牌");
        this.turn++;
    }
    public void highCard(){
        int[][] high =new int[4][2];
        Stack<Card> fold = new Stack<Card>();
        Iterator<Player> iter = this.players.iterator();
        for (int i = 0; iter.hasNext(); i++) {
            Player player = iter.next();
            //回合結束+1
            Iterator<Exchange> it = player.exchange.iterator();
            while (it.hasNext()) {
                Exchange ex = it.next();
                ex.setTurn();
                //還牌 移除交換關係
                if (ex.getTurn() == 3) {
                    ex.endExchange();
                    it.remove();
                }
            }
            Card c =player.getSelectedCard();
            fold.push(c);
            System.out.printf("玩家:%s 選擇%s %s ",player.getName(),c.getSuit(),c.getRank());
            int [] item = new int[] { i, (c.getRank().ordinal()+1)*10+c.getSuit().ordinal() };
            high[i]=item;
        }
        this.deck.push(fold);
        // 按第二個索引排序 先比數字
        Arrays.sort(high, Comparator.comparingInt((int[] a) -> a[1]).reversed());

        System.out.println();
        Player win = this.players.get(high[0][0]);
        win.setPoint();
        System.out.printf("第%d局 玩家:%s獲勝 獲得1分 目前%d分\n",this.turn,win.getName(),win.getPoint());
    }
    public void whosWinner(){
        players.forEach(player -> System.out.println(player.getName() + ": " + player.getPoint()));
        List<String> maxPlayerNames = this.players.stream()
                .filter(p -> p.getPoint() == this.players.stream()
                        .mapToInt(Player::getPoint)
                        .max()
                        .orElseThrow(NoSuchElementException::new))
                .map(Player::getName)
                .collect(Collectors.toList());
         System.out.println("勝利玩家: " + maxPlayerNames);

    }
    public int getTurn() {
        return turn;
    }
    public void setTurn() {
        this.turn++;
    }
    public List<Player> getPlayers(){
        return  this.players;
    }
    // endregion
}
