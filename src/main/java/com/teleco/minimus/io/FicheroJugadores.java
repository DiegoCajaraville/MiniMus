package com.teleco.minimus.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.teleco.minimus.entities.Jugador;
import com.teleco.minimus.entities.Pareja;
import com.teleco.minimus.services.Jugadores;
import com.teleco.minimus.services.Parejas;

public class FicheroJugadores {

    private static final Log LOGGER = LogFactory.getLog(FicheroJugadores.class);

    public static boolean lectura(String fileName, boolean override) {
        
        try {
            InputStream archivo = FicheroJugadores.class.getClassLoader().getResourceAsStream(fileName);
            File tempFile = File.createTempFile("temp_", ".txt");
            FileUtils.copyInputStreamToFile(archivo, tempFile);

            List<String> lineas = FileUtils.readLines(tempFile, "UTF-8");

            for (String linea : lineas) {
                
                String[] campos = linea.split(" ");

                // Jugador
                if (campos.length >= 0 && campos[0].equals("J")) {

                    String nombreJugador = campos[2];
                    for(int j = 3; j < campos.length; j++) 
                        nombreJugador += " " + campos[j];

                    Jugadores.nuevoJugador(new Jugador( Integer.parseInt(campos[1]), nombreJugador), override);
                }

                // Pareja
                else if (campos.length >= 0 && campos[0].equals("P")) {

                    String nombrePareja = campos[4];
                    for(int j = 5; j < campos.length; j++) 
                        nombrePareja += " " + campos[j];

                    Jugador jugador1 = Jugadores.getJugador(Integer.parseInt(campos[2]));
                    Jugador jugador2 = Jugadores.getJugador(Integer.parseInt(campos[3]));

                    if ( jugador1 != null && jugador2 != null ) {
                        
                        HashMap<Integer, Jugador> jugadores = new HashMap<Integer, Jugador>();
                        jugadores.put(jugador1.getId(), jugador1);
                        jugadores.put(jugador2.getId(), jugador2);

                        Parejas.nuevaPareja(new Pareja( Integer.parseInt(campos[1]), nombrePareja, jugador1, jugador2), override);
                    }
                }
            }

        } catch (IOException e) {
            LOGGER.error("Error en la lectura del fichero de jugadores");
            return false;
        } finally {

        }

        return true;
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
    
}
