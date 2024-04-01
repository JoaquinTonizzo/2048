package logica;

import java.util.Random;

public class Juego2048 {
	int TAMANO = 4;
	int[][] tablero;
	Random rand;
	Integer turno, puntos;

	public Juego2048() {
		reiniciarJuego();
	}

	public void reiniciarJuego() {
		tablero = new int[TAMANO][TAMANO];
		turno = 0;
		puntos = 0;
		agregarNumero();
		agregarNumero();
	}

	// Agregar numero 2 o 4;
	public void agregarNumero() {
		int fila, columna;
		rand = new Random();
		do {
			fila = rand.nextInt(4); // Genera un entero aleatorio entre 0 y 3;
			columna = rand.nextInt(4); // Genera un entero aleatorio entre 0 y 3;
		} while (tablero[fila][columna] != 0); // Si existe un valor en una posicion, seguir cambiando de forma aleatoria los valores de fila y columna.
		// Cuando termina de encontrar una posicion vacia, asigno de forma aleatoria los valores {2,4}
		// Por defecto, cualquier posicion de una matriz tiene asignado el valor 0;
		tablero[fila][columna] = rand.nextInt(2) * 2 + 2;
	}

	public int[][] obtenerTablero() {
		return tablero;
	}

	public String obtenerTurno() {
		return turno.toString();
	}

	public boolean mover(int direccion) {
		boolean movimientoValido = false;
		switch (direccion) {
		case 1: // Arriba
			movimientoValido = moverArriba();
			break;
		case 2: // Abajo
			movimientoValido = moverAbajo();
			break;
		case 3: // Izquierda
			movimientoValido = moverIzquierda();
			break;
		case 4: // Derecha
			movimientoValido = moverDerecha();
			break;
		}
		turno++;
		return movimientoValido;
	}
	
	private boolean moverArriba() {
	    boolean movimientoRealizado = false; // Inicializa la variable para indicar si se realizó algún movimiento durante el proceso
	    for (int columna = 0; columna < TAMANO; columna++) { // Recorre todas las columnas del tablero    
	        int filaActual = 0; // Inicializa la fila actual en 0
	        for (int fila = 1; fila < TAMANO; fila++) { // Recorre todas las filas del tablero menos la 0
	            if (tablero[fila][columna] != 0) { // Verifica si la casilla actual no está vacía (es diferente de 0)
	                if (tablero[filaActual][columna] == 0) { // Verifica si la casilla superior a la actual actual está vacía (es igual a 0)
	                    tablero[filaActual][columna] = tablero[fila][columna]; // Mueve el valor de la casilla actual a la casilla superior
	                    tablero[fila][columna] = 0; // Vacía la casilla actual
	                    movimientoRealizado = true; // Indica que se realizó un movimiento
	                } 
	                else if (tablero[filaActual][columna] == tablero[fila][columna]) { // Verifica si la casilla superior a la actual actual es igual al de la casilla actual
	                    tablero[filaActual][columna] *= 2; // Duplica el valor de la casilla superior a la actual (se combinan)
	                    tablero[fila][columna] = 0; // Vacía la casilla actual
	                    movimientoRealizado = true; // Indica que se realizó un movimiento
	                    sumarPuntos(tablero[filaActual][columna]); // Suma los puntos del valor duplicado
	                    filaActual++; // Incrementa la fila actual para evitar fusiones duplicadas
	                } 
	                 else { // Si la casilla actual no es vacia pero no puede fusionarse con su superior
	                    filaActual++; // Incrementamos la fila actual porque no podemos mover ningún valor allí
	                    if (filaActual != fila) { // Si la fila actual no es igual a la fila actual del bucle exterior, movemos el valor de la casilla actual a la fila actual
	                        tablero[filaActual][columna] = tablero[fila][columna]; // Movemos el valor de la casilla actual a la casilla superior
	                        tablero[fila][columna] = 0; // Vaciamos la casilla original
	                       movimientoRealizado = true; // Indicamos que se realizó un movimiento
	                    }
	                }
	            }
	        }
	    }
	    return movimientoRealizado; // Devuelve si se realizó algún movimiento durante el proceso
	}

	private boolean moverAbajo() {
	    boolean movimientoRealizado = false;
	    for (int columna = 0; columna < TAMANO; columna++) {
	        int filaActual = TAMANO - 1;
	        for (int fila = TAMANO - 2; fila >= 0; fila--) {
	            if (tablero[fila][columna] != 0) {
	                if (tablero[filaActual][columna] == 0) {
	                    tablero[filaActual][columna] = tablero[fila][columna];
	                    tablero[fila][columna] = 0;
	                    movimientoRealizado = true;
	                } else if (tablero[filaActual][columna] == tablero[fila][columna]) {
	                    tablero[filaActual][columna] *= 2;
	                    tablero[fila][columna] = 0;
	                    movimientoRealizado = true;
	                    sumarPuntos(tablero[filaActual][columna]);
	                    filaActual--;
	                } else {
	                    filaActual--;
	                    if (filaActual != fila) {
	                        tablero[filaActual][columna] = tablero[fila][columna];
	                        tablero[fila][columna] = 0;
	                        movimientoRealizado = true;
	                    }
	                }
	            }
	        }
	    }
	    return movimientoRealizado;
	}

