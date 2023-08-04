package com.teleco.minimus.comparators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.teleco.minimus.entities.Carta;

public class ComparatorGrande implements Comparator<ArrayList<Carta>> {

    private static final List<Character> tamanos = Arrays.asList('R', 'C', 'S', '7', '6', '5', '4', '1');  // 3 == R, 1==2

    @Override
    public int compare(ArrayList<Carta> c1, ArrayList<Carta> c2) {
        return compGrande(c1, c2);
    }

    public static int compGrande(ArrayList<Carta> c1, ArrayList<Carta> c2) {

        for(Character tamano : tamanos) {

            int cont1 = calcularPuntosGrande(c1, tamano);
            int cont2 = calcularPuntosGrande(c2, tamano);

            if(cont1 > cont2)
                return -1;
            else if(cont1 < cont2)
                return 1;
            else
                continue;
        }

        return 0;
    }

    private static int calcularPuntosGrande(ArrayList<Carta> cartas, Character denomincacion) {

        int contador = 0;

        for(Carta c : cartas) {
            if(denomincacion.equals(c.getDenominacion()))
                contador++;
            if(denomincacion.equals('R') && c.getDenominacion() =='3' )
                contador++;
            if(denomincacion.equals('1') && c.getDenominacion() =='2' )
                contador++;
        }

        return contador;
    }
    
}
