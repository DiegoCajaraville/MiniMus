package com.teleco.minimus.services;

import java.util.Arrays;

import com.teleco.minimus.entities.Jugada;
import com.teleco.minimus.entities.Jugador;
import com.teleco.minimus.entities.Pareja;
import com.teleco.minimus.entities.Partida;
import com.teleco.minimus.io.FicheroJugadas;
import com.teleco.minimus.io.FicheroJugadores;
import com.teleco.minimus.io.FicheroSalida;

public abstract class Comandos {

    public static String resolverComando(String comando) {

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
                return Comandos.comando9(comandoSplit);
            // PlayHand <jugada>
            case "PlayHand":
                return Comandos.comando10(comandoSplit);
            // ResolvePares <jugada>
            case "ResolvePares":
                return Comandos.comando11(comandoSplit);
            // ResolveJuego <jugada>
            case "ResolveJuego":
                return Comandos.comando12(comandoSplit);
            // ResolveGrande <jugada>
            case "ResolveGrande":
                return Comandos.comando13(comandoSplit);
            // ResolveChica <jugada>
            case "ResolveChica":
                return Comandos.comando14(comandoSplit);
            // StartDelivery <fichero_jugadas>
            case "StartDelivery":
                return Comandos.comando15(comandoSplit);
            // D <jugada>
            case "D":
                return Comandos.comando16(comandoSplit);
            // EndDelivery
            case "EndDelivery":
                return Comandos.comando17(comandoSplit);
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
            Jugada jugada = Jugada.generarJugadaAleatoria(idMano+i);
            delivery += jugada.getTiradaCartas() + "\n";
        }
        
        if( FicheroJugadas.escritura(delivery, comando[3]) )
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }

    private static String comando9(String[] comando) {

        Partida partida = new Partida();
        partida.setParejasPartida();
        partida.setJugadas(FicheroJugadas.lectura(comando[1]));

        if( FicheroSalida.escritura(partida.toString(), comando[2], false) )
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }

    private static String comando10(String[] comando) {

        String hand = String.join(" ", Arrays.copyOfRange(comando, 1, comando.length));
        Jugada jugada = FicheroJugadas.getJugadaByHand(hand);

        return String.join(" ", comando) + ": " + jugada.getResultadoJugada(false, true);
    }

    private static String comando11(String[] comando) {

        String hand = String.join(" ", Arrays.copyOfRange(comando, 1, comando.length));
        Jugada jugada = FicheroJugadas.getJugadaByHand(hand);

        return String.join(" ", comando) + ": " + jugada.resolverPares();
    }

    private static String comando12(String[] comando) {

        String hand = String.join(" ", Arrays.copyOfRange(comando, 1, comando.length));
        Jugada jugada = FicheroJugadas.getJugadaByHand(hand);

        return String.join(" ", comando) + ": " + jugada.resolverJuego();
    }

    private static String comando13(String[] comando) {

        String hand = String.join(" ", Arrays.copyOfRange(comando, 1, comando.length));
        Jugada jugada = FicheroJugadas.getJugadaByHand(hand);

        return String.join(" ", comando) + ": " + jugada.resolverGrande();
    }

    private static String comando14(String[] comando) {

        String hand = String.join(" ", Arrays.copyOfRange(comando, 1, comando.length));
        Jugada jugada = FicheroJugadas.getJugadaByHand(hand);

        return String.join(" ", comando) + ": " + jugada.resolverChica();
    }

    private static String comando15(String[] comando) {

        if(FicheroSalida.openDelivery(comando[1]))
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }

    private static String comando16(String[] comando) {

        String hand = String.join(" ", Arrays.copyOfRange(comando, 1, comando.length));
        
        if(FicheroJugadas.comprobarFormatoHand(hand)) {
            if(FicheroSalida.escrituraDelivery(hand))
                return String.join(" ", comando) + ": OK.";
            else
                return String.join(" ", comando) + ": FAIL.";
        }
        else 
            return String.join(" ", comando) + ": FAIL.";
    }

    private static String comando17(String[] comando) {

        if(FicheroSalida.closeDelivery())
            return String.join(" ", comando) + ": OK.";
        else
            return String.join(" ", comando) + ": FAIL.";
    }
    
}
