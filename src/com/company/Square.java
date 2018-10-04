package com.company;

import java.io.Serializable;

public class Square implements Serializable {
   private char state;

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    @Override
    public String toString() {
        if(state=='#')
            return ".";
        else
            return String.valueOf(state);
    }
}
