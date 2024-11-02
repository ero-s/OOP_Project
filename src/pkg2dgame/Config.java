package pkg2dgame;

import java.io.*;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"))) {
            // Full Screen
            bw.write(gp.fullScreenOn ? "On" : "Off");
            bw.newLine();

            // Music Volume
//            bw.write(Integer.toString(gp.music.volumeScale));
//            bw.newLine();
//
//            // SE Volume
//            bw.write(Integer.toString(gp.se.volumeScale));
//            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try (BufferedReader br = new BufferedReader(new FileReader("config.txt"))) {
            String s = br.readLine();

            // Full Screen
            if (s != null && s.equals("On")) {
                gp.fullScreenOn = true;
            } else {
                gp.fullScreenOn = false;
            }

            // Music Volume
//            s = br.readLine();
//            if (s != null) {
//                gp.music.volumeScale = Integer.parseInt(s);
//            }
//
//            // SE Volume
//            s = br.readLine();
//            if (s != null) {
//                gp.se.volumeScale = Integer.parseInt(s);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
