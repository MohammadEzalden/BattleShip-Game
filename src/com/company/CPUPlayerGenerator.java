package com.company;

import java.io.Serializable;

public class CPUPlayerGenerator implements Serializable {
    public ComputerPlayer nextPlayer(String type){
        if(type.equals("1")){
            return new SmartComputerPlayer(new Grid(),new SmartComputerStrategy());
        }
            return new RandomComputerPlayer(new Grid(),new RandomComputerStrategy());
    }
}
