package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class CON_Cabbage extends Entity {

    GamePanel gp;
    public CON_Cabbage(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Cabbage";
        value = 2;
        type = type_consumable;
        down1 = setup("/pics/objects/cabbage_buff.png",gp.tileSize/2, gp.tileSize/2);
        description = "[" + name + "]\n restores "+value+" mana.";
        collision = true;
        solidArea.x = 64;
        solidArea.y = 64;
        solidArea.width = 32;
        solidArea.height = 16;
        xOffset = 0;
        yOffset = 0;
        stackable = true;
        setCollisionArea(solidArea.x, solidArea.y, solidArea.width, solidArea.height, 0, 16);
    }

    public void use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You ate the " + name + "\n"
                + "Your mana has been recovered by " + value + ".";
        entity.mana += value;
        if(gp.player.mana > gp.player.maxMana){
            gp.player.mana = gp.player. maxMana;
        }
//        gp.playSE(value);
    }
}
