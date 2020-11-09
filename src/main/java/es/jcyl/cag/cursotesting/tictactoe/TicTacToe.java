package es.jcyl.cag.cursotesting.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {

	private int TAMANO = 3;

	private Casilla[][] tablero = null;
	private Jugador jugadorX = null;
	private Jugador jugadorO = null;
	
	private  Casilla turno = Casilla.X;
	
	public TicTacToe(Jugador jugadorX, Jugador jugadorO) {
		super();
		initTablero();
		this.jugadorX = jugadorX;
		this.jugadorO = jugadorO;
	}
	private void initTablero() {
		tablero = new Casilla[TAMANO][TAMANO];
		for (int i = 0; i < TAMANO; i++) {
			for (int j = 0; j < TAMANO; j++) {
				tablero[i][j] = Casilla.VACIA;
			}
		}
	}
	
	public Resultado jugar() {
		Resultado anterior = comprobarResultado();
		if (anterior != Resultado.SEGUIR) {
			throw new RuntimeException("No se puede seguir, la partida terminó con resultado:" + anterior);
		}
		Resultado resultado = null;
		if (turno == Casilla.X) {
			Posicion p = jugadorX.jugar(clonarTablero());
			resultado = jugar(p.getX(), p.getY(), turno);
			turno = Casilla.O;
		}
		else {
			Posicion p = jugadorO.jugar(clonarTablero());
			resultado = jugar(p.getX(), p.getY(), turno);
			turno = Casilla.X;
		}
		return resultado;
	}
	
	
	private Resultado jugar(int x, int y, Casilla turno) {
		if (x < 0 || x >= TAMANO) {
			throw new RuntimeException("valor de x invalido:" + x);
		}
		if (y < 0 || y >= TAMANO) {
			throw new RuntimeException("valor de y invalido:" + y);
		}
		if (tablero[x][y] == Casilla.VACIA) {
			tablero[x][y] = turno;
		}
		else {
			throw new RuntimeException("Ya ha rellenado la casilla " + x + " "  + y);
		}
		return comprobarResultado();
	}
	private Resultado comprobarResultado() {
		
		// probamos vertical
		for (int x = 0; x < TAMANO; x++) {
			if (igualesYNovacias(tablero[x])) {
				return ganador(tablero[x][0]);
			}
		}
		// Probamos horizontal
		for (int y = 0; y < TAMANO; y++) {
			List<Casilla> lista = new ArrayList<Casilla>();
			for (int x = 0; x < TAMANO; x++) {
				lista.add(tablero[x][y]);
			}
			Casilla[] tmp = lista.toArray(new Casilla[0]);
			if (igualesYNovacias(tmp)) {
				return ganador(tmp[0]);
			}
		}
		
		// Probamos diagonal 1
		List<Casilla> diagonal1 = new ArrayList<Casilla>();
		for (int x = 0; x < TAMANO; x++) {
			diagonal1.add(tablero[x][x]);
		}
		Casilla[] tmp2 = diagonal1.toArray(new Casilla[0]);
		if (igualesYNovacias(tmp2)) {
			return ganador(tmp2[0]);
		}
		
		// Probamos diagonal 2
		List<Casilla> diagonal2= new ArrayList<Casilla>();
		for (int x = 0; x < TAMANO; x++) {
			diagonal2.add(tablero[x][TAMANO-x-1]);
		}
		Casilla[] tmp3 = diagonal2.toArray(new Casilla[0]);
		if (igualesYNovacias(tmp3)) {
			return ganador(tmp3[0]);
		}
		
		if (isTablas()) {
			return Resultado.TABLAS;
		}
		
		return Resultado.SEGUIR;
	}
	
	private boolean isTablas() {
		for (Casilla[] columna: tablero) {
			for (Casilla casilla: columna) {
				if (casilla == Casilla.VACIA) {
					return false;
				}
			}
		}
		return true;
	}
	private Resultado ganador(Casilla casilla) {
		if (Casilla.X == casilla) {
			return Resultado.GANA_X;
		}
		else if (Casilla.O == casilla) {
			return Resultado.GANA_O;
		}
		return null;
	}
	private boolean igualesYNovacias(Casilla[] lista) {
		Casilla inicial  =lista[0];
		if (inicial == Casilla.VACIA) {
			return false;
		}
		for (Casilla casilla : lista) {
			if (casilla != inicial) {
				return false;
			}
		}
		return true;
	}
	
	private Casilla[][] clonarTablero() {
		Casilla[][] clonado = new Casilla[TAMANO][TAMANO];
		for (int i = 0; i < TAMANO; i++) {
			for (int j = 0; j < TAMANO; j++) {
				clonado[i][j] = tablero[i][j] ;
			}
		}
		
		return clonado;
	}
}
