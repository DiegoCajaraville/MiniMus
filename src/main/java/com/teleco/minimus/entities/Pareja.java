package com.teleco.minimus.entities;

public class Pareja {

    private int id;
    private String nombre;
    private Jugador jugador1;
    private Jugador jugador2;

    /**
     * @param nombre
     * @param jugadores
     */
    public Pareja(int id, String nombre, Jugador jugador1, Jugador jugador2) {
        this.id = id;
        this.nombre = nombre;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public boolean hasJugadorId(Integer id) {

        if( id.equals(jugador1.getId()) || id.equals(jugador2.getId()) )
            return true;
        else
            return false;
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
     * @return the jugador1
     */
    public Jugador getJugador1() {
        return jugador1;
    }


    /**
     * @param jugador1 the jugador1 to set
     */
    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    /**
     * @return the jugador2
     */
    public Jugador getJugador2() {
        return jugador2;
    }


    /**
     * @param jugador2 the jugador2 to set
     */
    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }


    @Override
    public String toString() {
        return nombre + ": " + jugador1 + " y " + jugador2;
    }

}
