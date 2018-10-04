package com.company;

import java.io.Serializable;

public class MarkPlayer implements Serializable{
    private int idGame,countwin,countplay,mark;
    private String namePlayer,typeenmy;


    public MarkPlayer(int idGame, int countwin, int countplay, int mark, String namePlayer, String typeenmy) {
        this.idGame = idGame;
        this.countwin = countwin;
        this.countplay = countplay;
        this.mark = mark;
        this.namePlayer = namePlayer;
        this.typeenmy = typeenmy;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setCountwin(int countwin) {
        this.countwin = countwin;
    }

    public void setCountplay(int countplay) {
        this.countplay = countplay;
    }

    public int getIdGame() {
        return idGame;
    }

    public int getCountwin() {
        return countwin;
    }

    public int getCountplay() {
        return countplay;
    }

    public int getMark() {
        return mark;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public String getTypeenmy() {
        return typeenmy;
    }
}
