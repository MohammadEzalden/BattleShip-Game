package com.company;

import java.io.Serializable;

public abstract class  ComputerPlayer extends BattleshipPlayer implements Serializable {

    protected AbstractComputerStrategy currentStrategy;


    public ComputerPlayer(Grid battleShipPlayerGrid, AbstractComputerStrategy currentStrategy) {
        super(battleShipPlayerGrid);
        currentStrategy.distribute(battleShipPlayerGrid);
    }
}
