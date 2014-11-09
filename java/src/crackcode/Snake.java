package crackcode;

import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class Snake {
	private Deque<Point> mTails = new LinkedList<Point>();
	private Point mPrevPos = new Point();
	private Direction mDir = Direction.DOWN;
	private Board mBoard;

	enum Direction {
		UP, DOWN, LEFT, RIGHT
	};

	private void setPos(int x, int y) {
		if (mTails.size() > 0) {
			return;
		}
		mTails.add(new Point(x, y));
	}

	void move() {
		Point cur = mTails.getFirst();
		int nx = cur.x;
		int ny = cur.y;

		if (mDir == Direction.DOWN) {
			ny += 1;
		} else if (mDir == Direction.UP) {
			ny -= 1;
		} else if (mDir == Direction.RIGHT)
			nx += 1;
		else
			nx -= 1;
		if (nx < 0 || ny < 0 || nx >= mBoard.getSize() || ny >= mBoard.getSize()) {
			System.out.println("die");
			return;
		}

		mPrevPos = new Point(mTails.getFirst().x, mTails.getFirst().y);

		if (mTails.size() > 1) {
			Point last = mTails.removeLast();
			last.setLocation(nx, ny);
			mTails.addFirst(last);
		} else {
			mTails.getFirst().x = nx;
			mTails.getFirst().y = ny;
		}

		if (mTails.getFirst().equals(mBoard.getStarPos())) {
			mBoard.onStarCaptured();
			addTail();
		}
	}

	void changeDirection(Direction dir) {
		mDir = dir;
	}

	private void addTail() {
		mTails.addLast(new Point(mPrevPos));
	}

	public void print() {
		int n = mBoard.getSize();
		Point p = new Point();
		for (int y = 0; y < n; y++) {
			p.y = y;
			for (int x = 0; x < n; x++) {
				p.x = x;
				if (p.equals(mBoard.getStarPos())) {
					System.out.print("*");
				}
				if (mTails.contains(p)) {
					System.out.print("x");
				} else {
					System.out.print("_");
				}
			}
			System.out.println("\n");
		}
		System.out.println("\n");
	}

	static class Board {
		private int mSize;
		private Snake mSnake = new Snake();
		private Point mStarPos = new Point(0, 1);

		Board(int size){
			mSize = size;
			mSnake.setPos(size / 2, size / 2);
			mSnake.setBoard(this);
		}

		public void onStarCaptured() {
			Random r = new Random(System.currentTimeMillis());
			Point n = mStarPos;
			while (true) {
				n.x = r.nextInt(mSize);
				n.y = r.nextInt(mSize);
				if (mSnake.mTails.contains(n)) {
					continue;
				}
				break;
			}
		}

		void print() {
			mSnake.print();
		}

		public int getSize() {
			return mSize;
		}

		Snake getSnake() {
			return mSnake;
		}

		Point getStarPos() {
			return mStarPos;
		}
	}

	public static void main(String[] args) {
		Board board = new Board(6);
		Snake snake = board.getSnake();
		snake.print();

		snake.changeDirection(Direction.LEFT);
		snake.move();
		snake.move();
		snake.move();

		snake.changeDirection(Direction.UP);
		snake.move();
		snake.move();
		snake.print();

		snake.changeDirection(Direction.RIGHT);
		snake.move();
		snake.move();
		snake.print();
	}

	public void setBoard(Board board) {
		mBoard = board;
	}
}
