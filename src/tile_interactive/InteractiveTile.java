package tile_interactive;

import Entity.Entity;
import pkg2dgame.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InteractiveTile extends Entity {

    GamePanel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void playSE(){

    }

    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = null;
        return tile;
    }

    public void update() {
        // Any update logic specific to InteractiveTile can be added here

        // if tile needs to sustain extra hits
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 20){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }


    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Ensure the entity is within view of the camera before drawing
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(down1, screenX, screenY, null);
        }
    }
}

