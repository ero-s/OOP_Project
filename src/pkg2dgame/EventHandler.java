package pkg2dgame;

import Entity.Entity;

public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;
    public int map, row, col;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    
    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        map = 0;
        col = 0;
        row = 0;
        while(map < gp.maxMap && col< gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 42;
            eventRect[map][col][row].y = 42;
            eventRect[map][col][row].width = 1;
            eventRect[map][col][row].height = 1;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;

                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
    }
    
    public void checkEvent(){
        //checks if player is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        
        if(canTouchEvent){
            
//            if(hit(0,24,21,"any")==true){damagePit(gp.dialogueState);}
//            if(hit(0,20,21,"any")==true){healingPool(gp.dialogueState);}

            // spring to autumn
            if(hit(0,31,41,"any")){teleport(1,9,44);}
            else if(hit(1,8,45,"any")){teleport(0,29,41);}

            // spring to castle
            if(hit(0,5,11,"any") || hit(0,4,11,"any")){teleport(3,24,6);}
            else if(hit(3,30,30,"any")){teleport(0,4,10);}

            if(hit(1,14,2,"any")){teleport(2,18,7);}

            if(hit(0, 32, 33, "up") == true) { speak(gp.npc[0][0]); }
        }
    }
    public void teleport(int map, int col, int row){
        gp.gameState = gp.transitionState;
        this.map = map;
        this.col = col;
        this.row = row;
        canTouchEvent = false;
    }

    public void speak(Entity entity){
        if(gp.keyH.enterPressed == true){
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }
    public boolean hit(int map, int col, int row, String reqDirection){
        boolean hit = false;
        if(map == gp.currentMap){
            gp.player.mapTransArea.x = gp.player.worldX + gp.player.mapTransArea.x;
            gp.player.mapTransArea.y = gp.player.worldY + gp.player.mapTransArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.mapTransArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                if(gp.player.direction.contentEquals(reqDirection)|| reqDirection.contentEquals("any")){
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.mapTransArea.x = gp.player.mapTransAreaDefaultX;
            gp.player.mapTransArea.y = gp.player.mapTransAreaDefaultY;

            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit";
        gp.player.life -= 1;
//        eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    
    public void healingPool(int gameState){

        if(gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.ui.currentDialogue = "You drank the water.\nYour life and mana have been recovered.";
            gp.player.life = gp.player.maxLife;
            gp.player.mana =  gp.player.maxMana;
            gp.aSetter.setMonster();
        }
    }
}
