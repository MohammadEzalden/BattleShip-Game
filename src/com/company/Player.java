package com.company;

public abstract class Player {
    private IPlayable currentGame;
    public void subscribeTo(IPlayable game)//throws PlayerNotCompatableException
    {
        currentGame=game;
    }
    public void leaveGame(IPlayable game){
        currentGame = null;

    }
}
