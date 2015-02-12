package BattleshipGame;

public class Ship
{
    //instance variables
    //the row (0 - 9) which contains the bow (front) of the ship
    //all ships will be pointing up or left
    private int bowRow;
    //the column that contains the bow of the ship
    private int bowColumn;
    //the number of squares occupied by the ship. An empty sea object has length 1
    static int length;
    //true if the ship is horizontal, false otherwise
    private boolean horizontal;
    
    //an array of booleans telling whether that part of the ship has been hit 
    //beleive this will be called to check whether the ship has been sunk
    private boolean[] hit;
    
    //getters
    
    //returns the row in which the bow is located
    int getBowRow()
    {
        return bowRow;
    }
    
    void initialiseHitArray()
    {
        hit = new boolean[this.getLength()]; //generates a new arrays with the length based on the length of the ship
        for(int i = 0 ; i < this.getLength() ; i++)
        {
            hit[i] = false;
        }
    }
    
    //returns the column in which the bow is located
    int getBowColumn()
    {
        return bowColumn;
    }
    
    //returns whether the ship is horizontal or not
    boolean isHorizontal()
    {
        return horizontal;
    }
    
    //returns the type of this ship 
    //apparently it doesn't matter what this method returns?? needs to be confirmed
    String getShipType()
    {
        return ("test");
    }
    
    //returns the length of the current ship. 
    int getLength()
    {
        return length;
    }
    
    //setters
    
    //sets the value of the bowRow
    void setBowRow(int row)
    {
        this.bowRow = row;//needs to be double checked
    }
    
    //sets the value of bowColumn
    void setBowColumn(int column)
    {
        this.bowColumn = column;
    }
    
    //sets the value of the instance variable horizontal
    void setHorizontal(boolean horizontal)
    {
        this.horizontal = horizontal;
    }
    
    //checks whether the ship can fit on the grid with the given parameters.
    //Checks Bow, Stern, Port, Starboard and diagonal grid locations to the parameters passed.
    //returns true if the coordinates passed as parameters are suitable to place the ship on
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
    		if(column == 0)//far left
    		{
    			left = column;
    		}
    		else if(column+(this.getLength()-1) == 9)//far right
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
    		if(row==0)//at the top
    		{
    			top = row;
    		}
    		else if((row+this.getLength()-1)==9)//at the bottom
    		{
    			bottom = row+this.getLength() - 1;
    		}
    		for(int a = holder ; a < holder2 ; a++)//columns
			{
				for(int b = top ; b <= bottom ; b++)//rows
				{
					if(ocean.isOccupied(b, (column+a)))
					{
						return false;
					}
				}
			}
    		
    	}
    	return true; //if all checks have passed successfully
    }
    

    //puts the ship into the ocean. This involves giving values to the bowRow
    //bowColumn and horizontal instance variables in the intance of the ship class
    //requests the grid array from the ocean class and fills the relevant elements of the array with the 
    //relevant ship. The method will cycle through the grid array depending on the length of the current ship
    //ADDITIONAL CHECKS NEED TO BE CARRIED OUT BEFORE THIS METHOD IS FINALISED
    void placeShipAt(int tempRow, int tempColumn, boolean tempHorizontal, Ocean ocean)
    {
    	this.setBowRow(tempRow);
    	this.setBowColumn(tempColumn);
    	this.setHorizontal(tempHorizontal);
		
		for(int i = 0; i < this.getLength(); i++)
		{
			if (horizontal == true)
			{
				ocean.getShipArray()[this.getBowRow()][this.getBowColumn() + i] = this;
			} 
			else //vertical
			{
				ocean.getShipArray()[this.getBowRow() + i][this.getBowColumn()] = this;
			}
		}
    }
    
    //if part of the ship occupies the given row and column and the ship hasn't been sunk
    //mark that part of the ship as hit and return true. Otherwise if 
    //return true if the shot hits the ship and the ship is still afloat
    //not sure if this should return true if the same coordinates are used for a 
    //floating ship
    boolean shootAt(int row, int column)
    {
        boolean check = false;
        
        return check;
    }
    
    //checks the hit array if every element says the ship is sunk then returns true
    boolean isSunk()
    {
        boolean check = true;
        for(int i = 0 ; i<hit.length;i++)
        {
            if(hit[i] == false) //cycles through 
            {
                check = false;
            }
        }
        return check;
    }
    
    
    
    
    
}