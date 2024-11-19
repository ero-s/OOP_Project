package Entity;

import java.util.Random;
import pkg2dgame.GamePanel;

public class NPC_Khai extends Entity {
    private int goalCol = -1; // Goal column for pathfinding
    private int goalRow = -1; // Goal row for pathfinding

    public NPC_Khai(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 4;
        setCollisionArea(16,16,32,32,32,32);
        getImage();
        setDialogue();

    }

    public void getImage() {
        up1 = setup("/pics/player/Khai/up1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/pics/player/Khai/up2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/pics/player/Khai/left1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/pics/player/Khai/left2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/pics/player/Khai/right1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/pics/player/Khai/right2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/pics/player/Khai/down1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/pics/player/Khai/down2.png", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "Hello, bb girl";
    }

    public void setAction() {
        if (onPath) {
//            //set goal Position
//            int goalCol = 4;
//            int goalRow = 11;

            //set to follow player
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol, goalRow);
        } else {
            // Random movement when not on a path
            actionLockCounter++;
            if (actionLockCounter >= 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                } else if (i <= 50) {
                    direction = "down";
                } else if (i <= 75) {
                    direction = "left";
                } else {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }

    @Override
    public void speak() {
        super.speak();
        // Toggle pathfinding when the NPC is spoken to
        if (!onPath) {
            onPath = true;
            goalCol = 23; // Set the destination column
            goalRow = 39; // Set the destination row
        } else {
            onPath = false; // Disable pathfinding
        }
    }
}
