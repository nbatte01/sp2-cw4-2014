/**
 * @author Nicholas Jay Batten, Username: nbatte01, Date: 29/03/2015
 * This code is created for the 4th and final assignment of the software and programming 2 module at 
 * Birkebeck, University of London.
 * 
 *
 */

package BattleshipGame;

public class Ship
{
    private int bowRow;
    private int bowColumn;
    int length;
    private boolean horizontal;
    boolean[] hit;
    
    
    /**
     * @return int - the row number of the bow of the ship
     */
    int getBowRow()
    {
        return bowRow;
    }
    
    
    /**
     * 	Initializes the hit array
     */
    void setHitArray()
    {
        hit = new boolean[this.getLength()]; //generates a new arrays with the length based on the length of the ship
        for(int i = 0 ; i < this.getLength() ; i++)
        {
            hit[i] = false;
        }
    }
    
    
    /**
     * @return int - the column numner where the bow of teh ship is located
     */
    int getBowColumn()
    {
        return bowColumn;
    }
    
    
    /**
     * @return boolean - true if the ship is horizontal and false if it is vertical
     */
    boolean isHorizontal()
    {
        return horizontal;
    }
    
    
    /**
     * Return the type of ship in string format. This is overwritten by the subclasses of 'Ship' and thus
     * the return of "test" is only used for testing purposes
     * 
     * @return String - type of ship
     */
    String getShipType()
    {
        return ("test");
    }
    
    
    /**
     * @return int - the length of the current ship
     */
    int getLength()
    {
        return length;
    }
    
    
    /**
     * sets the value of 'bowRow'
     * 
     * @param row - passed from the 'placeShipAt' method
     */
    private void setBowRow(int row)
    {
        this.bowRow = row;
    }
    
        
    /**
     * sets the value of 'bowColumn'
     * 
     * @param column - passed from the 'placeShipAt' method
     */
    private void setBowColumn(int column)
    {
        this.bowColumn = column;
    }
    
    
    /**
     * Sets the value of horizontal
     * 
     * @param horizontal - passed from the 'placeShipAt' method
     */
    private void setHorizontal(boolean horizontal)
    {
        this.horizontal = horizontal;
    }
    
    
    /**
     * Checks whether the passed ship parameters can fit onto the grid with overlapping another ship
     * or being within 1 coordinate of another ship either vertically, horizontally or diagonally.
     * Does NOT give value to bowRow, bowColumn, horizontal. Only returns whether the location is suitable
     * 
     * @param row - the row number where the proposed position of the ship's bow would be
     * @param column - the column number where the proposed position of the ship's bow would be
     * @param horizontal - Whether or not the proposed ship is horizontal or vertical
     * @param ocean - received from the 'placeAllShipsRandomly' method in the 'Ocean' class for reference
     * @return boolean - True if parameters passed are suitable coordinates for the ship, false otherwise
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean)
    {
    	int left = column -1;
    	int right = column+this.getLength();
    	int top = row-1;
    	int bottom = row + this.getLength();
    	int holder = -1;
    	int holder2 = 2;
    	
    	if(((horizontal)&&(row==0))||((!horizontal)&&(column==0)))
    	{
    		holder = 0;
    	}
    	if(((horizontal)&&(row==9))||((!horizontal)&&(column==9)))
    	{
    		holder2 = 1;
    	}
    	
    	if(((this.getLength() + column) > 10)||((this.getLength() + row) > 10))//if ship can't fit on grid
    	{
    		return false;
    	}
    	
    	if(horizontal)
    	{
    		if(column == 0)
    		{
    			left = column;
    		}
    		else if(column+(this.getLength()-1) == 9)
    		{
				right = column+this.getLength()-1;	
    		}
    		for(int a = holder ; a < holder2 ; a++)
			{
				for(int b = left ; b <= right ; b++)
				{
					if(ocean.isOccupied((row+a), (b)))
					{
						return false;
					}
				}
			}	
    		
    	}
    	else//vertical
    	{
    		if(row==0)
    		{
    			top = row;
    		}
    		else if((row+this.getLength()-1)==9)
    		{
    			bottom = row+this.getLength() - 1;
    		}
    		for(int a = holder ; a < holder2 ; a++)
			{
				for(int b = top ; b <= bottom ; b++)
				{
					if(ocean.isOccupied(b, (column+a)))
					{
						return false;
					}
				}
			}
    		
    	}
    	return true; 
    }
    
    
    /**
     * puts the ship into the ocean. This involves giving values to the bowRow
     * bowColumn and horizontal instance variables in the instance of the 'Ship' class
     * requests the grid array from the ocean class and fills the relevant elements of the array with the 
     * relevant ship. The method will cycle through the grid array depending on the length of the current ship
     * 
     * @param row - The row number where the bow of the ship will be placed
     * @param column - The column number where the bow of the ship will be placed
     * @param horizontal - True if the ship will be placed horizontally, false otherwise 
     * @param ocean - received from the 'placeAllShipsRandomly' method in the 'Ocean' class for reference
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean)
    {
    	this.setBowRow(row);
    	this.setBowColumn(column);
    	this.setHorizontal(horizontal);
		
		for(int i = 0; i < this.getLength(); i++)
		{
			if (horizontal == true)
			{
				ocean.getShipArray()[row][column + i] = this;
			} 
			else //vertical
			{
				ocean.getShipArray()[row + i][column] = this;
			}
		}
    }
    
    
    /**
     * If part of a ship occupies the passed coordinates the return true, else false
     * 
     * @param row - The row number where the user wishes to shoot
     * @param column - The column number where the user wishes to shoot
     * @return - boolean - True if coordinates passed is the location of a ship, false otherwise
     */
    boolean shootAt(int row, int column)
    {
        if(!this.isSunk())
        {
        	if(this.isHorizontal())//horizontal
            {
            	int position = column - this.getBowColumn();
            	this.hit[position]=true;
            	return true;
            }
            else//vertical
            {
            	int position = row - this.getBowRow();
            	this.hit[position]=true;
            	return true;
            }
        }
        return false;
    }
    
    
    /**
     * Cycle through the 'hit' array to check whether all sections of the ship have been sunk
     * 
     * @return boolean - true if all sections of the current ship have been hit
     */
    boolean isSunk()
    {
        for(int i = 0 ; i<this.hit.length;i++)
        {
            if(!this.hit[i]) 
            {
            	return false;
            }
        }
        return true;
    }
    
    /**
     * Returns a boolean variable from the 'hit' array to determine if a section of the ship has been hit or not
     * 
     * @param row - 
     * @param column
     * @return boolean - an element from the 'hit' array to determine if that section has been hit or not
     */
    boolean sectionHit(int row, int column)
    {
    	int position;
    	
    	if(this.isHorizontal())//horizontal
    	{
    		position = column - this.getBowColumn();
    	}
    	else//Vertical
    	{
    		position = row - this.getBowRow();
    	}
    	return hit[position];
    }
    
    
    
    
    
}
    
    
    
