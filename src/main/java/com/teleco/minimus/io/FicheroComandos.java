package com.teleco.minimus.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FicheroComandos {

    private static final Log LOGGER = LogFactory.getLog(FicheroComandos.class);

    public static ArrayList<String> lectura(String fileName) {

        ArrayList<String> comandos = null;
        
        try {
            InputStream archivo = FicheroJugadores.class.getClassLoader().getResourceAsStream(fileName);
            File tempFile = File.createTempFile("temp_", ".txt");
            FileUtils.copyInputStreamToFile(archivo, tempFile);

            comandos = (ArrayList<String>) FileUtils.readLines(tempFile, "UTF-8");

        } catch (IOException e) {
            LOGGER.error("Error en la lectura del fichero de comandos");
            return null;
        } finally {
        }

        return comandos;
    }
}
