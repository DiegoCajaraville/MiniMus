package com.teleco.minimus.entities;

public class Carta {

    private char denominacion;      // 1, 2, 3, 4, 5, 6, 7, S, C, R
    private char palo;              // B, E, C, O
    private int puntos;             // 1, 4, 4, 6, 7, 10

    public Carta (String representacion) {
        
        if( Baraja.denominaciones.contains(representacion.charAt(0)) && 
                Baraja.palos.contains(representacion.charAt(1)) ) {
            this.denominacion = representacion.charAt(0);
            this.palo = representacion.charAt(1);
            this.puntos = calculaPuntos();
        }
        else
            return;
    }

    public Carta (char denominacion, char palo) {

        if( Baraja.denominaciones.contains(denominacion) && Baraja.palos.contains(palo) ) {
            this.denominacion = denominacion;
            this.palo = palo;
            this.puntos = calculaPuntos();
        }
        else
            return;
    }
    
    private int calculaPuntos() {

        if(denominacion == 'R' || denominacion == '3' || denominacion == 'C' || denominacion == 'S')
            return 10;
        else if(denominacion == '1' || denominacion == '2')
            return 1;
        else
            return (denominacion - 48);     // En codigo ASCII el '1' vale 49, etc
    }

    public String getRepresentacion() {
        char[] representacion = {denominacion, palo};
        return String.valueOf(representacion);
    }



    @Override
    public String toString() {
        return getRepresentacion();
    }
}
