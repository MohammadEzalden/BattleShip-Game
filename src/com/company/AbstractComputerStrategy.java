package com.company;

public abstract class AbstractComputerStrategy {

    abstract BattleshipPlayer.BattleshipMove getNextMove();
    abstract void distribute(Grid grid);
    abstract void setnotify(boolean res) ;

}
