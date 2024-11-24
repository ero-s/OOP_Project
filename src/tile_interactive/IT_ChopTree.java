package tile_interactive;

import Entity.Entity;
import pkg2dgame.GamePanel;

import java.awt.*;

public class IT_ChopTree extends InteractiveTile {
    GamePanel gp;
    public IT_ChopTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize + col;
        this.worldY = gp.tileSize + row;
        setCollisionArea(16,16,32,32,32,32);
        // Load image for the tree
        down1 = setup("/pics/tile_interactive/it_tree.png", gp.tileSize, gp.tileSize);
        destructible = true; // Mark tree as destructible


        // tiles sustaining extra hit
         life = 3;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;

        if(entity.getCurrentWeapon().type == type_axe){
            isCorrectItem = true;
        }

        return isCorrectItem;
    }

    public void playSE(){
        //sound effects
    }

    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = new IT_Trunk(gp, worldX, worldY);
        return tile;
    }

    public Color getParticleColor(){
        Color color = new Color(65,50,30);
        return color;
    }

    public int getParticleSize(){
        int size = 6;
        return size;
    }

    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}
