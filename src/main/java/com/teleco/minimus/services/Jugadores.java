package com.teleco.minimus.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import com.teleco.minimus.entities.Jugador;

public abstract class Jugadores {

    private static HashMap<Integer, Jugador> jugadores = new HashMap<Integer, Jugador>();
    
    public static boolean nuevoJugador(Jugador jugador) {
        
        if ( jugadores.containsKey(jugador.getId()) )
            return false;

        jugadores.put(jugador.getId(), jugador);
        return true;
    }

    public static Jugador getJugador(int id) {
        if ( jugadores.containsKey(id) )
            return jugadores.get(id);
        else
            return null;
    }

    public static String display() {
        String result = "";
        Iterator<Map.Entry<Integer, Jugador>> iterator = jugadores.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Jugador> entry = iterator.next();
            int key = entry.getKey();
            Jugador value = entry.getValue();
            result += "Jugador: " + key + ", Nombre: " + value.getNombre() + "\n";
        }
        return result;
    }

}
