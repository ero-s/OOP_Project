/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;
import java.util.Random;
import pkg2dgame.GamePanel;

/**
 *
 * @author austi
 */
public class NPC_Pugtato extends Entity{
    public NPC_Pugtato(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }
    
     public void getImage() {
        up1 = setup("/pics/player/Pugtato/up1.png",gp.tileSize, gp.tileSize);
        up2 = setup("/pics/player/Pugtato/up2.png",gp.tileSize, gp.tileSize);
        left1 = setup("/pics/player/Pugtato/left1.png",gp.tileSize, gp.tileSize);
        left2 = setup("/pics/player/Pugtato/left2.png",gp.tileSize, gp.tileSize);
        right1 = setup("/pics/player/Pugtato/right1.png",gp.tileSize, gp.tileSize);
        right2 = setup("/pics/player/Pugtato/right2.png",gp.tileSize, gp.tileSize);
        down1 = setup("/pics/player/Pugtato/down1.png",gp.tileSize, gp.tileSize);
        down2 = setup("/pics/player/Pugtato/down2.png",gp.tileSize, gp.tileSize);
    }
     
    public void setDialogue(){
        dialogues[0] = "sup btch";
        dialogues[1] = "u got trsdwasdwas\ndwasdwaseats?";
        dialogues[2] = "nge";
        dialogues[3] = "tangina mo";
        
    }
     
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        } 
    }
    public void speak(){
        //
        super.speak();
    }
}
