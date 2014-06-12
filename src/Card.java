// Card class represents a playing card.
// by Brian Widhalm


public class Card 
{
	private String face; // face of card ("Ace", "Deuce", ...)
	private String suit; // suit of card ("Hearts", "Diamonds", ...)

	// two-argument constructor initializes card's face and suit
	public Card( String cardFace, String cardSuit )
	{
	face = cardFace; // initialize face of card
	suit = cardSuit; // initialize suit of card
	} // end two-argument Card constructor
	// return String representation of Card
	public String toString()
	{
	return face + " of " + suit;
	} // end method toString
	
	// method to check card face
	public String getFace()
	{
		return face;
	} // end getFace method
	
	// method to check card suit
	public String getSuit()
	{
		return suit;
	} // end getSuit method
	
	// method to assign a numerical value for card
	//"Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"
	public int getNumericValue()
	{
		int cardValue = 0;
		
		if (face == "Deuce")
		{
			cardValue = 2;
		}
		else if (face == "Three")
		{
			cardValue = 3;
		}
		else if (face == "Four")
		{
			cardValue = 4;
		}
		else if (face == "Five")
		{
			cardValue = 5;
		}
		else if (face == "Six")
		{
			cardValue = 6;
		}
		else if (face == "Seven")
		{
			cardValue = 7;
		}
		else if (face == "Eight")
		{
			cardValue = 8;
		}
		else if (face == "Nine")
		{
			cardValue = 9;
		}
		else if (face == "Ten")
		{
			cardValue = 10;
		}
		else if (face == "Jack")
		{
			cardValue = 11;
		}
		else if (face == "Queen")
		{
			cardValue = 12;
		}
		else if (face == "King")
		{
			cardValue = 13;
		}
		else if (face == "Ace")
		{
			cardValue = 14;
		}
			
		return cardValue;
	}

} // end class Card
