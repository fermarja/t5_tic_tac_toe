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
		Jugador jugador = mock(Jugador.class);
		when(jugador.jugar(anyCasilla())).thenReturn(new Posicion(-1,0));		
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
	}
	
	@Test(expected=RuntimeException.class)
	public void xFueraRango() {
		Jugador jugador = mock(Jugador.class);
		when(jugador.jugar(anyCasilla())).thenReturn(new Posicion(Integer.MAX_VALUE,0));		
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
	}
	
	@Test(expected=RuntimeException.class)
	public void yFueraRangoNegativoExcepcion() {
		Jugador jugador = mock(Jugador.class);
		when(jugador.jugar(anyCasilla())).thenReturn(new Posicion(0,-1));		
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
	}
	
	@Test(expected=RuntimeException.class)
	public void yFueraRango() {
		Jugador jugador = mock(Jugador.class);
		when(jugador.jugar(anyCasilla())).thenReturn(new Posicion(0,Integer.MAX_VALUE));		
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
	}



	@Test(expected=RuntimeException.class)
	public void noSePuedeRepetirCasilla() {
		Jugador jugador = mock(Jugador.class);
		when(jugador.jugar(anyCasilla())).thenReturn(new Posicion(1, 1));		
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
		Jugador jugadorX = mock(Jugador.class);
		when(jugadorX.jugar(anyCasilla())).thenReturn(new Posicion(0, 0), new Posicion(1, 1), new Posicion(2, 2));		
		Jugador jugadorO = mock(Jugador.class);
		when(jugadorO.jugar(anyCasilla())).thenReturn(new Posicion(1, 0), new Posicion(2, 0));	
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
		Jugador jugadorY = jugadorFila(2);
		TicTacToe tic = new TicTacToe(jugadorX, jugadorY);
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
		tic.jugar();
	}
	
	
	private Casilla[][] anyCasilla() {
		return any(Casilla[][].class);
	}
	
	private Jugador nullJugador() {
		return mock(Jugador.class);
	}
	
	private Jugador jugadorFila(int fila) {
		Jugador jugador = mock(Jugador.class);
		when(jugador.jugar(anyCasilla())).thenReturn(new Posicion(fila, 0), new Posicion(fila, 1), new Posicion(fila, 2));		
		return jugador;
	}
	
	private Jugador jugadorColumna(int columna) {
		Jugador jugador = mock(Jugador.class);
		when(jugador.jugar(anyCasilla())).thenReturn(new Posicion(0, columna), new Posicion(1, columna), new Posicion(2, columna));		
		return jugador;
	}
 

}
