package crackcode.design;

import java.util.ArrayList;
import java.util.Date;

// reference : http://csrocks.net/HomePages/OopsHome.aspx
public class ParkingLot {

	enum VehicleType {
		COMPACT, MEDIUM, LARGE
	};

	static class TicketBooth {
		Ticket requestTicket(Vehicle v) {
			Ticket t = new Ticket(v); 
			v.setTicket(t);

			return t;
		}


		int calulateMoney(Ticket t) {
			return 100;
		}

		boolean payTicket(Ticket t, int money) {
			//
			return true;
		}

		void returnCahnges(int changes) {
		}

	}

	static class Ticket {
		private Date mDate;
		private boolean mPaid;

		Ticket(Vehicle v) {

		}

		Date getTimeIn() {
			return mDate;
		}

		void setPaid(boolean b) {
			mPaid = b;
		}

		public boolean isPaid() {
			return mPaid;
		}
	}

	static class Vehicle {
		private VehicleType mType;
		private Ticket mTicket;

		Vehicle(VehicleType type) {
			mType = type;
		}

		VehicleType getType() {
			return mType;
		}

		void setTicket(Ticket t) {
			mTicket = t;
		}

		Ticket getTicket() {
			return mTicket;
		}
	}

	static class ParkingSlot {
		Vehicle mVehicle;

		void reserve(Vehicle v) {
			mVehicle = v;
		}

		void unreserve() {
			mVehicle = null;
		}

		boolean isReserved() {
			return mVehicle == null ? false : true;
		}
	}

	private ArrayList<ParkingSlot> mParkingSlot;
	private TicketBooth mBooth = new TicketBooth();

	void addParakingSlot(ParkingSlot slot) {
		mParkingSlot.add(slot);
	}

	TicketBooth getTicketBooth() {
		return mBooth;
	}
}
