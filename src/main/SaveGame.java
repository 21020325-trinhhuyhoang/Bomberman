package main;

import Convert.Convert;

import java.io.*;

public class SaveGame {
    GamePanel gp;
    Convert cv;

    public SaveGame(GamePanel gp) {
        this.gp = gp;
        this.cv = new Convert();
    }

    public void MakeSave(String command) {
        try {
            //BufferedWriter bw = new BufferedWriter(new FileWriter("/resouces/Save/Save.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("Save.txt"));

            if (command == "NEW") {
                bw.write("NEWGAME");
            } else {
                /**
                 * cac thong so trong file save:
                 * Live cua player
                 * do dai fire
                 * bomb toi da
                 * toc do di chuyen
                 * diem (score)
                 * scorelive
                 * ten map (level).
                 */
                bw.write("" + gp.live);
                bw.newLine(); bw.write("" + gp.maxFire);
                bw.newLine(); bw.write("" + gp.maxBomb);
                bw.newLine(); bw.write("" + gp.speed);
                bw.newLine(); bw.write("" + gp.newscore);
                bw.newLine(); bw.write("" + gp.newScoreLive);
                bw.newLine(); bw.write("" + gp.level);
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("Khong the load file save game!");
            e.printStackTrace();
        }
    }

    public void LoadSave() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Save.txt"));

            //live cua player
            String s = br.readLine();
            if (s.equals("NEWGAME") == true) {
                gp.resetAllData();
                return;
            }
            gp.live = cv.stringtoInt(s);

            //do dai fire
            s = br.readLine();
            gp.maxFire = cv.stringtoInt(s);

            //bomb toi da
            s = br.readLine();
            gp.maxBomb = cv.stringtoInt(s);

            //toc do di chuyen
            s = br.readLine();
            gp.speed = cv.stringtoInt(s);

            //diem (score)
            s = br.readLine();
            gp.newscore = cv.stringtoInt(s);

            //diem live (scoreLive)
            s = br.readLine();
            gp.newScoreLive = cv.stringtoInt(s);

            //ten map
            s = br.readLine();
            gp.level = cv.stringtoInt(s);

            br.close();
        } catch(Exception e) {
            System.out.println("Khong load duoc file save!");
            e.printStackTrace();
        }
    }
}
