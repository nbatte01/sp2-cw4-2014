package BattleshipGame;

import java.util.Scanner;

public class BattleshipGame
{
    public static void main(String[]args)
    {
        System.out.print("\f");
        Scanner in = new Scanner(System.in);
        
        Ocean game = new Ocean(); 
        game.placeAllShipsRandomly(); 
        System.out.println();//used to provide a gap at the top of the output
        
        System.out.println("Welcome to Nick's Battleship Game!");
        
        System.out.println();
      
        
        while(!game.isGameOver())
        {
        	
        	game.print(); 
        	System.out.println();
			
        	System.out.print("Please enter a row: ");
        	String row = in.next();
        		
        	while(intCheck(row)==false)
        	{
        		System.out.println("Please ensure that you enter a int between 0 and 9");
        		row = in.next();
        	}
        	int checkedRow = (Integer.valueOf(row));
        	
        	System.out.print("Please enter a column: ");
        	String column = in.next();
        	
        	while(intCheck(column)==false)
        	{
        		System.out.println("Please ensure that you enter a int between 0 and 9");
        		column = in.next();
        	}
        	int checkedColumn = (Integer.valueOf(column));
        	
        	game.shootAt(checkedRow, checkedColumn);
        	
        	System.out.println();
        }
        if(game.isGameOver())//once all ships have been sunk
        {
        	System.out.println("You have sunk all the ships! Congratulations!");
        	System.out.println("You took "+ game.getShotsFired() + " Shots to complete the game");
        }
        
        in.close(); //closes the scanner 
    }
    
    //Ensures the input from the user is an int between 0 and 9
    static boolean intCheck(String a)
    {
    	boolean result = false;
        
        if((a.matches("[0-9]+"))&&(a.length() <= 1))
        {
            int b = (Integer.valueOf(a));
            if( (b >= 0)  &&  (b<= 10) ) 
            {
              result = true;
            }
        }
        return result;
    }
    
   
}
