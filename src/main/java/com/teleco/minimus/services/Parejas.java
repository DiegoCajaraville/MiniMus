package com.teleco.minimus.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.teleco.minimus.entities.Pareja;

public abstract class Parejas {

    private static HashMap<Integer, Pareja> parejas = new HashMap<Integer, Pareja>();
    private static ArrayList<Integer> ordenParejas = new ArrayList<Integer>();

    public static boolean nuevaPareja(Pareja pareja, boolean override) {

        if( override && parejas.containsKey(pareja.getId()) )
            eliminarPareja(pareja.getId());
        
        // Comprobar que no haya otra pareja con el mismo ID
        if ( !override && parejas.containsKey(pareja.getId()) )
            return false;
        
        // Comprobar que no haya ningun jugador de otra pareja ya existente
        Iterator<Map.Entry<Integer, Pareja>> iterator = parejas.entrySet().iterator();

        while (iterator.hasNext()) {
            Pareja temp_pareja = iterator.next().getValue();

            if( temp_pareja.hasJugadorId(pareja.getJugador1().getId()) || temp_pareja.hasJugadorId(pareja.getJugador2().getId()) )
                return false;
        }

        parejas.put(pareja.getId(), pareja);
        ordenParejas.add(pareja.getId());
        return true;
    }

    public static boolean eliminarPareja(int id) {

        // Comprobar que exista la pareja
        if ( !parejas.containsKey(id) )
            return false;
        
        parejas.remove(id);
        return false;
    }

    /*
     * Comprueba si hay alguna pareja que contenga este usuario
     */
    public static Integer hasJugadorWithId(int id) {

        Iterator<Map.Entry<Integer, Pareja>> iterator = parejas.entrySet().iterator();

        while (iterator.hasNext()) {
            Pareja temp_pareja = iterator.next().getValue();

            if( temp_pareja.hasJugadorId(id) )
                return temp_pareja.getId();
        }

        return null;
    }

    public static ArrayList<Pareja> getParejasPartida() {
        
        ArrayList<Pareja> parejasPartida = new ArrayList<Pareja>();

        for (Integer id : ordenParejas) {
            if( parejas.containsKey(id) )
                parejasPartida.add(parejas.get(id));

            if ( parejasPartida.size() == 2 )
                return parejasPartida;
        }
        return null;
    }

    public static void clear() {
        parejas.clear();
        ordenParejas.clear();
    }

    public static String dump() {
        String result = "";
        Iterator<Map.Entry<Integer, Pareja>> iterator = parejas.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, Pareja> entry = iterator.next();
            int key = entry.getKey();
            Pareja value = entry.getValue();
            result += "P " + key + " " + value.getJugador1().getId()
                    + value.getJugador2().getId() + value.getNombre();
            
            if( iterator.hasNext() )
                result += "\n";
        }

        return result;
    }
    
}
