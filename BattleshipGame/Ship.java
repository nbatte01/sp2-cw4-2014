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
    
    //Improvement method to reduce the size of the original by combining the diagonal and port starboard checks into one check
    //all checks can be completed in up to three row checks ie top row including starboard, NE and NW, middle row including BOW, STERN
    //and underneath the boat and the bottom row which include PORT, SE and SW
    boolean okToPlaceShipAt2(int row, int column, boolean horizontal, Ocean ocean)
    {
    	int left;
    	int right;
    	int top;
    	int bottom;
    	int holder;
    	int holder2;
    	
    	
    	
    	if(horizontal)
    	{
    		if((this.getLength() + column) > 10)//ship can't fit on grid
            {
        		return false;
            }
    		System.out.println("CHECKING: R"+row+" C"+column);
    	
    		if(column == 0)//far left
    		{
    			System.out.println("far left check");
    			right = this.getLength();
    			left = column;
    			if(row==0)//top row
    			{
    				holder = 0;
    				holder2 = 2;
    			}
    			else if(row == 9)//bottom row
    			{
    				holder = -1;
    				holder2 = 1;
    			}
    			else//a middle row
    			{
    				holder = -1;
    				holder2 = 2;
    			}
    			for(int a = holder;a<holder2;a++)//cycles through required rows 
				{
					for(int b = left; b <=right;b++)//cycles through the grid locations
					{
						System.out.println("CHECKING: R"+(row+a)+" C"+(b));
						if(ocean.isOccupied((row+a),(b)))
						{
							return false;
						}
					}
				}
    			
    		}
    		else if(column+(this.getLength()-1) == 9)//far right
    		{
    			System.out.println("far right check");
				right = column+this.getLength()-1;
				left = column -1; 
    			if(row==0)//top row of the right 
    			{
    				holder = 0;
    				holder2 = 2;
    			}
    			else if(row == 9)//bottom row of the right 
    			{
    				holder = -1;
    				holder2 = 1;
    			}
    			else//in the middle of the right
    			{
    				holder = -1;
    				holder2 = 2;
    			}
    			for(int a = holder ; a < holder2 ; a++)
				{
					for(int b = left ; b <= right ; b++)
					{
						System.out.println("CHECKING: R"+(row+a)+" C"+(b));
						if(ocean.isOccupied((row+a), (b)))
						{
							return false;
						}
					}
				}
    					
    		}
    		else// in the middle
    		{
    			System.out.println("middle check");
    			right = column+this.getLength();
    			left = column-1;
    			if(row==0)//top of the middle
    			{
    				for(int a = 0 ; a < 2 ; a++)
    				{
    					for(int b = left ; b <= right ; b++)
    					{
    						System.out.println("CHECKING: R"+(row+a)+" C"+(b));
    						if(ocean.isOccupied((row+a), (b)))
    						{
    							return false;
    						}
    					}
    				}
    			}
    			else if(row == 9)//bottom of the middle
    			{
    				for(int a = -1 ; a<1;a++)
    				{
    					
    					for(int b = left ; b<=right ; b++)
    					{
    						System.out.println("CHECKING: R"+(row+a)+" C"+(b));
    						if(ocean.isOccupied((row+a), (b)))
    						{
    							return false;
    						}
    					}
    				}
    			}
    			else//middle of the middle
    			{
    				for(int a = -1 ; a < 2 ; a++)
    				{
    					for(int b = left ; b <= right ; b++)
    					{
    						System.out.println("CHECKING: R"+(row+a)+" C"+b);
    						if(ocean.isOccupied((row+a), (b)))
    						{
    							return false;
    						}
    					}
    				}
    			}
    		}
    	}
    	else//vertical
    	{
    		if((this.getLength() + row) > 10)//ship can't fit on grid
            {
        		return false;
            }
    		if(row==0)//at the top
    		{
    			top = row;
    			bottom = this.getLength();
    			
    			if(column == 0)//top far left
    			{
    				holder = 0;
    				holder2 = 2;
    				
    			}
    			else if(column == 9)//top far right
    			{
    				holder = -1;
    				holder2 = 1;
    				
    			}
    			else// top middle
    			{
    				holder = -1;
    				holder2 = 2;
    				
    			}
    			for(int a = holder ; a < holder2 ; a++)
				{
					for(int b = top ; b <= bottom ; b++)
					{
						System.out.println("CHECKING: R"+(b)+" C"+(column+a));
						if(ocean.isOccupied(b, (column+a)))
						{
							return false;
						}
					}
				}
    		}
    		else if((row+this.getLength()-1)==9)//at the bottom
    		{
    			System.out.println("boat touches the bottom");
    			top = row-1;
    			bottom = row+this.getLength() - 1;
    			if(column == 0)//bottom far left
    			{
    				holder = 0;
    				holder2 = 2;
    				for(int a = 0 ; a < 2 ; a++)
    				{
    					for(int b = top ; b <=bottom ; b++)
    					{
    						System.out.println("CHECKING: R"+(b)+" C"+(column+a));
    						if(ocean.isOccupied(b, (column+a)))
    						{
    							return false;
    						}
    					}
    				}
    			}
    			else if(column == 9)//bottom far right
    			{
    				holder = -1;
    				holder2 = 1;
    				for(int a = -1 ; a < 1 ; a++)
    				{
    					for(int b = top ; b <= bottom ; b++)
    					{
    						System.out.println("CHECKING: R"+(b)+" C"+(column+a));
    						if(ocean.isOccupied(b, (column+a)))
    						{
    							return false;
    						}
    					}
    				}
    			}
    			else// bottom middle
    			{
    				for(int a = -1 ; a < 2 ; a++)//columns
    				{
    					for(int b = top ; b <= bottom ; b++)//rows
    					{
    						System.out.println("CHECKING: R"+(b)+" C"+(column+a));
    						if(ocean.isOccupied(b, (column+a)))
    						{
    							return false;
    						}
    					}
    				}
    			}
    			
    		}
    		else//in the middle
    		{
    			
    			top = row-1;
    			bottom = row+this.getLength();
    			if(column == 0)//middle far left
    			{
    				System.out.println("middle left checking");
    				for(int a = 0 ; a < 2 ; a++)//columns
    				{
    					for(int b = top ; b <= bottom ; b++)//rows
    					{
    						System.out.println("CHECKING: R"+(b)+" C"+(column+a));
    						if(ocean.isOccupied(b, (column+a)))
    						{
    							return false;
    						}
    					}
    				}
    			}
    			else if(column == 9)//middle far right
    			{
    				System.out.println("middle right checking");
    				for(int a = -1 ; a < 1 ; a++)//columns
    				{
    					for(int b = top ; b <= bottom ; b++)//rows
    					{
    						System.out.println("CHECKING: R"+(b)+" C"+(column+a));
    						if(ocean.isOccupied(b, (column+a)))
    						{
    							return false;
    						}
    					}
    				}
    			}
    			else//middle middle
    			{
    				System.out.println("middle middle checking");
    				for(int a = -1 ; a < 2 ; a++)//columns
    				{
    					for(int b = top ; b <= bottom ; b++)//rows
    					{
    						System.out.println("CHECKING: R"+(b)+" C"+(column+a));
    						if(ocean.isOccupied(b, (column+a)))
    						{
    							return false;
    						}
    					}
    				}
    			}
    		}
    	}
    	
    	
    	return true; //if all checks have passed successfully
    }
    
    
    
    //checks whether it is ok to put a ship of this length with its bow in
    //this location with the given orientation. returns false if it is not
    //basically takes a coordinate and checks whether the current ship will fit
    //will not change any arrays but will only return whether the chosen coordinate is good or bad for the current ship
    
    //NEEDS FURTHER TESTING TO ENSURE PORT AND STARBOARD CHECKS ARE FUNCTIONING CORRECTLY 
    //SIZE OF METHOD NEEDS TO BE REDUCED
    //NEED TO ADD CHECKS FOR DIAGONAL GRID LOCATIONS
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
        boolean diagonal = true;
        
        if(horizontal) // if the boat is horizontal
        {
        	if((this.getLength() + column) <= 10)//can ship fit on grid
            {
        		System.out.println("Ship can fit on grid with the current coordinates");
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
            			if(ocean.isOccupied(row,(column-1)))//checks the left side (BOW) of the ship
            			{
            				end1 = false;
            			}
            		}
            		if(column+(this.getLength())<=9)
            		{
            			System.out.println("STERN end is being checked");
            			if(ocean.isOccupied(row, (column+this.getLength())))//checks the right (STERN) side of the ship
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
            	
            	if((port==true)&&(starboard==true))//if the BOW,STERN, PORT and STARBOARD side of the ship have been checked
            	{
            		if(row==0)//top row
            		{
            			
            			if(column == 0)//if on the far left 
            			{
            				System.out.println("SE Check");
            				if(ocean.isOccupied((row+1),(column+this.getLength())))//SE
            				{
            					diagonal = false;
            				}
            			}
            			else if(column+(this.getLength()-1)==9)//if on the far right
            			{
            				System.out.println("SW Check");
            				if(ocean.isOccupied((row+1),(column-1)))//SW
            				{
            					diagonal = false;
            				}
            			}
            			else//in the middle
            			{
            				System.out.println("SW Check");
            				if(ocean.isOccupied((row+1),(column-1)))//SW
            				{
            					diagonal = false;
            				}
            				System.out.println("SE Check");
            				if(ocean.isOccupied((row+1),(column+this.getLength())))//SE
            				{
            					diagonal = false;
            				}
            			}
            		}
            		else if(row == 9)//bottom row
            		{
            			
            			if(column == 0)//if on the far left 
            			{
            				System.out.println("NE Check");
            				if(ocean.isOccupied((row-1), (column+this.getLength())))//NE
            				{
            					diagonal = false;
            				}
            			}
            			else if(column+(this.getLength()-1)==9)//if on the far right
            			{
            				System.out.println("NW Check");
            				if(ocean.isOccupied((row-1),(column-1)))//NW
            				{
            					diagonal = false;
            				}
            			}
            			else//if not on far left
            			{
            				System.out.println("NW Check");
            				if(ocean.isOccupied((row-1),(column-1)))//NW
            				{
            					diagonal = false;
            				}
            				System.out.println("NE Check");
            				if(ocean.isOccupied((row-1), (column+this.getLength())))//NE
            				{
            					diagonal = false;
            				}
            			}
            		}
            		else//in the middle
            		{
            			
            			if(column == 0)//if on the far left 
            			{
            				System.out.println("NE Check");
            				if(ocean.isOccupied((row-1), (column+this.getLength())))//NE
            				{
            					diagonal = false;
            				}
            				System.out.println("SE Check");
            				if(ocean.isOccupied((row+1),(column+this.getLength())))//SE
            				{
            					diagonal = false;
            				}
            			}
            			else if(column+(this.getLength()-1)==9)//if on the far right
            			{
            				System.out.println("NW Check");
            				if(ocean.isOccupied((row-1),(column-1)))//NW
            				{
            					diagonal = false;
            				}
            				System.out.println("SW Check");
            				if(ocean.isOccupied((row+1),(column-1)))//SW
            				{
            					diagonal = false;
            				}
            			}
            			else//in the middle
            			{
            				System.out.println("NW Check");
            				if(ocean.isOccupied((row-1),(column-1)))//NW
            				{
            					diagonal = false;
            				}
            				System.out.println("SW Check");
            				if(ocean.isOccupied((row+1),(column-1)))//SW
            				{
            					diagonal = false;
            				}
            				System.out.println("NE Check");
            				if(ocean.isOccupied((row-1), (column+this.getLength())))//NE
            				{
            					diagonal = false;
            				}
            				System.out.println("SE Check");
            				if(ocean.isOccupied((row+1),(column+this.getLength())))//SE
            				{
            					diagonal = false;
            				}
            			}
            		}
            	}
            	
            }
        	else
        	{
        		System.out.println("The ship can NOT fit on the grid under the current coordinates");
        	}
        	if((port == true) && (starboard == true) && (end1 == true) && (end2 == true) && (clearSpace == true) && (fitsOnGrid == true)&&(diagonal==true))//if all checks have passed
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
            	//Diagonal checking
            	if((port == true)&&(starboard==true))//if the BOW,STERN, PORT and STARBOARD side of the ship have been checked
            	{
            		if(column == 0)//far left
            		{
            			if(row==0)//top row 
            			{
            				if(ocean.isOccupied((row+this.getLength()),(column+1)))//SE
            				{
            					diagonal = false;
            				}
            			}
            			else if(row+(this.getLength()-1) == 9)//bottom row
            			{
            				if(ocean.isOccupied((row-1), (column+1)))//NE
            				{
            					diagonal = false;
            				}
            			}
            			else//in the middle
            			{
            				if(ocean.isOccupied((row-1), (column+1)))//NE
            				{
            					diagonal = false;
            				}
            				if(ocean.isOccupied((row+this.getLength()),(column+1)))//SE
            				{
            					diagonal = false;
            				}
            			}
            		}
            		else if(column == 9)//far right
            		{
            			if(row==0)//top row 
            			{
            				if(ocean.isOccupied((row+this.getLength()),(column-1)))//SW
            				{
            					diagonal = false;
            				}
            			}
            			else if(row+(this.getLength()-1) == 9)//bottom row
            			{
            				if(ocean.isOccupied((row-1), (column-1)))//NW
            				{
            					diagonal = false;
            				}
            			}
            			else//in the middle
            			{
            				if(ocean.isOccupied((row-1), (column-1)))//NW
            				{
            					diagonal = false;
            				}
            				if(ocean.isOccupied((row+this.getLength()),(column-1)))//SW
            				{
            					diagonal = false;
            				}
            			}
            		}
            		else//middle
            		{
            			if(row==0)//top row 
            			{
            				if(ocean.isOccupied((row+this.getLength()),(column-1)))//SW
            				{
            					diagonal = false;
            				}
            				if(ocean.isOccupied((row+this.getLength()),(column+1)))//SE
            				{
            					diagonal = false;
            				}
            			}
            			else if(row+(this.getLength()-1) == 9)//bottom row
            			{
            				if(ocean.isOccupied((row-1), (column-1)))//NW
            				{
            					diagonal = false;
            				}
            				if(ocean.isOccupied((row-1), (column+1)))//NE
            				{
            					diagonal = false;
            				}
            			}
            			else//in the middle
            			{
            				if(ocean.isOccupied((row-1), (column-1)))//NW
            				{
            					diagonal = false;
            				}
            				if(ocean.isOccupied((row-1), (column+1)))//NE
            				{
            					diagonal = false;
            				}
            				if(ocean.isOccupied((row+this.getLength()),(column-1)))//SW
            				{
            					diagonal = false;
            				}
            				if(ocean.isOccupied((row+this.getLength()),(column+1)))//SE
            				{
            					diagonal = false;
            				}
            			}
            		}
            	}
            }
        	
        }
        
        if((port == true) && (starboard == true) && (end1 == true) && (end2 == true) && (clearSpace == true) && (fitsOnGrid == true) && (diagonal == true))//if all checks have passed
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
    //requests the grid array from the ocean class and fills the relevant elements of the array with the 
    //relevant ship. The method will cycle through the grid array depending on the length of the current ship
    //ADDITIONAL CHECKS NEED TO BE CARRIED OUT BEFORE THIS METHOD IS FINALISED
    void placeShipAt(int tempRow, int tempColumn, boolean tempHorizontal, Ocean ocean)
    {
    	this.setBowRow(tempRow);
    	this.setBowColumn(tempColumn);
    	this.setHorizontal(tempHorizontal);
		
		for(int i = 0; i < this.getLength(); i++){
			if (horizontal == true){
				ocean.getShipArray()[this.getBowRow()][this.getBowColumn() + i] = this;
			} else {
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