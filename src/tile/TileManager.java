package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import pkg2dgame.GamePanel;
import pkg2dgame.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    public final int screenX = 0, screenY = 0;
    Graphics2D g2;
    
    

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        //
        loadMap("/pics/maps/world01.txt");
        
 
    }

    public void getTileImage() {
        setup(0, "/pics/tiles/demo/grass.png", false);
        setup(1, "/pics/tiles/acacia parts/acacia left.png", true);
        setup(2, "/pics/tiles/acacia parts/acacia middleleft.png", true);
        setup(3, "/pics/tiles/acacia parts/acacia middleright.png", true);
        setup(4, "/pics/tiles/acacia parts/acacia right.png", true);
        
        setup(5, "/pics/tiles/houses/house.png", true);
        setup(6, "/pics/tiles/houses/house2.png", true);

        setup(7, "/pics/tiles/mango parts/mango left.png", true);
        setup(8, "/pics/tiles/mango parts/mango right.png", true);

        setup(9, "/pics/tiles/acacia parts/forest acacia topleft.png", true);
        setup(10, "/pics/tiles/acacia parts/forest acacia top-centereft.png", true);
        setup(11, "/pics/tiles/acacia parts/forest acacia top-centerright.png", true);
        setup(12, "/pics/tiles/acacia parts/forest acacia topright.png", true);

        setup(13, "/pics/tiles/acacia parts/forest acacia first middleleft.png", true);
        setup(14, "/pics/tiles/acacia parts/forest acacia first middle-centerleft.png", true);
        setup(15, "/pics/tiles/acacia parts/forest acacia first middle-centerright.png", true);
        setup(16, "/pics/tiles/acacia parts/forest acacia first middleright.png", true);

        setup(17, "/pics/tiles/acacia parts/forest acacia second middleleft.png", true);
        setup(18, "/pics/tiles/acacia parts/forest acacia second middle-centerleft.png", true);
        setup(19, "/pics/tiles/acacia parts/forest acacia second middle-centerright.png", true);
        setup(20, "/pics/tiles/acacia parts/forest acacia second middleright.png", true);

        setup(21, "/pics/tiles/acacia parts/forest acacia third middleleft.png", true);
        setup(22, "/pics/tiles/acacia parts/forest acacia third middle-centerleft.png", true);
        setup(23, "/pics/tiles/acacia parts/forest acacia third middle-centerright.png", true);
        setup(24, "/pics/tiles/acacia parts/forest acacia third middleright.png", true);

        setup(25, "/pics/tiles/acacia parts/forest acacia bottomleft.png", true);
        setup(26, "/pics/tiles/acacia parts/forest acacia bottom-centerleft.png", true);
        setup(27, "/pics/tiles/acacia parts/forest acacia bottom-centerright.png", true);
        setup(28, "/pics/tiles/acacia parts/forest acacia bottomright.png", true);

        setup(29, "/pics/tiles/demo/path_topleft.png", false);
        setup(30, "/pics/tiles/demo/path_top.png", false);
        setup(31, "/pics/tiles/demo/path_topright.png", false);
        setup(32, "/pics/tiles/demo/path_left.png", false);
        setup(33, "/pics/tiles/demo/path.png", false);
        setup(34, "/pics/tiles/demo/path_right.png", false);
        setup(35, "/pics/tiles/demo/path_bottomleft.png", false);
        setup(36, "/pics/tiles/demo/path_bottom.png", false);
        setup(37, "/pics/tiles/demo/path_bottomright.png", false);

        setup(38, "/pics/tiles/houses/summerHouse1/topleft.png", true);
        setup(39, "/pics/tiles/houses/summerHouse1/topMiddle.png", true);
        setup(40, "/pics/tiles/houses/summerHouse1/topRight.png", true);
        setup(41, "/pics/tiles/houses/summerHouse1/bottomLeft.png", true);
        setup(42, "/pics/tiles/houses/summerHouse1/bottomMiddle.png", true);
        setup(43, "/pics/tiles/houses/summerHouse1/bottomRight.png", true);

        setup(44, "/pics/tiles/houses/summerHouse2/topleft.png", true);
        setup(45, "/pics/tiles/houses/summerHouse2/topRight.png", true);
        setup(46, "/pics/tiles/houses/summerHouse2/bottomLeft.png", true);
        setup(47, "/pics/tiles/houses/summerHouse2/bottomRight.png", true);

        setup(48, "/pics/tiles/houses/summerHouse3/topleft.png", true);
        setup(49, "/pics/tiles/houses/summerHouse3/topRight.png", true);
        setup(50, "/pics/tiles/houses/summerHouse3/bottomLeft.png", true);
        setup(51, "/pics/tiles/houses/summerHouse3/bottomRight.png", true);

        setup(52, "/pics/tiles/pathway/topLeft.png", false);
        setup(53, "/pics/tiles/pathway/top.png", false);
        setup(54, "/pics/tiles/pathway/topRight.png", false);
        setup(55, "/pics/tiles/pathway/left.png", false);
        setup(56, "/pics/tiles/pathway/pure.png", false);
        setup(57, "/pics/tiles/pathway/pureWRocks.png", false);
        setup(58, "/pics/tiles/pathway/pureWRocks1.png", false);
        setup(59, "/pics/tiles/pathway/pureWRocks2.png", false);
        setup(60, "/pics/tiles/pathway/right.png", false);
        setup(61, "/pics/tiles/pathway/bottomLeft.png", false);
        setup(62, "/pics/tiles/pathway/bottom.png", false);
        setup(63, "/pics/tiles/pathway/bottomRight.png", false);

        setup(64, "/pics/tiles/pathway/upperLeft-corner.png", false);
        setup(66, "/pics/tiles/pathway/upperRight-corner.png", false);
        setup(67, "/pics/tiles/pathway/lowerLeft-corner.png", false);
        setup(68, "/pics/tiles/pathway/lowerRight-corner.png", false);

        setup(69, "/pics/tiles/sea/sand.png", false);
        setup(70, "/pics/tiles/sea/sea left.png", false);
        setup(71, "/pics/tiles/sea/sea lower.png", false);
        setup(72, "/pics/tiles/sea/sea right.png", false);
        setup(73, "/pics/tiles/sea/sea upper.png", false);
        setup(74, "/pics/tiles/sea/sea waves1.png", false);
        setup(75, "/pics/tiles/sea/sea waves2.png", false);
    }
    
    public void setup(int index, String imagePath,  boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

            // Define a custom collision box for each tile if it has collision
            if (collision) {
                // Set a collision area that is slightly smaller than the tile (e.g., padding by 4px)
                tile[index].collisionBox = new Rectangle(4, 4, gp.tileSize - 8, gp.tileSize - 8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;  // Load base map

                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {
        int worldCol = 0, worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];
            Tile currentTile = tile[tileNum];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Check if the tile is within screen bounds to render
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                // Draw the tile
                g2.drawImage(currentTile.image, screenX, screenY, null);

                // Draw the tile's specific collision box if it has collision
                if (currentTile.collision) {
                    g2.setColor(Color.red);
                    g2.setStroke(new java.awt.BasicStroke(1));

                    // Calculate the tile's collision box world position
                    int collisionBoxX = screenX + currentTile.collisionBox.x;
                    int collisionBoxY = screenY + currentTile.collisionBox.y;
                    int collisionBoxWidth = currentTile.collisionBox.width;
                    int collisionBoxHeight = currentTile.collisionBox.height;

                    // Draw the tile's specific collision box
                    g2.drawRect(collisionBoxX, collisionBoxY, collisionBoxWidth, collisionBoxHeight);
                }
            }

            // Now draw the player's collision area
            int entityScreenX = gp.player.worldX - gp.player.screenX;
            int entityScreenY = gp.player.worldY - gp.player.screenY;

            // Extract the entity's solidArea (collision box)
            int collisionX = entityScreenX + gp.player.solidArea.x;
            int collisionY = entityScreenY + gp.player.solidArea.y;
            int collisionWidth = gp.player.solidArea.width;
            int collisionHeight = gp.player.solidArea.height;

            // Draw the entity's collision area
            g2.setColor(Color.blue);  // Use a different color for the entity's collision area
            g2.setStroke(new java.awt.BasicStroke(1));  // Set stroke for visibility
            g2.drawRect(collisionX, collisionY, collisionWidth , collisionHeight);

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        
        
    }


}
