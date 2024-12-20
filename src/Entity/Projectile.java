package Entity;

import pkg2dgame.GamePanel;

public class Projectile extends Entity{
    Entity user;
    public int projectileCooldown = 300; // Cooldown duration
    public int cooldownTimer = 0; // Timer to track cooldown
    public boolean used = false;
    public Projectile(GamePanel gp){
        super(gp);

    }
    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.cooldown = 5 * 60;
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;

        collisionOn = false;  // Reset collision flag at the beginning of each update

        // Check if the projectile collides with any solid tile before moving
        gp.cChecker.checkTile(this);  // Call the checkTile method to set collisionOn if there's a collision

        if (collisionOn) {
            // If a collision with a solid tile is detected, deactivate the projectile
            return;  // Stop further execution to avoid moving after hitting a solid tile
        }

        // Calculate the offset to center the upscaled projectile relative to the player
        int centerXOffset = xOffset;
        int centerYOffset = yOffset;
        // Adjust initial projectile position based on direction
        switch (direction) {
            case "up":
                this.worldX -= centerXOffset;
                this.worldY -= gp.tileSize + centerYOffset/2;
                break;
            case "down":
                this.worldX -= centerXOffset;
                this.worldY += gp.tileSize - centerYOffset;
                break;
            case "left":
                this.worldX -= gp.tileSize + centerXOffset/2;
                this.worldY -= centerYOffset;
                break;
            case "right":
                this.worldX += gp.tileSize/2 - centerXOffset;
                this.worldY -= centerYOffset;
                break;
        }
    }

    public void update() {
        collisionOn = false;  // Reset collision flag at the beginning of each update

        // Check for collisions with tiles
        gp.cChecker.checkTile(this);  // Set collisionOn if there's a collision

        if (collisionOn) {
            // Deactivate the projectile if a collision with a solid tile is detected
            alive = false;
            return;
        }

        // Only check for monsters if they exist in the game panel
        if (user == gp.player && gp.monster[1].length > 0) {  // Ensure monsters are present
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);

            // If a monster is hit, deal damage and deactivate the projectile
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, atkPower);
                generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
                alive = false;
                return;
            }
        }

        if (user != gp.player) {  // Ensure monsters are present
            boolean contactPlayer = gp.cChecker.checkPlayer(this);

            // If a monster is hit, deal damage and deactivate the projectile
            if (!gp.player.invincible && contactPlayer) {
                damagePlayer(atkPower);
                generateParticle(user.projectile, gp.player);
                alive = false;
                return;
            }
        }



        // Move the projectile based on direction
        switch (direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        // Decrease life over time
        life--;
        if (life <= 0) {
            alive = false;  // Deactivate after life expires
        }

        if (!alive) {
            return;
        }

        // Handle sprite animation
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }


    }

    public boolean haveResource(Entity user){
        boolean haveResource = false;
        return haveResource;
    }

    public void subtractResource(Entity user){ }


}
