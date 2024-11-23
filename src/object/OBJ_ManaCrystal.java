package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class OBJ_ManaCrystal extends Entity{
    GamePanel gp;

    public OBJ_ManaCrystal(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Mana Crystal";
        image = setup("/pics/objects/manacrystal_full.png", gp.tileSize, gp.tileSize);
        image2 = setup("/pics/objects/manacrystal_blank.png", gp.tileSize, gp.tileSize);
    }
}
