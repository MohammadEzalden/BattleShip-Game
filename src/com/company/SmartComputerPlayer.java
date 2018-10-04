package com.company;

import java.io.Serializable;

public class SmartComputerPlayer extends ComputerPlayer implements Serializable {

    public SmartComputerPlayer(Grid battleShipPlayerGrid, AbstractComputerStrategy currentStrategy) {
        super(battleShipPlayerGrid, currentStrategy);
        this.currentStrategy=new SmartComputerStrategy();
        battleShipPlayerGrid.startMines();
    }

    public boolean notifyPlayer(BattleshipMoveResult result) {
        if (result.getResalt()) {
            currentStrategy.setnotify(true);
            if(result.isIsdestroid())
            return true;
        } else {
            currentStrategy.setnotify(false);
            return false;
        }
        return false;
    }

    @Override
    public BattleshipMove getNextMove() {
        return currentStrategy.getNextMove();
    }

    public String getattack(){
        return ((SmartComputerStrategy)currentStrategy).getAttack();
    }

}
