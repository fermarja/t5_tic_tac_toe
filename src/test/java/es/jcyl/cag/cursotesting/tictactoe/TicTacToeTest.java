package es.jcyl.cag.cursotesting.tictactoe;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

public class TicTacToeTest {

	public TicTacToeTest () {
		super();
	}
	
	@Test(expected=RuntimeException.class)
	public void xFueraRangoNegativoExcepcion() {
		Jugador jugador = crearJugador(-1, 0);
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
	}
	
	@Test(expected=RuntimeException.class)
	public void xFueraRango() {
		Jugador jugador = crearJugador(Integer.MAX_VALUE, 0);	
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
	}
	
	@Test(expected=RuntimeException.class)
	public void yFueraRangoNegativoExcepcion() {
		Jugador jugador = crearJugador(0,-1);		
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
	}
	
	@Test(expected=RuntimeException.class)
	public void yFueraRango() {
		Jugador jugador = crearJugador(0, Integer.MAX_VALUE);	
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
	}



	@Test(expected=RuntimeException.class)
	public void noSePuedeRepetirCasilla() {
		Jugador jugador = crearJugador(1, 1);			
		TicTacToe tic = new TicTacToe(jugador, jugador);
		tic.jugar();
		tic.jugar();
	}
	
	@Test
	public void empiezaJugandiX() {
		Jugador jugador = jugadorFila(1);
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
		verify(jugador).jugar(anyCasilla());
	}
	
	@Test
	public void segundaJugadaY() {
		Jugador jugadorX = jugadorFila(1);
		Jugador jugadorY = jugadorFila(2);
		TicTacToe tic = new TicTacToe(jugadorX, jugadorY);		
		tic.jugar();
		verify(jugadorX).jugar(anyCasilla());
		tic.jugar();
		verify(jugadorY).jugar(anyCasilla());
	}
	
	@Test
	public void terceraJugadaX() {
		Jugador jugadorX = jugadorFila(1);
		Jugador jugadorY = jugadorFila(2);
		TicTacToe tic = new TicTacToe(jugadorX, jugadorY);		
		tic.jugar();
		verify(jugadorX).jugar(anyCasilla());
		tic.jugar();
		verify(jugadorY).jugar(anyCasilla());
		tic.jugar();
		verify(jugadorX, times(2)).jugar(anyCasilla());
	}
	
	
	@Test
	public void testDefectoSeguir() {
		Jugador jugadorX = jugadorFila(1);
		TicTacToe tic = new TicTacToe(jugadorX, nullJugador());		
		Resultado resultado = tic.jugar();
		Assert.assertEquals(Resultado.SEGUIR, resultado);
	}
	
	
	
	
	@Test
	public void testVictoriaHorizontal() {
		Jugador jugadorX = jugadorFila(1);
		Jugador jugadorO = jugadorFila(2);
		TicTacToe tic = new TicTacToe(jugadorX, jugadorO);
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
		Resultado resultado = tic.jugar();
		Assert.assertEquals(Resultado.GANA_X, resultado);
	}
	
	@Test
	public void testVictoriaVertical() {
		Jugador jugadorX = jugadorColumna(1);
		Jugador jugadorO = jugadorColumna(2);
		TicTacToe tic = new TicTacToe(jugadorX, jugadorO);
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
		Resultado resultado = tic.jugar();
		Assert.assertEquals(Resultado.GANA_X, resultado);
	}
	
	@Test
	public void testVictoriaDiagonal() {
		Jugador jugadorX = crearJugador(new int[][] {{ 0, 0 }, {1, 1 }, {2, 2}});
		Jugador jugadorO = crearJugador(new int[][] {{ 1, 0 }, {2, 1 }});
		TicTacToe tic = new TicTacToe(jugadorX, jugadorO);
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
		Resultado resultado = tic.jugar();
		Assert.assertEquals(Resultado.GANA_X, resultado);
	}
	
	@Test(expected = RuntimeException.class)
	public void testExcepcionSiContinuaTrasGanar() {
		Jugador jugadorX = jugadorFila(1);
		Jugador jugadorO = jugadorFila(2);
		TicTacToe tic = new TicTacToe(jugadorX, jugadorO);
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
	}
	
	@Test
	public void testTablas() {
		/*
		 0 X 0
		 X X 0
		 X 0 X
		 */
		Jugador jugadorX = crearJugador(new int[][] {{ 1, 0 }, {0, 1}, {1, 1}, {2,0}, {2,2}});
		Jugador jugadorO = crearJugador(new int[][] {{ 0, 0 }, {0, 2}, {1, 2} , {2,1}});
		TicTacToe tic = new TicTacToe(jugadorX, jugadorO);
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();		
		Resultado resultado = tic.jugar();
		Assert.assertEquals(Resultado.TABLAS, resultado);
	}
	
	
	private Casilla[][] anyCasilla() {
		return any(Casilla[][].class);
	}
	
	private Jugador nullJugador() {
		return mock(Jugador.class);
	}
	
	private Jugador jugadorFila(int fila) {
		return crearJugador(new int[][] {{ fila,0 }, {fila, 1 }, {fila, 2}});
	}
	
	private Jugador jugadorColumna(int columna) {
		return crearJugador(new int[][] {{0, columna}, {1, columna}, {2, columna}});
	}
	
	private Jugador crearJugador(int x, int y) {
		return crearJugador(new int[][] { { x, y } });
	}
	
	private Jugador crearJugador(int[]... jugadas) {
		Posicion[] posiciones = jugadasAPosiciones(jugadas);
		Jugador jugador = mock(Jugador.class);
		if (posiciones.length == 1) {
			when(jugador.jugar(anyCasilla())).thenReturn(posiciones[0]);		
		}
		else {
			Posicion[] resto =  new Posicion[posiciones.length -1];
			for (int i = 1; i < posiciones.length; i++) {
				resto[i-1] = posiciones[i];
			}
			when(jugador.jugar(anyCasilla())).thenReturn(posiciones[0], resto);
		}
		return jugador;
	}

	private Posicion[] jugadasAPosiciones(int[][] jugadas) {
		Posicion[] posiciones = new Posicion[jugadas.length];
		for (int i = 0; i < posiciones.length; i++) {
			int[] jugada = jugadas[i];
			posiciones[i] = new Posicion(jugada[0], jugada[1]);
		}
		return posiciones;
	}
 

}
