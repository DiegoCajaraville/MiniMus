package com.teleco.minimus.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.teleco.minimus.services.Parejas;

public class Partida {

    // Definir un arraylist y que la clase Pareja tenga un arraylist de jugadores
    private HashMap<Integer, Pareja> parejas = new HashMap<Integer, Pareja>();
    private ArrayList<Jugada> jugadas = new ArrayList<Jugada>();

    public Partida() {

        if ( Parejas.getParejas() != null ) {
            parejas = Parejas.getParejas();
        }
        else {
            HashMap<Integer, Jugador> jugadoresA = new HashMap<Integer, Jugador>();
            jugadoresA.put(1, new Jugador(1, "Jugador 1A"));
            jugadoresA.put(2, new Jugador(2, "Jugador 2A"));
            parejas.put(1, new Pareja(1, "Pareja A", jugadoresA));

            HashMap<Integer, Jugador> jugadoresB = new HashMap<Integer, Jugador>();
            jugadoresB.put(1, new Jugador(3, "Jugador 1B"));
            jugadoresB.put(2, new Jugador(4, "Jugador 2B"));
            parejas.put(2, new Pareja(2, "Pareja B", jugadoresB));
        }

    }


    /**
     * @return the jugadas
     */
    public ArrayList<Jugada> getJugadas() {
        return jugadas;
    }

    /**
     * @param jugadas the jugadas to set
     */
    public void setJugadas(ArrayList<Jugada> jugadas) {
        this.jugadas = jugadas;
    }




    @Override
    public String toString() {

        String result = "";
        Iterator<Map.Entry<Integer, Pareja>> iterator = parejas.entrySet().iterator();

        // Parejas
        while (iterator.hasNext()) 
            result += iterator.next().getValue() + ".\n";

        // Mano
        // TODO: problema para obtener quien es la mano
        result += "Mano: " + jugadas.get(0) + ".\n";

        for( Jugada jugada : jugadas ) 
            result += jugada + "\n";

        return result;
    }
}
