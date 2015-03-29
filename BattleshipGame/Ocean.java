/**
 * @author Nicholas Jay Batten, Username: nbatte01, Date: 29/03/2015
 * This code is created for the 4th and final assignment of the software and programming 2 module at 
 * Birkebeck, University of London.
 * 
 *
 */

package BattleshipGame;

import java.util.Random;

public class Ocean
{   
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;
    
    private final int GRIDSIZE = 10;
    private final int NUMOFSHIPS = 10;
    
    Ship[][] grid = new Ship[10][10];
    
    Random rnd = new Random();


    /**
     * Constructor class that also fills the grid array with instances of the EmptySea class
     */
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

    /**
     *Places all ships randomly onto the grid by firstly generating a random Row, Column and horizontal(boolean variable) 
     *then calling the 'okToPlaceShipAt' method in the Ship class to check whether the position is ok. If so the current ship
     *is placed on the coordinates and the process repeats for each ship. 
     */
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
            
            while(!current.okToPlaceShipAt(tempRow, tempColumn, tempHorizontal, this))
            {
                	tempRow = rnd.nextInt(GRIDSIZE - 0);
                    tempColumn = rnd.nextInt(GRIDSIZE - 0);
                    tempHorizontal = rnd.nextBoolean();
            }
            current.placeShipAt(tempRow, tempColumn, tempHorizontal, this);
		}
    }
    
    
    /**
     * returns true if the given location contains a ship else returns false
     * 
     * @param row - the row number 
     * @param column - the column number
     * @return boolean variable
     */
    boolean isOccupied(int row, int column)
    {
        Ship current = grid[row][column];
    	if(!"EmptySea".equals(current.getShipType()))
    	{
    		return true;
    	}
        return false;
    }
    
    
    /**
     * Prints Hit if the user hits a ship and the ship is not sunk or prints miss if the user hits a ship that is already sunk 
     * or hits empty sea
     * 
     * @param row - the row number the user wishes to shoot at
     * @param column - the column number the user wishes to shoot at
     * @return boolean variable
     */
    boolean shootAt(int row, int column)
    {
        boolean check = false;
        
        Ship current = grid[row][column];
 
        shotsFired++;
        System.out.println(); 
        
        if(isOccupied(row,column)==true)//if location contains a ship
        {
        	if(current.isSunk()==false)//if the boat is still afloat
        	{
        		current.shootAt(row, column);
        		hitCount++;
        		check = true;
        		if(current.isSunk())//if the current shot sinks the ship
        		{
        			shipsSunk++;
        			System.out.println("hit");
        			System.out.println("You just sank a "+ current.getShipType());
        		}
        		else
        		{
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

  
    /**
     * @return int - the number of shots that have been fired during the game
     */
    int getShotsFired()
    {
        return shotsFired;
    }
    
    
    /**
     * returns the number of hits recorded - shots that hit a ship 
     * all hits are counted and not just the first to hit a coordinate 
     * 
     * @return int - the count of shots that have hit a ship
     */
    int getHitCount()
    {
        return hitCount;
    }
    
    
    /**
     * @return int - number of ships that have been sunk during the current game
     */
    int getShipsSunk()
    {
        return shipsSunk;
    }
    
    
    /**
     * Signals the end of the game once all ships have been sunk
     * 
     * @return boolean - if all ships have been sunk
     */
    boolean isGameOver()
    {
        if(shipsSunk == NUMOFSHIPS)
        {
        	return true;
        }
        return false;
    }
    
    
    /**
     * @return 2D array - instances of the Ship class
     */
    Ship[][] getShipArray()
    {
    	return grid;
    }
    
  
    /**
     * prints the ocean to the user. This will print the current status of the game 
     * in the form of the array. 
     */
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
