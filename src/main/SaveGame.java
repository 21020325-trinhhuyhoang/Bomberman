package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame {
    GamePanel gp;

    public SaveGame(GamePanel gp) {
        this.gp = gp;
    }

    public void MakeSave(String command) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("/resouces/Save/Save.txt"));

            if (command == "New") {
                bw.write("NEWGAME");
            } else {
                /**
                 * cac thong so trong file save:
                 * Live cua player
                 * do dai fire
                 * bomb toi da
                 * toc do di chuyen
                 * chieu rong cua map
                 * chieu cao cua map
                 * cac tileMap
                 * so luong monster
                 * type monster[i]
                 * toa do x monster[i]
                 * toa do y monster[i]
                 */
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("Khong the load file save game!");
            e.printStackTrace();
        }
    }
}
