package com.company;
import java.io.Serializable;
import java.util.Random;

 class RandomComputerStrategy extends AbstractComputerStrategy implements Serializable {


    private int Height,width;
    private boolean attack_it[][];
    private String attack;
    public RandomComputerStrategy() {
        Height = Grid.getHeight();
        width = Grid.getWidth();
        attack_it =new boolean[Height][width];
        attack="";
    }

    @Override
    BattleshipPlayer.BattleshipMove getNextMove() {
        Random rand=new Random();
        int x=rand.nextInt(Height),y=rand.nextInt(width);
        while (attack_it[x][y]){
            x=rand.nextInt(Height);
            y=rand.nextInt(width);
        }
        attack_it[x][y]=true;
        attack=x+" "+y;
        return new BattleshipPlayer.BattleshipMove(attack);
    }

    @Override
    void distribute(Grid grid) {
         String[] orientations ={
                "left","right","up","down"
        };
        int [] lengths={5,4,3,3,2};
        for (int i :lengths) {
            Ship ship = new Ship(i);
            while (!grid.ships.containsKey(ship)){
                ship.addShip(grid,
                        (new Random()).nextInt(grid.getHeight())
                        , (new Random()).nextInt(grid.getWidth())
                        , orientations[(new Random()).nextInt(4)]);
            }
        }

    }

     @Override
     void setnotify(boolean res) {
     }

     public String getAttack() {
         return attack;
     }
 }
