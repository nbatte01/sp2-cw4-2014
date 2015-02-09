package BattleshipGame;

public class Submarine extends Ship
{
    
    
    //constructor
    
    Submarine()
    {
        length = 1;
        initialiseHitArray(); //re-initialise the hit array for the length of the current ship
    }
    
    //returns the type of ship
    @Override String getShipType()
    {
        return ("submarine");
    }
    
    //returns a single character String to use in the ocean's print method
    @Override public String toString()
    {
        return (" S ");
    }
}