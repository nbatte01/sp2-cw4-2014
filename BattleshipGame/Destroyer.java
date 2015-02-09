package BattleshipGame;

public class Destroyer extends Ship
{
    
    
    //constructor
    
    Destroyer()
    {
        length = 2;
        initialiseHitArray(); //re-initialise the hit array for the length of the current ship
    }
    
    //returns the type of ship
    @Override String getShipType()
    {
        return ("destroyer");
    }
    
    //returns a single character String to use in the ocean's print method
    @Override public String toString()
    {
        return (" D ");
    }
}