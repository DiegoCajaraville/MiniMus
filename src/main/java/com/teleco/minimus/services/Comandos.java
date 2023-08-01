package com.teleco.minimus.services;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.teleco.minimus.entities.Baraja;
import com.teleco.minimus.entities.Carta;
import com.teleco.minimus.entities.Jugada;
import com.teleco.minimus.entities.Jugador;
import com.teleco.minimus.entities.Pareja;
import com.teleco.minimus.entities.Partida;
import com.teleco.minimus.io.FicheroJugadas;
import com.teleco.minimus.io.FicheroJugadores;
import com.teleco.minimus.io.FicheroSalida;

public abstract class Comandos {

    private static final Log LOGGER = LogFactory.getLog(Comandos.class);

    public static String resolverComando(Partida partida, String comando) {

        String[] comandoSplit = comando.split("[ ]");

        switch(comandoSplit[0]) {
            // NewPlayer <id> <nombre>
            case "NewPlayer":
                return Comandos.comando1(comandoSplit);
            // DeletePlayer <id>
            case "DeletePlayer":
                return Comandos.comando2(comandoSplit);
            // NewCouple <id> <id_j1> <id_j2> <nombre>
            case "NewCouple":
                return Comandos.comando3(comandoSplit);
            // DeleteCouple <id>
            case "DeleteCouple":
                return Comandos.comando4(comandoSplit);
            // DumpPlayers <fichero_jugadores>
            case "DumpPlayers":
                return Comandos.comando5(comandoSplit);
            // ResetPlayers
            case "ResetPlayers":
                return Comandos.comando6(comandoSplit);
            // LoadPlayers <fichero_jugadores>
            case "LoadPlayers":
                return Comandos.comando7(comandoSplit);
            // GenerateRandomDelivery <n_jug> <id_mano> <fichero_jugadas>
            case "GenerateRandomDelivery":
                return Comandos.comando8(comandoSplit);
            // PlayGame <fichero_jugadas> <fichero_partida>
            case "PlayGame":
                //return Comandos.comando1(partida, comandoSplit);
            // PlayHand <jugada>
            case "PlayHand":
                //return Comandos.comando1(partida, comandoSplit);
            // ResolvePares <jugada>
            case "ResolvePares":
                //return Comandos.comando1(partida, comandoSplit);
            // ResolveJuego <jugada>
            case "ResolveJuego":
                //return Comandos.comando1(partida, comandoSplit);
            // ResolveGrande <jugada>
            case "ResolveGrande":
                //return Comandos.comando1(partida, comandoSplit);
            // ResolveChica <jugada>
            case "ResolveChica":
                //return Comandos.comando1(partida, comandoSplit);
            // StartDelivery <fichero_jugadas>
            case "StartDelivery":
                //return Comandos.comando1(partida, comandoSplit);
            // D <jugada>
            case "D":
                //return Comandos.comando1(partida, comandoSplit);
            // EndDelivery
            case "EndDelivery":
                //return Comandos.comando1(partida, comandoSplit);
            // No existe
            default:
                return "Este comando no existe";
        }
    }

    private static String comando1(String[] comando) {

        String nombreJugador = comando[2];
        for(int j = 3; j < comando.length; j++) 
            nombreJugador += " " + comando[j];
        
        if( Jugadores.nuevoJugador(new Jugador(Integer.parseInt(comando[1]), nombreJugador), false) )
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }

    private static String comando2(String[] comando) {
        if( Jugadores.eliminarJugador(Integer.parseInt(comando[1])) )
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }

    private static String comando3(String[] comando) {

        String nombrePareja = comando[4];
        for(int j = 5; j < comando.length; j++) 
            nombrePareja += " " + comando[j];

        Jugador jugador1 = Jugadores.getJugador(Integer.parseInt(comando[2]));
        Jugador jugador2 = Jugadores.getJugador(Integer.parseInt(comando[3]));

        if( jugador1 == null || jugador2 == null )
            return String.join(" ", comando) + ": FAIL.";

        if( Parejas.nuevaPareja( new Pareja(Integer.parseInt(comando[1]), nombrePareja, jugador1, jugador2), false ) )
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }

    private static String comando4(String[] comando) {
        if( Parejas.eliminarPareja(Integer.parseInt(comando[1])) )
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }

    private static String comando5(String[] comando) {
        
        String info = Jugadores.dump() + "\n" + Parejas.dump();
        
        if( FicheroJugadores.escritura(info, comando[1]) )
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }

    private static String comando6(String[] comando) {
        Parejas.clear();
        Jugadores.clear();
        return String.join(" ", comando) + ": OK.";
    }

    private static String comando7(String[] comando) {

        if( FicheroJugadores.lectura(comando[1], true) )
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }

    private static String comando8(String[] comando) {

        int numJugadas = Integer.parseInt(comando[1]);
        int idMano = Integer.parseInt(comando[2]);      // 1,2,3,4
        String delivery = "";

        for(int i=0; i<numJugadas; i++) {
            Jugada jugada = new Jugada();
            jugada.setMano(Jugada.getManoByIndex((idMano-1 + i) % 4));
            
            Baraja baraja = new Baraja();
            for(int j=0; j<4; j++)
                jugada.setNuevasCartas(baraja.getCartas(4));

            delivery += jugada.getTiradaCartas() + "\n";
        }
        
        if( FicheroJugadas.escritura(delivery, comando[3]) )
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }
    
}