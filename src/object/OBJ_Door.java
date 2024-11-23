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
public class OBJ_Door extends Entity{
    public OBJ_Door(GamePanel gp){
        super(gp);
        name = "Door";
        down1 = setup("/pics/objects/door.png",gp.tileSize, gp.tileSize);
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width  = 96;
        solidArea.height = 64;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        
    }
//    public void draw(Graphics2D g2, GamePanel gp) {
//        // Calculate the position relative to the player
//        int screenX = worldX - gp.player.worldX + gp.player.screenX;
//        int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//        // Draw the object itself
//        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//
//        // If the object has collision, draw its collision box
//        if (collision) {
//            g2.setColor(Color.red);
//            g2.setStroke(new java.awt.BasicStroke(2));  // Thicker stroke for visibility
//
//            // Draw the collision box (adjusted by solidArea position)
//            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
//        }
//    }
}
