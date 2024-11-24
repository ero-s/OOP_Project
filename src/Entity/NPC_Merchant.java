package Entity;

import object.CON_Cabbage;
import object.CON_Carrot;
import pkg2dgame.GamePanel;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {
        up1 = setup("/pics/player/Merchant/down1.png",gp.tileSize, gp.tileSize);
        up2 = setup("/pics/player/Merchant/down1.png",gp.tileSize, gp.tileSize);
        left1 = setup("/pics/player/Merchant/down1.png",gp.tileSize, gp.tileSize);
        left2 = setup("/pics/player/Merchant/down1.png",gp.tileSize, gp.tileSize);
        right1 = setup("/pics/player/Merchant/down1.png",gp.tileSize, gp.tileSize);
        right2 = setup("/pics/player/Merchant/down1.png",gp.tileSize, gp.tileSize);
        down1 = setup("/pics/player/Merchant/down1.png",gp.tileSize, gp.tileSize);
        down2 = setup("/pics/player/Merchant/down1.png",gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){
        dialogues[0] = "Greetings fellow traveler.\nI have some good stuff in store.\nCome take a look at my stash.";
    }

    public void setAction(){

    }
    public void speak(){
        //
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }

    public void setItems(){
        inventory.add(new CON_Cabbage(gp));
        inventory.add(new CON_Cabbage(gp));
        inventory.add(new CON_Cabbage(gp));
        inventory.add(new CON_Carrot(gp));
        inventory.add(new CON_Carrot(gp));
        inventory.add(new CON_Carrot(gp));
    }
}
