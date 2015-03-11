package BattleshipGame;

public class Destroyer extends Ship
{
    Destroyer()
    {
        length = 2;
        setHitArray(); //re-initialise the hit array for the length of the current ship
    }
    
    //returns the type of ship
    @Override String getShipType()
    {
        return ("destroyer");
    }
    
    //returns a single character String to use in the ocean's print method
    @Override public String toString()
    {
    	if(this.isSunk())
        {
        	return (" X ");
        }
        else
        {
        	return (" . ");
        }
    }
}