	private boolean moverIzquierda() {
	    boolean movimientoRealizado = false;
	    for (int fila = 0; fila < TAMANO; fila++) {
	        int columnaActual = 0;
	        for (int columna = 1; columna < TAMANO; columna++) {
	            if (tablero[fila][columna] != 0) {
	                if (tablero[fila][columnaActual] == 0) {
	                    tablero[fila][columnaActual] = tablero[fila][columna];
	                    tablero[fila][columna] = 0;
	                    movimientoRealizado = true;
	                } else if (tablero[fila][columnaActual] == tablero[fila][columna]) {
	                    tablero[fila][columnaActual] *= 2;
	                    tablero[fila][columna] = 0;
	                    movimientoRealizado = true;
	                    sumarPuntos(tablero[fila][columnaActual]);
	                    columnaActual++;
	                } else {
	                    columnaActual++;
	                    if (columnaActual != columna) {
	                        tablero[fila][columnaActual] = tablero[fila][columna];
	                        tablero[fila][columna] = 0;
	                        movimientoRealizado = true;
	                    }
	                }
	            }
	        }
	    }
	    return movimientoRealizado;
	}

	private boolean moverDerecha() {
	    boolean movimientoRealizado = false;
	    for (int fila = 0; fila < TAMANO; fila++) {
	        int columnaActual = TAMANO - 1;
	        for (int columna = TAMANO - 2; columna >= 0; columna--) {
	            if (tablero[fila][columna] != 0) {
	                if (tablero[fila][columnaActual] == 0) {
	                    tablero[fila][columnaActual] = tablero[fila][columna];
	                    tablero[fila][columna] = 0;
	                    movimientoRealizado = true;
	                } else if (tablero[fila][columnaActual] == tablero[fila][columna]) {
	                    tablero[fila][columnaActual] *= 2;
	                    tablero[fila][columna] = 0;
	                    movimientoRealizado = true;
	                    sumarPuntos(tablero[fila][columnaActual]);
	                    columnaActual--;
	                } else {
	                    columnaActual--;
	                    if (columnaActual != columna) {
	                        tablero[fila][columnaActual] = tablero[fila][columna];
	                        tablero[fila][columna] = 0;
	                        movimientoRealizado = true;
	                    }
	                }
	            }
	        }
	    }
	    return movimientoRealizado;
	}

	private void sumarPuntos(int valor) {
		puntos += valor; // Sumar puntos dependiendo del valor de la combinación
	}

	public String obtenerPuntos() {
		return puntos.toString();
	}
	
	public int obtenerPuntosInt() {
		return puntos;
	}

	public boolean verificarVictoria() {
		for (int fila = 0; fila < TAMANO; fila++) {
			for (int columna = 0; columna < TAMANO; columna++) {
				if (tablero[fila][columna] == 2048) {
					return true; // Se llegó el valor 2048
				}
			}
		}
		return false;
	}

	public boolean verificarDerrota() {
		for (int fila = 0; fila < TAMANO; fila++) {
			for (int columna = 0; columna < TAMANO; columna++) {
				if (tablero[fila][columna] == 0) {
					return false; // Hay espacios vacíos
				}
				if (fila < TAMANO - 1 && tablero[fila][columna] == tablero[fila + 1][columna]) {
					return false; // Hay un movimiento posible en vertical
				}
				if (columna < TAMANO - 1 && tablero[fila][columna] == tablero[fila][columna + 1]) {
					return false; // Hay una movimiento posible en horizontal
				}
			}
		}
		return true; // No hay movimientos posibles
	}

}

