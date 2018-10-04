package com.company;

public interface IPlayable {
    public void subscribe(Player player) ;//throws PlayerNotCompatableException;
    public void leave(Player player);
}
