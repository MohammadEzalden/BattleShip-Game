package com.company;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Load {


    private static FileInputStream fis1;
    private static ObjectInputStream ois1;
    private static FileInputStream fis2;
    private static BufferedInputStream bos2;
    private static ObjectInputStream ois2;
    private static FileInputStream fis3;
    private static BufferedInputStream bis3;
    private static ObjectInputStream ois3;
    private static FileInputStream fis4;
    private static BufferedInputStream bis4;
    private static ObjectInputStream ois4;

    public static BufferedInputStream getBos3() {
        return bis3;
    }
    public static BufferedInputStream getBos2() {
        return bos2;
    }
    public static ObjectInputStream getOis3() {
        return ois3;
    }

    public static BufferedInputStream getBis4() {
        return bis4;
    }

    /**  1  */
    public static void openQuickLoad() throws IOException {
        fis1=new FileInputStream("QuickSave.ser");
        ois1=new ObjectInputStream(fis1);
    }
    public static BattleshipGame QuickLoad() throws IOException, ClassNotFoundException {

        return  ((BattleshipGame) ois1.readObject());
    }
    public static void endQuickLoad() throws IOException {
        fis1.close();
        ois1.close();
    }

    /**  2   */
    public static void opengames() throws IOException {
        fis2=new FileInputStream("savef.ser");
        bos2=new BufferedInputStream(fis2);
    }
    public static GameHistory loadgames() throws IOException, ClassNotFoundException {
        ois2 = new ObjectInputStream(bos2);
        return  (GameHistory) ois2.readObject();

    }
    public static void endgames() throws IOException {
        fis2.close();
        ois2.close();
    }


    /**  3 */
    public static void openMarkPlayer() throws IOException {
        fis3=new FileInputStream("MarkPlayer.ser");
        ois3 = new ObjectInputStream(fis3);
    }
    public static List<MarkPlayer> loadmarkplayer() throws IOException, ClassNotFoundException {
            return  (ArrayList<MarkPlayer>)ois3.readObject();


    }
    public static void endmarkplayer() throws IOException {
        fis3.close();
        ois3.close();
    }

    /**  4  */
    public static void openHist() throws IOException {
        fis4=new FileInputStream("GameHistory.ser");
        bis4=new BufferedInputStream(fis4);
    }
    public static GameData loadHist() throws IOException, ClassNotFoundException {
        ois4 = new ObjectInputStream(bis4);
        return  (GameData) ois4.readObject();

    }
    public static void endHist() throws IOException {
        fis4.close();
        bis4.close();
        ois4.close();
    }

    /**   5 **/
    private static FileInputStream idf;
    private static ObjectInputStream oid;

    public static void openid() throws IOException {
        idf=new FileInputStream("id.ser");
        oid=new ObjectInputStream(idf);
    }
    public static int idf() throws IOException, ClassNotFoundException {

        return  ((int) oid.readObject());
    }
    public static void endid() throws IOException {
        idf.close();
        oid.close();
    }

}
