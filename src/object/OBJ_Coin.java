package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

import java.awt.image.BufferedImage;

public class OBJ_Coin extends Entity {
    GamePanel gp;

    public OBJ_Coin(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        value = 1;
        name = "Coin";
        down1 = setup("/pics/objects/coin.png",gp.tileSize/2, gp.tileSize/2);
        collision = true;
        solidArea.x = 64;
        solidArea.y = 64;
        solidArea.width = 32;
        solidArea.height = 16;
        xOffset = 0;
        yOffset = 0;
        setCollisionArea(solidArea.x, solidArea.y, solidArea.width, solidArea.height, 0, 16);
    }

    public void use(Entity entity){
        gp.playSE(1);
        gp.ui.showMessage("Coin + "+value);
        gp.player.coin += value;
    }
}
