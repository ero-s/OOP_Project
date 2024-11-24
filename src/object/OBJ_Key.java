/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import Entity.Entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgame.GamePanel;

/**
 *
 * @author austi
 */
public class OBJ_Key extends Entity{
    public OBJ_Key(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setup("/pics/objects/key.png",gp.tileSize/2, gp.tileSize/2);
        solidArea.x = 64;
        solidArea.y = 64;
        solidArea.width = 32;
        solidArea.height = 16;
        xOffset = 0;
        yOffset = 0;
        setCollisionArea(solidArea.x, solidArea.y, solidArea.width, solidArea.height, 0, 16);
        collision = true;
        stackable = true;
        description = "[" + name + "]\nIt opens a door.";
    }
    
    public void draw(Graphics2D g2, GamePanel gp) {
        // Calculate the position relative to the player
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Draw the object itself
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // If the object has collision, draw its collision box
        if (collision) {
            g2.setColor(Color.red);
            g2.setStroke(new java.awt.BasicStroke(2));  // Thicker stroke for visibility

            // Draw the collision box (adjusted by solidArea position)
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}
