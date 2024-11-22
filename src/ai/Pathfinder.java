package ai;

import pkg2dgame.GamePanel;
import java.util.ArrayList;
import java.util.LinkedList;

public class Pathfinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public LinkedList<Node> pathList = new LinkedList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public Pathfinder(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();
    }

    public void instantiateNodes() {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0, row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row] = new Node(col,row);
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes() {
        int col = 0, row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            col++;
            if(col == gp.maxWorldCol){
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

        // Set start and goal nodes
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int row = 0, col = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if(gp.tileM.tile[tileNum] != null){
                if(gp.tileM.tile[tileNum].collision){
                    node[col][row].solid = true;
                }
            }


            for(int i = 0; i < gp.iTile[1].length; i++){
                if(gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].destructible){
                    int itCol = gp.iTile[gp.currentMap][i].worldX/gp.tileSize;
                    int itRow = gp.iTile[gp.currentMap][i].worldY/gp.tileSize;
                    node[itCol][itRow].solid = true;
                }
            }
            getCost(node[col][row]);
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }

    public void getCost(Node node) {
        // G cost (distance from start node)
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // H cost (distance to goal node)
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // F cost (total cost)
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (!goalReached && step < 500) {
            int col = currentNode.col, row = currentNode.row;

            //check current node
            currentNode.checked = true;
            openList.remove(currentNode);

            //open the up node
            if(row - 1 >= 0){
                openNode(node[col][row-1]);
            }

            //open the left node
            if(col - 1 >= 0){
                openNode(node[col-1][row]);
            }

            //open the down node
            if(row + 1 < gp.maxWorldRow){
                openNode(node[col][row+1]);
            }
            //open the right node
            if(col + 1 < gp.maxWorldCol){
                openNode(node[col+1][row]);
            }
            //find the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;
            for(int i = 0; i < openList.size(); i++){
                if(openList.get(i).fCost < bestNodeFCost){
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).fCost;
                }
                else if(openList.get(i).fCost == bestNodeFCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            // if no node in openList
            if(openList.isEmpty()){
                break;
            }
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackThePath() {
        Node current = goalNode;
        while (current != null && current != startNode) {
            pathList.addFirst(current);
            current = current.parent;
        }
    }
}
