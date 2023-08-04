package com.teleco.minimus.dto;

public class Puntuaciones {

    private int puntosJ1PA;
    private int puntosJ1PB;
    private int puntosJ2PA;
    private int puntosJ2PB;

    public Puntuaciones(){
    }
    
    /**
     * @param puntosJ1PA
     * @param puntosJ1PB
     * @param puntosJ2PA
     * @param puntosJ2PB
     */
    public Puntuaciones(int puntosJ1PA, int puntosJ1PB, int puntosJ2PA, int puntosJ2PB) {
        this.puntosJ1PA = puntosJ1PA;
        this.puntosJ1PB = puntosJ1PB;
        this.puntosJ2PA = puntosJ2PA;
        this.puntosJ2PB = puntosJ2PB;
    }

    public int getPuntosPA() {
        return puntosJ1PA + puntosJ2PA;
    }

    public int getPuntosPB() {
        return puntosJ1PB + puntosJ2PB;
    }

    /**
     * @return the puntosJ1PA
     */
    public int getPuntosJ1PA() {
        return puntosJ1PA;
    }

    /**
     * @param puntosJ1PA the puntosJ1PA to set
     */
    public void setPuntosJ1PA(int puntosJ1PA) {
        this.puntosJ1PA = puntosJ1PA;
    }

    /**
     * @return the puntosJ1PB
     */
    public int getPuntosJ1PB() {
        return puntosJ1PB;
    }

    /**
     * @param puntosJ1PB the puntosJ1PB to set
     */
    public void setPuntosJ1PB(int puntosJ1PB) {
        this.puntosJ1PB = puntosJ1PB;
    }

    /**
     * @return the puntosJ2PA
     */
    public int getPuntosJ2PA() {
        return puntosJ2PA;
    }

    /**
     * @param puntosJ2PA the puntosJ2PA to set
     */
    public void setPuntosJ2PA(int puntosJ2PA) {
        this.puntosJ2PA = puntosJ2PA;
    }

    /**
     * @return the puntosJ2PB
     */
    public int getPuntosJ2PB() {
        return puntosJ2PB;
    }

    /**
     * @param puntosJ2PB the puntosJ2PB to set
     */
    public void setPuntosJ2PB(int puntosJ2PB) {
        this.puntosJ2PB = puntosJ2PB;
    }

    @Override
    public String toString() {
        return "" + puntosJ1PA + " " + puntosJ1PB + " " + puntosJ2PA + " " + puntosJ2PB
                + " - " + getPuntosPA() + " " + getPuntosPB();
    }
}
