import exe.ex3.game.Game;
import exe.ex3.game.GhostCL;
import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;

import java.awt.*;

/**
 * This is the major algorithmic class for Ex3 - the PacMan game:
 *
 * This code is a very simple example (random-walk algorithm).
 * Your task is to implement (here) your PacMan algorithm.
 */
public class Ex3Algo implements PacManAlgo {
    private int _count;

    public Ex3Algo() {
        _count = 0;
    }

    @Override
    /**
     *  Add a short description for the algorithm as a String.
     */
            //
    public String getInfo() {
        return null;
    }

    @Override
    /**
     * This ia the main method - that you should design, implement and test.
     */
    public int move(PacmanGame game) {
        if (_count == 0 || _count == 300) {
            int code = 0;
            int[][] board = game.getGame(0);
            printBoard(board);
            int blue = Game.getIntColor(Color.BLUE, code);
            int pink = Game.getIntColor(Color.PINK, code);
            int black = Game.getIntColor(Color.BLACK, code);
            int green = Game.getIntColor(Color.GREEN, code);
            System.out.println("Blue=" + blue + ", Pink=" + pink + ", Black=" + black + ", Green=" + green);
            String pos = game.getPos(code).toString();
            System.out.println("Pacman coordinate: " + pos);
            GhostCL[] ghosts = game.getGhosts(code);
            printGhosts(ghosts);
            int up = Game.UP, left = Game.LEFT, down = Game.DOWN, right = Game.RIGHT;
        }

        _count++;
        int dir = randomDirExt(game);
        return dir;
    }


    private static void printBoard(int[][] b) {
        for (int y = 0; y < b[0].length; y++) {
            for (int x = 0; x < b.length; x++) {
                int v = b[x][y];
                System.out.print(v + "\t");
            }
            System.out.println();
        }
    }

    private static void printGhosts(GhostCL[] gs) {
        for (int i = 0; i < gs.length; i++) {
            GhostCL g = gs[i];
            System.out.println(i + ") status: " + g.getStatus() + ",  type: " + g.getType() + ",  pos: " + g.getPos(0) + ",  time: " + g.remainTimeAsEatable(0));
        }
    }
    private Pixel2D toPixel (String s ){
        Pixel2D ans = new Index2D() ;
        String [] a = s.split(",");
        int x = Integer.parseInt(a[0]);
        int y = Integer.parseInt(a[1]);
        ans = new Index2D(x,y);
        return ans;
    }

    private static int randomDirExt(PacmanGame game) {

        int[][] board = game.getGame(0);
        Map map = new Map(board);

        String p = game.getPos(0);
        String[] parts = p.split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Pixel2D pose = new Index2D(x, y);

        Map2D allDistanceMap = map.allDistance(pose, 1);
        Pixel2D pink = point(pose, map, allDistanceMap);
        Pixel2D[] path = map.shortestPath(pose, pink, 1);
        int dir = find(pose, path[1]);
        return dir;
    }

    private static Pixel2D point(Pixel2D pose, Map map, Map2D allDistanceMap) {

        int di = Integer.MAX_VALUE;
        Pixel2D c = new Index2D(0, 0);
        int width = map.getWidth();
        int height = map.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (map.getPixel(i, j) == 3) {
                    if (allDistanceMap.getPixel(i, j) != -1) {
                        int cu = allDistanceMap.getPixel(i, j);
                        if (di > cu) {
                            di = cu;
                            c = new Index2D(i, j);
                        }
                    }
                }
            }
        }
        return c;
    }

    private static int find(Pixel2D pose, Pixel2D dest) {

        System.out.println("pose.getX(): " + pose.getX() + " pose.getY(): " + pose.getY() + " dest.getX()" + dest.getX() + " dest.getY(): " + dest.getY());
        if (pose.getX() == dest.getX()) {
            if (pose.getY() + 1 == dest.getY()) {
                return Game.UP;
            }
            if (pose.getY() - 1 == dest.getY()) {
                return Game.DOWN;
            }
            if (pose.getY() > dest.getY()) {
                return Game.UP;
            }
            if (pose.getY() < dest.getY()) {
                return Game.DOWN;
            }
        } else if (pose.getY() == dest.getY()) {
            if (pose.getX() + 1 == dest.getX()) {
                return Game.RIGHT;
            }
            if (pose.getX() - 1 == dest.getX()) {
                return Game.LEFT;
            }
            if (pose.getX() > dest.getX()) {
                return Game.RIGHT;
            }
            if (pose.getX() < dest.getX()) {
                return Game.LEFT;
            }
        }
        return -1;
    }

}
