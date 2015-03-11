package BattleshipGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class testShip 
{
	
	
	@Test
	public void testGetBowRow()
	{
		Ocean game = new Ocean();
		Destroyer destroyer = new Destroyer();
		destroyer.placeShipAt(5, 0, true,game);
		assertEquals(5,destroyer.getBowRow());
	}
	
	@Test
	public void testGetBowColumn()
	{
		Ocean game = new Ocean();
		Destroyer destroyer = new Destroyer();
		destroyer.placeShipAt(5, 0, true,game);
		assertEquals(0,destroyer.getBowColumn());
	}
	
	@Test	
	public void testGetShipType()
	{
		Ocean game = new Ocean();
		Destroyer d = new Destroyer();
		assertEquals("destroyer",d.getShipType());
		Battleship b = new Battleship();
		assertEquals("battleship",b.getShipType());
		Cruiser c = new Cruiser();
		assertEquals("cruiser",c.getShipType());
		Submarine s = new Submarine();
		assertEquals("submarine",s.getShipType());
	}
	
	@Test
	public void testIsHorizontal()
	{
		Ocean game = new Ocean();
		Destroyer d = new Destroyer();
		d.placeShipAt(5, 0, true,game);
		assertEquals(true,d.isHorizontal());
	}
	
	//acts as the test for the 'setHitArray', 'sectionHit' and 'shootAt' methods
	@Test
	public void testSetHitArray()
	{
		Ocean game = new Ocean();
		Destroyer d = new Destroyer();
		d.placeShipAt(5, 0, true,game);
		assertEquals(false,d.sectionHit(5,0));
		game.shootAt(5,0);
		assertEquals(true,d.sectionHit(5,0));
		
	}
	
	@Test
	public void testIsSunk()
	{
		Ocean game = new Ocean();
		Submarine s = new Submarine();
		assertEquals(false,s.isSunk());
		s.placeShipAt(0, 0, true, game);
		game.shootAt(0,0);
		assertEquals(true,s.isSunk());
	}
	
	@Test
	public void testGetLength()
	{
		Ocean game = new Ocean();
		Destroyer d = new Destroyer();
		assertEquals(2,d.getLength());
		Battleship b = new Battleship();
		assertEquals(4,b.getLength());
		Cruiser c = new Cruiser();
		assertEquals(3,c.getLength());
		Submarine s = new Submarine();
		assertEquals(1,s.getLength());
	}
	
	//acts as the test for the 'setHitArray' & 'sectionHit' methods
	@Test
	public void testOkToPlaceShipAt()
	{
		Ocean game = new Ocean();
		Destroyer d = new Destroyer();
		assertEquals(true,d.okToPlaceShipAt(0, 0, true, game));
		d.placeShipAt(0, 0, true, game);
		assertEquals(false,d.okToPlaceShipAt(0, 0, true, game));
	}

	//Test for individual ship subclasses
	
	@Test
	public void testToString()
	{
		Ocean game = new Ocean();
		Destroyer d = new Destroyer();
		assertEquals(" . ",d.toString());
		Battleship b = new Battleship();
		assertEquals(" . ",b.toString());
		Cruiser c = new Cruiser();
		assertEquals(" . ",c.toString());
		Submarine s = new Submarine();
		assertEquals(" . ",s.toString());
	}

}
