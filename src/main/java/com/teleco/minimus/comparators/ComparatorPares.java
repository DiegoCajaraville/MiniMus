package com.teleco.minimus.comparators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.teleco.minimus.entities.Carta;

public class ComparatorPares implements Comparator<ArrayList<Carta>> {

    private static final List<Character> denominaciones = Arrays.asList('R', 'C', 'S', '7', '6', '5', '4', '1');  // 3 == R, 1==2

    @Override
    public int compare(ArrayList<Carta> c1, ArrayList<Carta> c2) {
        return compPares(c1, c2);
    }

    public static int compPares(ArrayList<Carta> c1, ArrayList<Carta> c2) {

        int cont1 = calcularPuntosPares(c1);
        int cont2 = calcularPuntosPares(c2);

        if(cont1 > cont2)
            return -1;
        else if(cont1 < cont2)
            return 1;
        else
            return 0;
    }

    public static int calcularPuntosPares(ArrayList<Carta> cartas) {

        HashMap<Character, Integer> recuento = new HashMap<Character, Integer>();
        
        // Inicializacion
        for(Character c : denominaciones) 
            recuento.put(c, 0);

        // Recuento
        for( Carta c : cartas) {
            if(Character.valueOf('3').equals(c.getDenominacion()))
                recuento.put('R', recuento.get('R') + 1);
            else if( Character.valueOf('2').equals(c.getDenominacion()) )
                recuento.put('1', recuento.get('1') + 1);
            else
                recuento.put(c.getDenominacion(), recuento.get(c.getDenominacion()) + 1);
        }

        // Eliminamos los campos vacios
        Iterator<Map.Entry<Character, Integer>> iterator = recuento.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            if (entry.getValue() == 0) {
                iterator.remove();
            }
        }

        // Calcular piedras
        for (Map.Entry<Character, Integer> entry : recuento.entrySet()) {
            if( entry.getValue() == 4 )
                return 3;
            else if( entry.getValue() == 3 )
                return 2;
            else if( entry.getValue() == 2 ) {
                if(recuento.size() == 2)
                    return 3;
                else
                    return 1;
            }
        }

        return 0;
    }
    
}
