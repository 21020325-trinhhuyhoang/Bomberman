package Enemy;

import main.GamePanel;

import java.util.Comparator;
import java.util.PriorityQueue;
import Enemy.aPair;

public class AStar {
    public static int FindPath(GamePanel gp, int x, int y, int gx, int gy) {

        if (x == gx && y == gy) { return -1; }

        Comparator<inPQ> cmt = new PQComparator();
        PriorityQueue<inPQ> pq = new PriorityQueue<inPQ>(3 * gp.maxWorldCol * gp.maxWorldRow,cmt);
        int [][] f = new int[gp.maxWorldCol + 5][gp.maxWorldRow + 5];
        aPair [][] trace = new aPair[gp.maxWorldCol + 5][gp.maxWorldRow + 5];

        for (int i = 0; i <= gp.maxWorldCol; ++i) {
            for (int j = 0; j <= gp.maxWorldRow; ++j) {
                f[i][j] = -1;
                trace[i][j] = new aPair(-1,-1);
            }
        }

        f[x][y] = 0;

        pq.clear();
        inPQ tmp = new inPQ(0,x,y);
        inPQ newInPQ;
        pq.add(tmp);

        int _x, _y, _x2, _y2, _val, _val2;
        //System.out.println("a");
        //System.out.println(x + " " + y);

        while (true) {
            tmp = pq.peek();
            pq.poll();

            if (tmp == null) break;

            _x = tmp.x;
            _y = tmp.y;
            _val = tmp.val;

            if (_x == gx && _y == gy) { continue; }
            //System.out.println(_x + " " + _y + " " + _val);

            //______
            _val2 = _val + 1;
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    if (i * j == 0 && i + j != 0) {
                        _x2 = _x + i;
                        _y2 = _y + j;
                        //System.out.println(" ||   " + _x2 + " " + _y2 + " " + gp.tileM.mapEConllision[_x2][_y2]);

                        if (gp.tileM.mapEConllision[_x2][_y2] <= 0) {

                            if (f[_x2][_y2] == -1 || f[_x2][_y2] > _val2) {
                                f[_x2][_y2] = _val2;
                                trace[_x2][_y2] = new aPair(_x, _y);
                                newInPQ = new inPQ(_val2, _x2, _y2);
                                pq.add(newInPQ);
                            }
                        }
                    }
                }
            }
            //______

        }

        //System.out.println(f[gx][gy]);

        if (f[gx][gy] == -1) { return -1; }

        _x = gx; _y = gy;
        aPair newAPair;
        while (true) {
            newAPair = trace[_x][_y];

            _x2 = newAPair.x;
            _y2 = newAPair.y;

            if (_x2 == x && _y2 == y) break;

            _x = _x2;
            _y = _y2;
        }

        //return new aPair(_x, _y);

        int res = -1;
        if (x == _x && y == _y - 1) {
            res = 3;
        }

        if (x == _x && y == _y + 1) {
            res = 1;
        }

        if (x == _x - 1 && y == _y) {
            res = 4;
        }

        if (x == _x + 1 && y == _y) {
            res = 2;
        }

        //System.out.println(res);
        return res;
    }

    /**
     * ham nay dung de tim duong den vi tri bomberman cho cac mob di xuyen tuong.
     */

    public static int FindPath2(GamePanel gp, int x, int y, int gx, int gy) {

        if (x == gx && y == gy) { return -1; }

        Comparator<inPQ> cmt = new PQComparator();
        PriorityQueue<inPQ> pq = new PriorityQueue<inPQ>(3 * gp.maxWorldCol * gp.maxWorldRow,cmt);
        int [][] f = new int[gp.maxWorldCol + 5][gp.maxWorldRow + 5];
        aPair [][] trace = new aPair[gp.maxWorldCol + 5][gp.maxWorldRow + 5];

        for (int i = 0; i <= gp.maxWorldCol; ++i) {
            for (int j = 0; j <= gp.maxWorldRow; ++j) {
                f[i][j] = -1;
                trace[i][j] = new aPair(-1,-1);
            }
        }

        f[x][y] = 0;

        pq.clear();
        inPQ tmp = new inPQ(0,x,y);
        inPQ newInPQ;
        pq.add(tmp);

        int _x, _y, _x2, _y2, _val, _val2;
        //System.out.println("a");
        //System.out.println(x + " " + y);

        while (true) {
            tmp = pq.peek();
            pq.poll();

            if (tmp == null) break;

            _x = tmp.x;
            _y = tmp.y;
            _val = tmp.val;

            if (_x == gx && _y == gy) { continue; }
            //System.out.println(_x + " " + _y + " " + _val);

            //______
            _val2 = _val + 1;
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    if (i * j == 0 && i + j != 0) {
                        _x2 = _x + i;
                        _y2 = _y + j;
                        //System.out.println(" ||   " + _x2 + " " + _y2 + " " + gp.tileM.mapEConllision[_x2][_y2]);

                        if (gp.tileM.mapTileNum[_x2][_y2] != 1 && gp.tileM.mapBombs[_x2][_y2] == 0) {

                            if (f[_x2][_y2] == -1 || f[_x2][_y2] > _val2) {
                                f[_x2][_y2] = _val2;
                                trace[_x2][_y2] = new aPair(_x, _y);
                                newInPQ = new inPQ(_val2, _x2, _y2);
                                pq.add(newInPQ);
                            }
                        }
                    }
                }
            }
            //______

        }

        //System.out.println(f[gx][gy]);

        if (f[gx][gy] == -1) { return -1; }

        _x = gx; _y = gy;
        aPair newAPair;
        while (true) {
            newAPair = trace[_x][_y];

            _x2 = newAPair.x;
            _y2 = newAPair.y;

            if (_x2 == x && _y2 == y) break;

            _x = _x2;
            _y = _y2;
        }

        //return new aPair(_x, _y);

        int res = -1;
        if (x == _x && y == _y - 1) {
            res = 3;
        }

        if (x == _x && y == _y + 1) {
            res = 1;
        }

        if (x == _x - 1 && y == _y) {
            res = 4;
        }

        if (x == _x + 1 && y == _y) {
            res = 2;
        }

        //System.out.println(res);
        return res;
    }
}

class inPQ {
    public int val;
    public int x,y;

    public inPQ(int val, int x, int y) {
        this.val = val;
        this.x = x;
        this.y = y;
    }
}
class PQComparator implements Comparator<inPQ> {
    @Override
    public int compare(inPQ a, inPQ b) {
        if (a.val < b.val) {
            return -1;
        }
        if (a.val > b.val) {
            return 1;
        }

        return 0;
    }
}
