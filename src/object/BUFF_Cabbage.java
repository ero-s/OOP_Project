package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class BUFF_Cabbage extends Entity {
    public BUFF_Cabbage(GamePanel gp){
        super(gp);

        name = "Cabbage";
        down1 = setup("/pics/objects/cabbage_buff.png",gp.tileSize/2, gp.tileSize/2);
        description = "[" + name + "]\n Increases Damage.";
        atkPower = 3;
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
