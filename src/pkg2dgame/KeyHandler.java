/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dgame;

import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author austi
 */
public class KeyHandler implements KeyListener{
    
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, jPressed, shotkeyPressed, optionPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //character state

        //titleState
        if(gp.gameState == gp.titleState){
            titleState(code);
        }
        //play state
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        //pause
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        //dialogue
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        //character
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }
        //option
        else if(gp.gameState == gp.optionState){
            optionState(code);
        }
        //gameOverState
        else if(gp.gameState == gp.gameOverState){
            gameOverState(code);
        }
    }
    public void titleState(int code){
        if(gp.ui.titleScreenState == 0){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){ // new game
                    if(gp.saveLoad.getHasSave()){
                        gp.ui.titleScreenState = 1;
                    }
                    else{
                        gp.setupGame();
                        gp.player.setDefaultValues();
                        gp.saveLoad.save();
                        gp.saveLoad.setHasSave(false);
                        gp.gameState = gp.playState;
                    }                }

                if(gp.ui.commandNum == 1){ // load
                    boolean isTrue = gp.saveLoad.getHasSave();

                        if (isTrue) {
                            gp.saveLoad.load();
                            gp.gameState = gp.playState;
                        }
                        else{
                            gp.ui.titleScreenState = 2;
                        }
                }
                if(gp.ui.commandNum == 2){ // exit
                    System.exit(0);
                }
            }
        }
        else if(gp.ui.titleScreenState == 1){// newgame with saved progress
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){ // back
                    gp.ui.titleScreenState = 0;
                }
                if(gp.ui.commandNum == 1){ // new game
                    gp.setupGame();
                    gp.player.setDefaultValues();
                    gp.saveLoad.save();
                    gp.saveLoad.setHasSave(false);
                    gp.gameState = gp.playState;
                    gp.ui.titleScreenState = 0;
                }
            }
        }
        else if(gp.ui.titleScreenState == 2){// no saved progresses
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 0){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){ // back
                    gp.ui.titleScreenState = 0;
                }
            }
        }
    }
    public void playState(int code){
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_J){
            jPressed = true;
        }
        if(KeyEvent.VK_K == code){
            shotkeyPressed = true;
        }
        if(KeyEvent.VK_ESCAPE == code){
            gp.gameState = gp.optionState;
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code){
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }

        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
        playerInventory(code);
    }

    public void tradeState(int code){
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        if(gp.ui.subState == 0){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
        }

        if(gp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
    }

    public void playerInventory(int code){
        if (code == KeyEvent.VK_W) {
            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
                gp.playSE(1);
            }
        }

        if (code == KeyEvent.VK_A) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
                gp.playSE(1);
            }
        }

        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
                gp.playSE(1);
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
                gp.playSE(1);
            }
        }
    }

    public void npcInventory(int code){
        if (code == KeyEvent.VK_W) {
            if (gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
                gp.playSE(1);
            }
        }

        if (code == KeyEvent.VK_A) {
            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
                gp.playSE(1);
            }
        }

        if (code == KeyEvent.VK_S) {
            if (gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
                gp.playSE(1);
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSE(1);
            }
        }
    }
    public void gameOverState(int code){
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.retry();
            }
            else if(gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }
    public void optionState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0: maxCommandNum = 6; break;
            case 3, 5: maxCommandNum = 1;break;
        }

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
//            gp.playSE(9);

            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
//            gp.playSE(9);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_K){
            shotkeyPressed = false;
        }
    }
    
}