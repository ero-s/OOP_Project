package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class CON_Carrot extends Entity{
    public CON_Carrot(GamePanel gp){
        super(gp);

        name = "Carrot";
        down1 = setup("/pics/objects/carrot_buff.png",gp.tileSize/2, gp.tileSize/2);
        description = "[" + name + "]\n Increases Health.";
        defenseValue = 3;
        collision = true;
        solidArea.x = 64;
        solidArea.y = 64;
        solidArea.width = 32;
        solidArea.height = 16;
        xOffset = 0;
        yOffset = 0;
        setCollisionArea(solidArea.x, solidArea.y, solidArea.width, solidArea.height, 0, 16);
    }
}
