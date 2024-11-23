package data;

import pkg2dgame.GamePanel;
import java.io.*;

public class SaveLoad {
    GamePanel gp;
    public ObjectOutputStream oos;
    public DataStorage ds;
    public ObjectInputStream ois;
    //private boolean hasSave;
    private boolean existingSave;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
        existingSave = false;
    }
    // in GamePanel SaveLoad saveLoad

    public boolean getHasSave() {
        load(); // does this because we can't get the shits from load if we dont.
        return existingSave;
    }

    public void setHasSave(boolean existingSave) {
        this.existingSave = existingSave;
    }

    public void save() {
        try {

            oos = new ObjectOutputStream(new FileOutputStream(new File("src/data/save.dat")));
            ds = new DataStorage();

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
            ds.hasSave = true;
            oos.writeObject(ds);
            oos.close();
            System.out.println("Game saved successfully.");


        } catch (Exception e) {
            System.out.println("Save Exception! "+e);
        }

    }

    public void load() {
        File saveFile = new File("src/data/save.dat");

        if (!saveFile.exists()) {
            System.out.println("No save file found.");
            existingSave = false; // No save file means no existing save
            return;
        }

        // Check if the file is empty
        if (saveFile.length() == 0) {
            System.out.println("The save file is empty.");
            existingSave = false; // No valid save data
            return;
        }

        try {
            ois = new ObjectInputStream(new FileInputStream(new File("src/data/save.dat")));

            // Read Data Storage object
            ds = (DataStorage)ois.readObject();
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

            gp.hasSave = ds.hasSave;
            setHasSave(gp.hasSave);

            System.out.println("Game loaded successfully.");

        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
