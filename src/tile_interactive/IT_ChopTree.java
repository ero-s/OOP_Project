package tile_interactive;

import pkg2dgame.GamePanel;

public class IT_ChopTree extends InteractiveTile {
    GamePanel gp;
    public IT_ChopTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        setCollisionArea(16,16,32,32,32,32);
        // Load image for the tree
        down1 = setup("/pics/tile_interactive/it_tree.png", gp.tileSize, gp.tileSize);
        destructible = true; // Mark tree as destructible
    }
}
