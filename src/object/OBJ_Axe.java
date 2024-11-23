package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class OBJ_Axe extends Entity{
    public OBJ_Axe(GamePanel gp){
        super(gp);

        type = type_axe;
        name = "Woodcutter's Axe";
        down1 = setup("/pics/objects/axe.png", gp.tileSize/2, gp.tileSize/2);
        description = "[Woodcutter's Axe]\nA bit rusty but still reliable.";

        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 06;
    }
}
