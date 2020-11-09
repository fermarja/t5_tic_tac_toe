package es.jcyl.cag.cursotesting.tictactoe;

public class TicTacToe {

	private int TAMANO = 3;

	private Casilla[][] tablero = null;
	public TicTacToe() {
		super();
		initTablero();
	}
	private void initTablero() {
		tablero = new Casilla[TAMANO][TAMANO];
		for (int i = 0; i < TAMANO; i++) {
			for (int j = 0; j < TAMANO; j++) {
				tablero[i][j] = Casilla.VACIA;
			}
		}
	}
	
	public void jugar(int x, int y) {
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
}
