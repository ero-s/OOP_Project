package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class OBJ_Sword_Normal extends Entity{
    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);
        type = type_sword;
        attackArea.height = 32;
        attackArea.width = 64;
        name = "Normal Sword";
        down1 = setup("/pics/objects/Hammer.png", gp.tileSize/2, gp.tileSize/2);
        attackValue = 1;
        description = "[" + name + "]\nA Normal Sword.";
    }   
}
