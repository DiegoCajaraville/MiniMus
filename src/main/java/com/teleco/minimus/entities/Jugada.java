package com.teleco.minimus.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.teleco.minimus.comparators.ComparatorChica;
import com.teleco.minimus.comparators.ComparatorGrande;
import com.teleco.minimus.comparators.ComparatorJuego;
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

    public Puntuaciones resolverPares() {

        Puntuaciones puntos = new Puntuaciones();

        addPiedras(puntos, 0, ComparatorPares.calcularPuntosPares(cartasJ1PA));
        addPiedras(puntos, 1, ComparatorPares.calcularPuntosPares(cartasJ1PB));
        addPiedras(puntos, 2, ComparatorPares.calcularPuntosPares(cartasJ2PA));
        addPiedras(puntos, 3, ComparatorPares.calcularPuntosPares(cartasJ2PB));  

        return puntos;
    }

    public Puntuaciones resolverJuego() {
        
        ArrayList<ArrayList<Carta>> original = new ArrayList<ArrayList<Carta>>();
        original.add(cartasJ1PA);
        original.add(cartasJ1PB);
        original.add(cartasJ2PA);
        original.add(cartasJ2PB);
        ArrayList<ArrayList<Carta>> ordenado = new ArrayList<ArrayList<Carta>>(original);

        Collections.sort(ordenado, new ComparatorJuego());
        Puntuaciones puntos = new Puntuaciones();

        if( (original.indexOf(ordenado.get(0)) % 2) != (original.indexOf(ordenado.get(1)) % 2)
                && (ComparatorJuego.compJuego(ordenado.get(0), ordenado.get(1)) == 0)  ) {

            // Los jugadores en primera y segunda posición son de distintas parejas y tienen los mismos puntos
            // Observamos los otros dos: al menos el mejor debe tener juego
            if( ComparatorJuego.compJuego(ordenado.get(2), ordenado.get(3)) != 0 
                    && ComparatorJuego.hasJuego(ordenado.get(2)))
                addPiedras(puntos, original.indexOf(ordenado.get(0)), 2);
            else 
                addPiedras(puntos, manos.indexOf(mano), 2);
        }
        else if(!ComparatorJuego.hasJuego(ordenado.get(0)) && !(ComparatorJuego.compJuego(ordenado.get(0), ordenado.get(1)) == 0)) {
            // No hay empate y ningun jugador tiene juego
            addPiedras(puntos, original.indexOf(ordenado.get(0)), 1);
        }
        else {
            // En otro caso, el jugador ganador recibe 2 piedras
            addPiedras(puntos, original.indexOf(ordenado.get(0)), 2);
        }   

        // Asignacion por juego personal
        for( ArrayList<Carta> c : original) {
            if( ComparatorJuego.calcularPuntosJuego(c) == 31 )
                addPiedras(puntos, original.indexOf(c), 3);
            else if( ComparatorJuego.hasJuego(c) )
                addPiedras(puntos, original.indexOf(c), 2);
        }

        return puntos;
    }

    public Puntuaciones resolverGrande() {

        ArrayList<ArrayList<Carta>> original = new ArrayList<ArrayList<Carta>>();
        original.add(cartasJ1PA);
        original.add(cartasJ1PB);
        original.add(cartasJ2PA);
        original.add(cartasJ2PB);
        ArrayList<ArrayList<Carta>> ordenado = new ArrayList<ArrayList<Carta>>(original);

        Collections.sort(ordenado, new ComparatorGrande());
        Puntuaciones puntos = new Puntuaciones();

        if( (original.indexOf(ordenado.get(0)) % 2) != (original.indexOf(ordenado.get(1)) % 2)
                && (ComparatorGrande.compGrande(ordenado.get(0), ordenado.get(1)) == 0)  ) {
            // Los jugadores en primera y segunda posición son de distintas parejas y tienen los mismos puntos
            addPiedras(puntos, original.indexOf(ordenado.get(0)), 1);
            addPiedras(puntos, original.indexOf(ordenado.get(1)), 1);
        }
        else {
            // En otro caso, el jugador ganador recibe 3 piedras
            addPiedras(puntos, original.indexOf(ordenado.get(0)), 3);
        }   

        return puntos;
    }

    public Puntuaciones resolverChica() {

        ArrayList<ArrayList<Carta>> original = new ArrayList<ArrayList<Carta>>();
        original.add(cartasJ1PA);
        original.add(cartasJ1PB);
        original.add(cartasJ2PA);
        original.add(cartasJ2PB);
        ArrayList<ArrayList<Carta>> ordenado = new ArrayList<ArrayList<Carta>>(original);

        Collections.sort(ordenado, new ComparatorChica());
        Puntuaciones puntos = new Puntuaciones();

        if( (original.indexOf(ordenado.get(0)) % 2) != (original.indexOf(ordenado.get(1)) % 2)
                && (ComparatorChica.compChica(ordenado.get(0), ordenado.get(1)) == 0)  ) {
            // Los jugadores en primera y segunda posición son de distintas parejas y tienen los mismos puntos
            addPiedras(puntos, original.indexOf(ordenado.get(0)), 1);
            addPiedras(puntos, original.indexOf(ordenado.get(1)), 1);
        }
        else {
            // En otro caso, el jugador ganador recibe 3 piedras
            addPiedras(puntos, original.indexOf(ordenado.get(0)), 3);
        }   
        
        return puntos;
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

    public void addPiedras(Puntuaciones puntos, int index, int piedras) {

        switch(index) {
            case 0:
                puntos.setPuntosJ1PA(puntos.getPuntosJ1PA() + piedras);
                break;
            case 1:
                puntos.setPuntosJ1PB(puntos.getPuntosJ1PB() + piedras);
                break;
            case 2:
                puntos.setPuntosJ2PA(puntos.getPuntosJ2PA() + piedras);
                break;
            case 3:
                puntos.setPuntosJ2PB(puntos.getPuntosJ2PB() + piedras);
                break;
        }
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

    public static Jugada generarJugadaAleatoria(int idMano) {
        Jugada jugada = new Jugada();
        jugada.setMano(Jugada.getManoByIndex((idMano-1) % 4));
        
        Baraja baraja = new Baraja();
        for(int j=0; j<4; j++)
            jugada.setNuevasCartas(baraja.getCartas(4));

        return jugada;
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
            + getResultadoJugada(false, false); 
    }
}
