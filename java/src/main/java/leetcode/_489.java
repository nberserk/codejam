package leetcode;


import java.util.*;

import static org.junit.Assert.assertEquals;

public class _489 {


    private interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        public boolean move();

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft();

        public void turnRight();

        // Clean the current cell.
        public void clean();
    }

    private class RobotImpl implements Robot {
        int[][] map;
        int dir;
        int x, y;
        int R, C;

        public void init(int[][] m, int sx, int sy) {
            this.map = m;
            x = sx;
            y = sy;
            dir = 0;
            this.R = m.length;
            this.C = m[0].length;
        }

        @Override
        public boolean move() {
            int nx = x + dirOffset[this.dir][0];
            int ny = y + dirOffset[this.dir][1];
            if (nx < 0 || ny < 0 || nx >= C || ny >= R || map[ny][nx]==0) {
                return false;
            }
            x = nx;
            y = ny;
            return true;
        }

        @Override
        public void turnLeft() {
            this.dir++;
            this.dir %= 4;
        }

        @Override
        public void turnRight() {
            this.dir--;
            if (this.dir < 0) {
                this.dir += 4;
            }
        }

        @Override
        public void clean() {
            assert map[y][x] == 1;
            map[y][x] = -1;
        }

        public boolean allChecked() {
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c] == 1) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void printMap() {
            for (int r = 0; r < R; r++) {
                System.out.println(Arrays.toString(map[r]));
            }
            System.out.println("");
        }
    }

    int[][] dirOffset = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    int gDir = 0;
    private Robot robot;

    String hash(int x, int y){
        return x+","+y;
    }

    void clean(Robot robot, Set<String> visited, int x, int y){
        String hash = hash(x,y);
        if(visited.contains(hash)) return;
        visited.add(hash);
        robot.clean();
//        ((RobotImpl)robot).printMap();
//        System.out.println(String.format("entered %d,%d", x,y));
        for (int i = 0; i < 4; i++) {
            if (robot.move()){
                int nx = x+dirOffset[gDir][0];
                int ny = y+dirOffset[gDir][1];
                clean(robot, visited, nx,ny);

                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            gDir++;
            gDir%=4;
            robot.turnLeft();
        }
    }
    public void cleanRoom(Robot robot) {
        this.robot=robot;
        HashSet<String> visited = new HashSet<>();
        clean(robot, visited, 0,0);
    }

    @org.junit.Test
    public void test() {
        int[][] map = new int[][] {
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1}

        };
        _489 test = new _489();
        RobotImpl ri = new RobotImpl();
        ri.init(map, 3, 1);

        test.cleanRoom(ri);
        assertEquals(true, ri.allChecked());

        assertEquals(-1, -1%1000);
    }
}
