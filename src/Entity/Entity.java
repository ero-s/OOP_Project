package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
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
    public boolean onPath = false;

    // collision display
    public Rectangle solidArea = new Rectangle(64,64,64,64);
    public Rectangle mapTransArea = new Rectangle(0,0,0,0);
    public int xOffset;
    public int yOffset;
    public int solidAreaDefaultX = 64;
    public int solidAreaDefaultY = 64;
    public int mapTransAreaDefaultX = 0;
    public int mapTransAreaDefaultY = 0;

    // counter
    public int actionLockCounter = 0;
    public int spriteCounter = 0;
    public int invincibleCounter = 0;
    int deadCounter = 0;
    public int projectileCounter = 0;
    public int shotCounter = 0;
    int hpBarCounter = 0;
    public int cooldown = 0;

    //character attributes this
    public int maxLife;
    public int life;
    public String name;
    public int level, atkPower, defense, exp, nextLevelExp, coin, mana, useCost;
    public Projectile projectile;
    public Entity currentWeapon;
    public Entity currentShield;
    public int attack, defPower;

    // TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;

    //item attributes
    public ArrayList<Entity> inventory = new ArrayList<Entity>();
    public final int maxInventorySize = 20;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int value;
    public boolean stackable = false;
    public int amount = 1;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void damageReaction(){}
    public void setAction(){}
    public void use(Entity entity){}
    public void checkDrop(){}
    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX; // the dead monster's worldX
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
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
    public void checkCollision(){
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer){
            damagePlayer(atkPower);
        }
    }

    public Color getParticleColor(){
        Color color = null;
        return color;
    }

    public int getParticleSize(){
        int size = 0;
        return size;
    }

    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }

    public int getParticleMaxLife(){
        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed= generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp,generator, color , size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp,generator, color , size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp,generator, color , size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp,generator, color , size, speed, maxLife, 2, 1);

        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void update(){
        setAction();
        checkCollision();

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

                    hpBarCounter++;

                    if(hpBarCounter > 600){
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                }

                if(invincible){
                    hpBarOn = true;
                    hpBarCounter = 0;
                    changeAlpha(g2,0.4f);
                }

                // Run the death animation if the entity is in the process of dying
                if (!alive) {
                    deadAnimation(g2);
                }

                // Draw the character’s image
                g2.drawImage(image, screenX, screenY, null);

                // Draw the collision box
                g2.setColor(Color.red);
                int collisionBoxX = screenX + this.solidAreaDefaultX;
                int collisionBoxY = screenY + this.solidAreaDefaultY;
                g2.drawRect(collisionBoxX, collisionBoxY, this.solidArea.width, this.solidArea.height);

                // Reset opacity after each draw to avoid affecting other elements
                resetAlpha(g2);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entity entity = (Entity) obj;
        return name.equals(entity.name) && type == entity.type;
    }

    public void setCollisionArea(int x, int y, int width, int height, int defaultX, int defaultY){
        solidArea.x = x;
        solidArea.y = y;
        solidArea.width = width;
        solidArea.height = height;
        solidAreaDefaultX = defaultX;
        solidAreaDefaultY = defaultY;
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
        deadCounter++;
        int i = 5;
        if (dead) {
            // Blinking sequence: alternate alpha every 5 frames
            if (deadCounter <= i) changeAlpha(g2, 0f);
            else if (deadCounter > i && deadCounter <= i*2) changeAlpha(g2, 1f);
            else if (deadCounter > i*2 && deadCounter <= i*3)  changeAlpha(g2, 0f);
            else if (deadCounter > i*3 && deadCounter <= i*4)  changeAlpha(g2, 1f);
            else if (deadCounter > i*4 && deadCounter <= i*5)  changeAlpha(g2, 0f);
            else if (deadCounter > i*5 && deadCounter <= i*6)  changeAlpha(g2, 1f);
            else if (deadCounter > i*6 && deadCounter <= i*7)  changeAlpha(g2, 0f);
            else if (deadCounter > i*7 && deadCounter <= i*8)  changeAlpha(g2, 1f);

            // After the animation ends, stop drawing the entity and hide HP bar
            if (deadCounter >= 40) {
                dead = false;
                hpBarOn = false; // Hide HP bar
            }
        }
    }

    public void resetAlpha(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public static BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();

        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(Entity.class.getResourceAsStream(imagePath), "Image not found: " + imagePath));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Image path is incorrect or the image is missing: " + imagePath);
            e.printStackTrace();
        }

        return image;
    }
    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;
        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if(gp.pFinder.search()){
            System.out.println("Path is found");
            // next worldX and worldY
            int nextX = gp.pFinder.pathList.getFirst().col * gp.tileSize;
            int nextY = gp.pFinder.pathList.getFirst().row * gp.tileSize;

            //entity's solid area position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX & enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX & enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize){
                if(enLeftX > nextX){ direction = "left";
                }if(enLeftX < nextX) direction = "right";
            }
            else if(enTopY > nextY && enLeftX > nextX){
                direction = "up";
                checkCollision();
                if(collisionOn){
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                direction = "up";
                checkCollision();
                if(collisionOn){
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                direction = "down";
                checkCollision();
                if(collisionOn){
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                direction = "down";
                checkCollision();
                if(collisionOn){
                    direction = "right";
                }
            }

            //npc stops when reaching player
//            int nextCol = gp.pFinder.pathList.getFirst().col;
//            int nextRow = gp.pFinder.pathList.getFirst().row;
//            if(nextCol == goalCol && nextRow == goalRow){
//                onPath = false;
//            }
        }
    }
}
