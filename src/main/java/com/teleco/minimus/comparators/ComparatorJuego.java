package com.teleco.minimus.comparators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.teleco.minimus.entities.Carta;

public class ComparatorJuego implements Comparator<ArrayList<Carta>> {

    private static final List<Integer> juegos = Arrays.asList(31, 32, 40, 37, 36, 35, 34, 33);  

    @Override
    public int compare(ArrayList<Carta> c1, ArrayList<Carta> c2) {
        return compJuego(c1, c2);
    }

    public static int compJuego(ArrayList<Carta> c1, ArrayList<Carta> c2) {

        int cont1 = calcularPuntosJuego(c1);
        int cont2 = calcularPuntosJuego(c2);

        if( cont1 == cont2)
            return 0;
        else if(hasJuego(cont1) && hasJuego(cont2))
            // Los dos tienen juego
            if( juegos.indexOf(cont1) > juegos.indexOf(cont2) )
                return 1;
            else
                return -1;
        else
            if(cont1 < cont2)
                return 1;
            else 
                return -1;
    }

    public static int calcularPuntosJuego(ArrayList<Carta> cartas) {

        int contador = 0;

        for(Carta c : cartas)
            contador += c.getPuntos();

        return contador;
    }

    public static boolean hasJuego(ArrayList<Carta> cartas) {
        return hasJuego(calcularPuntosJuego(cartas));
    }

    public static boolean hasJuego(Integer puntos) {
        if( puntos >= 31 )
            return true;
        else
            return false;
    }
    
}
