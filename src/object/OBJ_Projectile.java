package object;

import Entity.Entity;
import Entity.Projectile;
import java.awt.*;
import pkg2dgame.GamePanel;

public class OBJ_Projectile extends Projectile {
    GamePanel gp;
    public OBJ_Projectile(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Green Stuff";
        speed = 10;
        maxLife = 60;
        atkPower = 1000;
        useCost = 1;
        alive = false;
        //collision area of projectile
        solidArea = new Rectangle(64,64,64,64);

        //offset of projectile
        xOffset = (gp.tileSize * 2 - gp.tileSize) / 2;
        yOffset = (gp.tileSize * 2 - gp.tileSize) / 2;
        getImage();
    }
    public void getImage(){
        up1 = setup("/pics/projectiles/green/up1.png", gp.tileSize*2, gp.tileSize*2);
        up2 = setup("/pics/projectiles/green/up2.png", gp.tileSize*2, gp.tileSize*2);
        right1 = setup("/pics/projectiles/green/right1.png", gp.tileSize*2, gp.tileSize*2);
        right2 = setup("/pics/projectiles/green/right2.png", gp.tileSize*2, gp.tileSize*2);
        down1 = setup("/pics/projectiles/green/down1.png", gp.tileSize*2, gp.tileSize*2);
        down2 = setup("/pics/projectiles/green/down2.png", gp.tileSize*2, gp.tileSize*2);
        left1 = setup("/pics/projectiles/green/left1.png", gp.tileSize*2, gp.tileSize*2);
        left2 = setup("/pics/projectiles/green/left2.png", gp.tileSize*2, gp.tileSize*2);
    }

    public boolean haveResource(Entity user){
        boolean haveResource = false;
        if(user.mana >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    
    public void subtractResource(Entity user){
        user.mana -= useCost;
    }

    public Color getParticleColor(){
        Color color = new Color(87, 230, 43);
        return color;
    }

    public int getParticleSize(){
        int size = 10;
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
