package com.mastermind.server.logic;

import junit.framework.TestCase;

public class GameLogicTestCase extends TestCase {
	
	private GameLogic logic;

	protected void setUp() throws Exception {
		super.setUp();
		
		logic = new GameLogic(null, null);
	}
	
	public void testGenerateClue() {
		int[] guesses = { 2, 2, 2, 2 };
		int[] clue = logic.generateClue(guesses);
		
		for(int c : clue) {
			System.out.print(c + "-");
		}
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		logic = null;
	}

}
