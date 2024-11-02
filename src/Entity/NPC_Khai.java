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
public class NPC_Khai extends Entity{
    public NPC_Khai(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }
    
     public void getImage() {
        up1 = setup("/pics/player/Khai/up1.png",gp.tileSize, gp.tileSize);
        up2 = setup("/pics/player/Khai/up2.png",gp.tileSize, gp.tileSize);
        left1 = setup("/pics/player/Khai/left1.png",gp.tileSize, gp.tileSize);
        left2 = setup("/pics/player/Khai/left2.png",gp.tileSize, gp.tileSize);
        right1 = setup("/pics/player/Khai/right1.png",gp.tileSize, gp.tileSize);
        right2 = setup("/pics/player/Khai/right2.png",gp.tileSize, gp.tileSize);
        down1 = setup("/pics/player/Khai/down1.png",gp.tileSize, gp.tileSize);
        down2 = setup("/pics/player/Khai/down2.png",gp.tileSize, gp.tileSize);
    }
     
    public void setDialogue(){
        dialogues[0] = "Hello, bb girl";
        dialogues[1] = "may i have ur hand?";
        dialogues[2] = "sagingngan";
        dialogues[3] = "ahehehhehehhe";
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
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        
        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
}
