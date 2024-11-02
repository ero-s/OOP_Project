package tile_interactive;

import Entity.Entity;
import pkg2dgame.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class InteractiveTile extends Entity {

    GamePanel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
    }

    public void update() {
        // Any update logic specific to InteractiveTile can be added here
    }

}
