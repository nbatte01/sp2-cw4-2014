package BattleshipGame;

import java.util.Scanner;

public class BattleshipGame
{
    public static void main(String[]args)
    {
        System.out.print("\f");
        Scanner in = new Scanner(System.in);
        
        Ocean game = new Ocean(); //this generates the blank 
        game.placeAllShipsRandomly(); //generates the ships positions on the empty ocean array
        game.print(); // prints the array for the user to see before he/she chooses her first shot
        
        //while the game has not finished
        /*while(game.isGameOver() == false)
        {
            System.out.println("Please enter the row number: ");
            int row = in.nextInt();
            System.out.println("Please enter the column number: ");
            int column = in.nextInt();
            
            // send the coordinates to the relevant methods
            
            game.print();
        }*/
        
        //Used for checking which object occupies the chosen location
        while(!game.isGameOver())
        {
        	System.out.print("Please enter a row");
        	int row = in.nextInt();
        	System.out.print("Please enter a column");
        	int column = in.nextInt();
        	game.checkPosition(row, column);
        	
        }
        
        in.close(); //closes the scanner 
    }
    
    //add some validation for the input from the user to ensure they are int's
}
