package tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import javax.imageio.ImageIO;
import pkg2dgame.GamePanel;
import pkg2dgame.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    boolean drawPath = true;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[1000];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/pics/maps/world01.txt", 0);
        loadMap("/pics/maps/world02.txt",1);
        loadMap("/pics/maps/dungeon01",2);
        loadMap("/pics/maps/dungeon02",3);
    }
    
    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
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
    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;  // Load base map

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

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
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
                if(currentTile != null) {
                    g2.drawImage(currentTile.image, screenX, screenY, null);

                    if(currentTile.collisionBox != null){
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





                // Draw the tile's specific collision box if it has collision
//                if (currentTile.collision) {
//                    g2.setColor(Color.red);
//                    g2.setStroke(new java.awt.BasicStroke(1));
//
//                    // Calculate the tile's collision box world position
//                    int collisionBoxX = screenX + currentTile.collisionBox.x;
//                    int collisionBoxY = screenY + currentTile.collisionBox.y;
//                    int collisionBoxWidth = currentTile.collisionBox.width;
//                    int collisionBoxHeight = currentTile.collisionBox.height;
//
//                    // Draw the tile's specific collision box
//                    g2.drawRect(collisionBoxX, collisionBoxY, collisionBoxWidth, collisionBoxHeight);
//                }
            }
//
//            // Now draw the player's collision area
//            int entityScreenX = gp.player.worldX - gp.player.screenX;
//            int entityScreenY = gp.player.worldY - gp.player.screenY;
//
//            // Extract the entity's solidArea (collision box)
//            int collisionX = entityScreenX + gp.player.solidArea.x;
//            int collisionY = entityScreenY + gp.player.solidArea.y;
//            int collisionWidth = gp.player.solidArea.width;
//            int collisionHeight = gp.player.solidArea.height;
//
//            // Draw the entity's collision area
//            g2.setColor(Color.blue);  // Use a different color for the entity's collision area
//            g2.setStroke(new java.awt.BasicStroke(1));  // Set stroke for visibility
//            g2.drawRect(collisionX, collisionY, collisionWidth , collisionHeight);

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        if(drawPath){
            g2.setColor(new Color(255,0,0,70));

            for(int i = 0; i < gp.pFinder.pathList.size(); i++){
                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }

    public void getTileImage() {
        setup(0, "/pics/tiles/master tiles/001.png", true);
        setup(1, "/pics/tiles/master tiles/002.png", true);
        setup(2, "/pics/tiles/master tiles/003.png", true);
        setup(3, "/pics/tiles/master tiles/004.png", true);
        setup(4, "/pics/tiles/master tiles/005.png", false);
        setup(5, "/pics/tiles/master tiles/006.png", true);
        setup(6, "/pics/tiles/master tiles/007.png", true);
        setup(7, "/pics/tiles/master tiles/008.png", true);
        setup(8, "/pics/tiles/master tiles/009.png", false);
        setup(9, "/pics/tiles/master tiles/010.png", false);
        setup(10, "/pics/tiles/master tiles/011.png", false);
        setup(11, "/pics/tiles/master tiles/012.png", false);
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
        setup(29, "/pics/tiles/master tiles/030.png", true);
        setup(30, "/pics/tiles/master tiles/031.png", true);
        setup(31, "/pics/tiles/master tiles/032.png", true);
        setup(32, "/pics/tiles/master tiles/033.png", true);
        setup(33, "/pics/tiles/master tiles/034.png", true);
        setup(34, "/pics/tiles/master tiles/035.png", true);
        setup(35, "/pics/tiles/master tiles/036.png", true);
        setup(36, "/pics/tiles/master tiles/037.png", true);
        setup(37, "/pics/tiles/master tiles/038.png", true);
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
        setup(52, "/pics/tiles/master tiles/053.png", true);
        setup(53, "/pics/tiles/master tiles/054.png", false);
        setup(54, "/pics/tiles/master tiles/055.png", true);
        setup(55, "/pics/tiles/master tiles/056.png", true);
        setup(56, "/pics/tiles/master tiles/057.png", false);
        setup(57, "/pics/tiles/master tiles/058.png", false);
        setup(58, "/pics/tiles/master tiles/059.png", true);
        setup(59, "/pics/tiles/master tiles/060.png", false);
        setup(60, "/pics/tiles/master tiles/061.png", false);
        setup(61, "/pics/tiles/master tiles/062.png", true);
        setup(62, "/pics/tiles/master tiles/063.png", true);
        setup(63, "/pics/tiles/master tiles/064.png", true);
        setup(64, "/pics/tiles/master tiles/065.png", true);
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
        setup(80, "/pics/tiles/master tiles/081.png", true);
        setup(81, "/pics/tiles/master tiles/082.png", true);
        setup(82, "/pics/tiles/master tiles/083.png", true);
        setup(83, "/pics/tiles/master tiles/084.png", true);
        setup(84, "/pics/tiles/master tiles/085.png", false);
        setup(85, "/pics/tiles/master tiles/086.png", false);
        setup(86, "/pics/tiles/master tiles/087.png", false);
        setup(87, "/pics/tiles/master tiles/088.png", false);
        setup(88, "/pics/tiles/master tiles/089.png", false);
        setup(89, "/pics/tiles/master tiles/090.png", true);
        setup(90, "/pics/tiles/master tiles/091.png", false);
        setup(91, "/pics/tiles/master tiles/092.png", true);
        setup(92, "/pics/tiles/master tiles/093.png", true);
        setup(93, "/pics/tiles/master tiles/094.png", true);
        setup(94, "/pics/tiles/master tiles/095.png", true);
        setup(95, "/pics/tiles/master tiles/096.png", true);
        setup(96, "/pics/tiles/master tiles/097.png", true);
        setup(97, "/pics/tiles/master tiles/098.png", true);
        setup(98, "/pics/tiles/master tiles/099.png", true);
        setup(99, "/pics/tiles/master tiles/100.png", true);
        setup(100, "/pics/tiles/master tiles/101.png", true);
        setup(101, "/pics/tiles/master tiles/102.png", true);
        setup(102, "/pics/tiles/master tiles/103.png", true);
        setup(103, "/pics/tiles/master tiles/104.png", true);
        setup(104, "/pics/tiles/master tiles/105.png", true);
        setup(105, "/pics/tiles/master tiles/106.png", true);
        setup(106, "/pics/tiles/master tiles/107.png", true);
        setup(107, "/pics/tiles/master tiles/108.png", true);
        setup(108, "/pics/tiles/master tiles/109.png", false);
        setup(109, "/pics/tiles/master tiles/110.png", false);
        setup(110, "/pics/tiles/master tiles/111.png", false);
        setup(111, "/pics/tiles/master tiles/112.png", true);
        setup(112, "/pics/tiles/master tiles/113.png", true);
        setup(113, "/pics/tiles/master tiles/114.png", true);
        setup(114, "/pics/tiles/master tiles/115.png", false);
        setup(115, "/pics/tiles/master tiles/116.png", true);
        setup(116, "/pics/tiles/master tiles/117.png", true);
        setup(117, "/pics/tiles/master tiles/118.png", true);
        setup(118, "/pics/tiles/master tiles/119.png", true);
        setup(119, "/pics/tiles/master tiles/120.png", true);
        setup(120, "/pics/tiles/master tiles/121.png", true);
        setup(121, "/pics/tiles/master tiles/122.png", true);
        setup(122, "/pics/tiles/master tiles/123.png", true);
        setup(123, "/pics/tiles/master tiles/124.png", true);
        setup(124, "/pics/tiles/master tiles/125.png", true);
        setup(125, "/pics/tiles/master tiles/126.png", true);
        setup(126, "/pics/tiles/master tiles/127.png", true);
        setup(127, "/pics/tiles/master tiles/128.png", true);
        setup(128, "/pics/tiles/master tiles/129.png", false);
        setup(129, "/pics/tiles/master tiles/130.png", false);
        setup(130, "/pics/tiles/master tiles/131.png", true);
        setup(131, "/pics/tiles/master tiles/132.png", true);
        setup(132, "/pics/tiles/master tiles/133.png", true);
        setup(133, "/pics/tiles/master tiles/134.png", true);
        setup(134, "/pics/tiles/master tiles/135.png", true);
        setup(135, "/pics/tiles/master tiles/136.png", true);
        setup(136, "/pics/tiles/master tiles/137.png", true);
        setup(137, "/pics/tiles/master tiles/138.png", true);
        setup(138, "/pics/tiles/master tiles/139.png", true);
        setup(139, "/pics/tiles/master tiles/140.png", true);
        setup(140, "/pics/tiles/master tiles/141.png", true);
        setup(141, "/pics/tiles/master tiles/142.png", true);
        setup(142, "/pics/tiles/master tiles/143.png", true);
        setup(143, "/pics/tiles/master tiles/144.png", true);
        setup(144, "/pics/tiles/master tiles/145.png", true);
        setup(145, "/pics/tiles/master tiles/146.png", true);
        setup(146, "/pics/tiles/master tiles/147.png", true);
        setup(147, "/pics/tiles/master tiles/148.png", true);
        setup(148, "/pics/tiles/master tiles/149.png", true);
        setup(149, "/pics/tiles/master tiles/150.png", true);
        setup(150, "/pics/tiles/master tiles/151.png", true);
        setup(151, "/pics/tiles/master tiles/152.png", true);
        setup(152, "/pics/tiles/master tiles/153.png", true);
        setup(153, "/pics/tiles/master tiles/154.png", true);
        setup(154, "/pics/tiles/master tiles/155.png", false);
        setup(155, "/pics/tiles/master tiles/156.png", false);
        setup(156, "/pics/tiles/master tiles/157.png", true);
        setup(157, "/pics/tiles/master tiles/158.png", true);
        setup(158, "/pics/tiles/master tiles/159.png", true);
        setup(159, "/pics/tiles/master tiles/160.png", true);
        setup(160, "/pics/tiles/master tiles/161.png", true);
        setup(161, "/pics/tiles/master tiles/162.png", true);
        setup(162, "/pics/tiles/master tiles/163.png", true);
        setup(163, "/pics/tiles/master tiles/164.png", true);
        setup(164, "/pics/tiles/master tiles/165.png", true);
        setup(165, "/pics/tiles/master tiles/166.png", true);
        setup(166, "/pics/tiles/master tiles/167.png", true);
        setup(167, "/pics/tiles/master tiles/168.png", true);

        setup(168, "/pics/tiles/master tiles/169.png", true);
        setup(169, "/pics/tiles/master tiles/170.png", false);
        setup(170, "/pics/tiles/master tiles/171.png", true);
        setup(171, "/pics/tiles/master tiles/172.png", true);
        setup(172, "/pics/tiles/master tiles/173.png", true);
        setup(173, "/pics/tiles/master tiles/174.png", true);
        setup(174, "/pics/tiles/master tiles/175.png", true);
        setup(175, "/pics/tiles/master tiles/176.png", true);
        setup(176, "/pics/tiles/master tiles/177.png", true);
        setup(177, "/pics/tiles/master tiles/178.png", true);

        setup(178, "/pics/tiles/master tiles/179.png", false);
        setup(179, "/pics/tiles/master tiles/180.png", true);
        setup(180, "/pics/tiles/master tiles/181.png", false);
        setup(181, "/pics/tiles/master tiles/182.png", false);
        setup(182, "/pics/tiles/master tiles/183.png", false);

        setup(183, "/pics/tiles/master tiles/184.png", true);
        setup(184, "/pics/tiles/master tiles/185.png", true);
        setup(185, "/pics/tiles/master tiles/186.png", true);
        setup(186, "/pics/tiles/master tiles/187.png", true);
        setup(187, "/pics/tiles/master tiles/188.png", true);
        setup(188, "/pics/tiles/master tiles/189.png", true);
        setup(189, "/pics/tiles/master tiles/190.png", true);
        setup(190, "/pics/tiles/master tiles/191.png", true);

        setup(191, "/pics/tiles/master tiles/192.png", false);
        setup(192, "/pics/tiles/master tiles/193.png", false);
        setup(193, "/pics/tiles/master tiles/194.png", false);
        setup(194, "/pics/tiles/master tiles/195.png", false);
        setup(195, "/pics/tiles/master tiles/196.png", false);
        setup(196, "/pics/tiles/master tiles/197.png", false);
        setup(197, "/pics/tiles/master tiles/198.png", false);
        setup(198, "/pics/tiles/master tiles/199.png", false);
        setup(199, "/pics/tiles/master tiles/200.png", false);
        setup(200, "/pics/tiles/master tiles/201.png", false);
        setup(201, "/pics/tiles/master tiles/202.png", false);
        setup(202, "/pics/tiles/master tiles/203.png", false);
        setup(203, "/pics/tiles/master tiles/204.png", false);
        setup(204, "/pics/tiles/master tiles/205.png", false);
        setup(205, "/pics/tiles/master tiles/206.png", false);

        setup(206, "/pics/tiles/master tiles/207.png", true);
        setup(207, "/pics/tiles/master tiles/208.png", true);
        setup(208, "/pics/tiles/master tiles/209.png", true);
        setup(209, "/pics/tiles/master tiles/210.png", true);
        setup(210, "/pics/tiles/master tiles/211.png", true);
        setup(211, "/pics/tiles/master tiles/212.png", true);
        setup(212, "/pics/tiles/master tiles/213.png", true);
        setup(213, "/pics/tiles/master tiles/214.png", true);
        setup(214, "/pics/tiles/master tiles/215.png", true);
        setup(215, "/pics/tiles/master tiles/216.png", true);
        setup(216, "/pics/tiles/master tiles/217.png", true);
        setup(217, "/pics/tiles/master tiles/218.png", true);
        setup(218, "/pics/tiles/master tiles/219.png", true);
        setup(219, "/pics/tiles/master tiles/220.png", true);
        setup(220, "/pics/tiles/master tiles/221.png", true);
        setup(221, "/pics/tiles/master tiles/222.png", true);
        setup(222, "/pics/tiles/master tiles/223.png", true);
        setup(223, "/pics/tiles/master tiles/224.png", true);
        setup(224, "/pics/tiles/master tiles/225.png", true);
        setup(225, "/pics/tiles/master tiles/226.png", true);
        setup(226, "/pics/tiles/master tiles/227.png", true);
        setup(227, "/pics/tiles/master tiles/228.png", true);
        setup(228, "/pics/tiles/master tiles/229.png", true);
        setup(229, "/pics/tiles/master tiles/230.png", true);
        setup(230, "/pics/tiles/master tiles/231.png", true);
        setup(231, "/pics/tiles/master tiles/232.png", true);
        setup(232, "/pics/tiles/master tiles/233.png", true);
        setup(233, "/pics/tiles/master tiles/234.png", true);
        setup(234, "/pics/tiles/master tiles/235.png", true);
        setup(235, "/pics/tiles/master tiles/236.png", true);
        setup(236, "/pics/tiles/master tiles/237.png", true);
        setup(237, "/pics/tiles/master tiles/238.png", true);
        setup(238, "/pics/tiles/master tiles/239.png", true);
        setup(239, "/pics/tiles/master tiles/240.png", true);
        setup(240, "/pics/tiles/master tiles/241.png", true);
        setup(241, "/pics/tiles/master tiles/242.png", true);
        setup(242, "/pics/tiles/master tiles/243.png", true);
        setup(243, "/pics/tiles/master tiles/244.png", true);
        setup(244, "/pics/tiles/master tiles/245.png", true);
        setup(245, "/pics/tiles/master tiles/246.png", true);
        setup(246, "/pics/tiles/master tiles/247.png", true);
        setup(247, "/pics/tiles/master tiles/248.png", true);
        setup(248, "/pics/tiles/master tiles/249.png", true);
        setup(249, "/pics/tiles/master tiles/250.png", true);
        setup(250, "/pics/tiles/master tiles/251.png", true);

        setup(251, "/pics/tiles/master tiles/252.png", false);
        setup(252, "/pics/tiles/master tiles/253.png", false);
        setup(253, "/pics/tiles/master tiles/254.png", false);
        setup(254, "/pics/tiles/master tiles/255.png", false);
        setup(255, "/pics/tiles/master tiles/256.png", false);
        setup(256, "/pics/tiles/master tiles/257.png", false);

        setup(256, "/pics/tiles/master tiles/257.png", true);
        setup(257, "/pics/tiles/master tiles/258.png", false);
        setup(258, "/pics/tiles/master tiles/259.png", false);
        setup(259, "/pics/tiles/master tiles/260.png", false);
        setup(260, "/pics/tiles/master tiles/261.png", false);
        setup(261, "/pics/tiles/master tiles/262.png", false);
        setup(262, "/pics/tiles/master tiles/263.png", false);
        setup(263, "/pics/tiles/master tiles/264.png", false);
        setup(264, "/pics/tiles/master tiles/265.png", false);
        setup(265, "/pics/tiles/master tiles/266.png", false);
        setup(266, "/pics/tiles/master tiles/267.png", false);
        setup(267, "/pics/tiles/master tiles/268.png", false);
        setup(268, "/pics/tiles/master tiles/269.png", false);
        setup(269, "/pics/tiles/master tiles/270.png", false);
        setup(270, "/pics/tiles/master tiles/271.png", false);
        setup(271, "/pics/tiles/master tiles/272.png", false);
        setup(272, "/pics/tiles/master tiles/273.png", false);
        setup(273, "/pics/tiles/master tiles/274.png", false);
        setup(274, "/pics/tiles/master tiles/275.png", false);
        setup(275, "/pics/tiles/master tiles/276.png", false);
        setup(276, "/pics/tiles/master tiles/277.png", false);
        setup(277, "/pics/tiles/master tiles/278.png", false);
        setup(278, "/pics/tiles/master tiles/279.png", false);
        setup(279, "/pics/tiles/master tiles/280.png", false);
        setup(280, "/pics/tiles/master tiles/281.png", false);
        setup(281, "/pics/tiles/master tiles/282.png", false);
        setup(282, "/pics/tiles/master tiles/283.png", false);
        setup(283, "/pics/tiles/master tiles/284.png", false);
        setup(284, "/pics/tiles/master tiles/285.png", false);
        setup(285, "/pics/tiles/master tiles/286.png", false);
        setup(286, "/pics/tiles/master tiles/287.png", false);
        setup(287, "/pics/tiles/master tiles/288.png", false);
        setup(288, "/pics/tiles/master tiles/289.png", false);
        setup(289, "/pics/tiles/master tiles/290.png", false);
        setup(290, "/pics/tiles/master tiles/291.png", false);
        setup(291, "/pics/tiles/master tiles/292.png", false);
        setup(292, "/pics/tiles/master tiles/293.png", false);
        setup(293, "/pics/tiles/master tiles/294.png", false);
        setup(294, "/pics/tiles/master tiles/295.png", false);
        setup(295, "/pics/tiles/master tiles/296.png", false);
        setup(296, "/pics/tiles/master tiles/297.png", false);
        setup(297, "/pics/tiles/master tiles/298.png", false);
        setup(298, "/pics/tiles/master tiles/299.png", false);
        setup(299, "/pics/tiles/master tiles/300.png", false);
        setup(300, "/pics/tiles/master tiles/301.png", false);
        setup(301, "/pics/tiles/master tiles/302.png", false);
        setup(302, "/pics/tiles/master tiles/303.png", false);
        setup(303, "/pics/tiles/master tiles/304.png", false);
        setup(304, "/pics/tiles/master tiles/305.png", false);

        setup(305, "/pics/tiles/master tiles/306.png", true);
        setup(306, "/pics/tiles/master tiles/307.png", true);
        setup(307, "/pics/tiles/master tiles/308.png", true);
        setup(308, "/pics/tiles/master tiles/309.png", true);
        setup(309, "/pics/tiles/master tiles/310.png", true);
        setup(310, "/pics/tiles/master tiles/311.png", true);
        setup(311, "/pics/tiles/master tiles/312.png", true);
        setup(312, "/pics/tiles/master tiles/313.png", true);
        setup(313, "/pics/tiles/master tiles/314.png", true);
        setup(314, "/pics/tiles/master tiles/315.png", true);
        setup(315, "/pics/tiles/master tiles/316.png", true);
        setup(316, "/pics/tiles/master tiles/317.png", true);
        setup(317, "/pics/tiles/master tiles/318.png", true);
        setup(318, "/pics/tiles/master tiles/319.png", true);
        setup(319, "/pics/tiles/master tiles/320.png", true);
        setup(320, "/pics/tiles/master tiles/321.png", true);
        setup(321, "/pics/tiles/master tiles/322.png", true);
        setup(322, "/pics/tiles/master tiles/323.png", true);
        setup(323, "/pics/tiles/master tiles/324.png", true);
        setup(324, "/pics/tiles/master tiles/325.png", true);
        setup(325, "/pics/tiles/master tiles/326.png", true);
        setup(326, "/pics/tiles/master tiles/327.png", true);
        setup(327, "/pics/tiles/master tiles/328.png", true);
        setup(328, "/pics/tiles/master tiles/329.png", true);
        setup(329, "/pics/tiles/master tiles/330.png", true);
        setup(330, "/pics/tiles/master tiles/331.png", true);
        setup(331, "/pics/tiles/master tiles/332.png", true);
        setup(332, "/pics/tiles/master tiles/333.png", true);
        setup(333, "/pics/tiles/master tiles/334.png", true);
        setup(334, "/pics/tiles/master tiles/335.png", true);
        setup(335, "/pics/tiles/master tiles/336.png", true);
        setup(336, "/pics/tiles/master tiles/337.png", true);
        setup(337, "/pics/tiles/master tiles/338.png", true);
        setup(338, "/pics/tiles/master tiles/339.png", true);
        setup(339, "/pics/tiles/master tiles/340.png", true);
        setup(340, "/pics/tiles/master tiles/341.png", true);
        setup(341, "/pics/tiles/master tiles/342.png", true);
        setup(342, "/pics/tiles/master tiles/343.png", true);
        setup(343, "/pics/tiles/master tiles/344.png", true);
        setup(344, "/pics/tiles/master tiles/345.png", true);
        setup(345, "/pics/tiles/master tiles/346.png", true);
        setup(346, "/pics/tiles/master tiles/347.png", true);
        setup(347, "/pics/tiles/master tiles/348.png", true);
        setup(348, "/pics/tiles/master tiles/349.png", true);
        setup(349, "/pics/tiles/master tiles/350.png", true);
        setup(350, "/pics/tiles/master tiles/351.png", true);
        setup(351, "/pics/tiles/master tiles/352.png", true);
        setup(352, "/pics/tiles/master tiles/353.png", true);
        setup(353, "/pics/tiles/master tiles/354.png", true);
        setup(354, "/pics/tiles/master tiles/355.png", true);
        setup(355, "/pics/tiles/master tiles/356.png", false);
        setup(356, "/pics/tiles/master tiles/357.png", false);
        setup(357, "/pics/tiles/master tiles/358.png", true);
        setup(358, "/pics/tiles/master tiles/359.png", true);
        setup(359, "/pics/tiles/master tiles/360.png", true);
        setup(360, "/pics/tiles/master tiles/361.png", true);
        setup(361, "/pics/tiles/master tiles/362.png", true);
        setup(362, "/pics/tiles/master tiles/363.png", true);
        setup(363, "/pics/tiles/master tiles/364.png", true);
        setup(364, "/pics/tiles/master tiles/365.png", true);
        setup(365, "/pics/tiles/master tiles/366.png", true);
        setup(366, "/pics/tiles/master tiles/367.png", true);
        setup(367, "/pics/tiles/master tiles/368.png", true);
        setup(368, "/pics/tiles/master tiles/369.png", true);
        setup(369, "/pics/tiles/master tiles/370.png", true);
        setup(370, "/pics/tiles/master tiles/371.png", true);
        setup(371, "/pics/tiles/master tiles/372.png", true);
        setup(372, "/pics/tiles/master tiles/373.png", true);
        setup(373, "/pics/tiles/master tiles/374.png", true);
        setup(374, "/pics/tiles/master tiles/375.png", true);
        setup(375, "/pics/tiles/master tiles/376.png", true);
        setup(376, "/pics/tiles/master tiles/377.png", true);
        setup(377, "/pics/tiles/master tiles/378.png", true);
        setup(378, "/pics/tiles/master tiles/379.png", true);
        setup(379, "/pics/tiles/master tiles/380.png", true);
        setup(380, "/pics/tiles/master tiles/381.png", true);
        setup(381, "/pics/tiles/master tiles/382.png", true);

        setup(382, "/pics/tiles/master tiles/383.png", true);
        setup(383, "/pics/tiles/master tiles/384.png", true);
        setup(384, "/pics/tiles/master tiles/385.png", true);
        setup(385, "/pics/tiles/master tiles/386.png", true);
        setup(386, "/pics/tiles/master tiles/387.png", true);
        setup(387, "/pics/tiles/master tiles/388.png", true);
        setup(388, "/pics/tiles/master tiles/389.png", true);
        setup(389, "/pics/tiles/master tiles/390.png", true);
        setup(390, "/pics/tiles/master tiles/391.png", true);
        setup(391, "/pics/tiles/master tiles/392.png", true);
        setup(392, "/pics/tiles/master tiles/393.png", true);
        setup(393, "/pics/tiles/master tiles/394.png", true);
        setup(394, "/pics/tiles/master tiles/395.png", true);
        setup(395, "/pics/tiles/master tiles/396.png", true);
        setup(396, "/pics/tiles/master tiles/397.png", true);
        setup(397, "/pics/tiles/master tiles/398.png", true);
        setup(398, "/pics/tiles/master tiles/399.png", false);
        setup(399, "/pics/tiles/master tiles/400.png", true);
        setup(400, "/pics/tiles/master tiles/401.png", true);
        setup(401, "/pics/tiles/master tiles/402.png", true);
        setup(402, "/pics/tiles/master tiles/403.png", true);
        setup(403, "/pics/tiles/master tiles/404.png", true);
        setup(404, "/pics/tiles/master tiles/405.png", true);
        setup(405, "/pics/tiles/master tiles/406.png", true);
        setup(406, "/pics/tiles/master tiles/407.png", true);
        setup(407, "/pics/tiles/master tiles/408.png", true);
        setup(408, "/pics/tiles/master tiles/409.png", true);
        setup(409, "/pics/tiles/master tiles/410.png", true);
        setup(410, "/pics/tiles/master tiles/411.png", true);
        setup(411, "/pics/tiles/master tiles/412.png", true);
        setup(412, "/pics/tiles/master tiles/413.png", true);
        setup(413, "/pics/tiles/master tiles/414.png", true);
        setup(414, "/pics/tiles/master tiles/415.png", false);
        setup(415, "/pics/tiles/master tiles/416.png", false);
        setup(416, "/pics/tiles/master tiles/417.png", false);
        setup(417, "/pics/tiles/master tiles/418.png", false);
        setup(418, "/pics/tiles/master tiles/419.png", false);
        setup(419, "/pics/tiles/master tiles/420.png", false);
        setup(420, "/pics/tiles/master tiles/421.png", false);
        setup(421, "/pics/tiles/master tiles/422.png", false);
        setup(422, "/pics/tiles/master tiles/423.png", false);
        setup(423, "/pics/tiles/master tiles/424.png", false);
        setup(424, "/pics/tiles/master tiles/425.png", false);
        setup(425, "/pics/tiles/master tiles/426.png", false);
        setup(426, "/pics/tiles/master tiles/427.png", false);

        setup(427, "/pics/tiles/master tiles/428.png", true);
        setup(428, "/pics/tiles/master tiles/429.png", true);
        setup(429, "/pics/tiles/master tiles/430.png", true);
        setup(430, "/pics/tiles/master tiles/431.png", true);
        setup(431, "/pics/tiles/master tiles/432.png", true);
        setup(432, "/pics/tiles/master tiles/433.png", true);
        setup(433, "/pics/tiles/master tiles/434.png", true);
        setup(434, "/pics/tiles/master tiles/435.png", true);
        setup(435, "/pics/tiles/master tiles/436.png", true);
        setup(436, "/pics/tiles/master tiles/437.png", true);
        setup(437, "/pics/tiles/master tiles/438.png", true);
        setup(438, "/pics/tiles/master tiles/439.png", true);
        setup(439, "/pics/tiles/master tiles/440.png", true);
        setup(440, "/pics/tiles/master tiles/441.png", true);
        setup(441, "/pics/tiles/master tiles/442.png", true);
        setup(442, "/pics/tiles/master tiles/443.png", true);
        setup(443, "/pics/tiles/master tiles/444.png", true);
        setup(444, "/pics/tiles/master tiles/445.png", true);
        setup(445, "/pics/tiles/master tiles/446.png", true);
        setup(446, "/pics/tiles/master tiles/447.png", true);
        setup(447, "/pics/tiles/master tiles/448.png", true);
        setup(448, "/pics/tiles/master tiles/449.png", true);
        setup(449, "/pics/tiles/master tiles/450.png", true);
        setup(450, "/pics/tiles/master tiles/451.png", true);
        setup(451, "/pics/tiles/master tiles/452.png", true);
        setup(452, "/pics/tiles/master tiles/453.png", true);
        setup(453, "/pics/tiles/master tiles/454.png", true);
        setup(454, "/pics/tiles/master tiles/455.png", true);
        setup(455, "/pics/tiles/master tiles/456.png", true);
        setup(456, "/pics/tiles/master tiles/457.png", true);
        setup(457, "/pics/tiles/master tiles/458.png", true);
        setup(458, "/pics/tiles/master tiles/459.png", true);
        setup(459, "/pics/tiles/master tiles/460.png", true);
        setup(460, "/pics/tiles/master tiles/461.png", true);
        setup(461, "/pics/tiles/master tiles/462.png", true);
        setup(462, "/pics/tiles/master tiles/463.png", true);
        setup(463, "/pics/tiles/master tiles/464.png", true);
        setup(464, "/pics/tiles/master tiles/465.png", true);
        setup(465, "/pics/tiles/master tiles/466.png", true);
        setup(466, "/pics/tiles/master tiles/467.png", true);
        setup(467, "/pics/tiles/master tiles/468.png", true);
        setup(468, "/pics/tiles/master tiles/469.png", true);
        setup(469, "/pics/tiles/master tiles/470.png", true);
        setup(470, "/pics/tiles/master tiles/471.png", true);
        setup(471, "/pics/tiles/master tiles/472.png", true);
        setup(472, "/pics/tiles/master tiles/473.png", true);
        setup(473, "/pics/tiles/master tiles/474.png", true);
        setup(474, "/pics/tiles/master tiles/475.png", true);
        setup(475, "/pics/tiles/master tiles/476.png", true);
        setup(476, "/pics/tiles/master tiles/477.png", true);
        setup(477, "/pics/tiles/master tiles/478.png", true);
        setup(478, "/pics/tiles/master tiles/479.png", true);
        setup(479, "/pics/tiles/master tiles/480.png", true);
        setup(480, "/pics/tiles/master tiles/481.png", true);
        setup(481, "/pics/tiles/master tiles/482.png", true);
        setup(482, "/pics/tiles/master tiles/483.png", true);
        setup(483, "/pics/tiles/master tiles/484.png", true);
        setup(484, "/pics/tiles/master tiles/485.png", true);
        setup(485, "/pics/tiles/master tiles/486.png", true);
        setup(486, "/pics/tiles/master tiles/487.png", true);

        setup(487, "/pics/tiles/master tiles/488.png", false);
        setup(488, "/pics/tiles/master tiles/489.png", false);
        setup(489, "/pics/tiles/master tiles/490.png", false);
        setup(490, "/pics/tiles/master tiles/491.png", false);
        setup(491, "/pics/tiles/master tiles/492.png", false);
        setup(492, "/pics/tiles/master tiles/493.png", false);
        setup(493, "/pics/tiles/master tiles/494.png", false);
        setup(494, "/pics/tiles/master tiles/495.png", false);

        setup(495, "/pics/tiles/master tiles/496.png", true);
        setup(496, "/pics/tiles/master tiles/497.png", false);
        setup(497, "/pics/tiles/master tiles/498.png", true);
        setup(498, "/pics/tiles/master tiles/499.png", false);
        setup(499, "/pics/tiles/master tiles/500.png", false);
        setup(500, "/pics/tiles/master tiles/501.png", true);
        setup(501, "/pics/tiles/master tiles/502.png", false);
        setup(502, "/pics/tiles/master tiles/503.png", false);
        setup(503, "/pics/tiles/master tiles/504.png", false);

        setup(504, "/pics/tiles/master tiles/505.png", true);
        setup(505, "/pics/tiles/master tiles/506.png", true);
        setup(506, "/pics/tiles/master tiles/507.png", true);
        setup(507, "/pics/tiles/master tiles/508.png", true);
        setup(508, "/pics/tiles/master tiles/509.png", true);
        setup(509, "/pics/tiles/master tiles/510.png", true);
        setup(510, "/pics/tiles/master tiles/511.png", true);
        setup(511, "/pics/tiles/master tiles/512.png", false);
        setup(512, "/pics/tiles/master tiles/513.png", false);
        setup(513, "/pics/tiles/master tiles/514.png", true);
        setup(514, "/pics/tiles/master tiles/515.png", true);
        setup(515, "/pics/tiles/master tiles/516.png", true);
        setup(516, "/pics/tiles/master tiles/517.png", true);
        setup(517, "/pics/tiles/master tiles/518.png", true);
        setup(518, "/pics/tiles/master tiles/519.png", true);
        setup(519, "/pics/tiles/master tiles/520.png", true);
        setup(520, "/pics/tiles/master tiles/521.png", true);
        setup(521, "/pics/tiles/master tiles/522.png", true);
        setup(522, "/pics/tiles/master tiles/523.png", true);
        setup(523, "/pics/tiles/master tiles/524.png", true);
        setup(524, "/pics/tiles/master tiles/525.png", true);
        setup(525, "/pics/tiles/master tiles/526.png", true);
        setup(526, "/pics/tiles/master tiles/527.png", true);
        setup(527, "/pics/tiles/master tiles/528.png", true);
        setup(528, "/pics/tiles/master tiles/529.png", true);
        setup(529, "/pics/tiles/master tiles/530.png", true);
        setup(530, "/pics/tiles/master tiles/531.png", true);
        setup(531, "/pics/tiles/master tiles/532.png", true);
        setup(532, "/pics/tiles/master tiles/533.png", true);
        setup(533, "/pics/tiles/master tiles/534.png", true);
        setup(534, "/pics/tiles/master tiles/535.png", true);
        setup(535, "/pics/tiles/master tiles/536.png", true);
        setup(536, "/pics/tiles/master tiles/537.png", true);
        setup(537, "/pics/tiles/master tiles/538.png", true);
        setup(538, "/pics/tiles/master tiles/539.png", true);
        setup(539, "/pics/tiles/master tiles/540.png", true);
        setup(540, "/pics/tiles/master tiles/541.png", true);
        setup(541, "/pics/tiles/master tiles/542.png", true);
        setup(542, "/pics/tiles/master tiles/543.png", true);
        setup(543, "/pics/tiles/master tiles/544.png", true);
        setup(544, "/pics/tiles/master tiles/545.png", true);
        setup(545, "/pics/tiles/master tiles/546.png", true);
        setup(546, "/pics/tiles/master tiles/547.png", true);
        setup(547, "/pics/tiles/master tiles/548.png", true);
        setup(548, "/pics/tiles/master tiles/549.png", true);
        setup(549, "/pics/tiles/master tiles/550.png", true);
        setup(550, "/pics/tiles/master tiles/551.png", true);
        setup(551, "/pics/tiles/master tiles/552.png", true);
        setup(552, "/pics/tiles/master tiles/553.png", true);
        setup(553, "/pics/tiles/master tiles/554.png", true);
        setup(554, "/pics/tiles/master tiles/555.png", true);
        setup(555, "/pics/tiles/master tiles/556.png", true);
        setup(556, "/pics/tiles/master tiles/557.png", true);
        setup(557, "/pics/tiles/master tiles/558.png", true);
    }
}
