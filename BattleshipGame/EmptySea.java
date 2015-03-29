/**
 * @author Nicholas Jay Batten, Username: nbatte01, Date: 29/03/2015
 * This code is created for the 4th and final assignment of the software and programming 2 module at 
 * Birkebeck, University of London.
 * 
 *
 */

package BattleshipGame;

public class EmptySea extends Ship
{
	/**
     * Constructor method that sets the length of the ship to 1 and 
     * re-initialises the hit array for the length of the current ship
     * EmptySea is treated as a regular ship for ease of programming
     */
    EmptySea()
    {
        length = 1;
        setHitArray();
    }
    
    
    /**
     * Overrides the 'getShipType' in the 'Ship' class with actual ship type
     */
    @Override String getShipType()
    {
        return ("EmptySea");
    }
    
    
    /**
     * returns a String which is printed to the user under the 'print' method in the 'Ocean' class
     * '-' if the emptySea has been hit (the user's coordinates missed a ship) and '.' if the emptySea has not been hit
     */
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
