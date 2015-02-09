package BattleshipGame;

public class Battleship extends Ship
{
    
    
    //constructor
    
    Battleship()
    {
        length = 4;
        initialiseHitArray(); //re-initialise the hit array for the length of the current ship 
    }
    
    //returns the type of ship
    @Override String getShipType()
    {
        return ("battleship");
    }
    
    //returns a single character String to use in the ocean's print method
    @Override public String toString()
    {
        return (" B ");
    }
}
