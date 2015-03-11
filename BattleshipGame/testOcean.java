package BattleshipGame;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Test;

public class testOcean 
{		
		//need to find a way to test the getshiparray method
		/*@Test
		public void testGetShipArray() 
		{
			Ocean game = new Ocean();
			
			System.out.print(Arrays.toString(game.getShipArray()));
		}*/
			
		@Test
		public void testIsGameOver() 
		{
			Ocean game = new Ocean();
			assertEquals(false,game.isGameOver()); //should return false
		}

		@Test
		public void testGetShipsSunk() 
		{
			Ocean game = new Ocean();
			assertEquals(0,game.getShipsSunk()); //should return 0
		}
		
		@Test
		public void testGetHitCount() 
		{
			Ocean game = new Ocean();
			assertEquals(0,game.getHitCount()); //should return 0
		}
		
		@Test
		public void testGetShotsFired() 
		{
			Ocean game = new Ocean();
			assertEquals(0,game.getShotsFired()); //should return 0
		}
		
		@Test
		public void testShootAt() 
		{
			Ocean game = new Ocean();
			Battleship battleshipTest = new Battleship();
			battleshipTest.placeShipAt(0, 0, true,game); //places a temporary battleship at position 0,0
			assertEquals(true,game.shootAt(0,0)); //should return true
		}
		
		@Test
		public void testIsOccupied() 
		{
			Ocean game = new Ocean();
			Battleship battleshipTest = new Battleship();
			battleshipTest.placeShipAt(0, 0, true,game); //places a temporary battleship at position 0,0
			assertEquals(true,game.isOccupied(0,0)); //should return true
		}
		
		@Test
		public void testConstructor()
		{
			Ocean game = new Ocean();
			assertEquals(0,game.getShotsFired()); //should return 0 after being initialised in the constructor
			assertEquals(0,game.getHitCount());  //should return 0 after being initialised in the constructor
			assertEquals(0,game.getShipsSunk());  //should return 0 after being initialised in the constructor
			
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

	


