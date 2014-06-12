import java.util.ArrayList;
import java.util.Random;

/**
 * Application to simulate the classic card game Go Fish.
 * 
 * @author brian 04/28/2012
 * 
 * 
 */

public class GoFish 
{
	// game status constants
	public enum GameStatus {PLAYER1TURN, PLAYER2TURN};
	
	// main method to implement game
	public static void main(String[] args) 
	{
		Random randomNumber = new Random();
		
		// Set up a new deck of cards
		DeckOfCards myDeckOfCards = new DeckOfCards();
		myDeckOfCards.shuffle(); // place Cards in random order

		// ArrayLists to hold each hand
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		ArrayList<Card> pond = new ArrayList<Card>();

		// Deal all cards out
		for (int i = 1; i <= 52; i++) 
		{
			if (i <= 7) 
			{ // Deal 7 cards to each player
				hand1.add(myDeckOfCards.dealCard());
				hand2.add(myDeckOfCards.dealCard());
			}
			else if (i > 7)
			{ // the rest of the deck goes in the pond
				pond.add(myDeckOfCards.dealCard());
			}
		}
		System.out.printf("Player #1 hand has %d cards.\n", hand1.size());
		System.out.println(hand1);
		System.out.printf("Player #2 hand has %d cards.\n", hand2.size());
		System.out.println(hand2);
		System.out.printf("The Go Fish pond has %d cards.\n", pond.size());
		
		GameStatus status = GameStatus.PLAYER1TURN;
		
		// game play
		while (hand1.size() > 0 && hand2.size() > 0)
		{ // play until someone runs out of cards
		  System.out.println("Check player 1 hand.");
		  hand1 = checkHand(hand1);
		  System.out.println("Check player 2 hand.");
		  hand2 = checkHand(hand2);
			while (status.equals(GameStatus.PLAYER1TURN))
			{ // Player 1 plays until 'GO FISH'
			  Card player1Card = pickCard(hand1);
			  System.out.println("Player 1 asks Player 2, Do you have any " + player1Card.getFace() + "'s");
			  System.out.println(hand2);
			  int card2Locale = askOpp(player1Card, hand2);
			  System.out.println(card2Locale);
			  if (card2Locale > 0)
			  {
			    hand1.add(hand2.get(card2Locale));
          hand1 = checkHand(hand1);
			  }
			  else 
			  {
			    if (pond.size() != 0)
          {
            // GO FISH!!!
            System.out.println("Go Fish Player 1");
            int pondCard = randomNumber.nextInt(pond.size());
            hand1.add(pond.get(pondCard));
            pond.remove(pondCard);
          }
          hand1 = checkHand(hand1);
          status = GameStatus.PLAYER2TURN;
			  }
			  
			}
			while (status.equals(GameStatus.PLAYER2TURN))
			{
			  Card player2Card = pickCard(hand2);
			  System.out.println("Player 2 asks Player 1, Do you have any " + player2Card.getFace() + "'s");
        System.out.println(hand1);
        int card1Locale = askOpp(player2Card, hand1);
        if (card1Locale > 0)
        {
          hand2.add(hand1.get(card1Locale));
          hand2 = checkHand(hand2);
        }
        else 
        {
          if (pond.size() != 0)
          {
            // GO FISH!!!
            System.out.println("Go Fish Player 2");
            int pondCard = randomNumber.nextInt(pond.size());
            hand2.add(pond.get(pondCard));
            pond.remove(pondCard);
          }
          hand2 = checkHand(hand2);
          status = GameStatus.PLAYER1TURN;
        }
			}
			System.out.printf("Player #1 hand has %d cards.\n", hand1.size());
			System.out.println(hand1);
	    System.out.printf("Player #2 hand has %d cards.\n", hand2.size());
	    System.out.println(hand2);
	    System.out.printf("The Go Fish pond has %d cards.\n", pond.size());
		}
		
		if (hand1.size() == 0)
		{
		  System.out.println("Player 1 wins!!!");
		}
		else if (hand2.size() == 0)
		{
		  System.out.println("Player 2 wins!!!");
		}
	}
	
	private static Card pickCard(ArrayList<Card> hand)
	{
	  Card cardChoice = null;
	  Random randomNumber = new Random();
	  int handLocation;
    if (hand.size() > 1)
    {
      handLocation = randomNumber.nextInt(hand.size() - 1);
    }
    else
    {
      handLocation = 0;
    }
	  cardChoice = hand.get(handLocation);
	  
	  return cardChoice;
	}
	
  private static ArrayList<Card> checkHand(ArrayList<Card> hand)
  {
    
    for (int i = 0; i <= hand.size() - 1; i++)
    {
      boolean matchFound = false;
      String checkFor = hand.get(i).getFace();
      Card firstCard = hand.get(i);
      hand.remove(i);
      for (int j = 0; j <= hand.size() - 1; j++)
      {
        String secondCard = hand.get(j).getFace();
        if (checkFor.equals(secondCard))
        {
          hand.remove(j);
          // System.out.println("Found a match! ");
          matchFound = true;
          break;
        }
        else
        {
          matchFound = false;
          // System.out.println("No match.");
        }
      }
      if (!matchFound)
      {
        hand.add(0, firstCard);
      }
    }
    return hand;
  }
	
	private static int askOpp(Card cardAsked, ArrayList<Card> hand)
	{
    int cardLocale = -1;
    String checkFor = cardAsked.getFace();

    for (int i = 1; i <= hand.size() - 1; i++)
    {
      String secondCard = hand.get(i - 1).getFace();
      if (checkFor.equals(secondCard))
      {
        hand.remove(i - 1);
        System.out.println("Found a match! ");
        cardLocale = i - 1;
        return cardLocale;
      }
      else
      {
        System.out.println("Go Fish!!!!");
      }
    }
	  
	  return cardLocale;
	}

}
