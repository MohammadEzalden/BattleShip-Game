package com.company;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class GameData implements Serializable{
    private int idgame;
    private String nameplayer;
    private String nameenmy;
    private int timeh;
    private int timem;
    private  List<String>listHmishit;
    private  List<String>listHCoordinates;
    private  List<String>listCmishit;
    private  List<String>listCCoordinates;

    public GameData(int idgame, String nameplayer, String nameenmy,
                    int timeh, int timem,
                    List<String> listHmishit, List<String> listHCoordinates,
                    List<String> listCmishit, List<String> listCCoordinates) {
        this.idgame = idgame;
        this.nameplayer = nameplayer;
        this.nameenmy = nameenmy;
        this.timeh = timeh;
        this.timem = timem;
        this.listHmishit = listHmishit;
        this.listHCoordinates = listHCoordinates;
        this.listCmishit = listCmishit;
        this.listCCoordinates = listCCoordinates;
    }

    public int getIdgame() {
        return idgame;
    }

    public void setIdgame(int idgame) {
        this.idgame = idgame;
    }

    public String getNameplayer() {
        return nameplayer;
    }

    public void setNameplayer(String nameplayer) {
        this.nameplayer = nameplayer;
    }

    public String getNameenmy() {
        return nameenmy;
    }

    public void setNameenmy(String nameenmy) {
        this.nameenmy = nameenmy;
    }

    public int getTimeh() {
        return timeh;
    }

    public void setTimeh(int timeh) {
        this.timeh = timeh;
    }

    public int getTimem() {
        return timem;
    }

    public void setTimem(int timem) {
        this.timem = timem;
    }


    public String getListHmishit() {

        return listHmishit.stream().collect(Collectors.joining("\n"));
    }

    public void setListHmishit(List<String> listHmishit) {
        this.listHmishit = listHmishit;
    }

    public String getListHCoordinates() {
        return listHCoordinates.stream().collect(Collectors.joining("\n"));
    }

    public void setListHCoordinates(List<String> listHCoordinates) {
        this.listHCoordinates = listHCoordinates;
    }

    public String getListCmishit() {
        return listCmishit.stream().collect(Collectors.joining("\n"));
    }

    public void setListCmishit(List<String> listCmishit) {
        this.listCmishit = listCmishit;
    }

    public String getListCCoordinates()
    {
        return listCCoordinates.stream().collect(Collectors.joining("\n"));
    }

    public void setListCCoordinates(List<String> listCCoordinates) {
        this.listCCoordinates = listCCoordinates;
    }
}
