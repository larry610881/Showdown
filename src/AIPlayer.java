import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AIPlayer extends Player{

    public AIPlayer(Game game,String name) {
        super(game);
        super.setName(name);
        System.out.println(String.format("已建立AI玩家:%s", super.getName()));
    }
    @Override
    public void takeTurn() {
        int size =super.hands.size();
        Random random = new Random();
        //有1/20的機率會換別人的牌
        if(!super.isUsedPrivilege()&&random.nextInt(20)==0){
            //除了自己以外的player
            List<Player> players =super.game.getPlayers().stream()
                        .filter(p -> p!=this)
                        .collect(Collectors.toList());
            super.exchangeHands(players.get(random.nextInt(3)));
        }
        if(size==0){
            super.radomCardByDeck();
        }else{
            super.show(super.hands.get(random.nextInt(size)));
        }

    }
}
