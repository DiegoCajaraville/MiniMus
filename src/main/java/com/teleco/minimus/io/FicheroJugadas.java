package com.teleco.minimus.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.teleco.minimus.entities.Carta;
import com.teleco.minimus.entities.Jugada;

public class FicheroJugadas {
    
    private static final Log LOGGER = LogFactory.getLog(FicheroJugadas.class);
    private static final String MANO_IDENTIFIER = "*";

    public static ArrayList<Jugada> lectura(String fileName) {

        ArrayList<Jugada> jugadas = new ArrayList<Jugada>();
        
        try {
            InputStream archivo = FicheroJugadores.class.getClassLoader().getResourceAsStream(fileName);
            File tempFile = File.createTempFile("temp_", ".txt");
            FileUtils.copyInputStreamToFile(archivo, tempFile);

            List<String> lineas = FileUtils.readLines(tempFile, "UTF-8");

            // Por cada jugada
            for (String linea : lineas) {
                
                Jugada jugada = getJugadaByHand(linea);

                if( jugada.getMano() == null ) {
                    String manoAnterior = jugadas.get( jugadas.size() -1 ).getMano();
                    jugada.setSiguienteMano(manoAnterior);
                }

                jugadas.add(jugada);
            }

        } catch (IOException e) {
            LOGGER.error("Error en la lectura del fichero de jugadas");
            return null;
        } finally {
            
        }

        return jugadas;
    } 


    public static boolean escritura(String contenido, String fileName) {
        
        try {
            FileUtils.writeStringToFile(new File("./src/main/resources/results/" + fileName), contenido, "UTF-8");
        } catch (IOException e) {
            LOGGER.error("Error en la escritura del fichero de jugadores");
            return false;
        } finally {
        }

        return true;
    } 

    public static Jugada getJugadaByHand(String hand) {

        Jugada jugada = new Jugada();

        Pattern conjuntoPattern = Pattern.compile("([*-])\\((.*?)\\)");
        Matcher matcher = conjuntoPattern.matcher(hand);

        // Por cada jugador
        int contador = 0;
        while (matcher.find()) {
            String simbolo = matcher.group(1);
            String conjunto = matcher.group(2);
            String[] elementos = conjunto.split(", ");
        
            // Procesar cada elemento del conjunto
            ArrayList<Carta> cartas = new ArrayList<Carta>();

            for (String elemento : elementos) 
                cartas.add(new Carta(elemento));
            
            jugada.setNuevasCartas(cartas);

            if(MANO_IDENTIFIER.equals(simbolo)) 
                jugada.setMano(Jugada.getManoByIndex(contador));
            
            contador++;
        }

        return jugada;
    }
}
