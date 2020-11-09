package es.jcyl.cag.cursotesting.tictactoe;

public class TicTacToe {

	private int TAMANO = 3;

	private Casilla[][] tablero = null;
	private Jugador jugadorX = null;
	private Jugador jugadorO = null;
	
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
	
	public void jugar() {
		Posicion p = jugadorX.jugar(clonarTablero());
		jugar(p.getX(), p.getY());
	}
	
	
	private void jugar(int x, int y) {
		if (x < 0 || x >= TAMANO) {
			throw new RuntimeException("valor de x invalido:" + x);
		}
		if (y < 0 || y >= TAMANO) {
			throw new RuntimeException("valor de y invalido:" + y);
		}
		if (tablero[x][y] == Casilla.VACIA) {
			tablero[x][y] = Casilla.X;
		}
		else {
			throw new RuntimeException("Ya ha rellenado la casilla " + x + " "  + y);
		}
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
