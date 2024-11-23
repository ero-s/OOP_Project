package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class OBJ_Shield_Iron extends Entity{
    public OBJ_Shield_Iron(GamePanel gp){
        super(gp);
        type = type_shield;
        name = "Iron Shield";
        down1 = setup("/pics/objects/shield_iron.png",gp.tileSize/2, gp.tileSize/2);
        defenseValue = 3;
        solidArea.x = 64;
        solidArea.y = 64;
        solidArea.width = 32;
        solidArea.height = 16;
        xOffset = 0;
        yOffset = 0;
        setCollisionArea(solidArea.x, solidArea.y, solidArea.width, solidArea.height, 0, 16);
        collision = true;
        description = "[" + name + "]\nAn Iron Shield.";
    }
}
