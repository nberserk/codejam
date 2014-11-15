package crackcode.design;

import crackcode.design.BattleShip.Board.CellState;

public class BattleShip {

	enum SHIP {
		SUBMARINE, PATROL, MINE_SWEEPER
	};

	enum DIRECTION {
		LEFT, RIGHT, UP, DOWN
	}

	static class Player {
		String id;
		String name;
		BattleShip mBattle;

		Player(BattleShip b){
			mBattle = b;
		}
	}

	Board mine = new Board();
	Board opponent = new Board();

	public void placeShip(SHIP ship, int x, int y, DIRECTION dir) {
		int size = 1;
		switch (ship) {
		case SUBMARINE:
			size = 5;
			break;
		case PATROL:
			size = 3;
		case MINE_SWEEPER:
			size = 2;
			break;
		default:
			size = 0;
			break;
		}
		mine.setCellValue(x, y, CellState.HIT);
	}

	static class NetworkHandler {
		void send(Packet p) {

		}

		void onReceived(Packet p) {
			if (p.getId() == Packet.PACKET_SHOT) {

			}
		}
	}

	static class Board {
		enum CellState {
			HIT, MISS, UNKNOWN
		};

		CellState[][] mCell;

		void init(int size) {
			mCell = new CellState[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					mCell[i][j] = CellState.UNKNOWN;
				}
			}
		}

		public void setCellValue(int x, int y, CellState hit) {
			mCell[x][y] = hit;
		}

		void drawBoard() {
		}

	}

	static class Packet {
		public static int PACKET_PLACE_SHIP_REQ = 1;
		public static int PACKET_SHOT = 2;
		private int id;

		Packet(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}
	
	static class PacketShot extends Packet {
		int x, y;
		PacketShot(int x, int y) {
			super(PACKET_SHOT);
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		
		BattleShip ship = new BattleShip();
		Player me = new Player(ship);


	}

}
