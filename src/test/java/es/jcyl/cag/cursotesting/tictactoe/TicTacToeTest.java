package es.jcyl.cag.cursotesting.tictactoe;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
	public void empiezaJugandoX() {
		Jugador jugador = nullJugador();
		TicTacToe tic = new TicTacToe(jugador, nullJugador());
		tic.jugar();
		verify(jugador).jugar(anyCasilla());
	}
	
	@Test
	public void segundaJugadoaY() {
		Jugador jugadorX = nullJugador();
		Jugador jugadorY = nullJugador();
		TicTacToe tic = new TicTacToe(jugadorX, jugadorY);		
		tic.jugar();
		verify(jugadorX).jugar(anyCasilla());
		tic.jugar();
		verify(jugadorY).jugar(anyCasilla());
	}
	
	@Test
	public void terceraJugadoaX() {
		Jugador jugadorX = nullJugador();
		Jugador jugadorY = nullJugador();
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
 

}
