package tile_interactive;

import pkg2dgame.GamePanel;
    public class IT_Trunk extends InteractiveTile{

    GamePanel gp;
    public IT_Trunk (GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize + col;
        this.worldY = gp.tileSize + row;

        down1 = setup("/pics/tile_interactive/it_stump.png", gp.tileSize, gp.tileSize);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
