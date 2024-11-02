package ai;

import pkg2dgame.GamePanel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PathFinder {
    //do mapTransition first

    GamePanel gp;
    Node[][] node;
    ArrayList <Node> openList = new ArrayList<>();
    public ArrayList <Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNodes();
    }
    public void instantiateNodes(){
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0, row = 0;
        while(col<gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row] = new Node(col,row);
            col++;
            if(col == gp.maxWorldCol){
                col = 0; row++;
            }
        }
    }
    public void resetNodes() {
        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            // Reset open, checked and solid state
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        // Set Start and Goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            // SET SOLID NODE
            // CHECK TILES
//            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
//            if (gp.tileM.tile[tileNum].collision == true) {
//                node[col][row].solid = true;
        }
    }
}
