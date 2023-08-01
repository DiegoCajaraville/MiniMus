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
import com.teleco.minimus.entities.Partida;

public class FicheroJugadas {
    

    private static final Log LOGGER = LogFactory.getLog(FicheroJugadas.class);
    private static final String MANO_IDENTIFIER = "*";

    public static boolean lectura(Partida partida, String fileName) {
        
        try {
            InputStream archivo = FicheroJugadores.class.getClassLoader().getResourceAsStream(fileName);
            File tempFile = File.createTempFile("temp_", ".txt");
            FileUtils.copyInputStreamToFile(archivo, tempFile);

            List<String> lineas = FileUtils.readLines(tempFile, "UTF-8");

            // Por cada jugada
            for (String linea : lineas) {
                
                Jugada jugada = new Jugada();

                Pattern conjuntoPattern = Pattern.compile("([*-])\\((.*?)\\)");
                Matcher matcher = conjuntoPattern.matcher(linea);

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

                    if(MANO_IDENTIFIER.equals(simbolo) && partida.getJugadas().size() == 0) 
                        jugada.setMano(Jugada.getManoByIndex(contador));
                    
                    contador++;
                }

                if( partida.getJugadas().size() != 0 && jugada.getMano() == null ) {
                    String manoAnterior = partida.getJugadas().get( partida.getJugadas().size() -1 ).getMano();
                    jugada.setSiguienteMano(manoAnterior);
                }

                partida.getJugadas().add(jugada);
            }

        } catch (IOException e) {
            LOGGER.error("Error en la lectura del fichero de jugadas");
            return false;
        } finally {
            
        }

        return true;
    } 
}
