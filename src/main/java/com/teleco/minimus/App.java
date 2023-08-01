package com.teleco.minimus;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.cli.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.teleco.minimus.entities.Partida;
import com.teleco.minimus.io.FicheroJugadas;
import com.teleco.minimus.io.FicheroJugadores;
import com.teleco.minimus.services.Jugadores;
import com.teleco.minimus.services.Parejas;

public class App {
    
    private static final Log LOGGER = LogFactory.getLog(App.class);

    private static String outputFile = null;
    private static String playersFile = null;
    
    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("j", true, "Modo de juego 2");
        options.addOption("c", true, "Modo de juego 3");
        options.addOption("o", true, "Fichero de salida");
        options.addOption("p", true, "Fichero de jugadores");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            // Salida de la jugada
            if (cmd.hasOption("o")) {
                outputFile = cmd.getOptionValue("o");
                LOGGER.info("El fichero de salida será: " + outputFile);
            }

            // Fichero de jugadores
            if (cmd.hasOption("p")) {
                playersFile = cmd.getOptionValue("p");
                LOGGER.info("El fichero de jugadores será: " + playersFile);
            }

            // Modo de juego
            if (cmd.hasOption("j")) {
                LOGGER.info("Se ha seleccionado el modo de juego: 2");
                modoJuego2(cmd.getOptionValue("j"));
            } 
            else if (cmd.hasOption("c")) {
                LOGGER.info("Se ha seleccionado el modo de juego: 3");
                modoJuego3();
            } 
            else {
                LOGGER.info("Se ha seleccionado el modo de juego: 1");
                modoJuego1();
            }

        } catch (ParseException e) {
            LOGGER.error("Se ha producido un error en la inicialización del programa. Revise los parametros de entrada.");
        }        
    }

    private static void modoJuego1() {

        Partida modo1 = new Partida();

        FicheroJugadores.lectura(playersFile);
        // System.out.println(Jugadores.display());
        // System.out.println(Parejas.display());
        
        System.out.println(modo1);
        return;
    }

    private static void modoJuego2(String jugadasFile) {

        Partida modo2 = new Partida();
        
        FicheroJugadas.lectura(modo2, jugadasFile);

        System.out.println(modo2);
        return;
    }

    private static void modoJuego3() {
        return;
    }
}
