package com.teleco.minimus.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.teleco.minimus.entities.Jugador;
import com.teleco.minimus.entities.Pareja;

public abstract class Parejas {

    private static HashMap<Integer, Pareja> parejas = new HashMap<Integer, Pareja>();
    //TODO: revisar
    private static ArrayList<Integer> ordenParejas = new ArrayList<Integer>();

    public static boolean nuevaPareja(Pareja pareja, Jugador jugador1, Jugador jugador2) {
        
        // Comprobar que no haya otra pareja con el mismo ID
        if ( parejas.containsKey(pareja.getId()) )
            return false;

        // Comprobar que no haya ningun jugador de otra pareja ya existente
        Iterator<Map.Entry<Integer, Pareja>> iterator = parejas.entrySet().iterator();

        while (iterator.hasNext()) {
            HashMap<Integer, Jugador> jugadores = iterator.next().getValue().getJugadores();

            if( jugadores.containsKey(jugador1.getId()) || jugadores.containsKey(jugador2.getId()) )
                return false;
        }

        parejas.put(pareja.getId(), pareja);
        ordenParejas.add(pareja.getId());
        return true;
    }

    // TODO: si hay más de 2 parejas validas
    public static HashMap<Integer, Pareja> getParejas() {
        
        HashMap<Integer, Pareja> setParejas = new HashMap<Integer, Pareja>();

        for (Integer id : parejas.keySet()) {
            setParejas.put(id, parejas.get(id));
            if ( setParejas.size() == 2 )
                return setParejas;
        }

        return null;
    }

    public static String display() {
        String result = "";
        Iterator<Map.Entry<Integer, Pareja>> it1 = parejas.entrySet().iterator();

        while (it1.hasNext()) {
            Map.Entry<Integer, Pareja> entry = it1.next();
            int key = entry.getKey();
            Pareja value = entry.getValue();
            result += "Pareja: " + key + ", Nombre: " + value.getNombre();
            
            HashMap<Integer, Jugador> jugadores = value.getJugadores();
            Iterator<Map.Entry<Integer, Jugador>> it2 = jugadores.entrySet().iterator();

            while (it2.hasNext()) 
                result += ", Jugador: " + it2.next().getValue().getNombre(); 
            
            result += "\n";
        }

        return result;
    }
    
}
