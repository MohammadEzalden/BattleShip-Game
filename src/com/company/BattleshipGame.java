package com.company;
import java.io.Serializable;
import java.util.*;

public class BattleshipGame extends Game implements Runnable,Serializable{



    private int whilecounter;

    public BattleshipPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    private  BattleshipPlayer currentPlayer;
    BattleshipGame() {
        players = new ArrayList<BattleshipPlayer>();
    }

    public BattleshipPlayer.BattleshipMoveResult passmove(BattleshipPlayer.BattleshipMove move,int index){
        BattleshipPlayer temp=(BattleshipPlayer) players.get(index);

        return temp.acceptPlayerMove(move);
    }

    public void runGame() throws InterruptedException {

        start(players);
        int i =0;
        while (true)
        {
            currentPlayer=(BattleshipPlayer) players.get(i%(players.size()));
            whilecounter = 0;
            while (true){
                try {
                    if(currentPlayer.getBattleShipPlayerGrid().ships.size()==0) {
                        stop();
                        break;
                    }
                    BattleshipPlayer.BattleshipMoveResult result = passmove(currentPlayer.getNextMove(), (1 + i) % players.size());
                    currentPlayer.notifyPlayer(result);
                    break;
                }
                catch(NullPointerException e){
                    Thread.sleep(100);
                    whilecounter ++;

                    if (whilecounter ==( welcome05controller.timeout+1)*10 ) {
                        System.out.println((welcome05controller.timeout+1)*10);
                        currentPlayer = (BattleshipPlayer) players.get((i + 1) % (players.size()));
                        i++;
                    }
                }

            }

            if(currentPlayer.getBattleShipPlayerGrid().ships.size()==0) {
                stop();
                break;
            }
            i++;

        }
        return;

    }

    @Override
    public void subscribe(Player player) {
        player.subscribeTo(this);
        players.add(player);
    }

    @Override
    public void leave(Player player) {

    }

    @Override
    public void run() {
        try {
            runGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
