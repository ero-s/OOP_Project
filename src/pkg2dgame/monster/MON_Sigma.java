/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dgame.monster;

import Entity.Entity;
import java.util.Random;

import object.CON_Cabbage;
import object.CON_Carrot;
import object.OBJ_Coin;
import pkg2dgame.GamePanel;

/**
 *
 * @author austi
 */
public class MON_Sigma extends Entity implements MON_Interface{
    GamePanel gp;
    public int invincibleCounter = 0;  // Counter to reset invincibility after a delay

    public MON_Sigma(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Sigma";
        maxLife = 500;
        defaultSpeed = 1;
        speed = defaultSpeed;
        life = maxLife;
        invincible = false;  // Monster starts without invincibility
        type = type_monster;
        atkPower = 2;
        defense = 2;
        exp = 3;
        alive = true;
        collision = true;
        xOffset = 0;
        yOffset = 0;

        solidArea.x = 16;
        solidArea.y = 32;
        solidArea.width = 64;
        solidArea.height = 64;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage() {
        up1 = setup("/pics/monsters/Sigma/up1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/pics/monsters/Sigma/up2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/pics/monsters/Sigma/left1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/pics/monsters/Sigma/left2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/pics/monsters/Sigma/right1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/pics/monsters/Sigma/right2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/pics/monsters/Sigma/down1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/pics/monsters/Sigma/down2.png", gp.tileSize, gp.tileSize);
    }

    @Override
    public void update() {
        // Handle invincibility
        if(!knockback){
            speed = defaultSpeed;
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {  // Reset invincibility after 60 frames (1 second)
                invincible = false;
                invincibleCounter = 0;
            }
        }

        // Set the monster's action (movement direction)
        setAction();

        // Check for collisions with tiles, NPCs, and the player
        collisionOn = false;
        gp.cChecker.checkTile(this);  // Check tile collision
        gp.cChecker.checkEntity(this, gp.npc);  // Check NPC collision

        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer){
            damagePlayer(atkPower);
        }

        // Only move if there is no collision
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        // Handle sprite animation switching
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if(!onPath && tileDistance < 5){
            int i = new Random().nextInt(100)+1;
            if(i > 50){
                onPath = true;
            }
        }
        if(onPath && tileDistance > 10){
            onPath = false;
        }
    }


    public void setAction() {
        if (onPath) {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

//            //set goal Position
//            int goalCol = 4;
//            int goalRow = 11;

            //set to follow player
            searchPath(goalCol, goalRow);



        }
        else {
            // Decide movement direction every 120 frames
            actionLockCounter++;
            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                } else if (i > 25 && i <= 50) {
                    direction = "down";
                } else if (i > 50 && i <= 75) {
                    direction = "left";
                } else {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
    }

    public void checkDrop() {
        // CAST A DIE
        int i = new Random().nextInt(100) + 1;
        // SET THE MONSTER DROP
        if (i <= 50) { // Includes '50'
            dropItem(new OBJ_Coin(gp));
        } else { // Includes '51 to 100'
            dropItem(new CON_Carrot(gp));
            dropItem(new CON_Cabbage(gp));
        }

    }
}

