package es.jcyl.cag.cursotesting.tictactoe;

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
		return Resultado.SEGUIR;
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
