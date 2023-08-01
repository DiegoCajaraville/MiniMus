package com.teleco.minimus.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.teleco.minimus.services.Parejas;

public class Partida {

    //private HashMap<Integer, Pareja> parejas = new HashMap<Integer, Pareja>();
    private Pareja parejaA = null;
    private Pareja parejaB = null;
    private ArrayList<Jugada> jugadas = new ArrayList<Jugada>();

    public Partida() {
    }

    public void setParejasPartida() {
        if ( Parejas.getParejasPartida() != null ) {
            ArrayList<Pareja> parejas = Parejas.getParejasPartida();
            parejaA = parejas.get(0);
            parejaB = parejas.get(1);
        }
        else {
            Jugador J1PA = new Jugador(1, "Jugador 1A");
            Jugador J2PA = new Jugador(2, "Jugador 2A");
            parejaA = new Pareja(1, "Pareja A", J1PA, J2PA);

            Jugador J1PB = new Jugador(3, "Jugador 1B");
            Jugador J2PB = new Jugador(4, "Jugador 2B");
            parejaB = new Pareja(2, "Pareja B", J1PB, J2PB);
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

        // Parejas
        result += parejaA + ".\n";
        result += parejaB + ".\n";

        // Mano (quien tiene la mano en la primera jugada)
        if( jugadas.size() != 0 && jugadas.get(0).getMano().equals("J1PA") )
            result += "Mano: " + parejaA.getJugador1().getNombre() + ".\n";
        else if( jugadas.size() != 0 && jugadas.get(0).getMano().equals("J1PB") )
            result += "Mano: " + parejaB.getJugador1().getNombre() + ".\n";
        else if( jugadas.size() != 0 && jugadas.get(0).getMano().equals("J2PA") )
            result += "Mano: " + parejaA.getJugador2().getNombre() + ".\n";
        else if( jugadas.size() != 0 && jugadas.get(0).getMano().equals("J2PB") )
            result += "Mano: " + parejaB.getJugador2().getNombre() + ".\n";

        // Jugadas
        for( Jugada jugada : jugadas ) 
            result += jugada + "\n";

        return result;
    }
}
