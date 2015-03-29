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
