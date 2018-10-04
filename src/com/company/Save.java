package com.company;

import java.io.*;

public class Save {
    private static ObjectOutputStream oos1;
    private static FileOutputStream fos1;
    private static ObjectOutputStream oos2;
    private static FileOutputStream fos2;
    private static BufferedOutputStream bos2;
    private static ObjectOutputStream oos3;
    private static FileOutputStream fos3;
    private static BufferedOutputStream bos3;
    private static ObjectOutputStream oos4;
    private static FileOutputStream fos4;
    private static BufferedOutputStream bos4;

    /**  1   */
    static void openQuickSave() throws IOException {

        fos1=new FileOutputStream("QuickSave.ser");
        oos1=new ObjectOutputStream(fos1);

    }
    static void QuickSave(Object object) throws IOException {
        oos1.writeObject(object);


    }
    static void endQuickSave() throws IOException {
        oos1.close();
        fos1.close();

    }

    /**  2   */
    static void opensavef() throws IOException {

        fos2=new FileOutputStream("savef.ser",true);
        bos2=new BufferedOutputStream(fos2);
        oos2=new ObjectOutputStream(bos2);

    }
    static void savef(Object object) throws IOException {
        oos2.writeObject(object);


    }
    static void endsavef() throws IOException {
        oos2.close();
        bos2.close();
        fos2.close();

    }

    /**  3   */
    static void openMarkPlayer() throws IOException {

        fos3=new FileOutputStream("MarkPlayer.ser");
        oos3=new ObjectOutputStream(fos3);

    }
    static void SaveMarkPlayer(Object object) throws IOException {

        oos3.writeObject(object);

    }
    static void endMarkPlayer() throws IOException {
        oos3.close();
        fos3.close();

    }


    /**  4   */
    static void openHist() throws IOException {

        fos4=new FileOutputStream("GameHistory.ser",true);
        bos4=new BufferedOutputStream(fos4);
        oos4=new ObjectOutputStream(bos4);

    }
    static void saveHist(Object object) throws IOException {
        oos4.writeObject(object);


    }
    static void endHist() throws IOException {
        oos4.close();
        bos4.close();
        fos4.close();

    }

    private static ObjectOutputStream oid;
    private static FileOutputStream fid;

    static void openid() throws IOException {

        fid=new FileOutputStream("id.ser");
        oid=new ObjectOutputStream(fid);

    }
    static void idf(Object object) throws IOException {
        oid.writeObject(object);


    }
    static void endid() throws IOException {
        fid.close();
        oid.close();

    }



}
