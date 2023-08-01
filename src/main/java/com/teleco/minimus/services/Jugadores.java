package com.teleco.minimus.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.teleco.minimus.entities.Jugador;

public abstract class Jugadores {

    private static HashMap<Integer, Jugador> jugadores = new HashMap<Integer, Jugador>();
    
    public static boolean nuevoJugador(Jugador jugador, boolean override) {
        
        if( override && jugadores.containsKey(jugador.getId()) )
            eliminarJugador(jugador.getId());

        if ( !override && jugadores.containsKey(jugador.getId()) )
            return false;

        jugadores.put(jugador.getId(), jugador);
        return true;
    }

    public static boolean eliminarJugador(int id) {

        // Comprobamos si existe el jugador
        if ( !jugadores.containsKey(id) )
            return false;

        // Eliminamos cualquier pareja creada que tenga este jugador
        if( Parejas.hasJugadorWithId(id) != null )
            Parejas.eliminarPareja( Parejas.hasJugadorWithId(id) );

        // Eliminamos jugador
        jugadores.remove(id);
        return true;
    }

    public static Jugador getJugador(int id) {
        if ( jugadores.containsKey(id) )
            return jugadores.get(id);
        else
            return null;
    }

    public static void clear() {
        jugadores.clear();
    }

    public static String dump() {
        String result = "";
        Iterator<Map.Entry<Integer, Jugador>> iterator = jugadores.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Jugador> entry = iterator.next();
            int key = entry.getKey();
            Jugador value = entry.getValue();
            result += "J " + key + " " + value.getNombre();
            
            if( iterator.hasNext() )
                result += "\n";
        }
        return result;
    }

}
