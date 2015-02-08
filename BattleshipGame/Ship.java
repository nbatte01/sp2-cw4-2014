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
    
    //instance methods
    
    //checks whether it is ok to put a ship of this length with its bow in
    //this location with the given orientation. returns false if it is not
    //basically takes a coordinate and checks whether the current ship will fit
    //will not change any arrays but will only return whether the chosen coordinate is good or bad for the current ship
    
    //NEEDS FURTHER TESTING TO ENSURE PORT AND STARBOARD CHECKS ARE FUNCTIONING CORRECTLY 
    //SIZE OF METHOD NEEDS TO BE REDUCED
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean)
    {
        System.out.println("OKtoPlace() is checking:"+"R"+row+"C"+column);
        System.out.println();
        boolean fitsOnGrid = false;
        boolean clearSpace = true;
        boolean ends = false;
        boolean end1 = true;
        boolean end2 = true;
        boolean port = true;
        boolean starboard = true;
        
        if(horizontal) // if the boat is horizontal
        {
        	if((this.getLength() + column) <= 10)//can ship fit on grid
            {
        		fitsOnGrid = true;
        		System.out.println("UNDERNEATH ship is being checked");
            	for(int i = 0 ; i < this.getLength() ; i++)
            	{	
            		if(ocean.isOccupied(row, (column+i)))//If a ship currently occupies this location
            		{
            			clearSpace = false;
            		}
            	}
            	if(clearSpace == true)//if there is room under the boat then check either end
            	{
            		if(column > 0)
            		{	
            			System.out.println("BOW end is being checked");
            			if(ocean.isOccupied(row,(column-1)))//checks the left side of the ship
            			{
            				end1 = false;
            			}
            		}
            		if(column+(this.getLength())<=9)
            		{
            			System.out.println("STERN end is being checked");
            			if(ocean.isOccupied(row, (column+this.getLength())))//checks the right side of the ship
            			{
            				end2 = false;
            			}
            		}
            	}
            	
            	if((end1==true)&&(end2==true))//if both ends are clear of other ships. PORT and STARBOARD checking
            	{
            		if(row > 0)//STARBOARD(RIGHT) CHECKING
            		{
            			System.out.println("STARBOARD side is being checked");
            			for(int i = 0 ; i < this.getLength(); i++)
            			{
            				if(ocean.isOccupied(row-1,(column+i))==true)
            				{
            					starboard = false;
            				}
            			}
            		}
            		if(row < 9)//PORT(LEFT) CHECKING
            		{
            			System.out.println("PORT side is being checked");
            			for(int i = 0 ; i < this.getLength(); i++)
            			{
            				if(ocean.isOccupied(row+1, (column+i))==true)
            				{
            					port = false;
            				}
            			}
            		}
            	}
            	
            }
        	if((port == true) && (starboard == true) && (end1 == true) && (end2 == true) && (clearSpace == true) && (fitsOnGrid == true))//if all checks have passed
        	{
        		return true;
        	}
        	else
        	{
        		return false;
        	}
            
        }
        else//if ship is vertical
        {
        	if((this.getLength() + row) <= 10)//can ship fit on grid
            {
        		fitsOnGrid = true;
        		System.out.println("UNDERNEATH ship is being checked");
        		for(int i = 0 ; i < this.getLength() ; i++)
            	{
            		if(ocean.isOccupied((row+i), column))//If a ship currently occupies this location
            		{
            			clearSpace = false;
            		}
            	}
        		if(clearSpace == true)//if there is room under the boat then check either end
            	{
            		if(row > 0)
            		{	
            			System.out.println("BOW end is being checked");
            			if(ocean.isOccupied((row-1),column))//checks the top side of the ship
            			{
            				end1 = false;
            			}
            		}
            		if(row+(this.getLength())<=9)
            		{
            			System.out.println("STERN end is being checked");
            			if(ocean.isOccupied((row+this.getLength()), column))//checks the bottom side of the ship
            			{
            				end2 = false;
            			}
            		}
            	}
            	if((end1==true)&&(end2==true))//if both ends are clear of other ships PORT AND STARBOARD CHECKING 
            	{
            		if(column>0)
            		{
            			System.out.println("PORT side is being checked");
            			for(int i = 0 ; i < this.getLength();i++)
            			{
            				if(ocean.isOccupied((row+i), (column-1))==true)
            				{
            					port = false;
            				}
            			}
            		}
            		if(column < 9)	
            		{
            			System.out.println("STARBOARD side is being checked");
            			for(int i = 0 ; i < this.getLength(); i++)
            			{
            				if(ocean.isOccupied((row+i), (column+1))==true)
            				{
            					starboard = false;
            				}
            			}
            		}
            	}
            }
        	
        }
        
        if((port == true) && (starboard == true) && (end1 == true) && (end2 == true) && (clearSpace == true) && (fitsOnGrid == true))//if all checks have passed
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    //puts the ship into the ocean. This involves giving values to the bowRow
    //bowColumn and horizontal instance variables in the intance of the ship class
    //it also involves adjusting the ship array in the Ocean object (class?)
    static void placeShipAt(int row, int column, boolean horizontal, Ocean ocean)
    {
        
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
    
    //checks the hit array if every element says the ship is sunk then return true
    boolean isSunk()
    {
        boolean check = true;
        for(int i = 0 ; i<hit.length;i++)
        {
            if(hit[i] == false) //cycles through 
            {
                check = false;
                break;
            }
        }
        return check;
    }
    
}