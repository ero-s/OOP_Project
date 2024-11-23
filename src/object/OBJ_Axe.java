package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class OBJ_Axe extends Entity{
    public OBJ_Axe(GamePanel gp){
        super(gp);
        type = type_axe;
        attackArea.height = 32;
        attackArea.width = 64;
        name = "Rusty Axe";
        down1 = setup("/pics/objects/axe.png", gp.tileSize/2, gp.tileSize/2);
        attackValue = 1;
        description = "[" + name + "]\na rusty axe used to \n clear vegetation.";
    }
}
