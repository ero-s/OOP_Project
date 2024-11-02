/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import Entity.Entity;
import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgame.GamePanel;

/**
 *
 * @author austi
 */
public class OBJ_Wheat extends Entity{
    public OBJ_Wheat(GamePanel gp){
        super(gp);
        name = "Wheat";
        down1 = setup("/pics/objects/wheat.png",gp.tileSize, gp.tileSize);
        collision = true;
    }
}
