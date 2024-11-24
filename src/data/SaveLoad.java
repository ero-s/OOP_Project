package data;

import Entity.Entity;
import object.*;
import pkg2dgame.GamePanel;
import java.io.*;

public class SaveLoad {
    GamePanel gp;
    private ObjectOutputStream oos;
    private DataStorage ds;
    private ObjectInputStream ois;
    private boolean existingSave;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
        existingSave = false;

    }

    private Entity getObject(String itemName) {
        Entity obj = null;

        switch (itemName) {
            case "Normal Sword": obj = new OBJ_Sword_Normal(gp); break;
            case "Wood Shield": obj = new OBJ_Shield_Wood(gp); break;
            case "Woodcutter's Axe": obj = new OBJ_Axe(gp); break;
            case "Cabbage": obj = new CON_Cabbage(gp); break;
            case "Axe": obj = new OBJ_Axe(gp); break;
            case "Key": obj = new OBJ_Key(gp); break;
            // add more cases if necessary
        }

        return obj;
    }

    // in GamePanel SaveLoad saveLoad

    public boolean getHasSave() {
        saveChecker(); // does this because we can't get the shits from load if we dont.
        return existingSave;
    }

    private boolean hasLoad() {
        File saveFile = new File("src/data/save.dat");

        if (!saveFile.exists()) {
            System.out.println("No save file found.");
            existingSave = false; // No save file means no existing save
            return false;
        }

        // Check if the file is empty
        if (saveFile.length() == 0) {
            System.out.println("The save file is empty.");
            existingSave = false; // No valid save data
            return false;
        }

        return true;
    }

    private void saveChecker() {
        File saveFile = new File("src/data/save.dat");

        if(!hasLoad()) {
            return;
        }

        try {
            ois = new ObjectInputStream(new FileInputStream(new File("src/data/save.dat")));

            // Read Data Storage object
            ds = (DataStorage)ois.readObject();
            ois.close();

            gp.hasSave = ds.hasSave();
            setHasSave(gp.hasSave);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setHasSave(boolean existingSave) {
        this.existingSave = existingSave;
    }

    // save method
    public void save() {
        try {

            oos = new ObjectOutputStream(new FileOutputStream(new File("src/data/save.dat")));
            ds = new DataStorage();

            // PLAYER STATS
            ds.setLevel(gp.player.level);
            ds.setAtkPower(gp.player.atkPower);
            ds.setCoin(gp.player.coin);
            ds.setDefense(gp.player.defense);
            ds.setNextLevelExp(gp.player.nextLevelExp);
            ds.setExp(gp.player.exp);
            ds.setUseCost(gp.player.useCost); // might be optional
            ds.setMana(gp.player.mana); // basin optional
            ds.setWorldX(gp.player.worldX);
            ds.setWorldY(gp.player.worldY);
            ds.setCurrentMap(gp.currentMap);
            ds.setHasSave(true);

            // PLAYER INVENTORY
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                System.out.println("item loaded!");
                ds.getItemNames().add(gp.player.inventory.get(i).name);
                ds.getItemAmounts().add(gp.player.inventory.get(i).amount);
            }

            // PLAYER EQUIPMENT
            ds.setCurrentWeaponSlot(gp.player.getCurrentWeaponSlot());
            ds.setCurrentShieldSlot(gp.player.getCurrentShieldSlot());

            oos.writeObject(ds);
            oos.close();

            System.out.println("Game saved successfully.");


        } catch (Exception e) {
            System.out.println("Save Exception! "+e);
        }

    }


    // load method
    public void load() {
        File saveFile = new File("src/data/save.dat");

        if(!hasLoad()) {
            return;
        }

        try {
            ois = new ObjectInputStream(new FileInputStream(new File("src/data/save.dat")));

            // Read Data Storage object
            ds = (DataStorage)ois.readObject();
            ois.close();

            //Retrieve Data
            gp.player.level = ds.getLevel();
            gp.player.atkPower = ds.getAtkPower();
            gp.player.coin = ds.getCoin();
            gp.player.defense = ds.getDefense();
            gp.player.nextLevelExp = ds.getNextLevelExp();
            gp.player.exp = ds.getExp();
            gp.player.useCost = ds.getUseCost();
            gp.player.mana = ds.getMana();

            gp.player.worldX = ds.getWorldX();
            gp.player.worldY = ds.getWorldY();
            gp.currentMap = ds.getCurrentMap();
            gp.hasSave = ds.hasSave();
            setHasSave(gp.hasSave);

            // PLAYER INVENTORY
            gp.player.inventory.clear(); // clears out the default items

            for (int i = 0; i < ds.getItemNames().size(); i++) {
                gp.player.inventory.add(getObject(ds.getItemNames().get(i)));
                gp.player.inventory.get(i).amount = ds.getItemAmounts().get(i);
            }

            // PLAYER EQUIPMENT
            gp.player.setCurrentWeapon(gp.player.inventory.get(ds.getCurrentWeaponSlot()));
            gp.player.setCurrentShield(gp.player.inventory.get(ds.getCurrentShieldSlot()));
            // theres a getter for attack, dmg, attack image (waiting)

            System.out.println("Game loaded successfully.");

            ois.close();

        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }

}
