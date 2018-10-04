package com.company;

import sun.awt.SunHints;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class BattleshipPlayer extends Player implements Serializable {

    private String movereceive;

    public BattleshipPlayer(Grid battleShipPlayerGrid) {
        this.BattleShipPlayerGrid = battleShipPlayerGrid;
    }

    public Grid getBattleShipPlayerGrid() {
        return BattleShipPlayerGrid;
    }

    protected Grid BattleShipPlayerGrid ;

    public abstract BattleshipMove getNextMove();

    public BattleshipMoveResult acceptPlayerMove(BattleshipMove move) {

        movereceive = move.getCoordinates();
        String[] Spilite = movereceive.split("\\s+");
        int AttackCox  =  Integer.parseInt(Spilite[0]);
        int AttackCoy  =  Integer.parseInt(Spilite[1]);

        BattleshipMoveResult BSMR=new BattleshipMoveResult(move);
        if(getBattleShipPlayerGrid().matrix[AttackCox][AttackCoy].getState() == '#') {
            System.out.println("attack in #");
            getBattleShipPlayerGrid().matrix[AttackCox][AttackCoy].setState('*');
            String key=String.valueOf(AttackCox)+" "+String.valueOf(AttackCoy);
            Ship ship =  getBattleShipPlayerGrid().fragments.get(key);
            ship.setHealth((ship.getHealth()) - 1 );
            getBattleShipPlayerGrid().fragments.put(key,ship);
            if(ship.getHealth() == 0 )
            {
                ship.destroyShip(getBattleShipPlayerGrid());
                BSMR.setIsdestroid(true);
                System.out.println("Ship is destroy");
            }
            BSMR.setResalt(true);
        }
        else  if(getBattleShipPlayerGrid().matrix[AttackCox][AttackCoy].getState() == '@') {
            BSMR.ismine = true;
            getBattleShipPlayerGrid().matrix[AttackCox][AttackCoy].setState('.');
            int[] movementx={-1,-1,-1,0,0,1,1,1};
            int[] movementy={-1,0,1,-1,1,-1,0,1};
            System.out.println("attack in @");
            for (int i = 0; i < movementx.length; i++) {
                if(      AttackCox+movementx[i]>-1
                        && AttackCox+movementx[i]<Grid.getWidth()
                        &&AttackCoy+movementy[i]>-1
                        && AttackCoy+movementy[i]<Grid.getHeight()
                        &&getBattleShipPlayerGrid().matrix[AttackCox+movementx[i]][AttackCoy+movementy[i]].getState()=='#'
                        ) {
                    System.out.println("enter condition ="+ (AttackCox + movementx[i]) +" "+(AttackCoy + movementy[i]));
                    int x=AttackCox + movementx[i],y=AttackCoy + movementy[i];
                    Ship sh= getBattleShipPlayerGrid().fragments.get(x+" "+y);
                    BSMR.minenighber.add(x + " " + y);
                    getBattleShipPlayerGrid().matrix[x][y].setState('*');

                    sh.setHealth(sh.getHealth()-1);
                    getBattleShipPlayerGrid().fragments.put(x+" "+y,sh);

                    if(sh.getHealth()==0){
                        sh.destroyShip(getBattleShipPlayerGrid());
                        BSMR.setIsdestroid(true);
                        System.out.println("Ship is destroy in mine");
                    }
                }
            }
            BSMR.setResalt(false);
        }

        else
            BSMR.setResalt(false);

        return BSMR;
    }
    public  abstract boolean notifyPlayer(BattleshipMoveResult result);

    public static class BattleshipMove {
        private String coordinates;

        public BattleshipMove(String coordiates) {
            this.coordinates = coordiates;
        }

        public String getCoordinates() {
            return coordinates;
        }
    }

    public class BattleshipMoveResult {
        private BattleshipMove move;
        private boolean resalt;
        private boolean isdestroid ;
        private boolean ismine;
        private LinkedList<String> minenighber = new LinkedList<String>();



        public LinkedList<String> getMinenighber() {
            return minenighber;
        }


        public boolean getIsmine() {
            return ismine;
        }


        public BattleshipMove getMove() {
            return move;
        }

        public BattleshipMoveResult(BattleshipMove move) {
            this.move = move;
        }

        public void setIsdestroid(boolean isdestroid) {
            this.isdestroid = isdestroid;
        }

        public boolean isIsdestroid() {
            return isdestroid;
        }

        public void setResalt(boolean resalt) {
            this.resalt = resalt;
        }

        public boolean getResalt() {
            return resalt;
        }

    }


}
