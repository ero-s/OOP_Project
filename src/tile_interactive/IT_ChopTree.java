package tile_interactive;

import pkg2dgame.GamePanel;

public class IT_ChopTree extends InteractiveTile {
    GamePanel gp;
    public IT_ChopTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        collisionOn = true;
        solidArea.x = 64;
        solidArea.y = 120;
        solidArea.width = 64;
        solidArea.height = 64;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        // Load image for the tree
        down1 = setup("/pics/tile_interactive/it_tree.png", gp.tileSize*2, gp.tileSize*2);
        destructible = true; // Mark tree as destructible
    }
}
