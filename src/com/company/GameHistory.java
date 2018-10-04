package com.company;

import java.io.Serializable;

public class GameHistory implements Serializable{
    private int hit1 , hit2 , mis1 , mis2 ,idGame;
    private String namePlayer;
    private BattleshipGame battleshipGame;

    public GameHistory(int hit1, int hit2, int mis1, int mis2,  String namePlayer,int idGame, BattleshipGame battleshipGame) {
        this.hit1 = hit1;
        this.hit2 = hit2;
        this.mis1 = mis1;
        this.mis2 = mis2;
        this.idGame = idGame;
        this.namePlayer = namePlayer;
        this.battleshipGame = battleshipGame;
    }

    public void print(){
        System.out.println(namePlayer);
        System.out.println(hit1);
    }


    public int getIdGame() {
        return idGame;
    }

    public BattleshipGame getBattleshipGame() {
        return battleshipGame;
    }
}
