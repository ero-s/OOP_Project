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
    public int mapTileNum[][];
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
        setup(0, "/pics/tiles/master tiles/001.png", false);
        setup(1, "/pics/tiles/master tiles/002.png", true);
        setup(2, "/pics/tiles/master tiles/003.png", true);
        setup(3, "/pics/tiles/master tiles/004.png", true);
        setup(4, "/pics/tiles/master tiles/005.png", true);
        setup(5, "/pics/tiles/master tiles/006.png", true);
        setup(6, "/pics/tiles/master tiles/007.png", true);
        setup(7, "/pics/tiles/master tiles/008.png", true);
        setup(8, "/pics/tiles/master tiles/009.png", true);
        setup(9, "/pics/tiles/master tiles/010.png", true);
        setup(10, "/pics/tiles/master tiles/011.png", true);
        setup(11, "/pics/tiles/master tiles/012.png", true);
        setup(12, "/pics/tiles/master tiles/013.png", true);
        setup(13, "/pics/tiles/master tiles/014.png", true);
        setup(14, "/pics/tiles/master tiles/015.png", true);
        setup(15, "/pics/tiles/master tiles/016.png", true);
        setup(16, "/pics/tiles/master tiles/017.png", true);
        setup(17, "/pics/tiles/master tiles/018.png", true);
        setup(18, "/pics/tiles/master tiles/019.png", true);
        setup(19, "/pics/tiles/master tiles/020.png", true);
        setup(20, "/pics/tiles/master tiles/021.png", true);
        setup(21, "/pics/tiles/master tiles/022.png", true);
        setup(22, "/pics/tiles/master tiles/023.png", true);
        setup(23, "/pics/tiles/master tiles/024.png", true);
        setup(24, "/pics/tiles/master tiles/025.png", true);
        setup(25, "/pics/tiles/master tiles/026.png", true);
        setup(26, "/pics/tiles/master tiles/027.png", true);
        setup(27, "/pics/tiles/master tiles/028.png", true);
        setup(28, "/pics/tiles/master tiles/029.png", true);
        setup(29, "/pics/tiles/master files/030.png", false);
        setup(30, "/pics/tiles/master files/031.png", false);
        setup(31, "/pics/tiles/master files/032.png", false);
        setup(32, "/pics/tiles/master files/033.png", false);
        setup(33, "/pics/tiles/master files/034.png", false);
        setup(34, "/pics/tiles/master files/035.png", false);
        setup(35, "/pics/tiles/master files/036.png", false);
        setup(36, "/pics/tiles/master files/037.png", false);
        setup(37, "/pics/tiles/master files/038.png", false);
        setup(38, "/pics/tiles/master tiles/039.png", true);
        setup(39, "/pics/tiles/master tiles/040.png", true);
        setup(40, "/pics/tiles/master tiles/041.png", true);
        setup(41, "/pics/tiles/master tiles/042.png", true);
        setup(42, "/pics/tiles/master tiles/043.png", true);
        setup(43, "/pics/tiles/master tiles/044.png", true);
        setup(44, "/pics/tiles/master tiles/045.png", true);
        setup(45, "/pics/tiles/master tiles/046.png", true);
        setup(46, "/pics/tiles/master tiles/047.png", true);
        setup(47, "/pics/tiles/master tiles/048.png", true);
        setup(48, "/pics/tiles/master tiles/049.png", true);
        setup(49, "/pics/tiles/master tiles/050.png", true);
        setup(50, "/pics/tiles/master tiles/051.png", true);
        setup(51, "/pics/tiles/master tiles/052.png", true);
        setup(52, "/pics/tiles/master tiles/053.png", false);
        setup(53, "/pics/tiles/master tiles/054.png", false);
        setup(54, "/pics/tiles/master tiles/055.png", false);
        setup(55, "/pics/tiles/master tiles/056.png", false);
        setup(56, "/pics/tiles/master tiles/057.png", false);
        setup(57, "/pics/tiles/master tiles/058.png", false);
        setup(58, "/pics/tiles/master tiles/059.png", false);
        setup(59, "/pics/tiles/master tiles/060.png", false);
        setup(60, "/pics/tiles/master tiles/061.png", false);
        setup(61, "/pics/tiles/master tiles/062.png", false);
        setup(62, "/pics/tiles/master tiles/063.png", false);
        setup(63, "/pics/tiles/master tiles/064.png", false);
        setup(64, "/pics/tiles/master tiles/065.png", false);
        setup(65, "/pics/tiles/master tiles/066.png", false);
        setup(66, "/pics/tiles/master tiles/067.png", false);
        setup(67, "/pics/tiles/master tiles/068.png", false);
        setup(68, "/pics/tiles/master tiles/069.png", false);
        setup(69, "/pics/tiles/master tiles/070.png", false);
        setup(70, "/pics/tiles/master tiles/071.png", false);
        setup(71, "/pics/tiles/master tiles/072.png", false);
        setup(72, "/pics/tiles/master tiles/073.png", false);
        setup(73, "/pics/tiles/master tiles/074.png", false);
        setup(74, "/pics/tiles/master tiles/075.png", false);
        setup(75, "/pics/tiles/master tiles/076.png", false);
        setup(76, "/pics/tiles/master tiles/077.png", false);
        setup(77, "/pics/tiles/master tiles/078.png", false);
        setup(78, "/pics/tiles/master tiles/079.png", false);
        setup(79, "/pics/tiles/master tiles/080.png", false);
        setup(80, "/pics/tiles/master tiles/081.png", false);
        setup(81, "/pics/tiles/master tiles/082.png", false);
        setup(82, "/pics/tiles/master tiles/083.png", false);
        setup(83, "/pics/tiles/master tiles/084.png", false);
        setup(84, "/pics/tiles/master tiles/085.png", false);
        setup(85, "/pics/tiles/master tiles/086.png", false);
        setup(86, "/pics/tiles/master tiles/087.png", false);
        setup(87, "/pics/tiles/master tiles/088.png", false);
        setup(88, "/pics/tiles/master tiles/089.png", false);
        setup(89, "/pics/tiles/master tiles/090.png", false);
        setup(90, "/pics/tiles/master tiles/091.png", false);
        setup(91, "/pics/tiles/master tiles/092.png", false);
        setup(92, "/pics/tiles/master tiles/093.png", false);
        setup(93, "/pics/tiles/master tiles/094.png", false);
        setup(94, "/pics/tiles/master tiles/095.png", false);
        setup(95, "/pics/tiles/master tiles/096.png", false);
        setup(96, "/pics/tiles/master tiles/097.png", false);
        setup(97, "/pics/tiles/master tiles/098.png", false);
        setup(98, "/pics/tiles/master tiles/099.png", false);
        setup(99, "/pics/tiles/master tiles/100.png", false);
        setup(100, "/pics/tiles/master tiles/101.png", false);
        setup(101, "/pics/tiles/master tiles/102.png", false);
        setup(102, "/pics/tiles/master tiles/103.png", false);
        setup(103, "/pics/tiles/master tiles/104.png", false);
        setup(104, "/pics/tiles/master tiles/105.png", false);
        setup(105, "/pics/tiles/master tiles/106.png", false);
        setup(106, "/pics/tiles/master tiles/107.png", false);
        setup(107, "/pics/tiles/master tiles/108.png", false);
        setup(108, "/pics/tiles/master tiles/109.png", false);
        setup(109, "/pics/tiles/master tiles/110.png", false);
        setup(110, "/pics/tiles/master tiles/111.png", false);
        setup(111, "/pics/tiles/master tiles/112.png", false);
        setup(112, "/pics/tiles/master tiles/113.png", false);
        setup(113, "/pics/tiles/master tiles/114.png", false);
        setup(114, "/pics/tiles/master tiles/115.png", false);
        setup(115, "/pics/tiles/master tiles/116.png", false);
        setup(116, "/pics/tiles/master tiles/117.png", false);
        setup(117, "/pics/tiles/master tiles/118.png", false);
        setup(118, "/pics/tiles/master tiles/119.png", false);
        setup(119, "/pics/tiles/master tiles/120.png", false);
        setup(120, "/pics/tiles/master tiles/121.png", false);
        setup(121, "/pics/tiles/master tiles/122.png", false);
        setup(122, "/pics/tiles/master tiles/123.png", false);
        setup(123, "/pics/tiles/master tiles/124.png", false);
        setup(124, "/pics/tiles/master tiles/125.png", false);
        setup(125, "/pics/tiles/master tiles/126.png", false);
        setup(126, "/pics/tiles/master tiles/127.png", false);
        setup(127, "/pics/tiles/master tiles/128.png", false);
        setup(128, "/pics/tiles/master tiles/129.png", false);
        setup(129, "/pics/tiles/master tiles/130.png", false);
        setup(130, "/pics/tiles/master tiles/131.png", false);
        setup(131, "/pics/tiles/master tiles/132.png", false);
        setup(132, "/pics/tiles/master tiles/133.png", false);
        setup(133, "/pics/tiles/master tiles/134.png", false);
        setup(134, "/pics/tiles/master tiles/135.png", false);
        setup(135, "/pics/tiles/master tiles/136.png", false);
        setup(136, "/pics/tiles/master tiles/137.png", false);
        setup(137, "/pics/tiles/master tiles/138.png", false);
        setup(138, "/pics/tiles/master tiles/139.png", false);
        setup(139, "/pics/tiles/master tiles/140.png", false);
        setup(140, "/pics/tiles/master tiles/141.png", false);
        setup(141, "/pics/tiles/master tiles/142.png", false);
        setup(142, "/pics/tiles/master tiles/143.png", false);
        setup(143, "/pics/tiles/master tiles/144.png", false);
        setup(144, "/pics/tiles/master tiles/145.png", false);
        setup(145, "/pics/tiles/master tiles/146.png", false);
        setup(146, "/pics/tiles/master tiles/147.png", false);
        setup(147, "/pics/tiles/master tiles/148.png", false);
        setup(148, "/pics/tiles/master tiles/149.png", false);
    }
    
    public void setup(int index, String imagePath, boolean collision) {
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
