package BattleshipGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class testBattleshipGame {

	@Test
	public void testIntCheck() 
	{
		assertEquals(false,BattleshipGame.intCheck("nick"));
		assertEquals(false,BattleshipGame.intCheck("10"));
		assertEquals(true,BattleshipGame.intCheck("0"));
		assertEquals(false,BattleshipGame.intCheck("-"));
		assertEquals(false,BattleshipGame.intCheck("-5"));
	}

}
