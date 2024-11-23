/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import Entity.Entity;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgame.GamePanel;

/**
 *
 * @author austi
 */
public class OBJ_Heart extends Entity {
    public OBJ_Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        image = setup("/pics/UIThings/heart/fullHeart.png",gp.tileSize/2, gp.tileSize/2);
        image2 = setup("/pics/UIThings/heart/halfHeart.png",gp.tileSize/2, gp.tileSize/2);
        image3 = setup("/pics/UIThings/heart/emptyHeart.png",gp.tileSize/2, gp.tileSize/2);

    }
    
}
