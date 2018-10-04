package com.company;

import java.io.Serializable;

public class HumanPlayer  extends BattleshipPlayer implements Serializable{

    private String nextmove;

    private boolean ishitmine = false;

    public boolean isIshitmine() {
        return ishitmine;
    }

    public String getNextmove() {
        return nextmove;
    }

    public void setNextmove(String nextmove) {

        this.nextmove = nextmove;
    }

    public HumanPlayer(Grid battleShipPlayerGrid) {
        super(battleShipPlayerGrid);
        //System.out.println("enter controller05 and create Human player ");
    }

    public boolean[][] attackgrid = new boolean[Grid.getHeight()][Grid.getHeight()];

    public boolean[][] attackgridviset = new boolean[Grid.getHeight()][Grid.getHeight()];




    @Override
    public BattleshipMove getNextMove() {
        return new BattleshipMove(getNextmove());
    }

    public static boolean setnotify(BattleshipPlayer.BattleshipMoveResult result) {

        if (result.getIsmine())
            return true;
        return false;

    }

    public boolean notifyPlayer(BattleshipMoveResult result) {

        String movereceive = result.getMove().getCoordinates();
        String[] Spilite = movereceive.split("\\s+");
        int AttackCox = Integer.parseInt(Spilite[0]);
        int AttackCoy = Integer.parseInt(Spilite[1]);
        ishitmine = false;
        nextmove = null;

        if ((!result.getResalt()) && (attackgrid[AttackCox][AttackCoy])) {
            attackgrid[AttackCox][AttackCoy] = true;
            attackgridviset[AttackCox][AttackCoy]= true;
        } else {
            if (!result.getResalt()) {
                attackgrid[AttackCox][AttackCoy] = false;
                attackgridviset[AttackCox][AttackCoy]= true;
            } else if (result.getResalt()) {
                attackgrid[AttackCox][AttackCoy] = true;
                attackgridviset[AttackCox][AttackCoy]= true;
            }
            if (result.getIsmine()) {
                for (String s : result.getMinenighber()) {
                    String[] Spilitemine = s.split("\\s+");
                    int AttackCoxmine = Integer.parseInt(Spilitemine[0]);
                    int AttackCoymine = Integer.parseInt(Spilitemine[1]);
                    attackgrid[AttackCoxmine][AttackCoymine] = true;
                }
                ishitmine = true;

                result.getMinenighber().clear();
            }
        }

        return false;
    }

}
