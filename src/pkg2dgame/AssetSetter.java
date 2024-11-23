/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dgame;

import object.*;
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

        gp.obj[mapNum][i] = new OBJ_Key(gp);
        setObjectTile(mapNum, i, 28, 39 );
        i++;

//        gp.obj[mapNum][i] = new CON_Carrot(gp);
//        setObjectTile(mapNum, i, 24, 39 );
//        i++;
//
//        gp.obj[mapNum][i] = new OBJ_Shield_Iron(gp);
//        setObjectTile(mapNum, i, 26, 39 );
//
//        gp.obj[mapNum][i] = new OBJ_Coin(gp);
//        setObjectTile(mapNum, i, 27, 39 );
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
        // Dungeon 1
        mapNum = 2;
        i = 0;

        gp.monster[2][0] = new MON_JakOLantern(gp);
        setMonsterTile(2, 0, 23, 23);
        i++;
        gp.monster[2][1] = new MON_KingJack(gp);
        setMonsterTile(2, 1, 22, 23);
        i++;
        gp.monster[2][2] = new MON_PickleRick(gp);
        setMonsterTile(2, 2, 21, 23);
        i++;
        gp.monster[2][3] = new MON_Mogger(gp);
        setMonsterTile(2, 3, 20, 23);
        i++;
        gp.monster[2][4] = new MON_Mewer(gp);
        setMonsterTile(2, 4, 19, 23);
        i++;
        gp.monster[2][5] = new MON_Jill(gp);
        setMonsterTile(2, 5, 19, 23);
        i++;


    }
    public void setInteractiveTile(){
        int i = 0;
        int mapNum = 0;
        gp.iTile[mapNum][i] = new IT_ChopTree(gp,32,41);i++;

        //mapNum = mapIndex
    }
}
