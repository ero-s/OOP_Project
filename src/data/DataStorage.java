package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    // ENCAPSULATED PLAYER STATS
    private int level, atkPower, defense,  exp, nextLevelExp, coin, mana, useCost, worldX, worldY, currentMap;

    // ENCAPSULATED SAVE IDENTIFIER
    private boolean hasSave = false;


    // ENCAPSULATED PLAYER INVENTORY
    private ArrayList<String> itemNames;
    private ArrayList<Integer> itemAmounts;

    public DataStorage() {
        itemNames = new ArrayList<>();
        itemAmounts = new ArrayList<>();
    }

    public ArrayList<Integer> getItemAmounts() {
        return itemAmounts;
    }

    public void setItemAmounts(ArrayList<Integer> itemAmounts) {
        this.itemAmounts = itemAmounts;
    }

    public ArrayList<String> getItemNames() {
        return itemNames;
    }

    public void setItemNames(ArrayList<String> itemNames) {
        this.itemNames = itemNames;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAtkPower() {
        return atkPower;
    }

    public void setAtkPower(int atkPower) {
        this.atkPower = atkPower;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public boolean hasSave() {
        return hasSave;
    }

    public void setHasSave(boolean hasSave) {
        this.hasSave = hasSave;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getUseCost() {
        return useCost;
    }

    public void setUseCost(int useCost) {
        this.useCost = useCost;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }


}
