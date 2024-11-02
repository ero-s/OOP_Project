/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dgame;

/**
 *
 * @author austi
 */
import Entity.Entity;
import Entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import tile.TileManager;
public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 32;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 14;
    public final int maxScreenRow = 8;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize  * maxScreenRow;
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    Thread getGameThread;
    
    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //full screen settings
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    //FPS
    int FPS = 60;
    
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    
    //Entity and object
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    Thread gameThread;
    
    //gamestate
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionState = 5;
    public int gameOverState = 6;
    
    public Entity obj[] = new Entity[100];
    public Entity npc[] = new Entity[100];
    public Entity monster[] = new Entity[100];
    public ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();
    
   
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame(){
        GamePanel gp;
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
        
//        playMusic(0);
        if(fullScreenOn){
            setFullScreen();
        }
    }
    public void retry(){
        player.setDefaultPositions();
        player.restoreLife();
        aSetter.setNPC();
        aSetter.setMonster();
    }
    public void restart(){
        player.setDefaultValues();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
    }
    public void setFullScreen() {

        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                drawToTempScreen();
                drawToScreen();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }


    public void update(){
        if(gameState == playState){
            //player
            player.update();
            
            //npc
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            //monster
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    if(monster[i].alive && !monster[i].dead){
                        monster[i].update();
                    }
                    else{
                        monster[i] = null;
                    }

                }
            }
            //projectile
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i).alive){
                    projectileList.get(i).update();
                }else{
                    projectileList.remove(projectileList.get(i));
                }
            }
            
        }
        if(gameState == pauseState){
            //nothing
        }
        
        
    }
    public void drawToTempScreen(){
        //title screen
        if(gameState == titleState){
            ui.draw((Graphics2D) g2);
        }
        else {
            //tile
            tileM.draw((Graphics2D) g2);
            entityList.add(player);
            //npc draw
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            //object draw
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            //monster draw
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }

            //projectile draw
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }

            //sort
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });
            //draw entities
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw((Graphics2D) g2);
            }
            for (int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }

            //ui
            ui.draw((Graphics2D) g2);
        }
    }
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
               
    }
}
