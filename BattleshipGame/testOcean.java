/**
 * @author Nicholas Jay Batten, Username: nbatte01, Date: 29/03/2015
 * This code is created for the 4th and final assignment of the software and programming 2 module at 
 * Birkebeck, University of London.
 * 
 *
 */

package BattleshipGame;

import static org.junit.Assert.*;


import org.junit.Test;

public class testOcean 
{		
			
		@Test
		public void testIsGameOver() 
		{
			Ocean game = new Ocean();
			assertEquals(false,game.isGameOver());
		}

		@Test
		public void testGetShipsSunk() 
		{
			Ocean game = new Ocean();
			assertEquals(0,game.getShipsSunk());
		}
		
		@Test
		public void testGetHitCount() 
		{
			Ocean game = new Ocean();
			assertEquals(0,game.getHitCount()); 
		}
		
		@Test
		public void testGetShotsFired() 
		{
			Ocean game = new Ocean();
			assertEquals(0,game.getShotsFired()); 
		}
		
		@Test
		public void testShootAt() 
		{
			Ocean game = new Ocean();
			Battleship battleshipTest = new Battleship();
			battleshipTest.placeShipAt(0, 0, true,game); 
			assertEquals(true,game.shootAt(0,0));
		}
		
		@Test
		public void testIsOccupied() 
		{
			Ocean game = new Ocean();
			Battleship battleshipTest = new Battleship();
			battleshipTest.placeShipAt(0, 0, true,game); 
			assertEquals(true,game.isOccupied(0,0)); 
		}
		
		@Test
		public void testConstructor()
		{
			Ocean game = new Ocean();
			assertEquals(0,game.getShotsFired());
			assertEquals(0,game.getHitCount());  
			assertEquals(0,game.getShipsSunk());  
			
		}
		
		@Test
		public void testPlaceAllShipsRandomly()
		{
			Ocean game = new Ocean();
			game.placeAllShipsRandomly();
			int count = 0;
			for(int i = 0 ; i < 10 ; i++)
			{
				for(int a = 0 ; a < 10 ; a++)
				{
					if(game.isOccupied(i, a) == true)
					{
						count++;
					}
				}
			}
			assertEquals(20,count);
		}
		

}

	


