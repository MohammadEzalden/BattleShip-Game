package com.company;

import java.io.Serializable;
import java.util.*;

public abstract class Game implements IPlayable ,Serializable {


    public abstract void subscribe(Player player) ;

    public abstract void leave(Player player);

    public abstract void runGame() throws InterruptedException;

    public void start(List players) {
        if(players.size()< 2) {
            System.out.println("Stop ");
            stop();
        }
    }


    public void stop() {
       // System.exit(0);
      /* if(players.size() == 1 )
            for (int i = 0; i <players.size() ; i++) {
                players.get(i);
                players.remove(i);
                Player p = (Player)players.get(i);
                p.leaveGame(this);
            }
            else
                return;
        System.out.println("The Game is END");*/

    }

   protected List players;
}
