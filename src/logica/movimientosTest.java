package logica;

import static org.junit.Assert.*;

import org.junit.Test;

public class movimientosTest {

    @Test
    public void testMovimientoArriba() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {0, 16, 2, 8},
            {8, 0, 4, 8},
            {8, 0, 8, 8},
            {16, 8, 16, 8}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(1)); // Mover arriba
        
        int[][] tableroEsperado = {
            {16, 16, 2, 16},
            {16, 8, 4, 16},
            {0, 0, 8, 0},
            {0, 0, 16, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testMovimientoAbajo() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {0, 0, 0, 0},
            {16, 0, 0, 0},
            {16, 0, 0, 0},
            {0, 0, 0, 0}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(2)); // Mover abajo
        
        int[][] tableroEsperado = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {32, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testMovimientoIzquierda() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {0, 0, 0, 0},
            {0, 8, 8, 16},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(3)); // Mover izquierda
        
        int[][] tableroEsperado = {
            {0, 0, 0, 0},
            {16, 16, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testMovimientoDerecha() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {0, 0, 0, 0},
            {8, 8, 16, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(4)); // Mover derecha
        
        int[][] tableroEsperado = {
            {0, 0, 0, 0},
            {0, 0, 16, 16},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    
    @Test
    public void testMovimientoArriba2() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {8, 0, 2, 8},
            {16, 8, 8, 8},
            {8, 8, 0, 8},
            {16, 8, 8, 8}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(1)); // Mover arriba
        
        int[][] tableroEsperado = {
            {8, 16, 2, 16},
            {16, 8, 16, 16},
            {8, 0, 0, 0},
            {16, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testMovimientoAbajo2() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {2, 0, 0, 0},
            {16, 2, 0, 0},
            {2, 16, 0, 0},
            {0, 2, 0, 0}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(2)); // Mover abajo
        
        int[][] tableroEsperado = {
            {0, 0, 0, 0},
            {2, 2, 0, 0},
            {16, 16, 0, 0},
            {2, 2, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testMovimientoIzquierda2() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {0, 0, 0, 0},
            {0, 8, 2, 8},
            {8, 2, 8, 0},
            {0, 0, 0, 0}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(3)); // Mover izquierda
        
        int[][] tableroEsperado = {
            {0, 0, 0, 0},
            {8, 2, 8, 0},
            {8, 2, 8, 0},
            {0, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testMovimientoDerech2() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
        	{8, 8, 8, 8},
            {8, 2, 8, 8},
            {0, 8, 2, 8},
            {0, 8, 2, 8}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(4)); // Mover derecha
        
        int[][] tableroEsperado = {
        	{0, 0, 16, 16},
            {0, 8, 2, 16},
            {0, 8, 2, 8},
            {0, 8, 2, 8}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }
    
    @Test
    public void testFusionMultipleDerecha() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {2, 2, 2, 2},
            {8, 4, 4, 4},
            {16, 8, 2, 16},
            {0, 0, 0, 0}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(4)); // Mover derecha
        int[][] tableroEsperado = {
            {0, 0, 4, 4},
            {0, 8, 4, 8},
            {16, 8, 2, 16},
            {0, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testMovimientoSinFusionIzquierda() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {2, 4, 8, 16},
            {0, 2, 4, 8},
            {0, 0, 2, 4},
            {0, 0, 0, 2}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(3)); // Mover izquierda
        int[][] tableroEsperado = {
            {2, 4, 8, 16},
            {2, 4, 8, 0},
            {2, 4, 0, 0},
            {2, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testNoMovimiento() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {2, 4, 8, 16},
            {16, 8, 4, 2},
            {2, 4, 8, 16},
            {16, 8, 4, 2}
        };
        juego.tablero = tableroInicial;
        assertFalse(juego.mover(1)); // Mover arriba
        assertFalse(juego.mover(2)); // Mover abajo
        assertFalse(juego.mover(3)); // Mover izquierda
        assertFalse(juego.mover(4)); // Mover derecha
        assertArrayEquals(tableroInicial, juego.obtenerTablero());
    }

    @Test
    public void testCondicionesLimite() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {2, 4, 8, 16},
            {16, 8, 4, 2},
            {2, 4, 8, 16},
            {16, 8, 4, 2}
        };
        juego.tablero = tableroInicial;
        assertFalse(juego.mover(1)); // Mover arriba
        assertFalse(juego.mover(3)); // Mover izquierda
        int[][] tableroEsperado = {
        	{2, 4, 8, 16},
            {16, 8, 4, 2},
            {2, 4, 8, 16},
            {16, 8, 4, 2}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }
    
}

