package logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Ranking {
	
	//AGREGAR MENSAJE EN CASO DE QUE RANKING ESTE VACIO Y SE INTENTE ABRIR, REVISAR EXCEPTION
    private List<Map.Entry<String, Integer>> ranking;

    public Ranking() {
        this.ranking = new ArrayList<>();
        cargarRanking();
    }
    
    public List<Map.Entry<String, Integer>> verRanking() {
    	return ranking;
    }

    @SuppressWarnings("unchecked")
	public void cargarRanking() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("ranking.txt"));
            ranking = (List<Map.Entry<String, Integer>>) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guardarRanking() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ranking.txt"));
            outputStream.writeObject(ranking);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void agregarAlRanking(String nombre, int puntaje) {
        boolean existeJugador = false;
        for (Map.Entry<String, Integer> jugador : ranking) {
            if (jugador.getKey().equals(nombre)) {
                existeJugador = true;
                if (puntaje > jugador.getValue()) {
                    jugador.setValue(puntaje);
                }
                break;
            }
        }

        if (!existeJugador) {
            Map.Entry<String, Integer> nuevoJugador = new AbstractMap.SimpleEntry<>(nombre, puntaje);
            ranking.add(nuevoJugador);
        }
        // Ordenamos el ranking por puntaje en orden descendente
        ranking.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
        
        // Mantener solo los primeros 5 jugadores en el ranking
        if (ranking.size() > 5) {
            ranking = new ArrayList<>(ranking.subList(0, 5));
        }
        // Guardar el ranking actualizado
        guardarRanking();
    }
    
    public String mostrarRanking() {
		StringBuilder rankingString = new StringBuilder();
		int position = 1;
		for (Map.Entry<String, Integer> entry : ranking) {
			rankingString.append(position).append(". ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
			position++;
		}
		return rankingString.toString();
    }
}
