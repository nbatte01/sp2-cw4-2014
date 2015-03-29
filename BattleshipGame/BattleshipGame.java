/**
 * @author Nicholas Jay Batten, Username: nbatte01, Date: 29/03/2015
 * This code is created for the 4th and final assignment of the software and programming 2 module at 
 * Birkebeck, University of London.
 * 
 *
 */

package BattleshipGame;

import java.util.Scanner;

public class BattleshipGame
{
	
    /**
     * Asks the user to enter a row number and column number as a string which is then passed to 'intCheck' method to ensure they 
     * adhere to the limitations of the game. If they do then they are converted to int and then passed to the 'shootAt' method in the 
     * 'Ocean' class
     */
    public static void main(String[]args)
    {
        Scanner in = new Scanner(System.in);
        
        Ocean game = new Ocean(); 
        game.placeAllShipsRandomly(); 
        System.out.println();
        
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
        
        in.close();
    }

    
    /**
     * Converts the entered string parameter into an int then checks that it is between 0 and 9
     * 
     * @param a - A string passed from the 'main' method
     * @return boolean - depending on whether the converted string is between 0 and 9
     */
    static boolean intCheck(String a)
    {
    	if((a.matches("[0-9]+"))&&(a.length() <= 1))
        {
            int b = (Integer.valueOf(a));
            if( (b >= 0)  &&  (b<= 10) ) 
            {
            	return true;
            }
        }
        return false;
    }
       
}
