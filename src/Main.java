import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        //建立玩家資訊 玩家編號1~4
        for (int i =1;i<=4;i++){
            String s;
            boolean bool = false;
            do{
                System.out.printf("P%s是否為AI玩家:請輸入Y/N\n",i);
                Scanner scanner = new Scanner(System.in);;
                s = scanner.nextLine().toLowerCase();
            }while (!s.equals("y") && !s.equals("n"));
            if(s.equals("y")){
                bool =true;
            }
            game.isAIPlayer(bool);
        }
        //發牌
        game.drawCard();
        //開始13回合遊戲
        while (game.getTurn()<=13){
            for(Player palyer:game.getPlayers()){
                System.out.printf("請玩家:%s 選擇動作\n",palyer.getName());
                palyer.takeTurn();
            }
            game.highCard();
            game.setTurn();
        }
        //宣告勝利玩家

        game.whosWinner();
    }
}