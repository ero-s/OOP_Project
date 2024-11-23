/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dgame.monster;

import Entity.Entity;
import java.util.Random;
import pkg2dgame.GamePanel;

/**
 *
 * @author austi
 */
public class MON_KingJack extends Entity {
    GamePanel gp;
    public int invincibleCounter = 0;  // Counter to reset invincibility after a delay

    public MON_KingJack(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "KingJack";
        speed = 1;
        maxLife = 10;
        life = maxLife;
        invincible = false;  // Monster starts without invincibility
        type = 2;
        atkPower = 2;
        defense = 2;
        exp = 3;
        alive = true;

        solidArea.x = 16;
        solidArea.y = 32;
        solidArea.width = 64;
        solidArea.height = 64;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage() {
        up1 = setup("/pics/monsters/KingJack/up1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/pics/monsters/KingJack/up2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/pics/monsters/KingJack/left1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/pics/monsters/KingJack/left2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/pics/monsters/KingJack/right1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/pics/monsters/KingJack/right2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/pics/monsters/KingJack/down1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/pics/monsters/KingJack/down2.png", gp.tileSize, gp.tileSize);
    }

    @Override
    public void update() {
        // Handle invincibility
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
        gp.cChecker.checkPlayer(this);  // Check collision with the player

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
    }

    public void setAction() {
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

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}

