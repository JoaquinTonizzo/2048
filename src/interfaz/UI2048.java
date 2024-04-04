package interfaz;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;

import logica.Juego2048;
import logica.Ranking;

public class UI2048 {
	private JFrame frame;
	private JLabel[][] tableroIG;
	private JLabel nroTurno;
	private JLabel valorPuntaje;
	private Juego2048 juego2048;
	private boolean movimientoProcesado;
	private Ranking ranking;

	private static final Color[] COLORES_NUMEROS = { new Color(0xFFFFFF), // Color 2
			new Color(0xEDE0C8), // Color 4
			new Color(0xF2B179), // Color 8
			new Color(0xF59563), // Color 16
			new Color(0xF67C5F), // Color 32
			new Color(0xF65E3B), // Color 64
			new Color(0xEDCF72), // Color 128
			new Color(0xEDCC61), // Color 256
			new Color(0xEDC850), // Color 512
			new Color(0xEDC53F), // Color 1024
			new Color(0xEDC22E) // Color 2048
	};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI2048 window = new UI2048();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UI2048() {
		iniciarMenu();
	}

	private void iniciarMenu() {
		// Inicializo el juego

		// VENTANA
		frame = new JFrame(); 
		frame.setTitle("Juego 2048");
		frame.setBounds(100, 100, 400, 500); // (PosicionX, PosicionY, Ancho, Altura)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que la aplicacion se cierre correctamente cuando el usuario la quita
		frame.setResizable(false); // Evito que lo puedan redimensionar
		this.ranking = new Ranking();
		
		// Panel principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0xEDE0C8)); // Color 4
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		// Panel de título
		JLabel lblTitulo = new JLabel("2048");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 150));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

		// Panel de botones
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(0xEDE0C8));
		panelPrincipal.add(panelBotones, BorderLayout.CENTER);
		panelBotones.setLayout(new GridLayout(3, 1, 0, 15));

		// Botón Jugar
		JButton btnJugar = new JButton("Jugar");
		btnJugar.setPreferredSize(new Dimension(150, 50)); // Tamaño ajustado
		btnJugar.setBackground(new Color(0xF59563));
		btnJugar.setForeground(new Color(0xFFFFFF));
		btnJugar.setFont(new Font("Tahoma", Font.BOLD, 20)); // Fuente más grande
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				iniciarJuego();
			}
		});
		panelBotones.add(btnJugar);
	
		ranking.cargarRanking();
		// Botón Ranking
		JButton btnRanking = new JButton("Ranking");
		btnRanking.setPreferredSize(new Dimension(150, 50)); // Tamaño ajustado
		btnRanking.setBackground(new Color(0xEDC850));
		btnRanking.setForeground(new Color(0xFFFFFF));
		btnRanking.setFont(new Font("Tahoma", Font.BOLD, 20)); // Fuente más grande
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarRanking();
			}
		});
		panelBotones.add(btnRanking);

		// Botón Salir
		JButton btnSalir = new JButton("Salir");
		btnSalir.setPreferredSize(new Dimension(150, 50)); // Tamaño ajustado
		btnSalir.setBackground(new Color(0xF65E3B));
		btnSalir.setForeground(new Color(0xFFFFFF));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 20)); // Fuente más grande
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ranking.guardarRanking();
				System.exit(0);
			}
		});
		panelBotones.add(btnSalir);

		// Centrar la ventana en la pantalla
		frame.setLocationRelativeTo(null);
		// Mostrar la ventana
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ranking.guardarRanking(); 
            }
        });
	}

	private void iniciarJuego() {
		// Inicializo el juego

		juego2048 = new Juego2048();
		movimientoProcesado = false;

		// VENTANA
		frame = new JFrame(); // Creo la ventana
		frame.setBounds(100, 100, 400, 500); // (posicionx,PosicionY,ancho,altura)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que la aplicacion se cierre correctamente cuando
																// el usuario la quita
		frame.setTitle("Juego 2048");
		frame.setResizable(false);// Evito que lo puedan redimenzionar
		frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

		// PANEL SUPERIOR
		JPanel panelSuperior = new JPanel();
		panelSuperior.setPreferredSize(new Dimension(frame.getWidth(), 100)); // Establecer la altura del panel superior
		panelSuperior.setBackground(new Color(250, 248, 239));
		frame.getContentPane().add(panelSuperior, BorderLayout.NORTH); // Agrego el panel a la ventana, en la parte superior (con borderlayout)
		panelSuperior.setLayout(null);

		// PANEL PUNTAJE
		JPanel panelPuntaje = new JPanel();
		panelPuntaje.setBounds(219, 22, 68, 53);
		panelSuperior.add(panelPuntaje);
		panelPuntaje.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelPuntaje.setBackground(new Color(205, 193, 180));

		JLabel puntaje = new JLabel("Puntaje");
		puntaje.setForeground(new Color(250, 248, 239));
		puntaje.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelPuntaje.add(puntaje);
		puntaje.setHorizontalAlignment(SwingConstants.CENTER);

		valorPuntaje = new JLabel(juego2048.obtenerPuntosString());
		valorPuntaje.setForeground(new Color(255, 255, 255));
		valorPuntaje.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelPuntaje.add(valorPuntaje);

		// PANEL TURNO
		JPanel panelturno = new JPanel();
		panelturno.setBounds(297, 22, 68, 53);
		panelSuperior.add(panelturno);
		panelturno.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelturno.setBackground(new Color(205, 193, 180));

		JLabel Turno = new JLabel("N-Turno");
		Turno.setForeground(new Color(250, 248, 239));
		Turno.setHorizontalAlignment(SwingConstants.CENTER);
		Turno.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelturno.add(Turno);

		nroTurno = new JLabel(juego2048.obtenerTurno());
		nroTurno.setForeground(new Color(255, 255, 255));
		nroTurno.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelturno.add(nroTurno);

		// TITULO 2048 JLABEL
		JLabel titulo2048 = new JLabel("2048");
		titulo2048.setForeground(new Color(119, 110, 101));
		titulo2048.setFont(new Font("Tahoma", Font.BOLD, 56));
		titulo2048.setBounds(10, 0, 156, 96);
		panelSuperior.add(titulo2048);

		// PANEL MATRIZ-JUEGO
		JPanel panelMatriz = new JPanel();
		panelMatriz.setBorder(new LineBorder(new Color(187, 173, 160), 4));
		panelMatriz.setBackground(new Color(187, 173, 160));
		frame.getContentPane().add(panelMatriz, BorderLayout.CENTER);
		panelMatriz.setLayout(new GridLayout(4, 4));

		// GENERACION DE CELDAS
		tableroIG = new JLabel[4][4];

		for (int fila = 0; fila < 4; fila++) {
			for (int columna = 0; columna < 4; columna++) {
				tableroIG[fila][columna] = new JLabel(); // Asigno la etiqueta
				tableroIG[fila][columna].setHorizontalAlignment(SwingConstants.CENTER); // Centro el texto de forma
																						// horizontal
				tableroIG[fila][columna].setOpaque(true);
				tableroIG[fila][columna].setFont(new Font("Arial", Font.BOLD, 24)); // Asigno tamanio y fuente de letra.
				tableroIG[fila][columna].setBorder(new LineBorder(new Color(187, 173, 160), 4));
				tableroIG[fila][columna].setBackground(new Color(205, 193, 180));
				panelMatriz.add(tableroIG[fila][columna]); // Agrego cada etiqueta creada al panel.
			}
		}

		// Agregar valores a tablero.
		actualizarValores(juego2048.obtenerTablero());

		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!movimientoProcesado) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						if (juego2048.mover(1)) {
							juego2048.agregarNumero();
							actualizarValores(juego2048.obtenerTablero());
							verificarEstadoJuego();
						}
						break;
					case KeyEvent.VK_DOWN:
						if (juego2048.mover(2)) {
							juego2048.agregarNumero();
							actualizarValores(juego2048.obtenerTablero());
							verificarEstadoJuego();
						}
						break;
					case KeyEvent.VK_LEFT:
						if (juego2048.mover(3)) {
							juego2048.agregarNumero();
							actualizarValores(juego2048.obtenerTablero());
							verificarEstadoJuego();
						}
						break;
					case KeyEvent.VK_RIGHT:
						if (juego2048.mover(4)) {
							juego2048.agregarNumero();
							actualizarValores(juego2048.obtenerTablero());
							verificarEstadoJuego();
						}
						break;
					}
					movimientoProcesado = true;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				movimientoProcesado = false;
			}
		});

		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	consultarNombreParaRanking();
            }
        });
	}

	// ACTUALIZACION DE LOS VALORES DE LAS CELDAS
	private void actualizarValores(int[][] tablero) {
		// Recorro el tablero para obtener sus valores y agregarlos a la interfaz grafica
		for (int fila = 0; fila < 4; fila++) {
			for (int columna = 0; columna < 4; columna++) {
				if (tablero[fila][columna] == 0) {
					tableroIG[fila][columna].setText(""); // Dejo vacias las posiciones que no tienen valor.
				} else {
					tableroIG[fila][columna].setText("" + tablero[fila][columna]); // Le asigno el valor, convirtiendolo
																					// a string.
				}
				actualizarColorEtiqueta(tableroIG[fila][columna], tablero[fila][columna]);
			}
		}
		valorPuntaje.setText(juego2048.obtenerPuntosString());
		nroTurno.setText(juego2048.obtenerTurno());
	}

	private void actualizarColorEtiqueta(JLabel etiqueta, int valor) {
		if (valor == 0) {
			etiqueta.setBackground(Color.LIGHT_GRAY);
		} else {
			int indiceColor = (int) (Math.log(valor) / Math.log(2)) - 1;
			etiqueta.setBackground(COLORES_NUMEROS[indiceColor]);
		}
	}

	private void verificarEstadoJuego() {
		if (juego2048.verificarVictoria()) {
			mostrarMensajeJuegoTerminado("¡Ganaste! ¿Qué deseas hacer?", "¡Victoria!", JOptionPane.INFORMATION_MESSAGE);
		} else if (juego2048.verificarDerrota()) {
			mostrarMensajeJuegoTerminado("¡Perdiste! ¿Qué deseas hacer?", "¡Derrota!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void mostrarMensajeJuegoTerminado(String mensaje, String titulo, int tipoMensaje) {
		int choice = JOptionPane.showOptionDialog(frame, mensaje, titulo, JOptionPane.YES_NO_OPTION, tipoMensaje, null,
				new String[] { "Volver a jugar", "Salir" }, "Volver a jugar");
		if (choice == JOptionPane.YES_OPTION) {
			consultarNombreParaRanking();
			juego2048.reiniciarJuego();			
			valorPuntaje.setText("0");
			nroTurno.setText("0");
			actualizarValores(juego2048.obtenerTablero());
		} else {
			consultarNombreParaRanking();
			System.exit(0);
		}
	}

	private void mostrarRanking() {
		JOptionPane.showMessageDialog(frame, ranking.mostrarRanking(), "Ranking", JOptionPane.INFORMATION_MESSAGE);
	}


	private void consultarNombreParaRanking() {
		String nombreJugador = JOptionPane.showInputDialog(frame, "Ingresa tu nombre:");
		if(nombreJugador==null || nombreJugador.trim().isEmpty()) {
			System.exit(0);
		}
		ranking.agregarAlRanking(nombreJugador, juego2048.obtenerPuntosInt());
		ranking.guardarRanking();
	}

}
