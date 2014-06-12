import java.util.ArrayList;

/**
 * Application to simulate the classic card game War.  
 * 
 * @author brian 04/27/2012
 * 
 * 
 */
public class War {
	
	public static void main(String[] args)
	{
		// variables
		Card play1Card;
		Card play2Card;
		int winner = 0; // flag for overall winner
		// Set up a new deck of cards
		DeckOfCards myDeckOfCards = new DeckOfCards();
		myDeckOfCards.shuffle(); // place Cards in random order
		
		// ArrayLists to hold each hand
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		
		// Deal all cards out
		for ( int i = 1; i <= 52; i++ )
		{
			if (i % 2 == 0)
			{
				hand2.add(myDeckOfCards.dealCard());
			}
			else
			{
				hand1.add(myDeckOfCards.dealCard());
			}
		}
		
		while (hand1.size() > 0 && hand2.size() >0)
		{
			// array list to hold multiple cards on the 'table'
			// mainly for when war is played
			ArrayList<Card> table = new ArrayList<Card>();
			
			// check if either hand is empty
			if (hand1.size() == 0 || hand2.size() == 0)
				break;
			
			// each play a card
			play1Card = hand1.get(0);
			System.out.printf("Player #1 plays %s.", play1Card.getFace());
			play2Card = hand2.get(0);
			System.out.printf("       Player #2 plays %s.\n", play2Card.getFace());
			
			// remove played cards from hands
			hand1.remove(0);
			hand2.remove(0);
			
			// check to see which card is higher
			int trickWinner = checkTrick(play1Card, play2Card);
			
			// if trick is a tie, play war!
			while (trickWinner == 0)
			{
				System.out.println("              WAR!!  WAR!!  WAR!!");
				// add cards to array list and play 3 more cards each
				table.add(play1Card);
				table.add(play2Card);
				// check if either hand is empty
				if (hand1.size() == 0)
					break;
				table.add(hand1.get(0)); // add the top card from the hand
				hand1.remove(0); // remove card from player's hand
				// check if either hand is empty
				if (hand1.size() == 0)
					break;
				table.add(hand1.get(0)); // add the top card from the hand
				hand1.remove(0); // remove card from player's hand
				// check if either hand is empty
				if (hand1.size() == 0)
					break;
				table.add(hand1.get(0)); // add the top card from the hand
				hand1.remove(0); // remove card from player's hand
				// check if either hand is empty
				if (hand2.size() == 0)
					break;
				table.add(hand2.get(0)); // add the top card from the hand
				hand2.remove(0); // remove card from player's hand
				// check if either hand is empty
				if (hand2.size() == 0)
					break;
				table.add(hand2.get(0)); // add the top card from the hand
				hand2.remove(0); // remove card from player's hand
				// check if either hand is empty
				if (hand2.size() == 0)
					break;
				table.add(hand2.get(0)); // add the top card from the hand
				hand2.remove(0); // remove card from player's hand
				
				// check if either hand is empty
				if (hand1.size() == 0 || hand2.size() == 0)
					break;
				
				// each play another card
				play1Card = hand1.get(0);
				System.out.printf("Player #1 plays %s.", play1Card.getFace());
				play2Card = hand2.get(0);
				System.out.printf("       Player #2 plays %s.\n", play2Card.getFace());
				
				// remove card from players' hands
				hand1.remove(0);
				hand2.remove(0);
				
				// check to see which card is higher
				trickWinner = checkTrick(play1Card, play2Card);	
				
				// give winner all table cards
				if (trickWinner == 1) 
				{
					// player 1 wins trick
					hand1.addAll(table);
					table.clear(); // clear table cards out
				}
				else if (trickWinner == 2)
				{
					// player 2 wins trick
					hand2.addAll(table);
					table.clear(); // clear table cards out
				}
			} // end while
			
			// give winner played cards
			if (trickWinner == 1)
			{
				// player 1 wins trick
				hand1.add(play1Card);
				play1Card = null; // clear card out
				hand1.add(play2Card);
				play2Card = null; // clear card out
				System.out.println("              Player #1 wins trick!\n");
				// System.out.printf("\nPlayer #1 hand holds %d cards.  Player #2 hand holds %d cards.\n\n", hand1.size(), hand2.size());
			}
			else if (trickWinner == 2)
			{
				// player 2 wins trick
				hand2.add(play1Card);
				play1Card = null; // clear card out
				hand2.add(play2Card);
				play2Card = null; // clear card out
				System.out.println("              Player #2 wins trick!\n");
				// System.out.printf("\nPlayer #1 hand holds %d cards.  Player #2 hand holds %d cards.\n\n", hand1.size(), hand2.size());
			}
		} // end while
		
		// check who ran out of cards
		if (hand1.size() <= 0)
		{
			winner = 2; // player 2 wins
		}
		else if (hand2.size() <= 0)
		{
			winner = 1; // player 1 wins
		}
		
		System.out.printf("Player %d WINS!\nThanks for playing!\n", winner);
	}
	
	// method to check who won the trick
	private static int checkTrick(Card player1Card, Card player2Card)
	{
		int trickWin = 0;
		
		// get face value of cards
		int card1Value = player1Card.getNumericValue();
		int card2Value = player2Card.getNumericValue();
		
		// set trick winner based on highest card value
		if (card1Value > card2Value)
		{
			trickWin = 1;
		}
		else if (card1Value < card2Value)
		{
			trickWin = 2;
		}
		else if (card1Value == card2Value)
		{
			trickWin = 0;
		}
		
		return trickWin;
	}
	

}
