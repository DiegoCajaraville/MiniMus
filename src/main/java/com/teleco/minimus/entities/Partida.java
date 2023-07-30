package com.teleco.minimus.entities;

import java.util.ArrayList;
import java.util.HashMap;

import com.teleco.minimus.services.Parejas;

public class Partida {

    private HashMap<Integer, Pareja> parejas;
    private ArrayList<Jugada> jugadas;

    public Partida() {

        if ( Parejas.getParejas() != null ) {
            parejas = Parejas.getParejas();
        }
        else {
            HashMap<Integer, Jugador> jugadores = new HashMap<Integer, Jugador>();
            jugadores.put(1, new Jugador(1, "Jugador 1A"));
            jugadores.put(2, new Jugador(2, "Jugador 2A"));

            jugadores.clear();
            jugadores.put(3, new Jugador(3, "Jugador 1B"));
            jugadores.put(4, new Jugador(4, "Jugador 2B"));

            parejas.put(1, new Pareja(2, "Pareja B", jugadores));
        }

    }


    @Override
    public String toString() {
        return "";
    }
}
