package BattleshipGame;

import java.util.Random;
//import java.util.ArrayList;

public class Ocean
{   
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;
    
    private final int GRIDSIZE = 10;
    private final int NUMOFSHIPS = 10;
    
    Ship[][] grid = new Ship[10][10];
    
    Random rnd = new Random();

    //Constructor class that also fills the grid array with instances of the EmptySea class
    Ocean()
    {
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
        
        for(int a = 0 ; a < GRIDSIZE ; a++)
        {
            for(int b = 0 ; b < GRIDSIZE ; b++)
            {
                grid[a][b] = new EmptySea();
            }
        }
        
    }

    //Places all ships randomly onto the grid by firstly generating a random Row, Column and horizontal(boolean variable) 
    //then calling the 'okToPlaceShipAt' method in the Ship class to check whether the position is ok. If so the current ship
    //is placed on the coordinates and the process repeats for each ship. 
    public void placeAllShipsRandomly()
    {		
		Ship current = new Ship();
		
		for (int i = 0; i < NUMOFSHIPS; i++)
		{
			if(i == 0)current= new Battleship();
			if(i >= 1)current = new Cruiser();
			if(i >= 3)current= new Destroyer();
			if(i > 5)current = new Submarine();
			
			int tempRow = rnd.nextInt(GRIDSIZE - 0);
            int tempColumn = rnd.nextInt(GRIDSIZE - 0);
            boolean tempHorizontal = rnd.nextBoolean();
            
            while(!current.okToPlaceShipAt(tempRow, tempColumn, tempHorizontal, this))//if the position is not suitable
            {
                	tempRow = rnd.nextInt(GRIDSIZE - 0);
                    tempColumn = rnd.nextInt(GRIDSIZE - 0);
                    tempHorizontal = rnd.nextBoolean();
            }
            current.placeShipAt(tempRow, tempColumn, tempHorizontal, this);//once a suitable position has been found
		}
    }
    
    //returns true if the given location contains a ship else returns false
    boolean isOccupied(int row, int column)
    {
        Ship current = grid[row][column];
    	if(!"EmptySea".equals(current.getShipType()))
    	{
    		return true;
    	}
        return false;
    }
    
    //Prints Hit if the user hits a ship and the ship is not sunk or prints miss if the user hits a ship that is already sunk 
    //or hits empty sea
    boolean shootAt(int row, int column)
    {
        boolean check = false;
        
        Ship current = grid[row][column];
 
        shotsFired++; //regardless of what happens in the method, an additional shot has been fired
        
        if(isOccupied(row,column)==true)//if location contains a ship
        {
        	if(current.isSunk()==false)//if the boat is still afloat
        	{
        		current.shootAt(row, column);
        		hitCount++;
        		check = true;
        		if(current.isSunk())
        		{
        			shipsSunk++;
        			System.out.println("hit");
        			System.out.println("You just sank a "+ current.getShipType());
        		}
        		else
        		{
        			System.out.println();
        			System.out.println("hit");
        		}
        	}
        	else//if the boat has already been sunk
        	{
        		System.out.println("miss");
        		check = false;
        	}
        }
        else//if shooting at an empty sea
        {
        	System.out.println("miss");
        	current.hit[0] = true;//marks the empty sea array as true and thus ensuring the correct character is printed once the user has fired at this location
        	check = false;
        }
        System.out.println();
        return check;
    }
    
    //returns the number of shots fired
    int getShotsFired()
    {
        return shotsFired;
    }
    
    //returns the number of hits recorded - shots that hit a ship
    //all hits are counted and not just the first to hit a coordinate 
    int getHitCount()
    {
        return hitCount;
    }
    
    //returns the number of ships that have been sunk in the game. max - 10
    int getShipsSunk()
    {
        return shipsSunk;
    }
    
    //returns true if all ships have been sunk
    boolean isGameOver()
    {
        if(shipsSunk == 10)
        {
        	return true;
        }
        return false;
    }
    
    Ship[][] getShipArray()
    {
    	return grid;
    }
    
    //prints the ocean to the user. This will print the current status of the game 
    //in the form of the array. It must only be called from the battleshipgame class  
    void print()
    {
        for(int i = 0 ;i < GRIDSIZE ; i++)//prints the columns headers
        {
            System.out.print("  " + i);
        }
            
        System.out.println();

        for(int a = 0 ; a < GRIDSIZE ; a++)//prints each row starting with the row numbers
        {
            System.out.print(a);
            for(int b = 0 ; b < GRIDSIZE ; b++)
            {
            	Ship current = grid[a][b];
            	if(isOccupied(a,b))
            	{
            		if(current.isSunk())//ship has sunk
            		{
            			System.out.print(" X ");
            		}
            		else//still afloat
            		{
            			if(current.sectionHit(a,b))
                		{
                			System.out.print(" S "); //IF THAT SECTION OF THE SHIP HAS BEEN HIT
                		}
                		else
                		{
                			System.out.print(grid[a][b]);//IF THAT SECTION OF THE SHIP HAS NOT BEEN HIT RETURN THE DEFAULT
                		}
            		}
            	}
            	else//empty sea
            	{
            		System.out.print(grid[a][b]); //prints the empty sea
            	}
            }
            System.out.println();
        }
    }
}
