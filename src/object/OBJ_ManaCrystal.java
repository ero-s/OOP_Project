package object;

import Entity.Entity;
import pkg2dgame.GamePanel;

public class OBJ_ManaCrystal extends Entity{

    public OBJ_ManaCrystal(GamePanel gp){
        super(gp);

        name = "Mana Crystal";
        image = setup("/pics/objects/manacrystal_full.png", gp.tileSize/2, gp.tileSize/2);
        image2 = setup("/pics/objects/manacrystal_blank.png", gp.tileSize/2, gp.tileSize/2);
    }
}
