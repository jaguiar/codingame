import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class WinamaxSolution {

	private final static Map<String, Integer> CARDS_VALUE = new HashMap<>();
	private final static String PAT = "PAT";
	private final static int NB_CARDS_TO_DISCARD = 3;
	private final static String[] AVAILABLE_CARDS = new String[]{ "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

	static {
		for (int i = 0; i < AVAILABLE_CARDS.length; i++) {
			CARDS_VALUE.put(AVAILABLE_CARDS[i],i);
		}
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Queue<Integer> player1Deck = initDeck(in);
		Queue<Integer> player2Deck = initDeck(in);

		Deque<Integer> player1CardsToAdd = new LinkedList<>();
		Deque<Integer> player2CardsToAdd = new LinkedList<>();
		int count = 0;
		while(!player1Deck.isEmpty()  && !player2Deck.isEmpty()) {

			// add card on top of each players' deck into the current game stack
			player1CardsToAdd.offer(player1Deck.poll());
			player2CardsToAdd.offer(player2Deck.poll());

			switch (player1CardsToAdd.peekLast().compareTo(player2CardsToAdd.peekLast())) {
			case -1: //player2 wins
				addAllWonCardsToPlayerDeck(player2Deck, player1CardsToAdd, player2CardsToAdd);
				count++;
				break;
			case 1: //player1 wins
				addAllWonCardsToPlayerDeck(player1Deck, player1CardsToAdd, player2CardsToAdd);
				count++;
				break;
			case 0: // bataille
				if(player1Deck.size() <= NB_CARDS_TO_DISCARD || player2Deck.size() <= NB_CARDS_TO_DISCARD){ // draw
					System.out.print(PAT);
					return; // end of game
				}
				for (int i = 0; i < NB_CARDS_TO_DISCARD; i++) {
					player1CardsToAdd.offer(player1Deck.poll());
					player2CardsToAdd.offer(player2Deck.poll());
				}
				break;
			}
		}

		// end of game
		int winner = player1Deck.isEmpty() ? 2 : 1;

		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");
		System.out.println(String.format("%s %s", winner, count));

	}

	private static Queue<Integer> initDeck(Scanner in) {
		int n = in.nextInt(); // the number of cards for player
		Queue<Integer> player1Deck = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			addCardValueToDeck(player1Deck, in.next()); // the n cards of player 1
		}
		return player1Deck;
	}

	private static void addCardValueToDeck(Queue<Integer> playerDeck, String card) {// BEWARE, we do not check string size
		playerDeck.offer(CARDS_VALUE.get(card.substring(0, card.length()-1)));
	}

	private static void addAllWonCardsToPlayerDeck(Queue<Integer> playerDeck, Queue<Integer> player1CardsToAdd, Queue<Integer> player2CardsToAdd) {
		while(!player1CardsToAdd.isEmpty()){ 
			playerDeck.offer(player1CardsToAdd.poll());
		}
		while(!player2CardsToAdd.isEmpty()){ 
			playerDeck.offer(player2CardsToAdd.poll());
		}
	}
}