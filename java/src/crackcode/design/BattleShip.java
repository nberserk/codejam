package crackcode.design;

public class BattleShip {

	enum SHIP {
		SUBMARINE, FRUGAL, MINE_SWEEPER
	};

	enum DIRECTION {
		LEFT, RIGHT, UP, DOWN
	}

	static class NetworkHandler {
		void send(Packet p) {

		}

		void onReceived(Packet p) {
			if (p.getId() == Packet.PACKET_SHOT) {
				p.
			}
		}
	}

	static class Board {
		enum CellState {
			HIT, MISS, NOT_OPENED
		};

		CellState[][] mCell;

		void init(int size) {
			mCell = new CellState[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					mCell[i][j] = CellState.NOT_OPENED;
				}
			}
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

		public void PacketShot(int x, int y) {
			super(PACKET_SHOT);
			this.x = x;
			this.y = y;
		}
	}


}
