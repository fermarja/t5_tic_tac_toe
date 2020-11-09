package es.jcyl.cag.cursotesting.tictactoe;

import org.junit.Test;

public class TicTacToeTest {

	public TicTacToeTest () {
		super();
	}
	
	@Test(expected=RuntimeException.class)
	public void xFueraRangoNegativoExcepcion() {
		TicTacToe tic = new TicTacToe();
		tic.jugar(-1, 0);
	}
	
	@Test(expected=RuntimeException.class)
	public void xFueraRango() {
		TicTacToe tic = new TicTacToe();
		tic.jugar(Integer.MAX_VALUE, 0);
	}
	
	@Test(expected=RuntimeException.class)
	public void yFueraRangoNegativoExcepcion() {
		TicTacToe tic = new TicTacToe();
		tic.jugar(0, -1);
	}
	
	@Test(expected=RuntimeException.class)
	public void yFueraRango() {
		TicTacToe tic = new TicTacToe();
		tic.jugar(Integer.MAX_VALUE, 0);
	}

	@Test(expected=RuntimeException.class)
	public void noSePuedeRepetirCasilla() {
		TicTacToe tic = new TicTacToe();
		tic.jugar(1, 1);
		tic.jugar(1, 1);
	}
 

}
