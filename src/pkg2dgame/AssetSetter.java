/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dgame;

import Entity.Andres;
import Entity.NPC_Khai;
import Entity.NPC_Pugtato;
import object.OBJ_Circus;
import object.OBJ_Key;
import object.OBJ_Wheat;
import object.OBJ_Door;
import pkg2dgame.monster.MON_Sigma;
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
    
    public void setObject(){
        gp.obj[0] = new OBJ_Circus(gp);
        gp.obj[0].worldX = 21 * gp.tileSize;
        gp.obj[0].worldY = 21* gp.tileSize;
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
//        gp.npc[0] = new Andres(gp);
//        gp.npc[0].worldX = 22 * gp.tileSize;
//        gp.npc[0].worldY = 21 * gp.tileSize;

        
    }
    public void setMonster(){
        int i = 0;
//        gp.monster[i] = new MON_Sigma(gp);
//        gp.monster[i].worldX = gp.tileSize * 23;
//        gp.monster[i].worldY = gp.tileSize * 23;
//        i++


    }
    public void setInteractiveTile(){
        int i = 0;
        gp.iTile[i] = new IT_ChopTree(gp,15,15);i++;
    }
}
