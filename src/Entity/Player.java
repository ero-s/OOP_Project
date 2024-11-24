package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import object.*;
import pkg2dgame.GamePanel;
import pkg2dgame.KeyHandler;

public class Player extends Entity {
    public int maxMana;
    public boolean attackCanceled;
    KeyHandler keyH;
    
    public final int screenX, screenY;
    private int projectileCooldown = 300; // Cooldown duration
    private int cooldownTimer = 0; // Timer to track cooldown
    private boolean cooldownMessageShown = false;
    int standCounter = 0;
    public boolean projectileUsed = false;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        
        screenX = gp.screenWidth /2 - (gp.tileSize/2);
        screenY = gp.screenHeight /2 - (gp.tileSize/2);

        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidArea.x = 36;
        solidArea.y = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 12;

        mapTransArea.x = -8;
        mapTransArea.y = -8;
        mapTransAreaDefaultX = mapTransArea.x;
        mapTransAreaDefaultY = mapTransArea.y;
        mapTransArea.width = 120;
        mapTransArea.height = 120;

        // can now be changed with items such as current sword
//        attackArea.width = 32;
//        attackArea.height = 32;
        
        setDefaultValues();
        setItems();
        getPlayerImage();
        getPlayerAttackImage();


    }

    public int getCurrentWeaponSlot() {
        int currentWeaponSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == getCurrentWeapon()) {
                currentWeaponSlot = i;
            }
        }

        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot() {
        int currentShieldSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == getCurrentShield()) {
                currentShieldSlot = i;
            }
        }

        return currentShieldSlot;
    }

    public void setDefaultValues() {
        gp.currentMap = 0;
        worldX = gp.tileSize * 36;
        worldY = gp.tileSize * 41;
        speed = 6;
        direction = "down";
        
        //player status
        maxLife = 6;
        life = maxLife;
        level = 1;
        atkPower = 1;
        defense = 1;
        exp = 0;
        projectile = new OBJ_Projectile(gp);
        nextLevelExp = 5;
        coin = 0;
        setCurrentWeapon(new OBJ_Sword_Normal(gp));
        setCurrentShield(new OBJ_Shield_Wood(gp));
        attack = getAttack();
        defPower = getDefense();
        maxMana = 4;
        mana = maxMana;
    }
    public void setItems(){
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Axe(gp));
    }
    public int getAttack(){
        attackArea = getCurrentWeapon().attackArea;
        return attack = atkPower * getCurrentWeapon().attackValue;
    }
    public int getDefense(){
        return defPower = defense * getCurrentShield().defenseValue;
    }
    public void setDefaultPositions(){
        gp.currentMap = 0;
        worldX = gp.tileSize * 29;
        worldY = gp.tileSize * 41;
        direction = "down";
    }
    public void restoreLife(){
        life = maxLife;
        invincible = false;
    }
    public void getPlayerImage() {

        up1 = setup("/pics/player/Hakobe/Walk back-1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/pics/player/Hakobe/Walk back-2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/pics/player/Hakobe/Walk left-1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/pics/player/Hakobe/Walk left-2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/pics/player/Hakobe/Walk right-1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/pics/player/Hakobe/Walk right-2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/pics/player/Hakobe/Walk front-1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/pics/player/Hakobe/Walk front-2.png", gp.tileSize, gp.tileSize);
    }

    public Player(GamePanel gp, int maxMana, boolean attackCanceled, KeyHandler keyH, int screenX, int screenY, int projectileCooldown, int cooldownTimer, boolean cooldownMessageShown, int standCounter, ArrayList<Entity> inventory, boolean projectileUsed, BufferedImage up1, BufferedImage up2, BufferedImage down1, BufferedImage down2, BufferedImage left1, BufferedImage left2, BufferedImage right1, BufferedImage right2) {
        super(gp);
        this.maxMana = maxMana;
        this.attackCanceled = attackCanceled;
        this.keyH = keyH;
        this.screenX = screenX;
        this.screenY = screenY;
        this.projectileCooldown = projectileCooldown;
        this.cooldownTimer = cooldownTimer;
        this.cooldownMessageShown = cooldownMessageShown;
        this.standCounter = standCounter;
        this.inventory = inventory;
        this.projectileUsed = projectileUsed;
        this.up1 = up1;
        this.up2 = up2;
        this.down1 = down1;
        this.down2 = down2;
        this.left1 = left1;
        this.left2 = left2;
        this.right1 = right1;
        this.right2 = right2;
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/pics/player/AttackHakobe/up2.png", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/pics/player/AttackHakobe/up1.png", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/pics/player/AttackHakobe/left1.png", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/pics/player/AttackHakobe/left2.png", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/pics/player/AttackHakobe/right1.png", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/pics/player/AttackHakobe/right2.png", gp.tileSize*2, gp.tileSize);
        attackDown1 = setup("/pics/player/AttackHakobe/down1.png", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/pics/player/AttackHakobe/down2.png", gp.tileSize, gp.tileSize*2);
    }
    public void update() {
        if(keyH.jPressed) {//attack control
            attack();
        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){
            if (keyH.upPressed) {
                direction = "up";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            }
            else if (keyH.downPressed) {
                direction = "down"; // Fix: should set direction to "down"
            }
            else if (keyH.rightPressed) {
                direction = "right";
            }

            //checks tile collsion
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check obj collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(gp.currentMap, objIndex);

            //check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //check iTile collision
            gp.cChecker.checkEntity(this, gp.iTile);

            //check event
            gp.eHandler.checkEvent();

            //if collision is false, player can move
            if(!collisionOn && !keyH.enterPressed){
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
            gp.keyH.enterPressed = false;

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

        if(gp.keyH.shotkeyPressed && !projectile.alive && shotCounter == 30 && projectile.haveResource(this)){
            //sets position, direction and user
            projectile.set(worldX, worldY, direction, true, this);

            projectile.subtractResource(this);

            //add it to the list
            gp.projectileList.add(projectile);

            shotCounter = 0;
            // gp.playSe(10);
        }

        //invincible time for player
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotCounter < 30){
            shotCounter++;
        }
        if(life <= 0){
            gp.gameState = gp.gameOverState;
        }
    }
    public void contactMonster(int i){
        if(i != 999){
            if(!invincible && !gp.monster[gp.currentMap][i].dead){
                int damage = gp.monster[gp.currentMap][i].atkPower - defense;
                if(damage < 0){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i, int atkPower) {
        if (i != 999) {
            System.out.println("Monster hit detected at index: " + i);
            System.out.println("Monster life before damage: " + gp.monster[gp.currentMap][i].life);
            if (!gp.monster[gp.currentMap][i].invincible && !gp.monster[gp.currentMap][i].dead) {
                int damage = atkPower -gp.monster[gp.currentMap][i].defense;
                if(damage < 0){
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.showMessage(damage + " damage!");
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();
                System.out.println("Monster life after damage: " + gp.monster[gp.currentMap][i].life);

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.player.exp += gp.monster[gp.currentMap][i].exp;
                    gp.ui.showMessage(gp.monster[gp.currentMap][i].name + " killed!");
                    gp.ui.showMessage(gp.monster[gp.currentMap][i].exp + " exp gained!");
                    gp.monster[gp.currentMap][i].alive = false;
                    gp.monster[gp.currentMap][i].dead = true;
                    System.out.println("Monster defeated!");
                }
            }
        } else {
            System.out.println("Missed the monster (no collision)");
        }
    }

    public void damageInteractiveTile(int i){
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false){ //
            gp.iTile[gp.currentMap][i].playSE();

            // generate particle
            generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);
            // for tile needing certain amount of hits to destroy
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            if(gp.iTile[gp.currentMap][i].life == 0){
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
        }
    }

    public void checkLevelUp(){
        if(exp >= nextLevelExp){
            level++;
            nextLevelExp *= 2;
            maxLife += 2;
            maxMana += 2;
            atkPower +=1;
            defense +=1;
            life = maxLife;
            mana = maxMana;

            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level "+level+" now!\n" +"You feel stronger!";
        }
    }
    public void pickUpObject(int mapNum, int i){
        if (i != 999) {
            if(gp.obj[mapNum][i].type == type_pickupOnly){
                gp.obj[mapNum][i].use(this);
                gp.obj[mapNum][i] = null;
            }
            else{
                String text;
                if (canObtainItem(gp.obj[gp.currentMap][i])) {
                    gp.playSE(1);
                    text = "Got a " + gp.obj[mapNum][i].name + "!";
                } else {
                    text = "You cannot carry any more!";
                }
                gp.ui.showMessage(text);
                gp.obj[mapNum][i] = null;
            }
        }
    }
    public void attack() {
        spriteCounter++;

        // Display first attack frame
        if (spriteCounter <= 10) {
            spriteNum = 1;
        }

        // Display second attack frame
        else if (spriteCounter <= 30) {
            spriteNum = 2;

            // Save current player collision area for attack detection
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's world area for attack
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            // Attack area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, atkPower);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            // Restore original position
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        // Reset after second frame
        if (spriteCounter > 30) {
            spriteNum = 1;
            spriteCounter = 0;
            gp.keyH.jPressed = false; // Reset attack key press
        }
    }
    public void interactNPC(int i){
        if(gp.keyH.enterPressed){
            if(i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }
    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                setCurrentWeapon(selectedItem);
                attack = getAttack();
            }

            if (selectedItem.type == type_shield) {
                setCurrentShield(selectedItem);
                defense = getDefense();
            }

            if (selectedItem.type == type_consumable) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }
    public int searchItemInInventory(String itemName) {
        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }

        return itemIndex;
    }
    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;

        // CHECK IF STACKABLE
        if (item.stackable) {
            int index = searchItemInInventory(item.name);
            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            } else { // New item so need to check vacancy
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        } else { // NOT STACKABLE so check vacancy
            if (inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if(keyH.jPressed){
                    tempScreenY -= gp.tileSize;
                    if(spriteNum == 1){image = attackUp1;}
                    if(spriteNum == 2){image = attackUp2;}
                }
                if(!keyH.jPressed){
                    if(spriteNum == 1){image = up1;}
                    if(spriteNum == 2){image = up2;}
                }
                break;
            case "down":
                if(keyH.jPressed){
                    if(spriteNum == 1){image = attackDown1;}
                    if(spriteNum == 2){image = attackDown2;}
                }
                if(!keyH.jPressed){
                    if(spriteNum == 1){image =down1;}
                    if(spriteNum == 2){image = down2;}
                }
                break;
            case "left":
                if(keyH.jPressed){
                    tempScreenX -= gp.tileSize;
                    if(spriteNum == 1){image = attackLeft1;}
                    if(spriteNum == 2){image = attackLeft2;}
                }
                if(!keyH.jPressed){
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                }
                break;
            case "right":
                if(keyH.jPressed){
                    if(spriteNum == 1){image = attackRight1;}
                    if(spriteNum == 2){image = attackRight2;}
                }
                if(!keyH.jPressed){
                    if(spriteNum == 1){image = right1;}
                    if(spriteNum == 2){image = right2;}
                }
                break;
        }
        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        g2.setColor(Color.green);
        g2.drawRect(screenX + mapTransArea.x, screenY + mapTransArea.y, mapTransArea.width, mapTransArea.height);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        System.out.println("WorldX: "+worldX/gp.tileSize+" WorldY: "+worldY/gp.tileSize);
//use to debug for damage intake
//        g2.setFont(new Font("Arial",Font.PLAIN, 26));
//        g2.setColor(Color.red);
//        g2.drawString("Invincible: "+invincibleCounter, 10, 400);



    }
}


