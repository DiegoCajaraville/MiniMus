package com.teleco.minimus.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Pareja {

    private int id;
    private String nombre;
    private HashMap<Integer, Jugador> jugadores;

    /**
     * @param nombre
     * @param jugadores
     */
    public Pareja(int id, String nombre, HashMap<Integer, Jugador> jugadores) {
        this.id = id;
        this.nombre = nombre;
        this.jugadores = jugadores;
    }


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * @return the jugadores
     */
    public HashMap<Integer, Jugador> getJugadores() {
        return jugadores;
    }


    /**
     * @param jugadores the jugadores to set
     */
    public void setJugadores(HashMap<Integer, Jugador> jugadores) {
        this.jugadores = jugadores;
    }


    @Override
    public String toString() {

        Iterator<Map.Entry<Integer, Jugador>> iterator = jugadores.entrySet().iterator();
        return nombre + ": " + iterator.next().getValue() + " y " + iterator.next().getValue();
    }

}
