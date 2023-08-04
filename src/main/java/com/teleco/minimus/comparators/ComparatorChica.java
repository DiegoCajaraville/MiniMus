package com.teleco.minimus.comparators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.teleco.minimus.entities.Carta;

public class ComparatorChica implements Comparator<ArrayList<Carta>> {

    private static final List<Character> tamanos = Arrays.asList('1', '4', '5', '6', '7', 'S', 'C', 'R');  // 3 == R, 1==2

    @Override
    public int compare(ArrayList<Carta> c1, ArrayList<Carta> c2) {
        return compChica(c1, c2);
    }

    public static int compChica(ArrayList<Carta> c1, ArrayList<Carta> c2) {

        for(Character tamano : tamanos) {

            int cont1 = calcularPuntosChica(c1, tamano);
            int cont2 = calcularPuntosChica(c2, tamano);

            if(cont1 > cont2)
                return -1;
            else if(cont1 < cont2)
                return 1;
            else
                continue;
        }

        return 0;
    }

    private static int calcularPuntosChica(ArrayList<Carta> cartas, Character denominacion) {

        int contador = 0;

        for(Carta c : cartas) {
            if(denominacion.equals(c.getDenominacion()))
                contador++;
            if(denominacion.equals('R') && c.getDenominacion() =='3' )
                contador++;
            if(denominacion.equals('1') && c.getDenominacion() =='2' )
                contador++;
        }

        return contador;
    }
    
}
