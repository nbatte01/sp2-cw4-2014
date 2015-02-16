package BattleshipGame;

public class EmptySea extends Ship
{
    
    
    //constructor
    
    EmptySea()
    {
        length = 1;
        setHitArray();
    }
    
    //This overwrites shootAt in the ship class and always returns false
    //to indicate that nothing was hit as it is always empty sea
    /*@Override boolean shootAt(int row, int column)
    {
        return false;
    }*/
    
    //returns the type of ship
    //THIS MAY NEED TO BE REMOVED FOR THE EMPTYSEA CLASS
    @Override String getShipType()
    {
        return ("EmptySea");
    }
    
    //overrides isSunk() in the ship class to indicate that the user has 
    //not sunk anything as you can't sink empty sea 
    /*@Override boolean isSunk()
    {
        return false;
    }*/
    
    //returns a single character String to use in the ocean's print method
    @Override public String toString()
    {
    	if(this.isSunk())
        {
        	return (" - ");
        }
        else
        {
        	return (" . ");
        }
    }
}
