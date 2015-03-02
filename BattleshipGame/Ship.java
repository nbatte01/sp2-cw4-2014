package BattleshipGame;

public class Ship
{
    private int bowRow;
    private int bowColumn;
    int length;
    private boolean horizontal;
    boolean[] hit;
    
    
    //returns the row in which the bow is located
    int getBowRow()
    {
        return bowRow;
    }
    
    //Initializes the hit array 
    void setHitArray()
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
    String getShipType()
    {
        return ("test");
    }
    
    //returns the length of the current ship. 
    int getLength()
    {
        return length;
    }
    
    //sets the value of the bowRow
    void setBowRow(int row)
    {
        this.bowRow = row;
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
    
    //puts the ship into the ocean. This involves giving values to the bowRow
    //bowColumn and horizontal instance variables in the instance of the ship class
    //requests the grid array from the ocean class and fills the relevant elements of the array with the 
    //relevant ship. The method will cycle through the grid array depending on the length of the current ship
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
    
    //if part of the ship occupies the given row and column and the ship hasn't been sunk
    //mark that part of the ship as hit and return true. 
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
    
    //checks the hit array if every element is true then returns true
    boolean isSunk()
    {
        for(int i = 0 ; i<this.hit.length;i++)
        {
            if(!this.hit[i]) //cycles through the hit array 
            {
            	return false;
            }
        }
        return true;
    }
    
    //returns true if the section has been hit
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
    
    
    
