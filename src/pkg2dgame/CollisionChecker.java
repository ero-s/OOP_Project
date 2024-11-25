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
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1] != null || gp.tileM.tile[tileNum2] != null){
                    if (gp.tileM.tile[tileNum1].collision &&  gp.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                    }
                }
                break;
            }
            case "down": {
                // Check downward collision using the adjusted bottom boundary
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum2] != null){
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                    }
                }
                break;
            }
            case "left": {
                // Check leftward collision using the adjusted left boundary
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum2] != null){
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                    }
                }
                break;
            }
            case "right": {
                // Check rightward collision using the adjusted right boundary
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum2] != null){
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                    }
                }
                break;
            }
        }

    }
    public int checkObject(Entity entity, boolean player) {
        int index = 999; // Default value to represent no collision

         // Loop through all objects in the game
        if(gp.obj[1] != null) {
            for (int i = 0; i < gp.obj[1].length; i++) {
                if (gp.obj[gp.currentMap][i] != null) {
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;

                    gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                    gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                    // Adjust the entity's solidArea based on its direction
                    switch (entity.direction) {
                        case "up": {
                            entity.solidArea.y -= entity.speed;
                            break;
                        }
                        case "down": {
                            entity.solidArea.y += entity.speed;
                            break;
                        }
                        case "left": {
                            entity.solidArea.x -= entity.speed;
                            break;
                        }
                        case "right": {
                            entity.solidArea.x += entity.speed;
                            break;
                        }
                    }
                    if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                        if (gp.obj[gp.currentMap][i].collision) {
                            entity.collisionOn = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                    gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
                }
            }
        }
        return index;
    }


    public int checkEntity(Entity entity, Entity[][] target)
    {
        int index = 999;   // no collision returns 999;
        //Use a temporal direction when it's being knockbacked
        String direction = entity.direction;
        for(int i = 0;i < target[1].length; i++)
        {
            if(target[gp.currentMap][i] != null)
            {
                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get the object's solid area position
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;       //entity's solid area and obj's solid area is different.
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch (direction)
                {
                    case "up" :
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down" :
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left" :
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right" :
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea))
                {
                    if(target[gp.currentMap][i] != entity) // avoid entity includes itself as a collision target
                    {
                        entity.collisionOn = true;
                        index = i;   // Non-player characters cannot pickup objects.
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX; //Reset
                entity.solidArea.y = entity.solidAreaDefaultY;

                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;     //Reset
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity)
    {
        boolean contactPlayer = false;
        // get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // get the object's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;       //entity's solid area and obj's solid area is different.
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction)
        {
            case "up" :
                entity.solidArea.y -= entity.speed;
                break;
            case "down" :
                entity.solidArea.y += entity.speed;
                break;
            case "left" :
                entity.solidArea.x -= entity.speed;
                break;
            case "right" :
                entity.solidArea.x += entity.speed;
                break;
        }
        if(entity.solidArea.intersects(gp.player.solidArea))
        {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX; ////Reset
        entity.solidArea.y = entity.solidAreaDefaultY;

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;     ////Reset
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
