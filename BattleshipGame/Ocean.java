package BattleshipGame;

import java.util.Random;
//import java.util.ArrayList;

public class Ocean
{
    //Instance variable is the variable declared inside a class, but outside a method
    
    //The number of shots that have been fired by the user
    private int shotsFired;
    //The number of times a user's shot hits a ship. This increases even if the user hits the same part of the ship
    private int hitCount;
    //the number of ships that have been sunk max = 10
    private int shipsSunk;
    
    private final int GRIDSIZE = 10;
    private final int NUMOFSHIPS = 10;
    
    static //This fills an array with 10 by 10 instances of the ship class
    Ship[][] grid = new Ship[10][10];
    
    Random rnd = new Random();
    
    //constructor class that will create an empty ocean 
    //fills the "ships array with emptysea objects
    //will also intialise any game variables such as the instance variables above
    Ocean()//constructor 
    {
        // initialise instance variables
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
        
        //generate the 10 x 10 array with emptySea objects //needs to be moved out of the constructor method
        for(int a = 0 ; a < GRIDSIZE ; a++)
        {
            for(int b = 0 ; b < GRIDSIZE ; b++)
            {
                grid[a][b] = new EmptySea();
            }
        }
        
    }
    
    //Used to check which ship current occupies the chosen coordinates
    void checkPosition(int row, int column)
    {
    	Ship current = grid[row][column];
    	System.out.print("currently occupied by: ");
    	System.out.println(current.getShipType());
    	
    }
    
    //places all ships randomly ont he array. these will overwrite the
    //emptysea objects that have already been created.
    //Larger ships must be palced before smaller ones
    //random must be used to choose the location of the ships
    public void placeAllShipsRandomly()
    {
        //first generate a random row, column and position then send this to 
        //the oktoplaceshipat method in the ship class. if this return true then the position
        //is ok to use. Once the true valeu has been returned then send the current coordinates
        //and the position (horizontal / vertical) to the placeshipat method in the ship class to 
        //place the ship on the current coordinates
		
		Ship current = new Ship();
		
		for (int i = 0; i < NUMOFSHIPS; i++) {
			
			if(i == 0)
			{
				current= new Battleship();
			}
			if(i >= 1)
			{
				current = new Cruiser();
			}
			if(i >= 3)
			{
				current= new Destroyer();
			}
			if(i > 5)
			{
				current = new Submarine();
			}
			
			
			
			int tempRow = randomCoordinateGenerator(GRIDSIZE);
            int tempColumn = randomCoordinateGenerator(GRIDSIZE);
            boolean tempHorizontal = randomHorizontalGenerator();
        
        	//-----------------------------------------------------------
            boolean check = false;
            
            while(!check)
            {
            	if(current.okToPlaceShipAt(tempRow, tempColumn, tempHorizontal, this))
                {
                	current.placeShipAt(tempRow, tempColumn, tempHorizontal, this);
                	check = true;
                }
            	else
            	{
                	//generates a new set of random numbers
                	tempRow = randomCoordinateGenerator(GRIDSIZE);
                    tempColumn = randomCoordinateGenerator(GRIDSIZE);
                    tempHorizontal = randomHorizontalGenerator();
            	}
            }
		}
    
    }
    
    
    
    
    //returns true if the given location contains a ship and false if
    //it doesn't 
    boolean isOccupied(int row, int column)
    {
        boolean check = false;
        
        Ship current = grid[row][column];
    	if(!"EmptySea".equals(current.getShipType()))
    	{
    		check = true;
    	}
        return check;
    }
    
    
    //generates a random number between 0 and 9 for use in placing the ships randomly
    int randomCoordinateGenerator(int max)
    {
        int num = rnd.nextInt(max - 0);
        return num;
    }
    
    //returns a randomly generated position - vertical or horizontal depending on the output 
    //of either true or false
    boolean randomHorizontalGenerator()
    {
        return rnd.nextBoolean();
    }
    
    //returns true if the location contain a ship that is still afloat (not emptysea)
    //updates shotsFired, hitCount. This method should return true if the 
    //users fires at the same coordinates and the ship is still afloat. once the
    //ship has completly sunk then the method will return false if the user fires at 
    //the same coordinates
    //WORK IN CONJUNCTION WITH THE shootAt METHOD IN SHIP
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
        			System.out.println("You just sank boat number:"+shipsSunk);
        			System.out.println("Which was a "+ current.getShipType());
        		}
        		else
        		{
        			System.out.println("You just hit part of a ship!");
        		}
        	}
        	else//if the boat has already been sunk
        	{
        		System.out.println("That ship has already been sunk!");
        		check = false;
        	}
        }
        else//if shooting at an empty sea
        {
        	System.out.println("You didn't hit anything!");
        	current.hit[0] = true;//marks the empty sea array as true and thus ensuring the correct character is printed once the user has fired at this location
        	check = false;
        }
        System.out.println("There are "+(10-shipsSunk)+" boats left to sink!");
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
        boolean check = false;
        if(shipsSunk == 10)
        {
        	check = true;
        }
        return check;
    }
    
    Ship[][] getShipArray()
    {
    	return grid;
    }
    
    //used only for dev and testing as this will return the real array of where the ships are
    //and not just the array the users sees. ONLY USE IN TESTING
    /*Ship[][] getShipArray()
    {
        return grid[][];
    }*/
    
    //prints the ocean to the user. This will print the current status of the game 
    //in the form of the array. It must only be called from the battleshipgame class 
    //prints the relevant grid elements based on how the game is playing out instead of the actual location of the ships 
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
                			System.out.print(" $ "); //IF THAT SECTION OF THE SHIP HAS BEEN HIT
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
