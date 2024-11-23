package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class CON_Carrot extends Entity{

    GamePanel gp;
    public CON_Carrot(GamePanel gp){
        super(gp);

        this.gp = gp;
        type = type_consumable;
        name = "Carrot";
        value = 2;
        down1 = setup("/pics/objects/carrot_buff.png",gp.tileSize/2, gp.tileSize/2);
        description = "[" + name + "]\nrestores health \nby "+value+".";
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

    public void use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You ate the " + name + "\n"
                + "Your life has been recovered by " + value + ".";
        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player. maxLife;
        }
//        gp.playSE(value);
    }
}
