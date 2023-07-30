package com.teleco.minimus.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
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
        
        for(Character j : palos) {
            for(Character i : denominaciones)
                cartas.add(new Carta(denominaciones.get(i), palos.get(j)));
        }
        return;
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
