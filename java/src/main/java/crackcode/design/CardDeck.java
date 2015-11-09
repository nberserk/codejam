package crackcode.design;

import java.util.ArrayList;

public class CardDeck {
	static class Card {
		enum Value {
			ace, two, three, four, ten, jack, queen, king
		};

		enum Shape {
			heart, space, diamond, clover, joker
		};

		Card(Value v, Shape s) {
			mValue = v;
			mShape = s;
		}

		Shape mShape;
		Value mValue;
		public Shape getShape() {
			return mShape;
		}

		public Value getValue() {
			return mValue;
		}
	}

	static class User {
		private String mName;
		ArrayList<Card> mCards = new ArrayList<Card>();

		User(String name) {
			mName = name;
		}
		void addCard(Card card) {
			mCards.add(card);
		}

		void removeCard(Card c) {
			mCards.remove(c);
		}

		String getName() {
			return mName;
		}

		int getCardCount() {
			return mCards.size();
		}
	}

	ArrayList<Card> mRemainingCards = new ArrayList<Card>();

	Card getNextCard() {
		Card ret = mRemainingCards.get(mRemainingCards.size() - 1);
		mRemainingCards.remove(mRemainingCards.size() - 1);
		return ret;
	}

	void shuffle() {
	}

	public static void main(String[] args) {
		CardDeck deck = new CardDeck();
		deck.shuffle();

		User darren = new User("darren");
		darren.addCard(deck.getNextCard());
	}

}
