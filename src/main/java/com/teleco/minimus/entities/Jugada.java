package com.teleco.minimus.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.teleco.minimus.comparators.ComparatorPares;
import com.teleco.minimus.dto.Puntuaciones;

public class Jugada {

    private ArrayList<Carta> cartasJ1PA;
    private ArrayList<Carta> cartasJ1PB;
    private ArrayList<Carta> cartasJ2PA;
    private ArrayList<Carta> cartasJ2PB;
    private String mano;

    private static final List<String> manos = Arrays.asList("J1PA", "J1PB", "J2PA", "J2PB");

    public void setNuevasCartas(ArrayList<Carta> cartas) {
        if(cartasJ1PA == null)
            cartasJ1PA = cartas;
        else if (cartasJ1PB == null)
            cartasJ1PB = cartas;
        else if (cartasJ2PA == null)
            cartasJ2PA = cartas;
        else if (cartasJ2PB == null)
            cartasJ2PB = cartas;
        return;
    }

    public void setSiguienteMano(String mano) {

        int indiceActual = manos.indexOf(mano);
        if (indiceActual != -1) {
            int indiceSiguiente = (indiceActual + 1) % manos.size();
            setMano(manos.get(indiceSiguiente));
        }
    }

    public static String getManoByIndex(int mano) {
        return manos.get(mano);
    }

    public String getTiradaCartas() {

        String tirada = "";

        tirada += ("J1PA".equals(mano) ? "*" : "-") + "(";
        for(int i=0; i<cartasJ1PA.size(); i++) {
            if(i==0) tirada += cartasJ1PA.get(0);
            else tirada += ", " + cartasJ1PA.get(i);
        }
        tirada += ")" + ("J1PB".equals(mano) ? "*" : "-") + "(";
        for(int i=0; i<cartasJ1PB.size(); i++) {
            if(i==0) tirada += cartasJ1PB.get(0);
            else tirada += ", " + cartasJ1PB.get(i);
        }
        tirada += ")" + ("J2PA".equals(mano) ? "*" : "-") + "(";
        for(int i=0; i<cartasJ2PA.size(); i++) {
            if(i==0) tirada += cartasJ2PA.get(0);
            else tirada += ", " + cartasJ2PA.get(i);
        }
        tirada += ")" + ("J2PB".equals(mano) ? "*" : "-") + "(";
        for(int i=0; i<cartasJ2PB.size(); i++) {
            if(i==0) tirada += cartasJ2PB.get(0);
            else tirada += ", " + cartasJ2PB.get(i);
        }
        tirada += ")";
        
        return tirada;
    }

    public Puntuaciones resolverPares() {

        ArrayList<ArrayList<Carta>> original = new ArrayList<ArrayList<Carta>>();
        original.add(cartasJ1PA);
        original.add(cartasJ1PB);
        original.add(cartasJ1PB);
        original.add(cartasJ2PB);
        ArrayList<ArrayList<Carta>> ordenado = new ArrayList<ArrayList<Carta>>(original);

        Collections.sort(ordenado, new ComparatorPares());
        return null;
    }

    public Puntuaciones resolverJuego() {
        return null;
    }

    public Puntuaciones resolverGrande() {
        return null;
    }

    public Puntuaciones resolverChica() {
        return null;
    }

    public Puntuaciones resolverJugada() {
        Puntuaciones pares = resolverPares();
        Puntuaciones juego = resolverJuego();
        Puntuaciones grande = resolverGrande();
        Puntuaciones chica = resolverChica();

        return new Puntuaciones(
            pares.getPuntosJ1PA() + juego.getPuntosJ1PA() + grande.getPuntosJ1PA() + chica.getPuntosJ1PA(), 
            pares.getPuntosJ1PB() + juego.getPuntosJ1PB() + grande.getPuntosJ1PB() + chica.getPuntosJ1PB(), 
            pares.getPuntosJ2PA() + juego.getPuntosJ2PA() + grande.getPuntosJ2PA() + chica.getPuntosJ2PA(), 
            pares.getPuntosJ2PB() + juego.getPuntosJ2PB() + grande.getPuntosJ2PB() + chica.getPuntosJ2PB() );
    }

    public String getResultadoJugada(boolean withSum, boolean withComma) {

        Puntuaciones pares = resolverPares();
        Puntuaciones juego = resolverJuego();
        Puntuaciones grande = resolverGrande();
        Puntuaciones chica = resolverChica();
        Puntuaciones jugada = resolverJugada();

        String result = null;

        if(withComma)
            result = "Grande " + grande.getPuntosPA() + " " + grande.getPuntosPB() + ", "
                + "Chica " + chica.getPuntosPA() + " " + chica.getPuntosPB() + ", "
                + "Pares " + pares.getPuntosPA() + " " + pares.getPuntosPB() + ", "
                + "Juego " + juego.getPuntosPA() + " " + juego.getPuntosPB();
        else
            result = "Grande " + grande.getPuntosPA() + " " + grande.getPuntosPB() + " "
                + "Chica " + chica.getPuntosPA() + " " + chica.getPuntosPB() + " "
                + "Pares " + pares.getPuntosPA() + " " + pares.getPuntosPB() + " "
                + "Juego " + juego.getPuntosPA() + " " + juego.getPuntosPB();
        
        if(withSum)
            result += " - " + jugada.getPuntosPA() + " " + jugada.getPuntosPB();

        return result;
    }

    /**
     * @return the mano
     */
    public String getMano() {
        return mano;
    }

    /**
     * @param mano the mano to set
     */
    public void setMano(String mano) {
        this.mano = mano;
    }

    @Override
    public String toString() {

        return getTiradaCartas() + "\n" 
            + getResultadoJugada(true, false); 
    }
}
