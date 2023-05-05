import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class HumanPlayer extends Player {
    public HumanPlayer(Game game,String name) {
        super(game);
        givenName(name);
        System.out.println(String.format("已建立真實玩家:%s", super.getName()));
    }


    @Override
    public void takeTurn() {
        int size = super.hands.size();
        if(!super.isUsedPrivilege()){
            Boolean privilege= scannerPrivilege();

            if(privilege){
                List<Player> players =super.game.getPlayers().stream()
                                        .filter(p->p!=this)
                                        .collect(Collectors.toList());
                super.exchangeHands(super.game.getPlayers().get(scannerPrivilegeByChoose(players)));
            }
        }
        super.show(super.hands.get(scannerShow()));
    }
    public void givenName(String name){
        super.setName(name);
    }
    public void seeHands(){
        int index =1;
        for (Card card:super.hands){
            System.out.printf("第%d張牌:%s %s\n",index,card.getSuit(),card.getRank());
            index++;
        }
    }
    public boolean scannerPrivilege(){
        String s ;
        do{
            System.out.println("是否使用特權:請輸入Y/N");
            s = super.scanner.nextLine().toLowerCase();
        }while (!s.equals("y") && !s.equals("n"));
        return s.equals("y");
    }
    public int scannerPrivilegeByChoose(List<Player> players){
        String s;
        do{
            Iterator<Player> iter = players.iterator();
            for (int i = 0; iter.hasNext(); i++) {
                Player player = iter.next();
                System.out.printf("編號:%d 玩家:%s\n",i,player.getName());
            }
            System.out.println("請選擇欲交換的玩家");
            s = super.scanner.nextLine();
        }while (!s.equals("0") && !s.equals("1")&&!s.equals("2"));
        return Integer.parseInt(s);
    }
    public int scannerShow(){
        String s;
        int size = super.hands.size();
        int num=20;
        do {
            System.out.println("請選擇欲出的牌:輸入SEE看手牌");
            s = scanner.nextLine().toLowerCase();
            if(s.equals("see")){
                seeHands();
                continue;
            }
            try {
                num = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                num = 20;
            }
        } while (num > size);
        return num-1;
    }
}
