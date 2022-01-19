package design.connect4;

import design.connect4.model.Board;
import design.connect4.model.Point;
import design.connect4.model.Stone;
import design.connect4.model.User;

// connect4
//
public class Connect4Game {
    public static void main(String[] args) {
        User black = new User(Stone.Black, "Darren");
        User white = new User(Stone.White, "White");

        Board board = new Board(10, 10, new User[]{black, white});
        board.putStone(new Point(0,0));
        board.putStone(new Point(0,0));
    }
}