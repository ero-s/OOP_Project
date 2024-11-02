/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dgame;

import Entity.Entity;

/**
 *
 * @author austi
 */
public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    
   public void checkTile(Entity entity) {
        // Adjusted collision box to shrink upwards
        int adjustedSolidAreaHeight = entity.solidArea.height-10 ; // Shrink height upwards by 10 units
        int adjustedSolidAreaWidth = entity.solidArea.width-10;   // Shrink width by 10 units

        // Adjusted collision box coordinates for left, right, top, and bottom boundaries
        int entityLeftWorldX = entity.worldX + entity.solidArea.x ;  // Shrink left and right collision area inward by 5 units
        int entityRightWorldX = entity.worldX + entity.solidArea.x + adjustedSolidAreaWidth;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + adjustedSolidAreaHeight;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;

        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        

        int tileNum1, tileNum2;
        
        

        switch (entity.direction) {
            case "up": {
                // Check upward collision
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
            case "down": {
                // Check downward collision using the adjusted bottom boundary
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
            case "left": {
                // Check leftward collision using the adjusted left boundary
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
            case "right": {
                // Check rightward collision using the adjusted right boundary
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
        }
    }

   
   public int checkObject(Entity entity, boolean player) {
    int index = 999; // Default value to represent no collision

    // Loop through all objects in the game
    for (int i = 0; i < gp.obj.length; i++) {
        if (gp.obj[i] != null) {
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;
            
            gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
            gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

            // Adjust the entity's solidArea based on its direction
            switch (entity.direction) {
                case "up": {
                    entity.solidArea.y -= entity.speed;break;}
                case "down" :{
                    entity.solidArea.y += entity.speed;break;}
                case "left":{
                    entity.solidArea.x -= entity.speed;break;}
                case "right" :{
                    entity.solidArea.x += entity.speed;break;}
            }
            if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                if(gp.obj[i].collision == true){
                entity.collisionOn = true;
                }
                if(player == true){
                index = i;
                }
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
            gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index; // Return the index of the object involved in collision, or 999 if no collision
    } 
   
   public int checkEntity(Entity entity, Entity[] target){
       int index = 999; // Default value to represent no collision

    // Loop through all objects in the game
    for (int i = 0; i < target.length; i++) {
        if (target[i] != null) {
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;
            
            target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
            target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

            // Adjust the entity's solidArea based on its direction
            switch (entity.direction) {
                case "up":{
                    entity.solidArea.y -= entity.speed;
                    break;
                }
                case "down":{
                    entity.solidArea.y += entity.speed;
                    break;
                }
                case "left":{
                    entity.solidArea.x -= entity.speed;
                    break;
                }
                case "right":{
                    entity.solidArea.x += entity.speed;
                    break;
                    }
                }
            if(entity.solidArea.intersects(target[i].solidArea)){
                if(target[i] != entity){
                    entity.collisionOn = true;
                    index = i;
                } 
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            target[i].solidArea.x = target[i].solidAreaDefaultX;
            target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
   }
   
   public boolean checkPlayer(Entity entity){
        boolean contactPlayer = false;
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
            
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

            // Adjust the entity's solidArea based on its direction
        switch (entity.direction) {
            case "up" -> {
                entity.solidArea.y -= entity.speed; break;
            }
            case "down" -> {
                entity.solidArea.y += entity.speed;break;
                }
            case "left" -> {
                entity.solidArea.x -= entity.speed;break;
            }
            case "right" -> {
                entity.solidArea.x += entity.speed;break;
                }
           }
            
            if(entity.solidArea.intersects(gp.player.solidArea)){
                entity.collisionOn = true;
                contactPlayer = true;
            }
                    
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            
            return contactPlayer;
   }
}
