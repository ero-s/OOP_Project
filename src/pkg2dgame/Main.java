package pkg2dgame;
import javax.swing.JFrame;
public class Main {

    public static JFrame window;
    public static void main(String[] args) {
        //TODO code application logic here
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tales of Two Brothers");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        gamePanel.config.loadConfig();

        if(gamePanel.fullScreenOn){
            window.setUndecorated(true);
        }

        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