//FUNCIONES DE MOVIMIENTO ANTERIORES QUE DAN ERROR EN LOS TEST YA QUE FUSIONAN NUMEROS DE MAS EN UN MISMO MOVIMEINTO
//LAS FUNCIONES QUE ESTAN ACTUALMENTE EN EL CODIGO SON HORRIBLES PERO PARECEN FUNCIONAR BIEN
/**
public boolean moverArriba() {
		boolean movimientoValido = false;
		for (int columna = 0; columna < TAMANO; columna++) {
			movimientoValido |= moverColumnaArriba(columna);
		}
		return movimientoValido;
	}

	private boolean moverColumnaArriba(int columna) {
		boolean movimientoValido = false;
		for (int fila = 1; fila < TAMANO; fila++) {
			if (tablero[fila][columna] != 0) {
				movimientoValido |= moverFichaHaciaArriba(columna, fila);
			}
		}
		return movimientoValido;
	}

	private boolean moverFichaHaciaArriba(int columna, int fila) {
		boolean movimientoValido = false;
		int filaActual = fila;
		while (filaActual > 0 && (tablero[filaActual - 1][columna] == 0 || tablero[filaActual - 1][columna] == tablero[filaActual][columna])) {
			if (tablero[filaActual - 1][columna] == 0) {
				tablero[filaActual - 1][columna] = tablero[filaActual][columna];
				tablero[filaActual][columna] = 0;
				filaActual--;
				movimientoValido = true;
			} else if (tablero[filaActual - 1][columna] == tablero[filaActual][columna]) {
				tablero[filaActual - 1][columna] *= 2;
				tablero[filaActual][columna] = 0;
				movimientoValido = true;
				sumarPuntos(tablero[filaActual - 1][columna]);
				break;
			}
		}
		return movimientoValido;
	}

	public boolean moverIzquierda() {
		boolean movimientoValido = false;
		for (int fila = 0; fila < TAMANO; fila++) {
			movimientoValido |= moverFilaIzquierda(fila);
		}
		return movimientoValido;
	}

	private boolean moverFilaIzquierda(int fila) {
		boolean movimientoValido = false;
		for (int columna = 1; columna < TAMANO; columna++) {
			if (tablero[fila][columna] != 0) {
				movimientoValido |= moverFichaHaciaIzquierda(fila, columna);
			}
		}
		return movimientoValido;
	}

	private boolean moverFichaHaciaIzquierda(int fila, int columna) {
		boolean movimientoValido = false;
		int columnaActual = columna;
		while (columnaActual > 0 && (tablero[fila][columnaActual - 1] == 0 || tablero[fila][columnaActual - 1] == tablero[fila][columnaActual])) {
			if (tablero[fila][columnaActual - 1] == 0) {
				tablero[fila][columnaActual - 1] = tablero[fila][columnaActual];
				tablero[fila][columnaActual] = 0;
				columnaActual--;
				movimientoValido = true;
			} else if (tablero[fila][columnaActual - 1] == tablero[fila][columnaActual]) {
				tablero[fila][columnaActual - 1] *= 2;
				tablero[fila][columnaActual] = 0;
				movimientoValido = true;
				sumarPuntos(tablero[fila][columnaActual - 1]);
				break;
			}
		}
		return movimientoValido;
	}

	private boolean moverDerecha() {
		boolean movimientoValido = false;
		for (int fila = 0; fila < TAMANO; fila++) {
			movimientoValido |= moverFilaDerecha(fila);
		}
		return movimientoValido;
	}

	private boolean moverFilaDerecha(int fila) {
		boolean movimientoValido = false;
		for (int columna = TAMANO - 2; columna >= 0; columna--) {
			if (tablero[fila][columna] != 0) {
				movimientoValido |= moverFichaHaciaDerecha(fila, columna);
			}
		}
		return movimientoValido;
	}

	private boolean moverFichaHaciaDerecha(int fila, int columna) {
		boolean movimientoValido = false;
		int columnaActual = columna;
		while (columnaActual < TAMANO - 1 && (tablero[fila][columnaActual + 1] == 0 || tablero[fila][columnaActual + 1] == tablero[fila][columnaActual])) {
			if (tablero[fila][columnaActual + 1] == 0) {
				tablero[fila][columnaActual + 1] = tablero[fila][columnaActual];
				tablero[fila][columnaActual] = 0;
				columnaActual++;
				movimientoValido = true;
			} else if (tablero[fila][columnaActual + 1] == tablero[fila][columnaActual]) {
				tablero[fila][columnaActual + 1] *= 2;
				tablero[fila][columnaActual] = 0;
				movimientoValido = true;
				sumarPuntos(tablero[fila][columnaActual + 1]); 
				break;
			}
		}
		return movimientoValido;
	}

	public boolean moverAbajo() {
		boolean movimientoValido = false;
		for (int columna = 0; columna < TAMANO; columna++) {
			movimientoValido |= moverColumnaAbajo(columna);
		}
		return movimientoValido;
	}

	private boolean moverColumnaAbajo(int columna) {
		boolean movimientoValido = false;
		for (int fila = TAMANO - 2; fila >= 0; fila--) {
			if (tablero[fila][columna] != 0) {
				movimientoValido |= moverFichaHaciaAbajo(columna, fila);
			}
		}
		return movimientoValido;
	}

	private boolean moverFichaHaciaAbajo(int columna, int fila) {
		boolean movimientoValido = false;
		int filaActual = fila;
		while (filaActual < TAMANO - 1 && (tablero[filaActual + 1][columna] == 0 || tablero[filaActual + 1][columna] == tablero[filaActual][columna])) {
			if (tablero[filaActual + 1][columna] == 0) {
				tablero[filaActual + 1][columna] = tablero[filaActual][columna];
				tablero[filaActual][columna] = 0;
				filaActual++;
				movimientoValido = true;
			} else if (tablero[filaActual + 1][columna] == tablero[filaActual][columna]) {
				tablero[filaActual + 1][columna] *= 2;
				tablero[filaActual][columna] = 0;
				movimientoValido = true;
				sumarPuntos(tablero[filaActual + 1][columna]); // Corregido: usar el valor de la ficha actual
				break;
			}
		}
		return movimientoValido;
	}
**/