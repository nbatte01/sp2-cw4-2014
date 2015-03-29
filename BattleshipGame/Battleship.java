/**
 * @author Nicholas Jay Batten, Username: nbatte01, Date: 29/03/2015
 * This code is created for the 4th and final assignment of the software and programming 2 module at 
 * Birkebeck, University of London.
 * 
 *
 */

package BattleshipGame;


public class Battleship extends Ship
{
	/**
     * Constructor method that sets the length of the ship to 4 and 
     * re-initialises the hit array for the length of the current ship
     */
    Battleship()
    {
        length = 4;
        setHitArray(); //re-initialise the hit array for the length of the current ship 
    }
    
    /**
     * Overrides the 'getShipType' in the 'Ship' class with actual ship type
     */
    @Override String getShipType()
    {
        return ("battleship");
    }
    
    /**
     * returns a String which is printed to the user under the 'print' method in the 'Ocean' class
     * 'x' if the entire ship is sunk and '.' if the ship is not
     */
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
