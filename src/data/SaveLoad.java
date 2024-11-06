package data;

import pkg2dgame.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }
    // in GamePanel SaveLoad saveLoad

    public void save() {
        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.atkPower = gp.player.atkPower;
            ds.coin = gp.player.coin;
            ds.defense = gp.player.defense;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.exp = gp.player.exp;
            ds.useCost = gp.player.useCost; // might be optional
            ds.mana = gp.player.mana; // basin optional
            ds.worldX = gp.player.worldX;
            ds.worldY = gp.player.worldY;
            ds.currentMap = gp.currentMap;


            oos.writeObject(ds);
            oos.close();
            System.out.println("Game saved successfully.");

        } catch (Exception e) {
            System.out.println("Save Exception! "+e);
        }

    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read Data Storage object
            DataStorage ds = (DataStorage)ois.readObject();
            ois.close();
            //Retrieve Data
            gp.player.level = ds.level;
            gp.player.atkPower = ds.atkPower;
            gp.player.coin = ds.coin;
            gp.player.defense = ds.defense;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.exp = ds.exp;
            gp.player.useCost = ds.useCost;
            gp.player.mana = ds.mana;

            gp.player.worldX = ds.worldX;
            gp.player.worldY = ds.worldY;
            gp.currentMap = ds.currentMap;

            System.out.println("Game loaded successfully.");

        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
