package com.company;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class SmartComputerStrategy extends AbstractComputerStrategy implements Serializable {
    private int Height=Grid.getHeight(),width=Grid.getWidth();
    private boolean attack_it[][],FS;
    private String attack;
    public SmartComputerStrategy() {
        Height = Grid.getHeight();
        width = Grid.getWidth();
        attack_it =new boolean[Height][width];
        for(int i=0;i<Height;i++)
            Arrays.fill(attack_it[i],Boolean.FALSE);
        FS = false;
        attack="";
    }

    @Override
    BattleshipPlayer.BattleshipMove getNextMove() {
        //not find any ship
        if(!FS) {
            attack = Search_New_Ship();
        }
        //find Ship -> attack down
        else {
            find_Ship();
        }
        return new BattleshipPlayer.BattleshipMove(attack);
    }

    @Override
    void distribute(Grid grid) {
        Grid newg = new Grid();
        String[] orientations ={
                "left","right","up","down"
        };
        int [] lengths={5,4,3,3,2};
        for (int i :lengths) {
            Ship ship = new Ship(i);
            boolean addedoriginal=false;

            while (!newg.ships.containsKey(ship)){

                int cox=(new Random().nextInt(newg.getHeight())),coy=(new Random().nextInt(newg.getWidth()));
                String orientation=orientations[(new Random().nextInt(4))];
                ship.addShip(newg,
                        cox
                        , coy
                        , orientation);
                if(newg.ships.containsKey(ship)){
                    if(!addedoriginal){
                        ship.addShip(grid,cox,coy,orientation);
                        addedoriginal=true;

                    }
                if(orientation.equals(orientations[0])||orientation.equals(orientations[1])) {
                    if(cox+1<newg.getHeight())
                        (new Ship(i)).addShip(newg, cox + 1,coy, orientation);
                    if(cox-1>=0)
                        (new Ship(i)).addShip(newg, cox -1, coy ,orientation );
                }
                if(orientation.equals(orientations[2])||orientation.equals(orientations[3])) {
                    if(coy+1<newg.getWidth())
                           (new Ship(i)).addShip(newg, cox ,coy+ 1, orientation);
                    if(coy-1>=0)
                        (new Ship(i)).addShip(newg, cox, coy -1 ,orientation );
                }

                }

            }

        }
    }

    public void setnotify(boolean res)
    {
       FS =  res;
    }//result Attack

    private String Search_New_Ship() {
        String att="";
        for(int i=0;i<Height;i++) {
            for(int j=0;j<width;j++){
                if(!attack_it[i][j]){      //attack it : is visit
                    attack_it[i][j]=true;
                   att = i+ " "+j;
                   FS=true;
                   return att;
                }
            }
        }
        return att;
    }

    private void find_Ship(){
        String[] Spilite = attack.split("\\s+");
        int Attackcox=Integer.parseInt(Spilite[0]);
        int Attackcoy=Integer.parseInt(Spilite[1]);
        if(Attackcox<Height-1){
            attack_it[Attackcox+1][Attackcoy]=true;
            attack=(Attackcox+1)+" "+Attackcoy;
        }
        else if(Attackcoy<width-1){
            attack=Attackcox + " " + (Attackcoy + 1) ;
            FS=false;
            attack_it[Attackcox][Attackcoy+1]=true;
        }
    }

    public String getAttack() {
        return attack;
    }
}
