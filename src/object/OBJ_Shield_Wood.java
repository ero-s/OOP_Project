package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class OBJ_Shield_Wood extends Entity{
    public OBJ_Shield_Wood(GamePanel gp){
        super(gp);
        type = type_shield;
        name = "Wood Shield";
        down1 = setup("/pics/objects/shield_wood.png",gp.tileSize/2, gp.tileSize/2);
        defenseValue = 1;
        description = "[" + name + "]\nA Wooden Shield.";
    }
}
