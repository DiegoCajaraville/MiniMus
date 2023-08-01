package com.teleco.minimus.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            else tirada += ", " + cartasJ1PA.get(0);
        }
        tirada += ")" + ("J1PB".equals(mano) ? "*" : "-") + "(";
        for(int i=0; i<cartasJ1PB.size(); i++) {
            if(i==0) tirada += cartasJ1PB.get(0);
            else tirada += ", " + cartasJ1PB.get(0);
        }
        tirada += ")" + ("J2PA".equals(mano) ? "*" : "-") + "(";
        for(int i=0; i<cartasJ2PA.size(); i++) {
            if(i==0) tirada += cartasJ2PA.get(0);
            else tirada += ", " + cartasJ2PA.get(0);
        }
        tirada += ")" + ("J2PB".equals(mano) ? "*" : "-") + "(";
        for(int i=0; i<cartasJ2PB.size(); i++) {
            if(i==0) tirada += cartasJ2PB.get(0);
            else tirada += ", " + cartasJ2PB.get(0);
        }
        tirada += ")";
        
        return tirada;
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

    // TODO: adaptar el formato de salida
    // TODO: crear metodos public separados para obtener la tirada de esta jugada y para los resultados
    @Override
    public String toString() {
        return getTiradaCartas();
    }
}
