package com.teleco.minimus;

import java.util.ArrayList;

import org.apache.commons.cli.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.teleco.minimus.entities.Partida;
import com.teleco.minimus.io.FicheroComandos;
import com.teleco.minimus.io.FicheroJugadas;
import com.teleco.minimus.io.FicheroJugadores;
import com.teleco.minimus.io.FicheroSalida;
import com.teleco.minimus.services.Comandos;

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
                LOGGER.info("El fichero de jugadas será: " + cmd.getOptionValue("j"));
                modoJuego2(cmd.getOptionValue("j"));
            } 
            else if (cmd.hasOption("c")) {
                LOGGER.info("Se ha seleccionado el modo de juego: 3");
                LOGGER.info("El fichero de comandos será: " + cmd.getOptionValue("c"));
                modoJuego3(cmd.getOptionValue("c"));
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

        if( playersFile != null )
            FicheroJugadores.lectura(playersFile, false);

        modo1.setParejasPartida();
        modo1.generarPartidaCompleta();

        if( outputFile != null )
            FicheroSalida.escritura(modo1.toString(), outputFile, false);
        else
            System.out.println(modo1);
        return;
    }

    private static void modoJuego2(String jugadasFile) {

        Partida modo2 = new Partida();

        if( playersFile != null )
            FicheroJugadores.lectura(playersFile, false);

        modo2.setParejasPartida();
        modo2.setJugadas( FicheroJugadas.lectura(jugadasFile) );

        if( outputFile != null )
            FicheroSalida.escritura(modo2.toString(), outputFile, false);
        else
            System.out.println(modo2);

        return;
    }

    private static void modoJuego3(String comandosFile) {

        ArrayList<String> comandos = FicheroComandos.lectura(comandosFile);

        for(String comando : comandos) {
            if(!comando.startsWith("#")) {
                String result = Comandos.resolverComando(comando);

                if( outputFile != null )
                    FicheroSalida.escritura(result+"\n", outputFile, true);
                else
                    System.out.println(result);
            }
        }
        return;
    }
}
