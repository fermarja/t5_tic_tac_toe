package es.jcyl.cag.cursotesting.tictactoe;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.BDDMockito.Then;
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
	public void empiezaJugandoX() {
		Jugador jugador = jugadorFila(1);
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
		verify(jugador).jugar(anyCasilla());
	}
	
	@Test
	public void segundaJugadoaY() {
		Jugador jugadorX = jugadorFila(1);
		Jugador jugadorY = jugadorFila(2);
		TicTacToe tic = new TicTacToe(jugadorX, jugadorY);		
		tic.jugar();
		verify(jugadorX).jugar(anyCasilla());
		tic.jugar();
		verify(jugadorY).jugar(anyCasilla());
	}
	
	@Test
	public void terceraJugadoaX() {
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
 

}
