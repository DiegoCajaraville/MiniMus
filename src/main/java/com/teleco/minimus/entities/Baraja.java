package com.teleco.minimus.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Baraja {

    private ArrayList<Carta> cartas;
    public static final List<Character> denominaciones = Arrays.asList('1', '2', '3', '4', '5', '6', '7', 'S', 'C', 'R');
    public static final List<Character> palos = Arrays.asList('B', 'C', 'E', 'O');

    public Baraja() {
        cartas = new ArrayList<Carta>();
        inicializaBaraja();
    }

    private void inicializaBaraja(){
        
        for(Character palo : palos) {
            for(Character denominacion : denominaciones)
                cartas.add(new Carta(denominacion, palo));
        }
        return;
    }

    public ArrayList<Carta> getCartas(int numCartas) {

        desordenaBaraja();

        ArrayList<Carta> setCartas = new ArrayList<Carta>();
        ArrayList<Carta> copia = new ArrayList<Carta>(cartas);

        for(Carta carta : copia) {
            setCartas.add(carta);
            cartas.remove(cartas.indexOf(carta));
            if(setCartas.size() == numCartas)
                return setCartas;
        }

        return null;
    }
    
    public void desordenaBaraja(){
        Collections.shuffle(cartas);
        return;
    }

    // TODO
    public void ordenaBaraja(){
        //Collections.sort(cartas);
        return;
    }

    public void restableceBaraja(){
        cartas.removeAll(cartas);
        inicializaBaraja();
        return;
    }

    @Override
    public String toString() {
        String representacion = "";

        for(int i=0; i < cartas.size();i++){
			representacion += cartas.get(i) + " ";
			if((i+1)%10 == 0) 
                representacion += "\n";
        }

        return representacion;
    }
}
