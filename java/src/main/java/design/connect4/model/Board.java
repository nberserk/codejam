package design.connect4.model;

import java.util.Arrays;



public class Board {

    private final User[] users;
    private Stone[][] stone;
    private int turn;

    public Board(int sizeX, int sizeY, User[] user) {
        stone = new Stone[sizeY][sizeX];
        for (int i=0;i<sizeY;i++){
            for (int j=0;j<sizeX;j++){
                stone[i][j] = Stone.Empty;
            }
        }
        this.users = user;
        turn = 0;
    }

    public void putStone(Point point) {
        if (stone.length <= point.getY() || stone[0].length <= point.getX()) {
            // log
            return;
        }
        if (stone[point.getY()][point.getX()] != Stone.Empty) {
            return;
        }
        User currentUser = getCurrentUser();
        stone[point.getY()][point.getX()] = currentUser.getStone();
        turn++;

        System.out.println(toString());

    }

    public User getCurrentUser() {
        if (turn%2 ==0){
            return users[0];
        }
        return users[1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------\n");
        for (int i = 0; i < stone.length; i++) {
            sb.append(Arrays.toString(stone[i]));
            sb.append("\n");
        }
        sb.append("---------------------\n");
        return sb.toString();
    }
}
