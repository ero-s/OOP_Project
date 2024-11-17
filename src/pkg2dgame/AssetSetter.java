/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dgame;

import object.OBJ_Circus;
import pkg2dgame.monster.*;
import tile_interactive.IT_ChopTree;

/**
 *
 * @author austi
 */
public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setMonsterTile(int mapNum, int ID, int x, int y){
        gp.monster[mapNum][ID].worldX = gp.tileSize * x;
        gp.monster[mapNum][ID].worldY = gp.tileSize * y;
    }

    public void setObjectTile(int mapNum, int ID, int x, int y){
        gp.obj[mapNum][ID].worldX = gp.tileSize * x;
        gp.obj[mapNum][ID].worldY = gp.tileSize * y;
    }

    public void setNPCTile(int mapNum, int ID, int x, int y){
        gp.monster[mapNum][ID].worldX = gp.tileSize * x;
        gp.monster[mapNum][ID].worldY = gp.tileSize * y;
    }

    public void setObject(){
        int i = 0;
        int mapNum = 0;
        gp.obj[mapNum][0] = new OBJ_Circus(gp);
        setObjectTile(mapNum, i, 21, 21);
        i++;
//
//        gp.obj[1] = new OBJ_Wheat(gp);
//        gp.obj[1].worldX = 20 *gp.tileSize;
//        gp.obj[1].worldY = 21 *gp.tileSize;
//
//        gp.obj[2] = new OBJ_Door(gp);
//        gp.obj[2].worldX = 26 * gp.tileSize;
//        gp.obj[2].worldY = 21 * gp.tileSize;
    }
    
    public void setNPC(){
        int i = 0;
        int mapNum = 0;
        // gp.npc[0] = new Andres(gp);
        // gp.npc[0].worldX = 22 * gp.tileSize;
        // gp.npc[0].worldY = 21 * gp.tileSize; 

        
    }
    public void setMonster(){
        int i = 0;
        int mapNum = 0;

        // Map 1
        gp.monster[mapNum][i] = new MON_Sigma(gp);
        setMonsterTile(mapNum, i, 23, 23);
        i++;

        // Map 2
        mapNum = 1;

        // Map 3
        mapNum = 2;

        // Dungeon 1
        mapNum = 3;
        gp.monster[mapNum][i] = new MON_JakOLantern(gp);
        setMonsterTile(mapNum, i, 23, 23);
        i++;
        gp.monster[mapNum][i] = new MON_KingJack(gp);
        setMonsterTile(mapNum, i, 22, 23);
        i++;
        gp.monster[mapNum][i] = new MON_PickleRick(gp);
        setMonsterTile(mapNum, i, 21, 23);
        i++;
        gp.monster[mapNum][i] = new MON_Mogger(gp);
        setMonsterTile(mapNum, i, 20, 23);
        i++;
        gp.monster[mapNum][i] = new MON_Mewer(gp);
        setMonsterTile(mapNum, i, 19, 23);
        i++;
    }
    public void setInteractiveTile(){
        int i = 0;
        int mapNum = 0;
        gp.iTile[mapNum][i] = new IT_ChopTree(gp,32,41);i++;

        //mapNum = mapIndex
    }
}
