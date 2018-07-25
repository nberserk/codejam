package leetcode;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

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
            if (nx < 0 || ny < 0 || nx >= C || ny >= R) {
                return false;
            }
            if (map[ny][nx] == 0) {
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
            if (map[y][x] != 1) {
                assert false;
            }

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

        public void pringMap() {
            for (int r = 0; r < R; r++) {
                System.out.println(Arrays.toString(map[r]));
            }
        }


    }

    int mod = 1000;
    int[][] dirOffset = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    int x = 0, y = 0;
    int gDir = 0;


    private class Node {
        int x, y;
        int pos; //y*mod+x
        Node parent;

        public Node(int x, int y, Node p) {
            this.x = x;
            this.y = y;
            this.pos = y * mod + x;
            this.parent = p;
        }
    }

    int diffToDir(int dx, int dy) {
        assert (Math.abs(dx) + Math.abs(dy)) == 1;
        if (dx < 0) {
            return 1;
        }
        if (dx > 0) {
            return 3;
        }
        if (dy < 0) {
            return 2;
        }
        return 0;
    }

    public boolean moveTo(int tx, int ty, Robot robot) {
        int orgx = x, orgy = y;
        int dx = tx - x;
        int dy = ty - y;
        int dd = diffToDir(dx, dy);
        int ddir = dd - gDir;
        if (ddir < 0) {
            ddir += 4;
        }
        if (ddir == 3) {
            gDir--;
            if (gDir < 0) {
                gDir += 4;
            }
            robot.turnRight();
        } else {
            while (ddir > 0) {
                gDir++;
                robot.turnLeft();
            }
            if (gDir >= 4) {
                gDir -= 4;
            }
        }

        if (!robot.move()) {
            x = orgx;
            y = orgy;
            return false;
        } else {
            x += dirOffset[gDir][0];
            y += dirOffset[gDir][1];
        }
        return true;
    }

    public void cleanRoom(Robot robot) {

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(0, 0, null));
        HashSet<Integer> visited = new HashSet<>();
        //HashMap<Integer, Node> tiles = new HashMap<>();

        while (stack.size() > 0) {
            Node cur = stack.pop();
            if (visited.contains(cur.pos)) {
                continue;
            }
            visited.add(cur.pos);
            if (x != cur.x || y != cur.y) {
                // move to cur using
                Node t = cur;
                while (true) {
                    if (Math.abs(x - cur.x) + Math.abs(y - cur.y) == 1) {
                        moveTo(cur.x, cur.y, robot);
                        break;
                    }
                    // move
                    t = t.parent;
                    if (t == null) {
                        break;
                    }
                    moveTo(t.x, t.y, robot);
                }
                if (x != cur.x && y != cur.y) { // met wall
                    assert false;
                    continue;
                }
            }
            ((RobotImpl) robot).pringMap();
            System.out.println(" ");
            robot.clean();
            ((RobotImpl) robot).pringMap();


            for (int i = 0; i < 4; i++) {
                int nx = x + dirOffset[i][0];
                int ny = y + dirOffset[i][1];
                stack.push(new Node(nx, ny, cur));
            }
        }
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

    }


}
