package BattleshipGame;

public class Cruiser extends Ship
{
    
    
    //constructor
    
    Cruiser()
    {
        length = 3;
        initialiseHitArray(); //re-initialise the hit array for the length of the current ship
    }
    
    //returns the type of ship
    @Override String getShipType()
    {
        return ("cruiser");
    }
    
    //returns a single character String to use in the ocean's print method
    @Override public String toString()
    {
        return ("S");
    }
}
