package pkg2dgame;

import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    private int sceneNum;
    private int scenePhase;

    // Scene number
    private final int NA = 0;
    private final int theBeginning = 1;
    private final int pickleRickScene = 2;


    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
    }

    // could be irrelevant
    public int getSceneNum(int sceneNum) {

        switch (sceneNum) {
            case 0:
                return NA;
            case 1:
                return theBeginning;
            case 2:
                return pickleRickScene;
        }

        return 0;
    }

    public void setSceneNum(int sceneNum) {
        this.sceneNum = sceneNum;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        switch (sceneNum) {
            case 0: theBeginning(); break;
        }
    }

    public void theBeginning() {

        // if narration is done theBeginning method is triggered so from Narration state to cutscene state

        /*
            if (narrationDone == true) {
                triggers this event
            }


         */
    }
}
