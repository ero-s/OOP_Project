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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import object.OBJ_Heart;
//import java.awt.image.BufferedImage;

public class UI {

	GamePanel gp;
    Graphics2D g2;
	Font arial_40, arial_80b;
	BufferedImage fullHeart, halfHeart, emptyHeart;
	public boolean messageOn = false;
	public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int quitgameState = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    public int subState = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

//	double playTime;
//	DecimalFormat dFormat = new DecimalFormat("#0.00");

	public UI(GamePanel gp) {
		this.gp = gp;

		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80b = new Font("Arial", Font.BOLD, 80);
//		OBJ_Key key = new OBJ_Key();
//		keyImage = key.image;
                Entity heart = new OBJ_Heart(gp);
                fullHeart = heart.image;
                halfHeart = heart.image2;
                emptyHeart = heart.image3;
                
	}

	public void showMessage(String text) {
        message.add(text);
        messageCounter.add(0);
	}

	public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //play state
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMessage();
        }
        //pause state
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        //dialogue state
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        //character state
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
        }
        //option
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();
        }
        //game over
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }
    public void drawMessage(){
        int messageX = gp.tileSize/2;
        int messageY = gp.tileSize*2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32f));
        for(int i = 0; i <message.size(); i++){
            if(message.get(i) != null){
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i)+1;
                messageCounter.set(i, counter);
                messageY += 40;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
        public void drawPlayerLife(){
//            gp.player.maxLife = 10;
//            gp.player.life = 10;
            int x = gp.tileSize/2;
            int y = gp.tileSize/2;
            int i = 0;
            
            //draw empty hearts
            while(i < gp.player.maxLife/2){
                g2.drawImage(emptyHeart, x, y,null);
                i++;
                x+=gp.tileSize;
            }
            
            //reset
            x = gp.tileSize/2;
            y = gp.tileSize/2;
            i = 0;
            while(i < gp.player.life){
                g2.drawImage(halfHeart, x, y,null);
                i++;
                if(i < gp.player.life){
                    g2.drawImage(fullHeart,x,y,null);
                }
                i++;
                x+=gp.tileSize;
            }
        }
        public void drawTitleScreen(){
            if(titleScreenState == 0){
                g2.setColor(Color.orange);
                g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 96F));
                String text = "Two Brothers in a Farm";
                int x = getXforCenteredtext(text);
                int y = gp.tileSize * 3;

                //shadow
                g2.setColor(Color.black);
                g2.drawString(text, x+5, y+5);
                //main text
                g2.setColor(Color.white);
                g2.drawString(text, x, y);

                //menu
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
                text = "NEW GAME";
                x = getXforCenteredtext(text);
                y += gp.tileSize * 2;
                g2.drawString(text, x, y);
                if(commandNum == 0){
                    g2.drawString(">", x-gp.tileSize, y);
                    if(gp.keyH.enterPressed){
                        gp.player.setDefaultValues();
                        gp.saveLoad.save();
                        gp.saveLoad.hasSave = false;
                    }
                }

                text = "LOAD GAME";
                x = getXforCenteredtext(text);
                y += gp.tileSize/2;
                g2.drawString(text, x, y);
                if(commandNum == 1){
                    g2.drawString(">", x-gp.tileSize, y);
                }

                text = "QUIT GAME";
                x = getXforCenteredtext(text);
                y += gp.tileSize/2;
                g2.drawString(text, x, y);
                if(commandNum == 2){
                    g2.drawString(">", x-gp.tileSize, y);
                }
            }
              
