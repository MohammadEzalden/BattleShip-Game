package com.company;


import java.io.Serializable;

public class Ship implements Serializable{


    public Ship(int shipLength ) {
        destroyed = false;

        this.shipLength=shipLength;
        health=shipLength;
    }
    private String[] orientations ={
      "left","right","up","down"
    };

    private boolean destroyed=false;
    private int shipLength;

    private int health;

    public class getshipsparts implements Serializable{
        getshipsparts(String oriant ,String shipcords ,int lenth )
        {
            Oriant = oriant;
            Lenth = lenth;
            parts = new boolean[5];
        }
        String Oriant  ;
        int Lenth ;
        boolean[] parts;

    }


    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    void destroyShip(Grid g){

        g.removeShip(this);
        destroyed=true;

    }

    boolean vacant(Grid g, int cox,int coy,String orientation) {
        if(orientation.equals(orientations[2])) {
            //up
                if(cox-shipLength+1<0)
                    return  false;
            for (int i = 0;i< shipLength; i++) {
                if(g.matrix[cox-i][coy].getState()!='.')
                    return  false;
            }
        }
        if(orientation.equals(orientations[3])){
            //down
            if(cox+shipLength>g.getWidth())
                return false;
            for(int i=0;i<shipLength;i++){
                if(g.matrix[cox+i][coy].getState()!='.')
                    return false;
            }

        }

        if(orientation.equals(orientations[0])) {
            //left
            if(coy-shipLength+1<0)
                return  false;
            for (int i = 0;i< shipLength; i++) {
                if(g.matrix[cox][coy-i].getState()!='.')
                    return  false;
            }
        }

        if(orientation.equals(orientations[1])){
            //right
            if(coy+shipLength>g.getHeight())
                return false;
            for(int i=0;i<shipLength;i++){
                if(g.matrix[cox][coy+i].getState()!='.')
                    return false;
            }

        }

        return  true;
    }

    public void  addShip(Grid g,int cox,int coy,String orientation){
        if(vacant(g,cox,coy,orientation)){
            if(orientation.equals(orientations[2])){
                //up
                for (int i = 0;i< shipLength; i++) {
                    g.matrix[cox-i][coy].setState('#');
                    String key=String.valueOf(cox-i)+" "+String.valueOf(coy);
                    g.fragments.put(key,this);

                    getshipsparts getshipsparts = new getshipsparts(orientation,key,shipLength);
                    getshipsparts.parts[i]= true;
                    g.gethipsOrientatonHashMap.put(key,getshipsparts);
                }
            }
            if(orientation.equals(orientations[3])){
                //down
                for (int i = 0;i< shipLength; i++) {
                    g.matrix[cox+i][coy].setState('#');
                    String key=String.valueOf(cox+i)+" "+String.valueOf(coy);
                    g.fragments.put(key,this);

                    getshipsparts getshipsparts = new getshipsparts(orientation,key,shipLength);
                    getshipsparts.parts[i]= true;
                    g.gethipsOrientatonHashMap.put(key,getshipsparts);
                }
            }

            if(orientation.equals(orientations[0])){
                //left
                for (int i = 0;i< shipLength; i++) {
                    g.matrix[cox][coy-i].setState('#');
                    String key=String.valueOf(cox)+" "+String.valueOf(coy-i);
                    g.fragments.put(key,this);

                    getshipsparts getshipsparts = new getshipsparts(orientation,key,shipLength);
                    getshipsparts.parts[i]= true;
                    g.gethipsOrientatonHashMap.put(key,getshipsparts);
                }
            }
            if(orientation.equals(orientations[1])){
                //right
                for (int i = 0;i< shipLength; i++) {
                    g.matrix[cox][coy+i].setState('#');
                    String key=String.valueOf(cox)+" "+String.valueOf(coy+i);
                    g.fragments.put(key,this);

                    getshipsparts getshipsparts = new getshipsparts(orientation,key,shipLength);
                    getshipsparts.parts[i]= true;
                    g.gethipsOrientatonHashMap.put(key,getshipsparts);
                }
            }
            g.add(this);
        }
        else ;
    }
}
