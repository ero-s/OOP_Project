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
        down1 = setup("/pics/player/Merchant/down1.png",gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){
        dialogues[0] = "Hi";
    }

    public void setAction(){

    }
    public void speak(){
        //
        super.speak();
        gp.gameState = gp.tradeState;

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
