package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import pkg2dgame.GamePanel;
import pkg2dgame.UtilityTool;

/**
 *
 * @author austi
 */
public class Entity {
    GamePanel gp;
    public BufferedImage image, image2, image3;
    public boolean collision = false;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    String dialogues[] = new String[20];
    public boolean invincible = false;

    // state
    public int worldX, worldY, speed;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dead = false;
    boolean hpBarOn = false;

    // collision display
    public Rectangle solidArea = new Rectangle(32,32,32,32);
    public int xOffset;
    public int yOffset;
    public int solidAreaDefaultX = 32;
    public int solidAreaDefaultY = 32;

    // counter
    public int actionLockCounter = 0;
    public int spriteCounter = 0;
    public int invincibleCounter = 0;
    int deadCounter = 0;
    public int projectileCounter = 0;
    public int shotCounter = 0;
    int hpBarCounter = 0;

    //character attributes this
    public int maxLife;
    public int life;
    public String name;
    public int type; //type of entity 1: npc, 2:monster
    public int level, atkPower, defense, exp, nextLevelExp, coin, mana, useCost;
    public Projectile projectile;


    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void damageReaction(){}
    public void setAction(){}
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer){
            damagePlayer(atkPower);
        }
        String dialogues[] = new String[20];
        int dialogueIndex = 0;

        if(!collisionOn){
                switch(direction){
                    case "up":{
                        worldY -= speed;
                        break;
                    }
                    case "down":{
                        worldY += speed;
                        break;
                    }
                    case "left":{
                        worldX -= speed;
                        break;
                    }
                    case "right":{
                        worldX += speed;
                        break;
                    }

                }
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
    }
    public void damagePlayer(int atkPower){
        if(!gp.player.invincible){
            //damage player
            int damage = atkPower - gp.player.defense;
            if(damage < 0){
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void draw(Graphics2D g2) {
        // Reset to fully opaque by default for each draw call
        resetAlpha(g2);

        // Draw entity if alive or in the process of dying (dead animation in progress)
        if (alive || dead) {
            BufferedImage image = null;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Ensure the entity is within view of the camera before drawing
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                // Choose the correct sprite based on direction and spriteNum
                switch (direction) {
                    case "up": image = (spriteNum == 1) ? up1 : up2; break;
                    case "down": image = (spriteNum == 1) ? down1 : down2; break;
                    case "left": image = (spriteNum == 1) ? left1 : left2; break;
                    case "right": image = (spriteNum == 1) ? right1 : right2; break;
                }

                // Draw the HP bar if the entity is alive and hpBarOn is true
                if (type == 2 && hpBarOn && alive) {
                    double oneScale = (double) gp.tileSize / maxLife;
                    double hpBarValue = oneScale * life;
                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

                    g2.setColor(new Color(255, 0, 40));
                    g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
                }

                // Run the death animation if the entity is in the process of dying
//                if (!alive) {
//                    deadAnimation(g2);
//                }

                // Draw the character’s image
                g2.drawImage(image, screenX, screenY, null);

                // Draw the collision box
                g2.setColor(Color.red);
                int collisionBoxX = screenX + solidArea.x;
                int collisionBoxY = screenY + solidArea.y;
                g2.drawRect(collisionBoxX, collisionBoxY, solidArea.width, solidArea.height);

                // Reset opacity after each draw to avoid affecting other elements
                resetAlpha(g2);
            }
        }
    }

//    public boolean isDying = false;

    public void triggerDeathAnimation() {
        // This method will be called when the entity "dies" to start the animation
        deadCounter = 0;
        dead = true;
        alive = false; // Sets alive to false, but keeps the entity visible for the animation
    }

    public void deadAnimation(Graphics2D g2) {
        // Ensure animation only plays if isDying is true
        if (dead) {
            // Blinking sequence: alternate alpha every 5 frames
            if (deadCounter < 5) changeAlpha(g2, 0f);
            else if (deadCounter < 10) changeAlpha(g2, 1f);
            else if (deadCounter < 15) changeAlpha(g2, 0f);
            else if (deadCounter < 20) changeAlpha(g2, 1f);
            else if (deadCounter < 25) changeAlpha(g2, 0f);
            else if (deadCounter < 30) changeAlpha(g2, 1f);
            else if (deadCounter < 35) changeAlpha(g2, 0f);
            else if (deadCounter < 40) changeAlpha(g2, 1f);

            // After the animation ends, stop drawing the entity and hide HP bar
            if (deadCounter >= 40) {
                dead = false;
                hpBarOn = false; // Hide HP bar
            } else {
                deadCounter++; // Increment counter if animation still playing
            }
        }
    }

    public void resetAlpha(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }


    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();

        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath), "Image not found: " + imagePath));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Image path is incorrect or the image is missing: " + imagePath);
            e.printStackTrace();
        }

        return image;
    }



}
