package com.fourx;
import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTest {

	@Test
	public void test() {

		GameBoard game = new GameBoard(2000, 4000, 2);
		assertEquals(2000, game.getRows());
		assertEquals(4000, game.getCols());

	}
}
