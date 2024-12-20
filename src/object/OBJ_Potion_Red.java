package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class OBJ_Potion_Red extends Entity{
    GamePanel gp;
    int value = 5;
    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Red Potion";
        down1 = setup("/pics/objects/potion_red.png",gp.tileSize/2,gp.tileSize/2);
        description = "[" + name + "]\nHeals your life by " + value + ".";
        stackable = true;
    }

    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drank the " + name + "!\n" + "Your life has been recovered by " + value + ".";
        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
    }
}