//            save and load mechanic
            else if(titleScreenState == 1) {
                drawLoadExist();
            }
            else if(titleScreenState == 2){
                drawNoLoad();
            }
            
        }
        public void drawNoLoad(){
            commandNum = 0;
            g2.setColor(Color.orange);
            g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
            int x,y;

            String text;

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32f));

            text = "You have no saved progresses.";

            // shadow
            g2.setColor(Color.black);
            x = getXforCenteredtext(text);
            y = gp.tileSize * 2;
            g2.drawString(text, x, y);

            //main(?)
            g2.setColor(Color.white);
            g2.drawString(text, x-4, y-4);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            text = "BACK";
            x = getXforCenteredtext(text);
            y += gp.tileSize * 2;

            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
                if(gp.keyH.enterPressed){
                    titleScreenState = 0;
                }
            }
        }
        public void drawLoadExist() {
            g2.setColor(Color.orange);
            g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
            int x,y;

            String text;

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32f));

            text = "Load exists, Are you sure to create game?";

            // shadow
            g2.setColor(Color.black);
            x = getXforCenteredtext(text);
            y = gp.tileSize * 2;
            g2.drawString(text, x, y);

            //main(?)
            g2.setColor(Color.white);
            g2.drawString(text, x-4, y-4);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            text = "BACK";
            x = getXforCenteredtext(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
                if(gp.keyH.enterPressed){
                    titleScreenState = 0;
                    return;
                }
            }

            text = "NEW GAME";
            x = getXforCenteredtext(text);
            y += gp.tileSize/2;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
                if(gp.keyH.enterPressed){
                    gp.player.setDefaultValues();
                    gp.saveLoad.save();
                    commandNum = 0;
                }
            }
        }

        public void drawGameOverScreen() {
            g2.setColor(new Color(0,0,0,150));
            g2.drawRect(0,0,gp.screenWidth, gp.screenHeight);
            int x;
            int y;
            String text;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

            text = "Game Over";
            // Shadow
            g2.setColor(Color.black);
            x = getXforCenteredtext(text);
            y = gp.tileSize * 2;
            g2.drawString(text, x, y);

            // Main
            g2.setColor(Color.white);
            g2.drawString(text, x - 4, y - 4);

            // Retry
            g2.setFont(g2.getFont().deriveFont(50f));
            text = "Retry";
            x = getXforCenteredtext(text);
            y += gp.tileSize * 2.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - 40, y);
            }

            // Back to the title screen
            text = "Quit";
            x = getXforCenteredtext(text);
            y += gp.tileSize/2;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - 40, y);
            }

        }
        public void drawDialogueScreen(){
            //window
            int x = gp.tileSize /2;
            int y = gp.tileSize * 21 /4;
            int width = gp.screenWidth - x*2;
            int height = gp.tileSize * 2;
            drawSubWindow(x ,y, width, height);
            
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32f));
            x += gp.tileSize /2;
            y += gp.tileSize / 2;
            
            for(String line : currentDialogue.split("\n")){
                g2.drawString(line, x, y);
                y += 40;
            }
        }

    public void drawOptionScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gp.tileSize * 4;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0: options_top(frameX, frameY);break;
            case 1: options_fullScreenNotification(frameX, frameY);break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_quitGame(frameX, frameY);break;
            case 4: options_saveGame(frameX, frameY);break;
            case 5: options_SaveOnQuit(frameX,frameY);break;
        }
        gp.keyH.enterPressed = false;
    }

    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;
        int lineHeight = gp.tileSize/2;

        // TITLE
        String text = "Options";
        textX = getXforCenteredtext(text);
        textY = frameY + lineHeight;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;

        textY += lineHeight*2;
        g2.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                gp.fullScreenOn = !gp.fullScreenOn;
                subState = 1;
            }
        }

        // MUSIC
        textY += lineHeight;
        g2.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        // SFX
        textY += lineHeight;
        g2.drawString("SFX", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }

        // CONTROL
        textY += lineHeight;
        g2.drawString("Control", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }

        textY += lineHeight;
        g2.drawString("Save", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 4;
                commandNum = 4;
            }
        }

        // Quit
        textY += lineHeight;
        g2.drawString("Quit", textX, textY);
        if (commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                subState = 3;
                commandNum = 0;
            }
        }

        // Back
        textY += lineHeight*3;
        g2.drawString("Back", textX, textY);
        if (commandNum == 6) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                gp.gameState = gp.playState;
                commandNum = 0;
                subState = 0;
            }
        }

        // FULL SCREEN CHECK BOX
        textX = frameX + (int)(gp.tileSize*3);
        textY = frameY + lineHeight*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn){
            g2.fillRect(textX, textY, 24,24);
        }

        // MUSIC VOLUME
        textY += lineHeight;
        g2.drawRect(textX, textY, 200, 24);
        // SFX VOLUME
        textY += lineHeight;
        g2.drawRect(textX, textY, 200, 24);

        gp.config.saveConfig();
    }
    public void options_saveGame(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3/2;
        commandNum = 0;
        currentDialogue = "Saving Game...";
        gp.saveLoad.save();

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY += gp.tileSize * 6/2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
            }
        }
    }

    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3/2;

        currentDialogue = "The change will take effect \n after restarting the game.";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY += gp.tileSize * 6/2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
            }
        }
    }

    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Control";
        int lineHeight = gp.tileSize/2;
        textX = getXforCenteredtext(text);
        textY = frameY + lineHeight;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += lineHeight;
        g2.drawString("Move", textX, textY);
        textY += lineHeight;
        g2.drawString("Interact", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Skill 1", textX, textY);
        textY += lineHeight;
        g2.drawString("Skill 2", textX, textY);
        textY += lineHeight;
        g2.drawString("Skill 3", textX, textY);
        textY += lineHeight;
        g2.drawString("Character Screen", textX, textY);
        textY += lineHeight;
        g2.drawString("Pause", textX, textY);
        textY += lineHeight;
        g2.drawString("Options", textX, textY);

        // BACK
        textY += lineHeight ;
        g2.drawString("Back", textX, textY);
        g2.drawString(">", textX - 25, textY);
        if (commandNum == 0) {
            if (gp.keyH.enterPressed) {
                subState = 0;
            }
        }

        textX = frameX + gp.tileSize * 4;
        textY = frameY + lineHeight;
        textY += lineHeight;

        g2.drawString("WASD", textX, textY);
        textY += lineHeight;

        g2.drawString("ENTER", textX, textY);
        textY += lineHeight;

        g2.drawString("J", textX, textY);
        textY += lineHeight;

        g2.drawString("K", textX, textY);
        textY += lineHeight;

        g2.drawString("L", textX, textY);
        textY += lineHeight;

        g2.drawString("M", textX, textY);
        textY += lineHeight;

        g2.drawString("C", textX, textY);
        textY += lineHeight;

        g2.drawString("P", textX, textY);
        textY += lineHeight;

        g2.drawString("ESC", textX, textY);
        textY += lineHeight;
    }

    public void options_quitGame(int frameX, int frameY) {

        int lineHeight = gp.tileSize / 2;
        int textX = frameX + gp.tileSize;
        int textY = frameY + lineHeight;

        currentDialogue = "Quit the game and return\nto the title screen?";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // Check for navigation input to switch between "Yes" and "No"
        if (gp.keyH.upPressed) {
            commandNum = (commandNum == 0) ? 1 : 0;  // Toggle between 0 and 1

        } else if (gp.keyH.downPressed) {
            commandNum = (commandNum == 1) ? 0 : 1;  // Toggle between 1 and 0

        }

        // YES option
        String text = "Yes";
        textX = getXforCenteredtext(text);
        textY += lineHeight * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                commandNum = 0;
                subState = 5;
            }
        }

        // NO option
        text = "No";
        textX = getXforCenteredtext(text);
        textY += lineHeight;
        g2.drawString(text, textX, textY);

        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 0;
            }
        }
    }

    public void options_SaveOnQuit(int frameX, int frameY){
        int lineHeight = gp.tileSize / 2;
        int textX = frameX + gp.tileSize;
        int textY = frameY + lineHeight;

        currentDialogue = "Would you like to save\nbefore quitting?";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // Check for navigation input to switch between "Yes" and "No"
        if (gp.keyH.upPressed) {
            commandNum = (commandNum == 0) ? 1 : 0;
            gp.keyH.upPressed = false;  // Reset key state
        } else if (gp.keyH.downPressed) {
            commandNum = (commandNum == 1) ? 0 : 1;
            gp.keyH.downPressed = false;  // Reset key state
        }


        // YES option
        String text = "Yes";
        textX = getXforCenteredtext(text);
        textY += lineHeight * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                gp.saveLoad.save();
                commandNum = 0;
                subState = 0;
                gp.gameState = gp.titleState;

            }
        }
        // NO option
        text = "No";
        textX = getXforCenteredtext(text);
        textY += lineHeight;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                commandNum = 0;
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }
    }


    public void drawCharacterScreen(){
            //display window
            final int frameX = gp.tileSize/2;
            final int frameY = gp.tileSize;
            final int frameWidth = gp.tileSize *4;
            final int frameHeight = gp.tileSize *5;
            drawSubWindow(frameX, frameY, frameWidth, frameHeight);

            //display text
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32f));
            int textX = frameX + 20;
            int textY = frameY + gp.tileSize/2;
            final int lineHeight = 64;

            //labels
            g2.drawString("Level: ",textX,textY);
            textY +=lineHeight;
            g2.drawString("Life: ",textX,textY);
            textY +=lineHeight;
            g2.drawString("Attack Power: ",textX,textY);
            textY +=lineHeight;
            g2.drawString("Defense: ",textX,textY);
            textY +=lineHeight;
            g2.drawString("Exp: ",textX,textY);
            textY +=lineHeight;
            g2.drawString("Next Level: ",textX,textY);
            textY +=lineHeight;
            g2.drawString("Coin: ",textX,textY);
            textY +=lineHeight;

            //values
            int tailX = (frameX + frameWidth) - 30;
            textY = frameY + gp.tileSize/2;
            String value;

            //level
            value = String.valueOf(gp.player.level);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value,textX,textY);
            textY +=lineHeight;

            //life
            value = String.valueOf(gp.player.maxLife);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value,textX,textY);
            textY +=lineHeight;

            //atkPower
            value = String.valueOf(gp.player.atkPower);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value,textX,textY);
            textY +=lineHeight;

            //defense
            value = String.valueOf(gp.player.defense);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value,textX,textY);
            textY +=lineHeight;

            //exp
            value = String.valueOf(gp.player.exp);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value,textX,textY);
            textY +=lineHeight;

            //nextLevelExp
            value = String.valueOf(gp.player.nextLevelExp);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value,textX,textY);
            textY +=lineHeight;

            //coin
            value = String.valueOf(gp.player.coin);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value,textX,textY);
            textY +=lineHeight;
        }

        
        public void drawSubWindow(int x, int y, int width, int height){
            Color c = new Color(165, 42 ,42, 200);
            g2.setColor(c);
            g2.fillRoundRect(x, y, width, height, 35, 35);
            
            c = new Color(255,255,255);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(x+5, y+5, width-10, height-10,25,25);
        }
        public void drawPauseScreen(){
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
            String text = "PAUSED";
            int x = getXforCenteredtext(text);
            int y = gp.screenHeight/2;
            
            g2.drawString(text, x, y);
            
        }
        public int getXforCenteredtext(String text){
            int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            int x = gp.screenWidth/2 - length/2;
            return x;
        }
        public int getXForAlignToRight(String text, int tailX){
            int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            int x = tailX - length/2;
            return x;
        }
}
