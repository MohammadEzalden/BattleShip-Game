package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class Grid implements Serializable{
    public void startMines(){
        Mine[] mines;
        // int nummins=(new Random()).nextInt(3);
        int nummins=(new Random()).nextInt(5)+1;
        if(nummins >0) {
            mines = new Mine[nummins];
            for (Mine m:
                    mines) {
                m=new Mine();
                Thread mineThread= new Thread(m);
                mineThread.start();
            }
        }
    }

    public Grid() {
        matrix=new Square[width][height];
        for (int i = 0; i <width ; i++) {
            for (int j = 0; j < height; j++) {
                matrix[i][j]=new Square();
            }
        }
        for (int i = 0; i <width ; i++) {
            for (int j = 0; j < height; j++) {
                matrix[i][j].setState('.');
            }
        }

    }
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }

    public void removeShip(Ship ship){
        if(ships.containsKey(ship)) {
            ships.remove(ship);
            ship = null;
        }
    }
    public void add(Ship ship){
        ships.put(ship,ship);
    }

    public static void setWidth(int width) {
        Grid.width = width;
    }

    public static void setHeight(int height) {
        Grid.height = height;
    }

    private static int width,height;
    public Square[][] matrix;

    HashMap<Ship,Ship> ships=new HashMap<Ship,Ship>();
    public HashMap<String,Ship> fragments = new HashMap<String, Ship>();
    public HashMap<String,Ship.getshipsparts> gethipsOrientatonHashMap = new HashMap<String,Ship.getshipsparts>();

    public void printgrid() {
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++)
                System.out.print(matrix[i][j].getState()+" ");
            System.out.println();
        }
    }
    @Override
    public String toString() {
        String result="";
        for (Square []i: matrix) {
            for (Square j: i) {
                result+=i;
            }
            result+="\n";

        }
        return result;
    }

    public class Mine implements Runnable{
        Mine(){
            currentx=(new Random()).nextInt(Grid.getWidth());
            currenty =(new Random()).nextInt(Grid.getHeight());
            while (true) {
                if(Grid.this.matrix[currentx][currenty].getState()=='.') {
                    Grid.this.matrix[currentx][currenty].setState('@');
                    break;
                }
                currentx=(new Random()).nextInt(Grid.getWidth());
                currenty =(new Random()).nextInt(Grid.getHeight());
            }
        }
        int currentx, currenty;
        void move(){
            int[] movementx={-1,1,0,0};
            int[] movementy={0,0,-1,1};
            while (true){
                int index = (new Random()).nextInt(4);
                if(currentx+movementx[index]<Grid.getWidth()
                        &&currentx+movementx[index]>-1
                        &&currenty+movementy[index]<Grid.getHeight()
                        &&currenty+movementy[index]>-1
                        &&Grid.this.matrix[currentx+movementx[index]][currenty+movementy[index]].getState()=='.'){
                    Grid.this.matrix[currentx][currenty].setState('.');
                    Grid.this.matrix[currentx+movementx[index]][currenty+movementy[index]].setState('@');
                    currentx+=movementx[index];
                    currenty+=movementy[index];
                    break;
                }
            }

        }
        @Override
        public void run() {
            while (Grid.this.matrix[currentx][currenty].getState()=='@'){
                move();
                if(Grid.this.matrix[currentx][currenty].getState()!='@'){
                    break;
                }
                try {
                    //  Thread.sleep((new Random().nextInt(1501))+1000);
                    Thread.sleep((new Random()).nextInt(4000)+1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
