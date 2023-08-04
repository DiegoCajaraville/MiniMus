package com.teleco.minimus.entities;

public class Carta implements Comparable<Carta> {

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

    /**
     * @return the denominacion
     */
    public char getDenominacion() {
        return denominacion;
    }

    /**
     * @param denominacion the denominacion to set
     */
    public void setDenominacion(char denominacion) {
        this.denominacion = denominacion;
    }

    /**
     * @return the palo
     */
    public char getPalo() {
        return palo;
    }

    /**
     * @param palo the palo to set
     */
    public void setPalo(char palo) {
        this.palo = palo;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public int compareTo (Carta c){
        if(this.palo < c.palo) return -1;
        else if(this.palo > c.palo) return 1;
        else{            
            if(Baraja.denominaciones.indexOf(this.denominacion) < Baraja.denominaciones.indexOf(c.denominacion)) return -1;
            else if(Baraja.denominaciones.indexOf(this.denominacion) > Baraja.denominaciones.indexOf(c.denominacion)) return 1;
            else return 0;
        }
    }


    @Override
    public String toString() {
        return getRepresentacion();
    }
}
