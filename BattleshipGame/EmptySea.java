package BattleshipGame;

public class EmptySea extends Ship
{
    EmptySea()
    {
        length = 1;
        setHitArray();
    }
    
    //returns the type of ship
    @Override String getShipType()
    {
        return ("EmptySea");
    }
    
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
