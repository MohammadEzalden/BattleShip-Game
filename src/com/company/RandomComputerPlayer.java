package com.company;

import java.io.Serializable;

public class RandomComputerPlayer extends  ComputerPlayer implements Serializable {
    public RandomComputerPlayer(Grid battleShipPlayerGrid, AbstractComputerStrategy currentStrategy) {
        super(battleShipPlayerGrid, currentStrategy);
        this.currentStrategy = new RandomComputerStrategy();
        battleShipPlayerGrid.startMines();
    }


    @Override
    public boolean notifyPlayer(BattleshipMoveResult result) {
        if(result.getResalt()) {
            return true;
        }
        else
        return false;
    }

    @Override
    public BattleshipMove getNextMove() {
        return currentStrategy.getNextMove();
    }

    public String getattack(){
        return ((RandomComputerStrategy)currentStrategy).getAttack();
    }
}
